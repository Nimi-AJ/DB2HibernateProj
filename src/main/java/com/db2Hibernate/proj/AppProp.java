package com.db2Hibernate.proj;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppProp {


    @Value("${SYSTEM_DIRECTORY}")
    public String SYSTEM_DIRECTORY;
}