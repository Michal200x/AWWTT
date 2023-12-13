package com.example.awwtt;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Controller
public class DataController {
    private final DataRepository dataRepository;
    private final MassCalculation massCalculation;
    private final FileDataRepository fileDataRepository;
    private final FrictionCalculation frictionCalculation;
    private static int fileNum = 0;

    public DataController(DataRepository dataRepository, MassCalculation massCalculation,
                          FileDataRepository fileDataRepository, FrictionCalculation frictionCalculation) {
        this.dataRepository = dataRepository;
        this.massCalculation = massCalculation;
        this.fileDataRepository = fileDataRepository;
        this.frictionCalculation = frictionCalculation;
    }

    @GetMapping("/add-by-keyboard")
    String addForm(){
        return "add-form";
    }

    @GetMapping("/add-by-file")
    String addFile(){
        return "add-by-file";
    }

    @PostMapping("/save")
    String saveData(
            @RequestParam("massBeforeFriction") @NumberFormat(pattern = "##0.##") double massBeforeFriction,
            @RequestParam("massAfterFriction") @NumberFormat(pattern = "##0.##") double massAfterFriction,
            @RequestParam("pressureForce") @NumberFormat(pattern = "##0.##") double pressureForce){
        dataRepository.addInputData(massBeforeFriction, massAfterFriction, pressureForce);
        return "redirect:/";
    }

    @PostMapping("/save-file")
    String saveDataFromFile(@RequestParam("file") MultipartFile file) {
        try {
            InputStream inputStream = file.getInputStream();
            fileDataRepository.readDataFromStream(fileNum, inputStream);
            fileNum++;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/";
    }

    @GetMapping("/data-view")
    String dataView(Model model){
        model.addAttribute("data", dataRepository.findData());
        model.addAttribute("deltaMass", massCalculation.calculateDeltaMass());
        model.addAttribute("deltaMassInPercent", massCalculation.calculateDeltaMassInPercent());
        model.addAttribute("fileDataMap", fileDataRepository.findAllFileData());
        model.addAttribute("averageForce", frictionCalculation.calculateAverageForce());
        model.addAttribute("coefficientFriction", frictionCalculation.calculateCoefficientFriction());
        return "data-view";
    }
}
