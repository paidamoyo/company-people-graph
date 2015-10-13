package com.graph.application.view;

import java.util.Arrays;
import java.util.Collections;

import org.junit.Test;

import com.graph.domain.Company;
import com.graph.domain.Person;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class CompanyPeopleGraphTest {

    @Test
    public void shouldDisplayAGraphOfCityCompanyAndEmployees() throws Exception {

        //given
        Person benny = Person.from("Benny", "Ou", "bennyou.cpt@gmail.com", "Allan Gray");
        Person evan = Person.from("Evan", "Walther", "evan@fitkey.co.za", "FitKey");
        Person kelvin = Person.from("Kelvin", "Smith", "kelvin@fitkey.co.za", "FitKey");
        Person joshua = Person.from("Joshua", "Shimkin", "josh@fitkey.co.za", "FitKey");

        Company allanGray = Company.from("Allan Gray", "Cape Town", Collections.singletonList(benny));
        Company fitKey = Company.from("FitKey", "Joburg", Arrays.asList(evan, kelvin, joshua));
        Company thoughtWorks = Company.from("ThoughtWorks", "Joburg", Collections.emptyList());


        CompanyPeopleGraph companyPeopleGraph = new CompanyPeopleGraph(Arrays.asList(allanGray, fitKey, thoughtWorks));

        //when
        StringBuilder graph = companyPeopleGraph.display();

        //then
        StringBuilder expected = new StringBuilder();
        expected
                .append("Cape Town").append("\n")
                .append("\t").append("Allan Gray").append("\n")
                .append("\t").append("\t").append("Benny,Ou,bennyou.cpt@gmail.com").append("\n")
                .append("Joburg").append("\n")
                .append("\t").append("FitKey").append("\n")
                .append("\t").append("\t").append("Evan,Walther,evan@fitkey.co.za").append("\n")
                .append("\t").append("\t").append("Joshua,Shimkin,josh@fitkey.co.za").append("\n")
                .append("\t").append("\t").append("Kelvin,Smith,kelvin@fitkey.co.za").append("\n")
                .append("\t").append("ThoughtWorks").append("\n");

        assertThat(graph.toString(), is(expected.toString()));

    }

    @Test
    public void shouldDisplayCitiesInAlphabeticOrder() throws Exception {

        //given;
        Company allanGray = Company.from("Allan Gray", "Harare", Collections.emptyList());
        Company fitKey = Company.from("FitKey", "Durban", Collections.emptyList());


        CompanyPeopleGraph companyPeopleGraph = new CompanyPeopleGraph(Arrays.asList(fitKey, allanGray));
        //when
        StringBuilder graph = companyPeopleGraph.display();

        //then
        StringBuilder expected = new StringBuilder();
        expected
                .append("Durban").append("\n")
                .append("\t").append("FitKey").append("\n")
                .append("Harare").append("\n")
                .append("\t").append("Allan Gray").append("\n");

        assertThat(graph.toString(), is(expected.toString()));
    }

    @Test
    public void shouldDisplayCompanyNamesInAlphabeticOrder() throws Exception {

        //given;
        Company allanGray = Company.from("Allan Gray", "Harare", Collections.emptyList());
        Company fitKey = Company.from("FitKey", "Durban", Collections.emptyList());
        Company mone = Company.from("Mone", "Durban", Collections.emptyList());


        CompanyPeopleGraph companyPeopleGraph = new CompanyPeopleGraph(Arrays.asList(fitKey, mone,allanGray));
        //when
        StringBuilder graph = companyPeopleGraph.display();

        //then
        StringBuilder expected = new StringBuilder();
        expected
                .append("Durban").append("\n")
                .append("\t").append("FitKey").append("\n")
                .append("\t").append("Mone").append("\n")
                .append("Harare").append("\n")
                .append("\t").append("Allan Gray").append("\n");

        assertThat(graph.toString(), is(expected.toString()));
    }

    @Test
    public void shouldDisplayEmployeesNamesAndSurnamesAndEmailInAlphabeticOrder() throws Exception {

        //given;
        Person evan = Person.from("Evan", "Walther", "evan@fitkey.co.za", "FitKey");
        Person kelvin = Person.from("Kelvin", "Smith", "kelvin@fitkey.co.za", "FitKey");
        Person josh = Person.from("Kelvin", "Shimkin", "kelvin@fitkey.co.za", "FitKey");
        Person keem = Person.from("Kelvin", "Shimkin", "relvin@fitkey.co.za", "FitKey");

        Company allanGray = Company.from("Allan Gray", "Harare", Collections.emptyList());
        Company fitKey = Company.from("FitKey", "Durban",Arrays.asList(kelvin,evan,keem,josh));
        Company mone = Company.from("Mone", "Durban", Collections.emptyList());


        CompanyPeopleGraph companyPeopleGraph = new CompanyPeopleGraph(Arrays.asList(fitKey, mone,allanGray));
        //when
        StringBuilder graph = companyPeopleGraph.display();

        //then
        StringBuilder expected = new StringBuilder();
        expected
                .append("Durban").append("\n")
                .append("\t").append("FitKey").append("\n")
                .append("\t").append("\t").append("Evan,Walther,evan@fitkey.co.za").append("\n")
                .append("\t").append("\t").append("Kelvin,Shimkin,kelvin@fitkey.co.za").append("\n")
                .append("\t").append("\t").append("Kelvin,Shimkin,relvin@fitkey.co.za").append("\n")
                .append("\t").append("\t").append("Kelvin,Smith,kelvin@fitkey.co.za").append("\n")
                .append("\t").append("Mone").append("\n")
                .append("Harare").append("\n")
                .append("\t").append("Allan Gray").append("\n");

        assertThat(graph.toString(), is(expected.toString()));
    }
}