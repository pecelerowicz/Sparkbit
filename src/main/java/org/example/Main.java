package org.example;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> commands = List.of("LEFT", "GRAB", "LEFT", "BACK", "LEFT", "BACK", "LEFT");

        RoboticCodeRepresentationGenerator roboticCodeRepresentationGenerator =
                new RoboticCodeRepresentationGenerator(commands);

        System.out.println(roboticCodeRepresentationGenerator.getRcr("GRAB"));
        System.out.println(roboticCodeRepresentationGenerator.getRcr("BACK"));
        System.out.println(roboticCodeRepresentationGenerator.getRcr("LEFT"));
        System.out.println(roboticCodeRepresentationGenerator.getRcr("LEFTg"));
    }
}