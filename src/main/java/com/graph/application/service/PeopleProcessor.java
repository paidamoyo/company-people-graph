package com.graph.application.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import com.google.common.collect.FluentIterable;
import com.graph.domain.Company;
import com.graph.domain.Person;

public class PeopleProcessor {

    private Path path;


    public PeopleProcessor(Path path) {
        this.path = path;
    }

    public List<Person> getPeople(List<Company> companies) {

        try {
            return FluentIterable.from(Files.readAllLines(path))
                    .transform(line -> createPerson(line, companies))
                    .toList();
        } catch (IOException e) {
            System.out.println("file reading exception:" + e);
            throw new PeopleProcessorException("file reading exception", e);

        }
    }

    private Person createPerson(String line, List<Company> companies) {
        String[] people = line.split(",");
        String[] fullName = people[0].split(" ");
        String email = people[1];
        String companyName = people[2];

        Company company = findCompanyByName(companies, companyName);


        return Person.from(fullName[0], fullName[1], email, company);


    }

    private Company findCompanyByName(List<Company> companies, String companyName) {
        return FluentIterable.from(companies)
                .firstMatch(input -> input.getName().equals(companyName))
                .or(Company.from(companyName, null));
    }
}
