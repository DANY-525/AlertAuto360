package com.example.autoAlert360Pro.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@Builder

public class VehicleTest {
    private int id;
    private String color;
    private int model;
    private int type;
    private String linea;
    private Date enrollDate;
}

