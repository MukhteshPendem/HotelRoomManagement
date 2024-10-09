package com.mukhtesh.lil.landon_hotel.web.controller;

import com.mukhtesh.lil.landon_hotel.service.RoomReservationService;
import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;

@Controller
@RequestMapping("/occupancy")
public class OccupancyController {

    private final RoomReservationService roomReservationService;

    public OccupancyController(RoomReservationService roomReservationService) {
        this.roomReservationService = roomReservationService;
    }

    @GetMapping
    public String getOccupancy(Model model, @RequestParam(value = "date", required = false) String dateString){

        Date date = null;

        if (StringUtils.isNotBlank(dateString)){

            try {
                date = Date.valueOf(dateString);
            }
            catch (IllegalArgumentException e){

                e.printStackTrace();

            }


        }
        model.addAttribute("date",date);
        model.addAttribute("reservations",this.roomReservationService.getRoomReservationByDate(dateString));
        return "occupancy";

    }

}
