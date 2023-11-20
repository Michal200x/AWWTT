package com.example.awwtt;

import org.springframework.stereotype.Service;

@Service
public class MassCalculation {
    private final DataRepository dataRepository;

    public MassCalculation(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    public double calculateDeltaMass(){
        return  dataRepository.findData().getMassBeforeFriction() - dataRepository.findData().getMassAfterFriction();
    }

    public double calculateDeltaMassInPercent(){
        double absValueOfMass = Math.abs(
                (dataRepository.findData().getMassBeforeFriction() - dataRepository.findData().getMassAfterFriction())
                /dataRepository.findData().getMassBeforeFriction());

        return absValueOfMass * 100;
    }
}
