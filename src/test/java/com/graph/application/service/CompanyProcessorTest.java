package com.graph.application.service;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.graph.domain.Company;
import com.graph.domain.Person;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CompanyProcessorTest {

    private Path currentDirectory;
    private Path pathPeople;
    private CompanyProcessor companyProcessor;

    @Before
    public void setUp() throws Exception {
        currentDirectory = Paths.get("").toAbsolutePath();
        pathPeople = Paths.get(String.format("%s/src/test/resources/People.txt", currentDirectory.toString()));

    }

    @Test
    public void shouldGetCompanies() throws Exception {

        //given
        Path pathCompany = Paths.get(String.format("%s/src/test/resources/Companies.txt", currentDirectory.toString()));
        companyProcessor = new CompanyProcessor(new PeopleProcessor(pathPeople), pathCompany);

        Person benny = Person.from("Benny", "Ou", "bennyou.cpt@gmail.com", "Allan Gray");
        Person evan = Person.from("Evan", "Walther", "evan@fitkey.co.za", "FitKey");
        Person kelvin = Person.from("Kelvin", "Smith", "kelvin@fitkey.co.za", "FitKey");
        Person joshua = Person.from("Joshua", "Shimkin", "josh@fitkey.co.za", "FitKey");
        Person mary = Person.from("Mary", "Jane", "mary.jane@happytown.co", "Happy Town");

        //when
        List<Company> companies = companyProcessor.process();

        //then
        Company allanGray = Company.from("Allan Gray", "Cape Town", Collections.singletonList(benny));
        Company fitKey = Company.from("FitKey", "Joburg", Arrays.asList(evan, kelvin, joshua));
        Company thoughtWorks = Company.from("ThoughtWorks", "Joburg", Collections.emptyList());
        assertThat(companies, is(Arrays.asList(allanGray, fitKey, thoughtWorks)));
    }

    @Test(expected = CompanyProcessorException.class)
    public void shouldThrowExceptionIfPathIsInvalid() throws Exception {

        //given
        Path pathCompany = Paths.get("company-pec/test/resources/Companies.txt");
        companyProcessor = new CompanyProcessor(new PeopleProcessor(pathPeople), pathCompany);

        //when

        companyProcessor.process();

        //then
    }

    @Test(expected = CompanyProcessorException.class)
    public void shouldThrowExceptionIfFileIsNotFormattedCorrectly() throws Exception {

        //given
        Path pathCompany = Paths.get(currentDirectory.toString() + "/src/test/resources/InvalidCompanies.txt");
        companyProcessor = new CompanyProcessor(new PeopleProcessor(pathPeople), pathCompany);


        //when
        companyProcessor.process();

        //then
    }
}