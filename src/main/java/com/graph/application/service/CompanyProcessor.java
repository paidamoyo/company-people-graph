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
            System.out.println("file reading exception:" + e);
            throw new CompanyProcessorException("file reading exception", e);

        }
    }

    private Company createCompany(String line) {
        String[] company = line.split(",");
        if(company.length != 2){
            System.out.println("file is not formatted correctly");
            throw new CompanyProcessorException("file is not formatted correctly, there should be 2 commas only");
        }
        return Company.from(company[0], company[1]);
    }
}
