package com.api.apiinventario.dto;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class InventarioDto extends RepresentationModel<InventarioDto> {
    private Integer idProducto;
    private String nombreProducto;
    private Integer stock;
}
