package com.mesadeayuda.service;

import com.mesadeayuda.dto.CrearRolDto;
import com.mesadeayuda.dto.RolResponseDto;
import com.mesadeayuda.dto.TicketResponseDto;
import com.mesadeayuda.model.Rol;
import com.mesadeayuda.model.Ticket;
import com.mesadeayuda.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.channels.IllegalChannelGroupException;
import java.util.List;

@Service
public class RolService {

    @Autowired
    private RolRepository rolRepository;

    // crear rol
    public RolResponseDto crearRol(CrearRolDto crearRolDto){
        rolRepository.findByNombre(crearRolDto.nombre())
                .orElseThrow(()->new IllegalArgumentException("Rol no encontrado"));

        Rol rol = new Rol();
        rol.setNombre(crearRolDto.nombre());

        Rol guardar = rolRepository.save(rol);

        return mapToResponse(guardar);
    }
    // listar roles
    public List<RolResponseDto> listarRoles(){
        return rolRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    // buscar por id
    public RolResponseDto buscarPorId(Long id){
        Rol rol = rolRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Rol no encontrado"));

        return mapToResponse(rol);
    }

    // buscar por nombre
    public RolResponseDto buscarPorNombre(String nombre){
        Rol rol = rolRepository.findByNombre(nombre)
                .orElseThrow(()-> new IllegalArgumentException("Rol no encontrado"));

        return mapToResponse(rol);
    }

    // eliminar
    public void eliminarRol(Long id) {
        Rol rol = rolRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));

        rolRepository.delete(rol);
    }

    private RolResponseDto mapToResponse(Rol rol) {
        return new RolResponseDto(
                rol.getId(),
                rol.getNombre()
        );
    }
}
