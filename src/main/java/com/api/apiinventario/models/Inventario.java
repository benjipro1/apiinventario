package com.api.apiinventario.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="inventario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Inventario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="inventario")
    private Integer idProducto;
    private String nombreProducto;
    private Integer stock;
}
