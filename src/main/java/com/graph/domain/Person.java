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
    private final String companyName;

    public static Person from(String name, String surname, String email, String companyName) {
        return new Person(name, surname, email, companyName);
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


