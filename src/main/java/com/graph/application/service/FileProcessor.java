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


    public List<Person> getPeople() {
        final List<Company> companies = new CompanyProcessor(pathCompany).getCompanies();
        return new PeopleProcessor(pathPeople).getPeople(companies);
    }
}
