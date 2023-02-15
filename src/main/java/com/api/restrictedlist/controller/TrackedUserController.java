package com.api.restrictedlist.controller;

import com.api.restrictedlist.constants.AllConstants;
import com.api.restrictedlist.dto.TrackedUserDTO;
import com.api.restrictedlist.exception.NotFoundCpfException;
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
    @Autowired
    AllConstants allConstants;

    @PostMapping
    public ResponseEntity<TrackedUserModel> saveTrackedUser(@RequestBody @Valid TrackedUserDTO trackedUserDTO){
        trackedUserDTO.setCpf(trackedUserService.clearCpf(trackedUserDTO.getCpf()));
        trackedUserService.cpfValidator(trackedUserDTO.getCpf());
        trackedUserService.cpfAlreadyExists(trackedUserDTO.getCpf());
     return ResponseEntity.status(HttpStatus.CREATED).body(trackedUserService.save(trackedUserDTO));
    }

    @GetMapping
    public ResponseEntity<List<TrackedUserModel>> findAllTrackedUser(){
        return ResponseEntity.status(HttpStatus.OK).body(trackedUserService.findAll());
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<TrackedUserModel> findOneTrackedUser(@PathVariable("cpf") String cpf){
        String clearCpf = trackedUserService.clearCpf(cpf);
        trackedUserService.cpfValidator(clearCpf);
        return ResponseEntity.status(HttpStatus.OK).body(trackedUserService.findByCpf(clearCpf));
    }

        @DeleteMapping("/{cpf}")
    public ResponseEntity<String> deleteTrackedUser(@PathVariable("cpf") String cpf) {
            String clearCpf = trackedUserService.clearCpf(cpf);
            trackedUserService.cpfValidator(clearCpf);
            TrackedUserModel trackedUserModelSearch = trackedUserService.findByCpf(clearCpf);
            if (trackedUserModelSearch == null){
                throw new NotFoundCpfException(allConstants.NOT_FOUND_CPF_MSG);
            }
            trackedUserService.delete(trackedUserModelSearch);
            return ResponseEntity.status(HttpStatus.OK).body(allConstants.DELETED_CPF_MSG);
    }

}