package com.graph.domain;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class PersonTest {

    @Test
    public void shouldGetCompanyCity() throws Exception {

        //given
        Person person = Person.from("Homy", "Simpson", "as@s.com", Company.from("Att", "Harare"));

        //when
        String companyCity = person.getCompanyCity();

        //then
        assertThat(companyCity, is("Harare"));
    }

    @Test
    public void shouldGetCompanyName() throws Exception {

        //given
        Person person = Person.from("Homy", "Simpson", "as@s.com", Company.from("Att", "Harare"));

        //when
        String companyName = person.getCompanyName();

        //then
        assertThat(companyName, is("Att"));
    }

    @Test
    public void shouldCompareByName() throws Exception {

        //given
        Person ash = Person.from("Ash", "Simpson", "as@s.com", Company.from("Att", "Harare"));
        Person homy = Person.from("Homy", "Simpson", "as@s.com", Company.from("Att", "Harare"));

        //when
        int compareTo = ash.compareTo(homy);

        //then
        assertTrue(compareTo<0);

    }

    @Test
    public void shouldCompareByNameThenSurname() throws Exception {

        //given
        Person old = Person.from("Homy", "Zen", "as@s.com", Company.from("Att", "Harare"));
        Person simpson = Person.from("Homy", "Simpson", "as@s.com", Company.from("Att", "Harare"));

        //when
        int compareTo = old.compareTo(simpson);

        //then
        assertTrue(compareTo>0);

    }

    @Test
    public void shouldCompareByNameThenSurnameThenEmail() throws Exception {

        //given
        Person bs = Person.from("Homy", "Zen", "bs@s.com", Company.from("Att", "Harare"));
        Person ms = Person.from("Homy", "Zen", "ms@s.com", Company.from("Att", "Harare"));

        //when
        int compareTo = bs.compareTo(ms);

        //then
        assertTrue(compareTo<0);

    }


    @Test
    public void shouldBeEqualIfNameAndSurnameAndEmailAreEqual() throws Exception {

        //given
        Person homyOne = Person.from("Homy", "Zen", "bs@s.com", Company.from("Att", "Harare"));
        Person homyTwo = Person.from("Homy", "Zen", "bs@s.com", Company.from("Att", "Harare"));

        //when
        int compareTo = homyOne.compareTo(homyTwo);

        //then
        assertTrue(compareTo==0);

    }

    @Test
    public void shouldBeEqual1IfObectIsNull() throws Exception {

        //given
        Person homyOne = Person.from("Homy", "Zen", "bs@s.com", Company.from("Att", "Harare"));

        //when
        int compareTo = homyOne.compareTo(null);

        //then
        assertTrue(compareTo==1);

    }
}