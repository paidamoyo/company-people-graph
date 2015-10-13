package com.graph.application.service;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.graph.domain.Person;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class PeopleProcessorTest {

    private Path currentDirectory;

    @Before
    public void setUp() throws Exception {
        currentDirectory = Paths.get("").toAbsolutePath();

    }


    @Test
    public void shouldGetPeople() throws Exception {
        //given
        Path pathPeople = Paths.get(String.format("%s/src/test/resources/People.txt", currentDirectory.toString()));
        PeopleProcessor peopleProcessor = new PeopleProcessor(pathPeople);


        //when
        List<Person> people = peopleProcessor.getPeople();

        //then
        Person benny = Person.from("Benny", "Ou", "bennyou.cpt@gmail.com", "Allan Gray");
        Person evan = Person.from("Evan", "Walther", "evan@fitkey.co.za", "FitKey");
        Person kelvin = Person.from("Kelvin", "Smith", "kelvin@fitkey.co.za", "FitKey");
        Person joshua = Person.from("Joshua", "Shimkin", "josh@fitkey.co.za", "FitKey");
        Person mary = Person.from("Mary", "Jane", "mary.jane@happytown.co", "Happy Town");


        assertThat(people, is(Arrays.asList(benny, evan, kelvin, joshua, mary)));
    }

    @Test(expected = PeopleProcessorException.class)
    public void shouldThrowPeopleProcessorExceptionWhenPathIsInvalid() throws Exception {
        //given
        Path pathPeople = Paths.get(String.format("%s/src/test/resources/Peoplemmm.txt", currentDirectory.toString()));
        PeopleProcessor peopleProcessor = new PeopleProcessor(pathPeople);

        //when
        peopleProcessor.getPeople();

        //then

    }

    @Test(expected = PeopleProcessorException.class)
    public void shouldThrowPeopleProcessorExceptionWhenFileIsNotValid() throws Exception {
        //given
        Path pathPeople = Paths.get(String.format("%s/src/test/resources/InvalidPeople.txt", currentDirectory.toString()));
        PeopleProcessor peopleProcessor = new PeopleProcessor(pathPeople);

        //when
        peopleProcessor.getPeople();

        //then

    }

    @Test(expected = PeopleProcessorException.class)
    public void shouldThrowPeopleProcessorExceptionWhenFullNameIsNotValid() throws Exception {
        //given
        Path pathPeople = Paths.get(String.format("%s/src/test/resources/InvalidFullNamePeople.txt", currentDirectory.toString()));
        PeopleProcessor peopleProcessor = new PeopleProcessor(pathPeople);


        //when
        peopleProcessor.getPeople();

        //then

    }

}