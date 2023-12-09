package com.example.awwtt;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileDataTest {

    @Test
    public void testFileDataConstructorAndGetters() {
        // Given
        double time = 5.0;
        double frictionForce = 12.5;
        double penetrationDepth = 3.0;
        double temperature = 25.0;

        // When
        FileData fileData = new FileData(time, frictionForce, penetrationDepth, temperature);

        // Then
        assertEquals(time, fileData.getTime());
        assertEquals(frictionForce, fileData.getFrictionForce());
        assertEquals(penetrationDepth, fileData.getPenetrationDepth());
        assertEquals(temperature, fileData.getTemperature());
    }

    @Test
    public void testFileDataSetters() {
        // Given
        FileData fileData = new FileData(0.0, 0.0, 0.0, 0.0);

        // When
        double time = 10.0;
        double frictionForce = 15.0;
        double penetrationDepth = 5.0;
        double temperature = 30.0;

        fileData.setTime(time);
        fileData.setFrictionForce(frictionForce);
        fileData.setPenetrationDepth(penetrationDepth);
        fileData.setTemperature(temperature);

        // Then
        assertEquals(time, fileData.getTime());
        assertEquals(frictionForce, fileData.getFrictionForce());
        assertEquals(penetrationDepth, fileData.getPenetrationDepth());
        assertEquals(temperature, fileData.getTemperature());
    }
}
