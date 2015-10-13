package com.graph.application.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import com.graph.domain.Company;
import com.graph.domain.Person;

public class CompanyProcessor implements FileProcessorBase<Company> {

    private static final int COMPANY_LINE_ITEMS_LENGTH = 2;
    private static final String COMPANY_LINE_ITEMS_SEPARATOR = ",";

    private Path path;
    private PeopleProcessor peopleProcessor;

    public CompanyProcessor(PeopleProcessor peopleProcessor, Path path) {
        this.peopleProcessor = peopleProcessor;
        this.path = path;
    }

    @Override
    public List<Company> process() {
        return getCompanies(this.peopleProcessor.process());
    }

    private List<Company> getCompanies(List<Person> people) {

        try {
            return FluentIterable.from(Files.readAllLines(path))
                    .transform(line -> createCompany(line, people))
                    .toList();
        } catch (IOException e) {
            String message = String.format("error reading file: %s ", path);
            System.out.println(message + e);
            throw new CompanyProcessorException(message, e);

        }
    }

    private Company createCompany(String line, List<Person> people) {
        String[] company = line.split(COMPANY_LINE_ITEMS_SEPARATOR);
        if (company.length != COMPANY_LINE_ITEMS_LENGTH) {
            String message = String.format("error in file: %s line: %s is not formatted correctly", path, line);
            System.out.println(message);
            throw new CompanyProcessorException(message);
        }
        String name = company[0];
        String city = company[1];

        final ImmutableList<Person> employees = findPeopleByCompanyName(people, name);

        return Company.from(name, city, employees);
    }

    private ImmutableList<Person> findPeopleByCompanyName(List<Person> people, String name) {
        return FluentIterable.from(people)
                .filter(person -> {
                    return person.getCompanyName().equals(name);
                }).toList();
    }


}
