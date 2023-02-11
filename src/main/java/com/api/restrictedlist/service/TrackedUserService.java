package com.api.restrictedlist.service;

import com.api.restrictedlist.dto.TrackedUserDTO;
import com.api.restrictedlist.exception.ExistsCpfException;
import com.api.restrictedlist.exception.NotFoundCpfException;
import com.api.restrictedlist.model.TrackedUserModel;
import com.api.restrictedlist.repository.TrackedUserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Service
public class TrackedUserService {

    @Autowired
    TrackedUserRepository trackedUserRepository;

    @Transactional
    public TrackedUserModel save(TrackedUserDTO trackedUserDTO) {
        var trackedUserModel = new TrackedUserModel();
        BeanUtils.copyProperties(trackedUserDTO, trackedUserModel);
        trackedUserModel.setCreatedAt(LocalDateTime.now(ZoneId.of("UTC")));
       return trackedUserRepository.save(trackedUserModel);
    }

    public  void cpfAlreadyExists(TrackedUserDTO trackedUserDTO) {
        if (trackedUserRepository.findByCpf(trackedUserDTO.getCpf()).isPresent()){
            throw new ExistsCpfException("CPF already exists!");
        }
    }


    public List<TrackedUserModel> findAll() {
        return trackedUserRepository.findAll();
    }

    public TrackedUserModel findByCpf(String cpf) {
        return trackedUserRepository.findByCpf(cpf)
                .orElseThrow(() -> new NotFoundCpfException("CPF not found!"));
    }

    public void delete(TrackedUserModel trackedUserModel) {
        trackedUserRepository.delete(trackedUserModel);
    }
}
