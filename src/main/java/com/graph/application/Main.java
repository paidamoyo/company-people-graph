package com.graph.application;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import com.graph.application.service.FileProcessor;
import com.graph.application.view.CompanyPeopleGraph;
import com.graph.domain.Company;

public class Main {

    public static void main(String[] args) {
        System.out.println("app is running!" + "\n");

        Path pathCompany = Paths.get(args[0]);
        Path pathPeople = Paths.get(args[1]);

        List<Company> companies = new FileProcessor(pathCompany, pathPeople).companyPeopleGraph();

        StringBuilder companyPeopleGraph = new CompanyPeopleGraph(companies).display();

        System.out.println("company people graph:" + "\n") ;
        System.out.println(companyPeopleGraph);

    }


}

