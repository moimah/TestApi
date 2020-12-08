package com.moimah.ecommerce.controller;

import com.moimah.ecommerce.dto.Tb01UserDto;
import com.moimah.ecommerce.entity.QTb01User;
import com.moimah.ecommerce.entity.Tb01User;
import com.moimah.ecommerce.repository.UserRepository;
import com.moimah.ecommerce.utils.SortDirecction;
import com.moimah.ecommerce.utils.exceptions.NotFoundException;
import com.moimah.ecommerce.utils.ratelimiter.RateLimiter;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.reflect.Type;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * REST Controller for Tb01Users
 */
@SuppressWarnings("all")
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "user")
public class UserController {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private UserRepository repository;

    @Autowired
    private RateLimiter rateLimiter;


    @GetMapping("/get")
    public ResponseEntity<List<Tb01UserDto>> findAll() {
        if (!rateLimiter.getBucket().tryConsume(1)) {
            return new ResponseEntity<>(HttpStatus.TOO_MANY_REQUESTS);
        }
        return new ResponseEntity<List<Tb01UserDto>>(new ModelMapper().map(repository.findAll(), List.class), HttpStatus.OK);
    }

    @GetMapping("/get/params")
    public ResponseEntity<List<Tb01UserDto>> findAllByParams(@RequestParam Map<String, Object> paramsToSearch,
                                                             @RequestParam(name = "page", defaultValue = "0") int page,
                                                             @RequestParam(name = "size", defaultValue = "500") int size,
                                                             @RequestParam(name = "sort", defaultValue = "DESC") String sort

    ) {
        if (!rateLimiter.getBucket().tryConsume(1)) {
            return new ResponseEntity<>(HttpStatus.TOO_MANY_REQUESTS);
        }
        Predicate predicate = createPredicate(paramsToSearch);
        List<Tb01User> list = repository.findAll(predicate,
                PageRequest.of(page, size, Sort.by(SortDirecction.sortBy(sort), "tb01CreatedOn"))).toList();
        return new ResponseEntity<List<Tb01UserDto>>(new ModelMapper().map(list, List.class),HttpStatus.OK);
    }

    @GetMapping("/get/userId/{id}")
    public ResponseEntity<Tb01UserDto> findById(@PathVariable Long id) throws Exception {
        if (!rateLimiter.getBucket().tryConsume(1)) {
            return new ResponseEntity<>(HttpStatus.TOO_MANY_REQUESTS);
        }
        Tb01User tb01User = new ModelMapper().map(repository.findById(id), (Type) Tb01User.class);
        if (tb01User == null)
            throw new NotFoundException(Tb01User.class, id);
        return new ResponseEntity<>(new ModelMapper().map(tb01User, Tb01UserDto.class), HttpStatus.OK);
    }


    @DeleteMapping(path = {"/{id}"})
    public ResponseEntity<Object> deleteById(@PathVariable Long id) {
        if (!rateLimiter.getBucket().tryConsume(1)) {
            return new ResponseEntity<>(HttpStatus.TOO_MANY_REQUESTS);
        }
        repository.deleteById(id);
        return ResponseEntity.accepted().build();
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody Tb01UserDto dto) {
        if (!rateLimiter.getBucket().tryConsume(1)) {
            return new ResponseEntity<>(HttpStatus.TOO_MANY_REQUESTS);
        }
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(repository.save(new ModelMapper().map(dto, Tb01User.class)).getTb01UserId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@RequestBody Tb01UserDto dto, @PathVariable long id) {
        if (!rateLimiter.getBucket().tryConsume(1)) {
            return new ResponseEntity<>(HttpStatus.TOO_MANY_REQUESTS);
        }

        Tb01User tb01User = new ModelMapper().map(repository.findById(id), (Type) Tb01User.class);
        if (tb01User == null)
            return ResponseEntity.notFound().build();

        dto.setTb01UserId(id);
        repository.save(new ModelMapper().map(dto, Tb01User.class));

        return ResponseEntity.ok().build();
    }

    @GetMapping("/count")
    public ResponseEntity<Long> count(Map parameters) {
        if (!rateLimiter.getBucket().tryConsume(1)) {
            return new ResponseEntity<>(HttpStatus.TOO_MANY_REQUESTS);
        }
        return new ResponseEntity<>(repository.count(createPredicate(parameters)), HttpStatus.OK);
    }

    /**
     * Created dynamic predicate based in params using QueryDSL
     *
     * @param paramsToSearch
     * @return
     */
    public Predicate createPredicate(Map<String, Object> paramsToSearch) {
        //Create QueryDSL objects
        QTb01User qUser = QTb01User.tb01User;

        //Retrieve all params
        Boolean userActive = BooleanUtils.isNotFalse(Boolean.valueOf((String) paramsToSearch.get("tb01Active"))) ? Boolean.valueOf((String) paramsToSearch.get("tb01Active")) : null;
        Boolean userInactive = BooleanUtils.isNotFalse(Boolean.valueOf((String) paramsToSearch.get("tb01InActive"))) ? Boolean.valueOf((String) paramsToSearch.get("tb01InActive")) : null;
        String city = StringUtils.isNotEmpty((String) paramsToSearch.get("tb01City")) ? (String) paramsToSearch.get("tb01City") : null;
        String name = StringUtils.isNotEmpty((String) paramsToSearch.get("tb01Name")) ? (String) paramsToSearch.get("tb01Name") : null;

        //Create QueryDSL Predicates
        Predicate qUserActive = null;
        Predicate qCity = null;
        Predicate qName = null;

        //Conditions
        if (userActive != null) {
            qUserActive = qUser.tb01Active.eq(userActive);
        }
        if (userInactive != null) {
            if (userInactive == true) {
                qUserActive = qUser.tb01Active.eq(false);
                if (userActive != null && userActive == true) {
                    qUserActive = null;
                }
            }
        }
        if (city != null) {
            qCity = qUser.tb01City.startsWith(city);
        }
        if (name != null) {
            qName = qUser.tb01Name.startsWith(name);
        }

        final Predicate qPredicate =
                new BooleanBuilder()
                        .and(qUserActive)
                        .and(qCity)
                        .and(qName);

        return qPredicate;

    }

    /**
     * Htpp get to retrieve a list of cities
     *
     * @return
     */
    @GetMapping("/cities")
    public ResponseEntity<ArrayList<String>> findCities() {
        if (!rateLimiter.getBucket().tryConsume(1)) {
            return new ResponseEntity<>(HttpStatus.TOO_MANY_REQUESTS);
        }
        ArrayList list = (ArrayList<String>)entityManager
                        .createNativeQuery("SELECT DISTINCT tb01_city FROM tb01_user;")
                        .getResultList();
        return new ResponseEntity<ArrayList<String>>(list, HttpStatus.OK);
    }


}
