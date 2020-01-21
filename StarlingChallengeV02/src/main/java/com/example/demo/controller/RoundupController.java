package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.RoundupService;

@RestController
public class RoundupController {
	public RoundupController(RoundupService roundupService) {
        this.roundupService = roundupService;
    }

    private final RoundupService roundupService;

    @GetMapping
    public String roundup() {
        return roundupService.roundup();
    }
}
