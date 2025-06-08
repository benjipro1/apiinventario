package com.api.apiinventario.services;

import com.api.apiinventario.repository.InventarioRepository;
import com.api.apiinventario.dto.InventarioDto;
import com.api.apiinventario.models.Inventario;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;


@Service
public class InventarioService {
    @Autowired
    private InventarioRepository inventarioRepository;
    private InventarioDto toDTO(Inventario inventario) {
        return new InventarioDto(
            inventario.getIdProducto(),
            inventario.getNombreProducto(),
            inventario.getStock()
        );
    }

    private Inventario toEntity(InventarioDto dto) {
        Inventario inventario = new Inventario();
        inventario.setIdProducto(dto.getIdProducto());
        inventario.setNombreProducto(dto.getNombreProducto());
        inventario.setStock(dto.getStock());
        return inventario;
    } 

    public InventarioDto crear (InventarioDto dto) {
        Inventario inventario = toEntity(dto);
        return toDTO(inventarioRepository.save(inventario));
    }

    public List<InventarioDto> listar() {
        return inventarioRepository.findAll().stream()
            .map(this::toDTO)
            .collect(Collectors.toList());
    }

    public InventarioDto buscar(Integer id) {
        Inventario inventario = inventarioRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        return toDTO(inventario);
    }

    public InventarioDto actualizar(Integer id, InventarioDto dto) {
        Inventario existente = inventarioRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        existente.setNombreProducto(dto.getNombreProducto());
        existente.setStock(dto.getStock());
        return toDTO(inventarioRepository.save(existente));
    }

    public void eliminar(Integer id) {
        inventarioRepository.deleteById(id);
    }

}
