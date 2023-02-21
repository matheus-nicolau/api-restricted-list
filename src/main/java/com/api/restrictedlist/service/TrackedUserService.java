package com.api.restrictedlist.service;

import com.api.restrictedlist.constants.AllConstants;
import com.api.restrictedlist.dto.TrackedUserDTO;
import com.api.restrictedlist.exception.ExistsCpfException;
import com.api.restrictedlist.exception.InvalidCpfException;
import com.api.restrictedlist.exception.NotFoundCpfException;
import com.api.restrictedlist.model.TrackedUserModel;
import com.api.restrictedlist.repository.TrackedUserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class TrackedUserService {

    @Autowired
    TrackedUserRepository trackedUserRepository;
    @Autowired
    AllConstants allConstants;

    @Transactional
    public TrackedUserModel save(TrackedUserDTO trackedUserDTO) {
        var trackedUserModel = new TrackedUserModel();
        BeanUtils.copyProperties(trackedUserDTO, trackedUserModel);
        trackedUserModel.setCreatedAt(LocalDateTime.now(ZoneId.of("UTC-0300")));
        trackedUserRepository.save(trackedUserModel);
        return trackedUserModel;
    }

    public void cpfAlreadyExists(String cpf) {
        Optional<TrackedUserModel> cpfDTOValidation = trackedUserRepository.findByCpf(cpf);
        if (cpfDTOValidation.isPresent()) {
            throw new ExistsCpfException(allConstants.ALREADY_EXISTS_CPF_MSG);
        }
    }

    public void cpfValidator(String cpf) {

        for (int i = 0; i < cpf.length(); i++) {
            if (cpf.equals(allConstants.NOT_VALID_CPF[i])) {
                throw new InvalidCpfException(allConstants.INVALID_CPF_MSG);
            }
        }

        char[] stringToChar = cpf.toCharArray();
        int[] redoneInt = new int[11];

        for (int i = 0; i < redoneInt.length; i++) {
            redoneInt[i] = Integer.parseInt(String.valueOf(stringToChar[i]));
        }

        int[] firstVerification = {10, 9, 8, 7, 6, 5, 4, 3, 2};
        int[] secondVerification = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};

        for (int i = 0; i < firstVerification.length; i++) {
            firstVerification[i] *= redoneInt[i];
        }
        for (int i = 0; i < secondVerification.length; i++) {
            secondVerification[i] *= redoneInt[i];
        }

        int firstVerificationSum = Arrays.stream(firstVerification).sum();
        int secondVerificationSum = Arrays.stream(secondVerification).sum();

        firstVerificationSum = 11 - (firstVerificationSum % 11);
        secondVerificationSum = 11 - (secondVerificationSum % 11);

        if (firstVerificationSum >= 10) {
            firstVerificationSum = 0;
        }
        if (secondVerificationSum >= 10) {
            secondVerificationSum = 0;
        }
        if (firstVerificationSum != redoneInt[9] && secondVerificationSum != redoneInt[10]) {
            throw new InvalidCpfException(allConstants.INVALID_CPF_MSG);
        }
    }

    public String clearCpf(String cpf) {
        if (cpf.length() < 11 || cpf.length() > 14) {
            throw new InvalidCpfException(allConstants.INVALID_CPF_MSG);
        }
        return cpf.replaceAll("\\D", "");
    }

    public List<TrackedUserModel> findAll() {
        return trackedUserRepository.findAll();
    }

    public TrackedUserModel findByCpf(String cpf) {
        return trackedUserRepository.findByCpf(cpf)
                .orElseThrow(() -> new NotFoundCpfException(allConstants.NOT_FOUND_CPF_MSG));
    }

    public void delete(TrackedUserModel trackedUserModel) {
        trackedUserRepository.delete(trackedUserModel);
    }
}
