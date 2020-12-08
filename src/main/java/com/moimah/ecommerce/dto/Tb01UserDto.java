package com.moimah.ecommerce.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * Basic POJO-DTO to Tb01UserDto
 */
public class Tb01UserDto implements Serializable {

    private Long tb01UserId;
    private String tb01Name;
    private String tb01Surname;
    private Boolean tb01Active;
    private String tb01Email;
    private String tb01City;
    private Date tb01Dob;
    private Date tb01CreatedOn;

    public Long getTb01UserId() {
        return tb01UserId;
    }

    public void setTb01UserId(Long tb01UserId) {
        this.tb01UserId = tb01UserId;
    }

    public String getTb01Name() {
        return tb01Name;
    }

    public void setTb01Name(String tb01Name) {
        this.tb01Name = tb01Name;
    }

    public String getTb01Surname() {
        return tb01Surname;
    }

    public void setTb01Surname(String tb01Surname) {
        this.tb01Surname = tb01Surname;
    }

    public Boolean getTb01Active() {
        return tb01Active;
    }

    public void setTb01Active(Boolean tb01Active) {
        this.tb01Active = tb01Active;
    }

    public String getTb01Email() {
        return tb01Email;
    }

    public void setTb01Email(String tb01Email) {
        this.tb01Email = tb01Email;
    }

    public String getTb01City() {
        return tb01City;
    }

    public void setTb01City(String tb01City) {
        this.tb01City = tb01City;
    }

    public Date getTb01Dob() {
        return tb01Dob;
    }

    public void setTb01Dob(Date tb01Dob) {
        this.tb01Dob = tb01Dob;
    }

    public Date getTb01CreatedOn() {
        return tb01CreatedOn;
    }

    public void setTb01CreatedOn(Date tb01CreatedOn) {
        this.tb01CreatedOn = tb01CreatedOn;
    }

    @Override
    public String toString() {
        return "Tb01UserDto{" +
                "tb01UserId=" + tb01UserId +
                ", tb01Name='" + tb01Name + '\'' +
                ", tb01Surname='" + tb01Surname + '\'' +
                ", tb01Active=" + tb01Active +
                ", tb01Email='" + tb01Email + '\'' +
                ", tb01City='" + tb01City + '\'' +
                ", tb01Dob=" + tb01Dob +
                ", tb01CreatedOn=" + tb01CreatedOn +
                '}';
    }
}
