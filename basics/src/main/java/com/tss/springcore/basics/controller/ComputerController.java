package com.tss.springcore.basics.controller;

import com.tss.springcore.basics.model.Computer;
import com.tss.springcore.basics.model.Harddisk;
import com.tss.springcore.basics.service.ComputerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app")
public class ComputerController {

    private final ComputerService computerService;

    public ComputerController(ComputerService computerService) {
        this.computerService = computerService;
    }

    @GetMapping("/computers")
    public Computer getComputer() {
        return computerService.getComputer();
    }
}
