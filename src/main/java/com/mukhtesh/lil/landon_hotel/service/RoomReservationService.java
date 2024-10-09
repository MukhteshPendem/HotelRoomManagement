package com.mukhtesh.lil.landon_hotel.service;

import com.mukhtesh.lil.landon_hotel.data.entity.Guest;
import com.mukhtesh.lil.landon_hotel.data.entity.Reservation;
import com.mukhtesh.lil.landon_hotel.data.entity.Room;
import com.mukhtesh.lil.landon_hotel.data.repository.GuestRepository;
import com.mukhtesh.lil.landon_hotel.data.repository.ReservationRepository;
import com.mukhtesh.lil.landon_hotel.data.repository.RoomRepository;
import com.mukhtesh.lil.landon_hotel.service.model.RoomReservation;
import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class RoomReservationService {

    private final RoomRepository roomRepository;

    private final ReservationRepository reservationRepository;

    private final GuestRepository guestRepository;

    public RoomReservationService(RoomRepository roomRepository, ReservationRepository reservationRepository, GuestRepository guestRepository) {
        this.roomRepository = roomRepository;
        this.reservationRepository = reservationRepository;
        this.guestRepository = guestRepository;
    }

    public List <RoomReservation> getRoomReservationByDate(String reservationDate) {

        Date date = null;

        if (StringUtils.isNotEmpty(reservationDate)) {
            date = Date.valueOf(reservationDate);

        }

        else{
            date = Date.valueOf(LocalDate.now());
        }

        Map<Long, RoomReservation> roomReservations = new HashMap<>();

        List<Room> rooms = this.roomRepository.findAll();

        rooms.forEach(room -> {

            RoomReservation roomReservation = new RoomReservation();
            roomReservation.setRoomId(room.getId());
            roomReservation.setRoomName(room.getName());
            roomReservation.setRoomNumber(room.getRoomNumber());
            roomReservations.put(room.getId(), roomReservation);

        });

        List<Reservation> reservations = this.reservationRepository.findAllByReservationDate(date);
        reservations.forEach(reservation -> {

            RoomReservation roomReservation = roomReservations.get(reservation.getRoomId());
            roomReservation.setReservationId(reservation.getReservationId());
            roomReservation.setReservationDate(reservation.getReservationDate().toString());

            Optional<Guest> guest = this.guestRepository.findById(reservation.getGuestId());
            roomReservation.setGuestId(guest.get().getGuestId());
            roomReservation.setFirstName(guest.get().getFirstName());
            roomReservation.setLastName(guest.get().getLastName());

        });

        return roomReservations.values().stream().toList();

    }


}
