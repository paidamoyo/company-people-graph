package com.graph.domain;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Company {

    private final String name;
    private final String city;

    public static Company from  (String name, String city) {
        return new Company(name, city);
    }
}
