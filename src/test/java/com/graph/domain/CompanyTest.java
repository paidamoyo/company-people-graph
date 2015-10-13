package com.graph.domain;

import java.util.Collections;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class CompanyTest {


    @Test
    public void shouldCompareByName() throws Exception {

        //given
        Company att = Company.from("Att", "Jorbug", Collections.singletonList(Person.from("Homy", "Simpson", "as@s.com", "Att")));
        Company ben = Company.from("Ben", "Jorbug", Collections.singletonList(Person.from("Homy", "Simpson", "as@s.com", "Att")));

        //when
        int compareTo = att.compareTo(ben);

        //then
        assertTrue(compareTo < 0);

    }


    @Test
    public void shouldBeEqualIfNamesAreEqual() throws Exception {

        //given
        Company att = Company.from("Att", "Jorbug", Collections.singletonList(Person.from("Homy", "Simpson", "as@s.com", "Att")));
        Company attTwo = Company.from("Att", "Jorbug", Collections.singletonList(Person.from("Homy", "Simpson", "as@s.com", "Att")));

        //when
        int compareTo = att.compareTo(attTwo);

        //then
        assertTrue(compareTo == 0);

    }

    @Test
    public void shouldBeEqual1IfObjectIsNull() throws Exception {

        //given
        Company att = Company.from("Att", "Jorbug", Collections.singletonList(Person.from("Homy", "Simpson", "as@s.com", "Att")));

        //when
        int compareTo = att.compareTo(null);

        //then
        assertTrue(compareTo == 1);

    }


}