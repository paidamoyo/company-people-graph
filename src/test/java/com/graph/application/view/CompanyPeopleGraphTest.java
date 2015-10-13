package com.graph.application.view;

import java.util.Arrays;
import java.util.Collections;

import org.junit.Ignore;
import org.junit.Test;

import com.graph.domain.Company;
import com.graph.domain.Person;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class CompanyPeopleGraphTest {

    @Test
    @Ignore("Some syntax issue")
    public void shouldDisplay() throws Exception {

        //given
        Person benny = Person.from("Benny", "Ou", "bennyou.cpt@gmail.com", "Allan Gray");
        Person evan = Person.from("Evan", "Walther", "evan@fitkey.co.za", "FitKey");
        Person kelvin = Person.from("Kelvin", "Smith", "kelvin@fitkey.co.za", "FitKey");
        Person joshua = Person.from("Joshua", "Shimkin", "josh@fitkey.co.za", "FitKey");

        Company allanGray = Company.from("Allan Gray", "Cape Town", Collections.singletonList(benny));
        Company fitKey = Company.from("FitKey", "Joburg", Arrays.asList(evan,kelvin,joshua));
        Company thoughtWorks = Company.from("ThoughtWorks", "Joburg", Collections.EMPTY_LIST);



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


        assertThat(graph,is(expected));

    }
}