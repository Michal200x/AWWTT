package com.example.awwtt;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FrictionCalculationTest {

    @Mock
    private FileDataRepository fileDataRepository;

    @InjectMocks
    private FrictionCalculation frictionCalculation;

    @Test
    public void testCalculateAverageForceWithEmptyFileData() {
        // Given
        when(fileDataRepository.findFileData()).thenReturn(new ArrayList<>());

        // When
        double result = frictionCalculation.calculateAverageForce();

        // Then
        assertEquals(0.0, result);
    }

    @Test
    public void testCalculateAverageForceWithNonEmptyFileData() {
        // Given
        List<FileData> fileDataList = new ArrayList<>();
        fileDataList.add(new FileData(1, 10.0, 15.0, 20.00));
        fileDataList.add(new FileData(2, 15.0, 20.0, 25.00));
        when(fileDataRepository.findFileData()).thenReturn(fileDataList);

        // When
        double result = frictionCalculation.calculateAverageForce();

        // Then
        assertEquals(12.5, result);
    }
}
