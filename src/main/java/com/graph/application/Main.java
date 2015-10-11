package com.graph.application;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) {
        System.out.println("app is running!");

        Path company = Paths.get(args[0]);
        Path people = Paths.get(args[1]);

//        new CompanyProcessor(company).process();

    }


}

