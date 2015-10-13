package com.graph.application.service;

import java.nio.file.Path;
import java.util.List;

import com.graph.domain.Company;
import com.graph.domain.Person;
import lombok.Getter;

public class FileProcessor {

    @Getter
    private Path pathCompany;

    @Getter
    private Path pathPeople;


    public FileProcessor(Path pathCompany, Path pathPeople) {
        this.pathCompany = pathCompany;
        this.pathPeople = pathPeople;
    }


    public List<Company> process() {
        List<Person> people = new PeopleProcessor(pathPeople).getPeople();
        return new CompanyProcessor(pathCompany).getCompanies(people);
    }


}
