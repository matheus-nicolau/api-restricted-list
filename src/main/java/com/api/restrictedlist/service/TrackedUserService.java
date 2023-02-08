package com.api.restrictedlist.service;

import com.api.restrictedlist.model.TrackedUserModel;
import com.api.restrictedlist.repository.TrackedUserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrackedUserService {

    @Autowired
    TrackedUserRepository trackedUserRepository;

    @Transactional
    public TrackedUserModel save(TrackedUserModel trackedUserModel) {
        return trackedUserRepository.save(trackedUserModel);
    }

    public List<TrackedUserModel> findAll() {
        return trackedUserRepository.findAll();
    }

    public Optional<TrackedUserModel> findById(String userCpf) {
        return trackedUserRepository.findById(userCpf);
    }

    public void delete(TrackedUserModel trackedUserModel) {
        trackedUserRepository.delete(trackedUserModel);
    }
}
