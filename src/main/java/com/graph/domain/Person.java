package com.graph.domain;


import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Person {

    private final String name;
    private final String surname;
    private final String email;
    private final Company company;

    public static Person from(String name, String surname, String email, Company company) {
        return new Person(name, surname, email, company);
    }
}
