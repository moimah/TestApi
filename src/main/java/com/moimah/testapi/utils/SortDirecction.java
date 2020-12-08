package com.moimah.testapi.utils;

import org.springframework.data.domain.Sort;

/**
 * Custom SortDirecction using static function sortBy
 */
public class SortDirecction {

    public static Sort.Direction sortBy(String direcion){
        if(direcion.contains("DESC")){
            return Sort.Direction.DESC;
        }
        if(direcion.contains("ASC")){
            return Sort.Direction.ASC;
        }
        return null;
    }

}
