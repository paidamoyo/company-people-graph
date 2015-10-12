package com.graph.application.service;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.graph.domain.Company;
import com.graph.domain.Person;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class FileProcessorTest {

    @Test
    public void shouldProcessWellFormattedCompanies() throws Exception {
        //given
        Path pathCompany = Paths.get("/Users/ashchapfuwa/Dropbox/projects/personal/company-people-graph/src/test/resources/Companies.txt");

        Path pathPeople = Paths.get("/Users/ashchapfuwa/Dropbox/projects/personal/company-people-graph/src/test/resources/People.txt");

        FileProcessor fileProcessor = new FileProcessor(pathCompany, pathPeople);

        //when
        List<Person> people = fileProcessor.getPeople();

        //then
        Company allanGray = Company.from("Allan Gray", "Cape Town");
        Company fitKey = Company.from("FitKey", "Joburg");
        Company happyTown = Company.from("Happy Town", null);


        Person benny = Person.from("Benny", "Ou", "bennyou.cpt@gmail.com", allanGray);
        Person evan = Person.from("Evan", "Walther", "evan@fitkey.co.za", fitKey);
        Person kelvin = Person.from("Kelvin", "Smith", "kelvin@fitkey.co.za", fitKey);
        Person joshua = Person.from("Joshua", "Shimkin", "josh@fitkey.co.za", fitKey);
        Person mary = Person.from("Mary", "Jane", "mary.jane@happytown.co", happyTown);
        assertThat(people, is(Arrays.asList(benny, evan, kelvin, joshua, mary)));
    }
}