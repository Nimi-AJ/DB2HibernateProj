package com.db2Hibernate.proj.base;

import com.db2Hibernate.proj.JSONModels.Details;

import java.util.List;

public interface RestServiceBase {
    List<Details> getIds();
    List<Details> getNames();
}
