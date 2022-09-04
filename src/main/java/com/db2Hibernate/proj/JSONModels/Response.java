package com.db2Hibernate.proj.JSONModels;
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

//{"code":"OK","data":[3,1,2],"status":200}
@JsonIgnoreProperties(ignoreUnknown = true)
public class Response {
    private String code;
    private List<Details> data;
    private String status;

    public Response() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<Details> getData() {
        return data;
    }

    public void setData(List<Details> data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Quote{" +
                "code='" + code + '\'' +
                ", data=" + data +
                ", status=" + status +
                '}';
    }
}
