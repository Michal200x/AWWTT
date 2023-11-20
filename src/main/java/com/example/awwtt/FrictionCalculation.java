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

    public double calculateAverageForce (){
        if (fileDataRepository.findFileData() == null || fileDataRepository.findFileData().isEmpty()) {
            return 0.0;
        }

        double sum = 0.0;

        for (FileData fileData : fileDataRepository.findFileData()) {
            sum += fileData.getFrictionForce();
        }

        return sum / fileDataRepository.findFileData().size();
    }

    public double calculateCoefficientFriction(){
        return calculateAverageForce()/(50 * dataRepository.findData().getPressureForce());
    }
}
