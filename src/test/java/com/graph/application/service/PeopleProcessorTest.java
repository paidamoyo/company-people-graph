package com.graph.application.service;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.graph.domain.Company;
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
    public void shouldGetPeopleGivenCompanies() throws Exception {
        //given
        Path pathPeople = Paths.get(String.format("%s/src/test/resources/People.txt", currentDirectory.toString()));
        PeopleProcessor peopleProcessor = new PeopleProcessor(pathPeople);

        Company allanGray = Company.from("Allan Gray", "Cape Town");
        Company fitKey = Company.from("FitKey", "Joburg");
        Company thoughtWorks = Company.from("ThoughtWorks", "Joburg");
        List<Company> companies = Arrays.asList(allanGray, fitKey, thoughtWorks);

        //when
        List<Person> people = peopleProcessor.getPeople(companies);

        //then
        Person benny = Person.from("Benny", "Ou", "bennyou.cpt@gmail.com", allanGray);
        Person evan = Person.from("Evan", "Walther", "evan@fitkey.co.za", fitKey);
        Person kelvin = Person.from("Kelvin", "Smith", "kelvin@fitkey.co.za", fitKey);
        Person joshua = Person.from("Joshua", "Shimkin", "josh@fitkey.co.za", fitKey);
        Person mary = Person.from("Mary", "Jane", "mary.jane@happytown.co", Company.from("Happy Town", null));
        assertThat(people, is(Arrays.asList(benny, evan, kelvin, joshua, mary)));
    }

    @Test(expected = PeopleProcessorException.class)
    public void shouldThrowPeopleProcessorExceptionWhenPathIsInvalid() throws Exception {
        //given
        Path pathPeople = Paths.get(String.format("%s/src/test/resources/Peoplemmm.txt", currentDirectory.toString()));
        PeopleProcessor peopleProcessor = new PeopleProcessor(pathPeople);

        Company allanGray = Company.from("Allan Gray", "Cape Town");
        Company fitKey = Company.from("FitKey", "Joburg");
        Company thoughtWorks = Company.from("ThoughtWorks", "Joburg");
        List<Company> companies = Arrays.asList(allanGray, fitKey, thoughtWorks);

        //when
        peopleProcessor.getPeople(companies);

        //then

    }

    @Test(expected = PeopleProcessorException.class)
    public void shouldThrowPeopleProcessorExceptionWhenFileIsNotValid() throws Exception {
        //given
        Path pathPeople = Paths.get(String.format("%s/src/test/resources/InvalidPeople.txt", currentDirectory.toString()));
        PeopleProcessor peopleProcessor = new PeopleProcessor(pathPeople);

        Company allanGray = Company.from("Allan Gray", "Cape Town");
        Company fitKey = Company.from("FitKey", "Joburg");
        Company thoughtWorks = Company.from("ThoughtWorks", "Joburg");
        List<Company> companies = Arrays.asList(allanGray, fitKey, thoughtWorks);

        //when
        peopleProcessor.getPeople(companies);

        //then

    }

    @Test(expected = PeopleProcessorException.class)
    public void shouldThrowPeopleProcessorExceptionWhenFullNameIsNotValid() throws Exception {
        //given
        Path pathPeople = Paths.get(String.format("%s/src/test/resources/InvalidFullNamePeople.txt", currentDirectory.toString()));
        PeopleProcessor peopleProcessor = new PeopleProcessor(pathPeople);

        Company allanGray = Company.from("Allan Gray", "Cape Town");
        Company fitKey = Company.from("FitKey", "Joburg");
        Company thoughtWorks = Company.from("ThoughtWorks", "Joburg");
        List<Company> companies = Arrays.asList(allanGray, fitKey, thoughtWorks);

        //when
        peopleProcessor.getPeople(companies);

        //then

    }

}