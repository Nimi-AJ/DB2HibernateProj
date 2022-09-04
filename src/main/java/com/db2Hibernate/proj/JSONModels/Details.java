package com.db2Hibernate.proj.JSONModels;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Details {
    private long id;
    private String lastName;


    public Details() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }



//    @Override
//    public String toString() {
//        return "" + id + "";
//
//    }
}
