package com.avhwServer.avapi.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "Users")
public class Users {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    private String firstName;
    private String lastName;
    private String npiNumber;
    private String address;
    private String phone;
    private String email;

    public Users(){

    }

    public Users(Long userId, String firstName, String lastName, String npiNumber, String address, String phone, String email){
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.npiNumber = npiNumber;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }

    public Long getUserId(){
        return userId;
    }

    public void setUserId(Long userId){
        this.userId = userId;
    }

    public String getFirstName(){
        return firstName;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public String getNpiNumber(){
        return npiNumber;
    }

    public void setNpiNumber(String npiNumber){
        this.npiNumber = npiNumber;
    }

    public String getAddress(){
        return address;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public String getPhone(){
        return phone;
    }

    public void setPhone(String phone){
        this.phone = phone;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

}
