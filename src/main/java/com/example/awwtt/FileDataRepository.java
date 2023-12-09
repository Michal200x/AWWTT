package com.example.awwtt;

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

    public void readDataFromStream(InputStream inputStream) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            for (int i = 0; i < 38; i++) {
                reader.readLine();
            }

            String line;
            while ((line = reader.readLine()) != null) {
                FileData fileData = parseFileDataFromLine(line);
                fileDataList.add(fileData);
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private FileData parseFileDataFromLine(String line) {
        String[] tokens = line.split("\t");

        double time = parseDouble(tokens, 0);
        double frictionForce = parseDouble(tokens, 1);
        double penetrationDepth = parseDouble(tokens, 2);
        double temperature = parseDouble(tokens, 3);

        return new FileData(time, frictionForce, penetrationDepth, temperature);
    }

    double parseDouble(String[] tokens, int index) {
        if (index < tokens.length) {
            String formattedToken = StringUtils.replace(tokens[index], ",", ".");
            return Double.parseDouble(formattedToken);
        }
        return 0.0;
    }

    List<FileData> findFileData() {
        return fileDataList;
    }
}
