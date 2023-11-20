package com.example.awwtt.test;

import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FileScanner {

    public static void main(String[] args) {
        List<List<Double>> data = readDataFromAscFile("example.ASC");

        for (List<Double> line : data) {
            System.out.println(line);
        }
    }

    public static List<List<Double>> readDataFromAscFile(String fileName) {
        List<List<Double>> result = new ArrayList<>();

        try {
            // Wczytaj plik z folderu resources
            ClassPathResource resource = new ClassPathResource(fileName);
            InputStream inputStream = resource.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            // Pominięcie 41 pierwszych wierszy
            for (int i = 0; i < 38; i++) {
                reader.readLine();
            }

            // Wczytywanie danych od linii 42
            String line;
            while ((line = reader.readLine()) != null) {
                List<Double> parsedNumbers = parseNumbersFromLine(line);
                result.add(parsedNumbers);
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    private static List<Double> parseNumbersFromLine(String line) {
        List<Double> numbers = new ArrayList<>();
        String[] tokens = line.split("\t");

        // Pobranie wyłącznie 4 liczb zmiennoprzecinkowych
        for (int i = 0; i < Math.min(tokens.length, 4); i++) {
            // Zamiana przecinka na kropkę i parsowanie do Double
            String formattedToken = StringUtils.replace(tokens[i], ",", ".");
            double number = Double.parseDouble(formattedToken);
            numbers.add(number);
        }

        return numbers;
    }
}
