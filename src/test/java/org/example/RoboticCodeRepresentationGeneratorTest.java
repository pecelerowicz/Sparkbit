package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

class RoboticCodeRepresentationGeneratorTest {

    @Test
    void itShouldGetRcr1() {
        // Given
        List<String> commands = List.of("LEFT", "GRAB", "LEFT", "BACK", "LEFT", "BACK", "LEFT");
        RoboticCodeRepresentationGenerator roboticCodeRepresentationGenerator =
                new RoboticCodeRepresentationGenerator(commands);
        String label1 = "GRAB";
        String label2 = "BACK";
        String label3 = "LEFT";
        String label4 = "RIGHT";

        // When
        String rcr1 = roboticCodeRepresentationGenerator.getRcr(label1);
        String rcr2 = roboticCodeRepresentationGenerator.getRcr(label2);
        String rcr3 = roboticCodeRepresentationGenerator.getRcr(label3);

        // Then
        Assertions.assertEquals("00", rcr1);
        Assertions.assertEquals("01", rcr2);
        Assertions.assertEquals("1", rcr3);
        Exception exception = assertThrows(RuntimeException.class, () -> {
            roboticCodeRepresentationGenerator.getRcr(label4);
        });
        Assertions.assertEquals("Wrong label", exception.getMessage());
    }

    @Test
    void itShouldGetRcr2() {
        // Given
        List<String> commands = List.of("a", "a", "a", "b", "b", "c", "c", "c", "c", "d", "d", "d", "d", "d");
        RoboticCodeRepresentationGenerator roboticCodeRepresentationGenerator =
                new RoboticCodeRepresentationGenerator(commands);
        String label1 = "a";
        String label2 = "b";
        String label3 = "c";
        String label4 = "d";
        String label5 = "e";

        // When
        String rcr1 = roboticCodeRepresentationGenerator.getRcr(label1);
        String rcr2 = roboticCodeRepresentationGenerator.getRcr(label2);
        String rcr3 = roboticCodeRepresentationGenerator.getRcr(label3);
        String rcr4 = roboticCodeRepresentationGenerator.getRcr(label4);

        // Then
        Assertions.assertEquals("01", rcr1);
        Assertions.assertEquals("00", rcr2);
        Assertions.assertEquals("10", rcr3);
        Assertions.assertEquals("11", rcr4);
        Exception exception = assertThrows(RuntimeException.class, () -> {
            roboticCodeRepresentationGenerator.getRcr(label5);
        });
        Assertions.assertEquals("Wrong label", exception.getMessage());
    }

    @Test
    void itShouldGetRcr3() {
        // Given
        List<String> commands =
                List.of("a", "a", "a", "a", "a", "b", "b", "b", "b", "c", "c", "c", "d", "d", "e");
        RoboticCodeRepresentationGenerator roboticCodeRepresentationGenerator =
                new RoboticCodeRepresentationGenerator(commands);
        String label1 = "a";
        String label2 = "b";
        String label3 = "c";
        String label4 = "d";
        String label5 = "e";

        // When
        String rcr1 = roboticCodeRepresentationGenerator.getRcr(label1);
        String rcr2 = roboticCodeRepresentationGenerator.getRcr(label2);
        String rcr3 = roboticCodeRepresentationGenerator.getRcr(label3);
        String rcr4 = roboticCodeRepresentationGenerator.getRcr(label4);
        String rcr5 = roboticCodeRepresentationGenerator.getRcr(label5);

        // Then
        Assertions.assertEquals("11", rcr1);
        Assertions.assertEquals("10", rcr2);
        Assertions.assertEquals("00", rcr3);
        Assertions.assertEquals("011", rcr4);
        Assertions.assertEquals("010", rcr5);
    }

}