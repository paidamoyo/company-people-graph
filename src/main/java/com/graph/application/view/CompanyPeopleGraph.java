package com.graph.application.view;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.graph.domain.Company;

public class CompanyPeopleGraph {


    private List<Company> companies;

    public CompanyPeopleGraph(List<Company> companies) {
        this.companies = companies;
    }

    public StringBuilder display() {
        StringBuilder stringBuilder = new StringBuilder();

        Stream<Map.Entry<String, List<Company>>> sortedCompaniesByLocation =
                groupCompaniesByLocation();

        sortedCompaniesByLocation.forEach(companyCityMap -> {
            String city = companyCityMap.getKey();
            stringBuilder.append(city).append("\n");
            companyCityMap.getValue()
                    .stream()
                    .sorted()
                    .forEach(company -> {
                        stringBuilder.append("\t").append(company.getName()).append("\n");
                        company.getEmployees()
                                .stream()
                                .sorted()
                                .forEach(person -> stringBuilder.append("\t").append("\t")
                                        .append(person.getName()).append(",")
                                        .append(person.getSurname()).append(",")
                                        .append(person.getEmail()).append("\n"));
                    });
        });

        return stringBuilder;
    }

    private Stream<Map.Entry<String, List<Company>>> groupCompaniesByLocation() {
        return companies
                .stream()
                .collect(Collectors.groupingBy(Company::getCity))
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey());
    }

}
