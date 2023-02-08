package com.api.restrictedlist.repository;

import com.api.restrictedlist.model.TrackedUserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackedUserRepository extends JpaRepository<TrackedUserModel, String> {

}
