package com.avhwServer.avapi.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "Enrollees")
public class Enrollees {

	@Id
    private Integer id;

    private String userId;
    private String firstName;
    private String lastName;
    private Integer version;
    private String company;

    public Enrollees(){

    }

    public Enrollees(Integer id, String userId, String firstName, String lastName, Integer version, String company){
        this.id = id;
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.version = version;
        this.company = company;
    }

    public int getId(){
        return this.id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getUserId(){
        return userId;
    }

    public void setUserId(String userId){
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

    public Integer getVersion(){
        return version;
    }

    public void setVersion(Integer version){
        this.version = version;
    }

    public String getCompany(){
        return company;
    }

    public void setCompany(String company){
        this.company = company;
    }

}