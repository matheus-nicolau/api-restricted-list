package com.api.restrictedlist.repository;

import com.api.restrictedlist.model.TrackedUserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TrackedUserRepository extends JpaRepository<TrackedUserModel, String> {

    Optional<TrackedUserModel> findByCpf(String cpf);


}
