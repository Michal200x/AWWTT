package com.example.awwtt;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Repository
public class FileDataRepository {

    private final List<FileData> fileDataList = new ArrayList<>();

    public List<FileData> readDataFromAscFile(String fileName) {
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
                FileData fileData = parseFileDataFromLine(line);
                fileDataList.add(fileData);
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fileDataList;
    }

    private FileData parseFileDataFromLine(String line) {
        String[] tokens = line.split("\t");

        // Pobranie wyłącznie 4 liczb zmiennoprzecinkowych
        double time = parseDouble(tokens, 0);
        double frictionForce = parseDouble(tokens, 1);
        double penetrationDepth = parseDouble(tokens, 2);
        double temperature = parseDouble(tokens, 3);

        return new FileData(time, frictionForce, penetrationDepth, temperature);
    }

    private double parseDouble(String[] tokens, int index) {
        if (index < tokens.length) {
            // Zamiana przecinka na kropkę i parsowanie do Double
            String formattedToken = StringUtils.replace(tokens[index], ",", ".");
            return Double.parseDouble(formattedToken);
        }
        return 0.0; // Domyślna wartość, jeśli brak odpowiedniego tokenu
    }

    List<FileData> findFileData(){
        return fileDataList;
    }
}
