package com.graph.application.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import com.google.common.collect.FluentIterable;
import com.graph.domain.Person;

public class PeopleProcessor implements FileProcessorBase<Person> {

    private static final int PERSON_LINE_ITEM_LENGTH = 3;
    private static final int FULL_NAME_ARRAY_LENGTH = 2;
    private static final String PERSON_LINE_ITEM_SEPARATOR = ",";
    private static final String NAME_SURNAME_SEPARATOR = " ";
    private Path path;


    public PeopleProcessor(Path path) {
        this.path = path;
    }

    @Override
    public List<Person> process() {
        return getPeople();
    }

    public List<Person> getPeople() {

        try {
            return FluentIterable.from(Files.readAllLines(this.path))
                    .transform(this::createPerson)
                    .toList();
        } catch (IOException e) {
            String message = "error reading file: " + this.path + " ";
            System.out.println(message + e);
            throw new PeopleProcessorException(message, e);

        }
    }

    private Person createPerson(String line) {
        String[] person = line.split(PERSON_LINE_ITEM_SEPARATOR);

        if (person.length != PERSON_LINE_ITEM_LENGTH) {
            String message = String.format("error in file: %s line: %s is not formatted correctly", path, line);
            throwException(message);
        }

        String[] fullName = person[0].split(NAME_SURNAME_SEPARATOR);
        if (fullName.length != FULL_NAME_ARRAY_LENGTH) {
            String message = String.format("error in file: %s line: %s fullname is not formatted correctly", path, line);
            throwException(message);
        }

        String email = person[1];

        String companyName = person[2];

        return Person.from(fullName[0], fullName[1], email, companyName);


    }

    private void throwException(String message) {
        System.out.println(message);
        throw new PeopleProcessorException(message);
    }



}
