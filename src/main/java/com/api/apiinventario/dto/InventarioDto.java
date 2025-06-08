package com.api.apiinventario.dto;
import lombok.*;
@Data
@NoArgsConstructor
@AllArgsConstructor

public class InventarioDto {
    private Integer idProducto;
    private String nombreProducto;
    private Integer stock;
}
