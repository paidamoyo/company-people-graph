package com.graph.application.service;

import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.graph.domain.Company;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CompanyProcessorTest {

    @Test
    public void shouldProcessWellFormattedCompanies() throws Exception {
        //given
        CompanyProcessor companyProcessor = new CompanyProcessor(Paths.get("/Users/ashchapfuwa/Dropbox/projects/personal/company-people-graph/src/test/resources/Companies.txt"));

        //when
        List<Company> companies = companyProcessor.process();

        //then
        Company one = Company.from("Allan Gray", "Cape Town");
        Company two = Company.from("FitKey", "Joburg");
        Company three = Company.from("ThoughtWorks", "Joburg");

        assertThat(companies, is(Arrays.asList(one, two, three)));
    }
}