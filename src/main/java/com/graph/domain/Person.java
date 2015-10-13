package com.graph.domain;


import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Person implements Comparable {

    private final String name;
    private final String surname;
    private final String email;
    private final Company company;

    public static Person from(String name, String surname, String email, Company company) {
        return new Person(name, surname, email, company);
    }

    public String getCompanyCity() {
        return this.getCompany().getCity();
    }

    public String getCompanyName() {
        return this.getCompany().getName();
    }

    @Override
    public int compareTo(Object o) {
        if(o == null){
            return 1;
        }
        Person other = (Person) o;
        if (this == other) {
            return 0;
        }
        int name = this.name.compareTo(other.name);
        int surname = this.surname.compareTo(other.surname);
        int email = this.email.compareTo(other.email);

        if (name != 0) {
            return name;
        } else if (surname != 0) {
            return surname;
        }
        return email;
    }
}


