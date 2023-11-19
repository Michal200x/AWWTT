package com.example.awwtt;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DataController {
    private final DataRepository dataRepository;
    private final MassCalculation massCalculation;

    public DataController(DataRepository dataRepository, MassCalculation massCalculation) {
        this.dataRepository = dataRepository;
        this.massCalculation = massCalculation;
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

    @GetMapping("/data-view")
    String dataView(Model model){
        model.addAttribute("data", dataRepository.findData());
        model.addAttribute("deltaMass", massCalculation.calculateDeltaMass());
        model.addAttribute("deltaMassInPercent", massCalculation.calculateDeltaMassInPercent());
        return "data-view";
    }
}
