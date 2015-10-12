package com.graph.application.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import com.google.common.collect.FluentIterable;
import com.graph.domain.Company;

public class CompanyProcessor {

    private Path path;


    public CompanyProcessor(Path path) {
        this.path = path;
    }

    public List<Company> getCompanies() {

        try {
            return FluentIterable.from(Files.readAllLines(path))
                    .transform(this::createCompany)
                    .toList();
        } catch (IOException e) {
            String message = String.format("error reading file: %s ", path);
            System.out.println(message + e);
            throw new CompanyProcessorException(message, e);

        }
    }

    private Company createCompany(String line) {
        String[] company = line.split(",");
        if (company.length != 2) {
            String message = String.format("error in file: %s line: %s is not formatted correctly", path, line);
            System.out.println(message);
            throw new CompanyProcessorException(message);
        }
        return Company.from(company[0], company[1]);
    }
}
