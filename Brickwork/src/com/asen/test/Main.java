package com.asen.test;


import com.asen.test.Services.ConstructionService.ConstructionWork;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Insert N and M: ");

        ConstructionWork constructionWork = new ConstructionWork(scanner);
        System.out.println("Insert Bricks:");
        constructionWork.createConstruction(scanner);

    }


}