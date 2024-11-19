package com.example.autoAlert360Pro.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@Builder

public class VehicleDto {

  private String  placa;
  private String  tipo;
  private Date  fechaSoat;
  private Date  fechaTechno;
  private String  ubicacion;
}
