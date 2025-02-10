package com.cts.bookingservice.dto;

import lombok.Data;

@Data
public class RoomDTO {
    private Long roomId;
    private String roomNo;
    private RoomType roomType;
    private int maxOccupancy;
    private double price;

}
