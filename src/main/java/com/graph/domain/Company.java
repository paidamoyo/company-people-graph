package com.graph.domain;

import java.util.List;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Company implements Comparable {

    private final String name;
    private final String city;
    private final List<Person> employees;

    public static Company from(String name, String city, List<Person> employees) {
        return new Company(name, city, employees);
    }

    @Override
    public int compareTo(Object o) {
        if (o == null) {
            return 1;
        }

        Company other = (Company) o;

        if (this == other) {
            return 0;
        }

        return this.name.compareTo(other.getName());
    }
}
