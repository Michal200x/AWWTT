package com.example.awwtt;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DataTest {

    @Test
    public void testMassBeforeFriction() {
        //Given
        Data data = new Data();
        //When
        data.setMassBeforeFriction(10.0);
        //Then
        assertEquals(10.0, data.getMassBeforeFriction(), 0.0001);
    }

    @Test
    public void testMassAfterFriction() {
        //Given
        Data data = new Data();
        //When
        data.setMassAfterFriction(8.5);
        //Then
        assertEquals(8.5, data.getMassAfterFriction(), 0.0001);
    }

    @Test
    public void testPressureForce() {
        //Given
        Data data = new Data();
        //When
        data.setPressureForce(20.0);
        //Then
        assertEquals(20.0, data.getPressureForce(), 0.0001);
    }
}
