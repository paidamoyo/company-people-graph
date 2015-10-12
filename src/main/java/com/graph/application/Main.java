package com.graph.application;

import java.nio.file.Path;
import java.nio.file.Paths;

import com.graph.application.service.FileProcessor;

public class Main {

    public static void main(String[] args) {
        System.out.println("app is running!");

        Path pathCompany = Paths.get(args[0]);
        Path pathPeople = Paths.get(args[1]);

        String people = new FileProcessor(pathCompany, pathPeople).getPeople().toString();

        System.out.println("all people are:" + people);

    }


}

