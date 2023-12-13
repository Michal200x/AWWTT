package com.example.awwtt;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FrictionCalculation {
    private final FileDataRepository fileDataRepository;
    private final DataRepository dataRepository;

    public FrictionCalculation(FileDataRepository fileDataRepository, DataRepository dataRepository) {
        this.fileDataRepository = fileDataRepository;
        this.dataRepository = dataRepository;
    }

    public double calculateAverageForce() {
        List<FileData> latestFileData = fileDataRepository.findLatestFileData();
        if (latestFileData.isEmpty()) {
            return 0.0;
        }

        double sum = 0.0;

        for (FileData fileData : latestFileData) {
            sum += fileData.getFrictionForce();
        }

        return sum / latestFileData.size();
    }

    public double calculateCoefficientFriction() {
        double averageForce = calculateAverageForce();
        double pressureForce = dataRepository.findData().getPressureForce();
        if (pressureForce == 0) {
            return 0;
        }
        return averageForce / (50 * pressureForce);
    }
}
