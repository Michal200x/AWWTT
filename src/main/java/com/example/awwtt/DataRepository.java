package com.example.awwtt;

import org.springframework.stereotype.Repository;

@Repository
public class DataRepository {
    Data inputData = new Data();

    void addInputData( double massBeforeFriction, double massAfterFriction, double pressureForce){
        inputData.setMassBeforeFriction(massBeforeFriction);
        inputData.setMassAfterFriction(massAfterFriction);
        inputData.setPressureForce(pressureForce);
    }

    Data findData(){
        return inputData;
    }
}
