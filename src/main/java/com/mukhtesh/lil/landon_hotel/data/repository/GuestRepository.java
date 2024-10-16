package com.mukhtesh.lil.landon_hotel.data.repository;

import com.mukhtesh.lil.landon_hotel.data.entity.Guest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GuestRepository extends JpaRepository<Guest, Long> {

    List<Guest> getGuestsByEmailAddress(String email);

}
