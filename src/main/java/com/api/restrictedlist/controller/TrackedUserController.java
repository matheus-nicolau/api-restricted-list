package com.api.restrictedlist.controller;

import com.api.restrictedlist.dto.TrackedUserDTO;
import com.api.restrictedlist.model.TrackedUserModel;
import com.api.restrictedlist.service.TrackedUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/cpf")
public class TrackedUserController {

    @Autowired
    TrackedUserService trackedUserService;
    @PostMapping
    public ResponseEntity<TrackedUserModel> saveTrackedUser(@RequestBody @Valid TrackedUserDTO trackedUserDTO){
        trackedUserService.cpfAlreadyExists(trackedUserDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(trackedUserService.save(trackedUserDTO));
    }

    @GetMapping
    public ResponseEntity<List<TrackedUserModel>> findAllTrackedUser(){
        return ResponseEntity.status(HttpStatus.OK).body(trackedUserService.findAll());
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<Object> findOneTrackedUser(@PathVariable("cpf") String cpf){
        return ResponseEntity.status(HttpStatus.OK).body(trackedUserService.findByCpf(cpf));
    }


}