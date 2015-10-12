package com.graph.application.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import com.google.common.collect.FluentIterable;
import com.graph.domain.Company;
import com.graph.domain.Person;
import lombok.Getter;

public class FileProcessor {

    @Getter
    private Path pathCompany;

    @Getter
    private Path pathPeople;


    private List<Company> companies = new ArrayList<>();
    private List<Person> people = new ArrayList<>();

    public FileProcessor(Path pathCompany, Path pathPeople) {
        this.pathCompany = pathCompany;
        this.pathPeople = pathPeople;
    }

    public List<Person> getPeople() {
        getCompanies();
        readFIle(pathPeople, this::addPeople);
        return this.people;
    }

    private void addPeople(String line) {
        String[] people = line.split(",");
        String[] fullName = people[0].split(" ");
        String email = people[1];
        String companyName = people[2];

        Company company = FluentIterable.from(companies)
                .firstMatch(input -> input.getName().equals(companyName))
                .or(Company.from(companyName, null));


        Person person = Person.from(fullName[0], fullName[1], email, company);
        this.people.add(person);


    }


    private List<Company> getCompanies() {
        readFIle(pathCompany, this::addCompany);
        return this.companies;
    }

    private void readFIle(Path pathCompany, Consumer<String> consumer) {
        try {
            Files.lines(pathCompany).forEach(consumer);
        } catch (IOException e) {
            System.out.println("file reading exception:" + e);

        }
    }

    private void addCompany(String line) {
        String[] company = line.split(",");
        this.companies.add(Company.from(company[0], company[1]));
    }


}
