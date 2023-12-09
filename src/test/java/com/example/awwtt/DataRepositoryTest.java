package com.example.awwtt;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DataRepositoryTest {

    @Test
    public void testAddInputData() {
        // Given
        DataRepository dataRepository = new DataRepository();
        double massBeforeFriction = 10.0;
        double massAfterFriction = 8.5;
        double pressureForce = 20.0;

        // When
        dataRepository.addInputData(massBeforeFriction, massAfterFriction, pressureForce);
        Data result = dataRepository.findData();

        // Then
        assertEquals(massBeforeFriction, result.getMassBeforeFriction());
        assertEquals(massAfterFriction, result.getMassAfterFriction());
        assertEquals(pressureForce, result.getPressureForce());
    }

    @Test
    public void testFindData() {
        // Given
        DataRepository dataRepository = new DataRepository();
        double massBeforeFriction = 12.0;
        double massAfterFriction = 9.0;
        double pressureForce = 25.0;
        dataRepository.addInputData(massBeforeFriction, massAfterFriction, pressureForce);

        // When
        Data result = dataRepository.findData();

        // Then
        assertEquals(massBeforeFriction, result.getMassBeforeFriction());
        assertEquals(massAfterFriction, result.getMassAfterFriction());
        assertEquals(pressureForce, result.getPressureForce());
    }
}
