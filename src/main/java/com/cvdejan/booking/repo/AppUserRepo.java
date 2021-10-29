package com.cvdejan.booking.repo;

import com.cvdejan.booking.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepo extends JpaRepository<AppUser, Long> {
    AppUser findByUserName(String userName);

}