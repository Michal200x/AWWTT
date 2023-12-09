package com.example.awwtt;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MassCalculationTest {

    @Mock
    private DataRepository dataRepository;

    @InjectMocks
    private MassCalculation massCalculation;

    @Test
    public void testCalculateDeltaMass() {
        // Given
        Data testData = new Data();
        testData.setMassBeforeFriction(10.0);
        testData.setMassAfterFriction(8.5);
        testData.setPressureForce(20.0);
        when(dataRepository.findData()).thenReturn(testData);

        // When
        double result = massCalculation.calculateDeltaMass();

        // Then
        assertEquals(1.5, result);
    }

    @Test
    public void testCalculateDeltaMassInPercent() {
        // Given
        Data testData = new Data();
        testData.setMassBeforeFriction(10.0);
        testData.setMassAfterFriction(8.5);
        testData.setPressureForce(20.0);
        when(dataRepository.findData()).thenReturn(testData);

        // When
        double result = massCalculation.calculateDeltaMassInPercent();

        // Then
        assertEquals(15.0, result);
    }
}
