package com.mukhtesh.lil.landon_hotel.service.model;

import lombok.Data;

@Data
public class RoomReservation {

    private long roomId;

    private String roomName;

    private String roomNumber;

    private long guestId;

    private String firstName;

    private String lastName;

    private String reservationDate;

    private long reservationId;


}
