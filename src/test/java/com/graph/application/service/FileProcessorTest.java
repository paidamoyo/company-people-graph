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
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class FileProcessorTest {

    private Path currentDirectory;

    @Before
    public void setUp() throws Exception {
        currentDirectory = Paths.get("").toAbsolutePath();

    }

    @Test
    public void shouldProcessWellFormattedCompanies() throws Exception {
        //given

        Path pathCompany = Paths.get(String.format("%s/src/test/resources/Companies.txt", currentDirectory.toString()));
        Path pathPeople = Paths.get(String.format("%s/src/test/resources/People.txt", currentDirectory.toString()));


        FileProcessor fileProcessor = new FileProcessor(pathCompany, pathPeople);

        //when
        List<Company> companies = fileProcessor.companyPeopleGraph();

        //then

        Person benny = Person.from("Benny", "Ou", "bennyou.cpt@gmail.com", "Allan Gray");
        Person evan = Person.from("Evan", "Walther", "evan@fitkey.co.za", "FitKey");
        Person kelvin = Person.from("Kelvin", "Smith", "kelvin@fitkey.co.za", "FitKey");
        Person joshua = Person.from("Joshua", "Shimkin", "josh@fitkey.co.za", "FitKey");

        Company allanGray = Company.from("Allan Gray", "Cape Town", Collections.singletonList(benny));
        Company fitKey = Company.from("FitKey", "Joburg", Arrays.asList(evan, kelvin, joshua));
        Company thoughtWorks = Company.from("ThoughtWorks", "Joburg", Collections.EMPTY_LIST);


        assertThat(companies, is(Arrays.asList(allanGray, fitKey, thoughtWorks)));
    }
}