package com.api.apiinventario.controllers;

import com.api.apiinventario.dto.InventarioDto;
import com.api.apiinventario.services.InventarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo; 
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
@RequestMapping("/api/inventario")
public class InventarioController {
    @Autowired
    private InventarioService inventarioService;

    @PostMapping
    public ResponseEntity<InventarioDto> crear(@RequestBody InventarioDto dto) {
        return ResponseEntity.ok(inventarioService.crear(dto));
    }

    @GetMapping
    public ResponseEntity<List<InventarioDto>> listar() {
        return ResponseEntity.ok(inventarioService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<InventarioDto> buscar(@PathVariable Integer id) {
        return ResponseEntity.ok(inventarioService.buscar(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<InventarioDto> actualizar(@PathVariable Integer id, @RequestBody InventarioDto dto) {
        return ResponseEntity.ok(inventarioService.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        inventarioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/hateoas/{id}")
    public InventarioDto obtenerHateoas(@PathVariable Integer id) {
        InventarioDto dto = inventarioService.buscar(id);
        dto.add(linkTo(methodOn(InventarioController.class).obtenerHateoas(id)).withSelfRel());
        dto.add(linkTo(methodOn(InventarioController.class).listarHateoas()).withRel("TODOS"));
        dto.add(linkTo(methodOn(InventarioController.class).eliminar(id)).withRel("ELIMINAR"));
        return dto;
    }
    
    @GetMapping("/hateoas")
    public List<InventarioDto> listarHateoas() {
        List<InventarioDto> lista = inventarioService.listar();

        for (InventarioDto dto : lista) {
            dto.add(linkTo(methodOn(InventarioController.class).obtenerHateoas(dto.getIdProducto())).withSelfRel());
        }
        return lista;
    }
    
}
