package com.leony.app;

import com.leony.home.ProducingJSON;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        producingJSON();
    }

    private static void producingJSON() {
        ProducingJSON producingJSON = new ProducingJSON();

        printMessage("ProducingJSON: manual JSON creation");
        producingJSON.manualJSONCreation();

        printMessage("ProducingJSON: using generator API");
        try {
            producingJSON.usingGeneratorAPI();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        printMessage("ProducingJSON: using bindings API");
        try {
            producingJSON.usingBindingAPI();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void printMessage(String msg) {
        System.out.println("\n========= " + msg + " ===========\n");
    }
}
