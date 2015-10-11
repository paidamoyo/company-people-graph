package com.graph.application.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import com.graph.domain.Company;
import lombok.Getter;

public class CompanyProcessor {

    @Getter
    private Path path;


    private List<Company> companies = new ArrayList<>();

    public CompanyProcessor(Path path) {
        this.path = path;
    }

    public List<Company> process() {
        try {
            Files.lines(path).forEach(this::create);
        } catch (IOException e) {
            System.out.println("file reading exception:" + e);

        }
        return companies;
    }

    private void create(String line) {
        final String[] company = line.split(",");
        companies.add(Company.from(company[0], company[1]));
    }
}
