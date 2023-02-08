package com.api.restrictedlist.controller;

import com.api.restrictedlist.dto.TrackedUserDTO;
import com.api.restrictedlist.model.TrackedUserModel;
import com.api.restrictedlist.service.TrackedUserService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cpf")
public class TrackedUserController {

    @Autowired
    TrackedUserService trackedUserService;
    @PostMapping
    public ResponseEntity<Object> saveTrackedUser(@RequestBody @Valid TrackedUserDTO trackedUserDTO){

        var trackedUserModel = new TrackedUserModel();
        BeanUtils.copyProperties(trackedUserDTO, trackedUserModel);
        trackedUserModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));

        return ResponseEntity.status(HttpStatus.CREATED).body(trackedUserService.save(trackedUserModel));
    }

    @GetMapping
    public ResponseEntity<List<TrackedUserModel>> findAllTrackedUser(){
        return ResponseEntity.status(HttpStatus.OK).body(trackedUserService.findAll());
    }

    @GetMapping("/{userCpf}")
    public ResponseEntity<Object> findOneTrackedUser(@PathVariable("userCpf") String userCpf){
        Optional<TrackedUserModel> trackedUserModelOptional = trackedUserService.findById(userCpf);
        if (!trackedUserModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("This CPF not exists!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(trackedUserModelOptional.get());
    }

    @DeleteMapping("/{userCpf}")
    public ResponseEntity<Object> deleteTrackedUser(@PathVariable("userCpf") String userCpf) {
        Optional<TrackedUserModel> trackedUserModelOptional = trackedUserService.findById(userCpf);
        if (!trackedUserModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("This CPF not found!");
        }
        trackedUserService.delete(trackedUserModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("CPF deleted successfully.");
    }
}