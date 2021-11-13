package com.example.springintegrationproblems.service;

import org.springframework.stereotype.Service;

@Service
public class UppercaseService {

    public String makeItBig(String toUpper) {
        return toUpper.toUpperCase();
    }
    
    public String makeItSmall(String toLower) {
        return toLower.toLowerCase();
    }
}
