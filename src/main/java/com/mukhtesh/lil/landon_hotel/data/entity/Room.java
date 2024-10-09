package com.mukhtesh.lil.landon_hotel.data.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "rooms")
@Data
public class Room {

    @Id
    @Column(name = "room_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "room_number")
    private String roomNumber;

    @Column(name = "bed_info")
    private String bedInfo;


}
