package com.graph.application.service;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.graph.domain.Company;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CompanyProcessorTest {

    private Path currentDirectory;

    @Before
    public void setUp() throws Exception {
        currentDirectory = Paths.get("").toAbsolutePath();

    }

    @Test
    public void shouldGetCompanies() throws Exception {

        //given
        Path pathCompany = Paths.get(String.format("%s/src/test/resources/Companies.txt", currentDirectory.toString()));
        CompanyProcessor companyProcessor = new CompanyProcessor(pathCompany);

        //when
        List<Company> companies = companyProcessor.getCompanies();

        //then
        Company allanGray = Company.from("Allan Gray", "Cape Town");
        Company fitKey = Company.from("FitKey", "Joburg");
        Company thoughtWorks = Company.from("ThoughtWorks", "Joburg");
        assertThat(companies, is(Arrays.asList(allanGray, fitKey, thoughtWorks)));
    }

    @Test(expected = CompanyProcessorException.class)
    public void shouldThrowExceptionIfPathIsInvalid() throws Exception {

        //given
        Path pathCompany = Paths.get("company-pec/test/resources/Companies.txt");
        CompanyProcessor companyProcessor = new CompanyProcessor(pathCompany);

        //when

        companyProcessor.getCompanies();

        //then
    }

    @Test(expected = CompanyProcessorException.class)
    public void shouldThrowExceptionIfFileIsNotFormattedCorrectly() throws Exception {

        //given
        String pathCompany = currentDirectory.toString() + "/src/test/resources/InvalidCompanies.txt";

        CompanyProcessor companyProcessor = new CompanyProcessor(Paths.get(pathCompany));

        //when
        companyProcessor.getCompanies();

        //then
    }
}