package com.codeclan.example.WhiskyTracker.controllers;

import com.codeclan.example.WhiskyTracker.models.Whisky;
import com.codeclan.example.WhiskyTracker.repositories.WhiskyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@RestController
public class WhiskyController {

    @Autowired
    WhiskyRepository whiskyRepository;

    @GetMapping(value = "/whiskies")
    public ResponseEntity<List<Whisky>> getAllWhiskies
            (@RequestParam(name="age", required=false) Integer age,
            @RequestParam(name="distillery", required=false) String distillery){

         if (age == null && distillery != null){
             return new ResponseEntity<>(whiskyRepository.findWhiskyByYear(age), HttpStatus.OK);
        } else if (age != null && distillery != null){
             // write query to get dist by year
             return new ResponseEntity<>(whiskyRepository.findByDistillery_idAndAge(distillery,age), HttpStatus.OK);
         }
        return new ResponseEntity<>(whiskyRepository.findAll(),HttpStatus.OK);
    }

}
