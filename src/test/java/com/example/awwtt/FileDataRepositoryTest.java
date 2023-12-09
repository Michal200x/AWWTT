package com.example.awwtt;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class FileDataRepositoryTest {

    @Test
    public void testReadDataFromStream() {
        // Given
        StringBuilder fileContentBuilder = new StringBuilder();
        fileContentBuilder.append("header1\theader2\theader3\theader4\n");

        for (int i = 0; i < 39; i++) {
            fileContentBuilder.append("1.0\t10.0\t2.5\t25.0\n");
        }

        fileContentBuilder.append("3.0\t12.0\t12.5\t23.0\n");

        String fileContent = fileContentBuilder.toString();

        InputStream inputStream = new ByteArrayInputStream(fileContent.getBytes(StandardCharsets.UTF_8));
        FileDataRepository fileDataRepository = new FileDataRepository();

        // When
        fileDataRepository.readDataFromStream(inputStream);
        List<FileData> result = fileDataRepository.findFileData();

        // Then
        assertNotNull(result);
        assertEquals(3, result.size());

        FileData firstData = result.get(0);
        assertEquals(1.0, firstData.getTime());
        assertEquals(10.0, firstData.getFrictionForce());
        assertEquals(2.5, firstData.getPenetrationDepth());
        assertEquals(25.0, firstData.getTemperature());

        FileData lastData = result.get(result.size() - 1);
        assertEquals(3.0, lastData.getTime());
        assertEquals(12.0, lastData.getFrictionForce());
        assertEquals(12.5, lastData.getPenetrationDepth());
        assertEquals(23.0, lastData.getTemperature());
    }

    @Test
    public void testParseDouble() {
        // Given
        FileDataRepository fileDataRepository = new FileDataRepository();
        String[] tokens = {"1.0", "10,5", "2.5", "0.0"};

        // When
        double result1 = fileDataRepository.parseDouble(tokens, 0);
        double result2 = fileDataRepository.parseDouble(tokens, 1);
        double result3 = fileDataRepository.parseDouble(tokens, 2);
        double result4 = fileDataRepository.parseDouble(tokens, 3);

        // Then
        assertEquals(1.0, result1);
        assertEquals(10.5, result2);
        assertEquals(2.5, result3);
        assertEquals(0.0, result4);
    }
}
