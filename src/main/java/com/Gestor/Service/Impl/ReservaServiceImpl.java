package com.Gestor.Service.Impl;

import com.Gestor.Model.*;
import com.Gestor.Repository.ReservaRepository;
import com.Gestor.Repository.ServicioRepository;
import com.Gestor.Repository.UsuarioRepository;
import com.Gestor.Service.HistorialReservaService;
import com.Gestor.Service.ReservaService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ReservaServiceImpl implements ReservaService {

    private final ReservaRepository reservaRepository;
    private final UsuarioRepository usuarioRepository;
    private final ServicioRepository servicioRepository;
    private final HistorialReservaService historialReservaService;

    public ReservaServiceImpl(ReservaRepository reservaRepository, UsuarioRepository usuarioRepository,
                              ServicioRepository servicioRepository, HistorialReservaService historialReservaService) {
        this.reservaRepository = reservaRepository;
        this.usuarioRepository = usuarioRepository;
        this.servicioRepository = servicioRepository;
        this.historialReservaService = historialReservaService;
    }

    @Override
    @Transactional
    public Reserva crearReserva(Reserva reserva) {
        Usuario usuario = usuarioRepository.findById(reserva.getUsuario().getId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Servicio servicio = servicioRepository.findById(reserva.getServicio().getId())
                .orElseThrow(() -> new RuntimeException("Servicio no encontrado"));

        reserva.setUsuario(usuario);
        reserva.setServicio(servicio);
        Reserva nuevaReserva = reservaRepository.save(reserva);

        // ✅ Registrar en el historial
        historialReservaService.registrarAccion(nuevaReserva, "CREADA");

        return nuevaReserva;
    }

    @Override
    public Optional<Reserva> obtenerReservaPorId(Long id) {
        return reservaRepository.findById(id);
    }

    @Override
    public List<Reserva> listarReservas() {
        return reservaRepository.findAll();
    }

    @Override
    public List<Reserva> listarReservasPorUsuario(Long usuarioId) {
        return reservaRepository.findByUsuarioId(usuarioId);
    }

    @Override
    public List<Reserva> listarReservasPorServicio(Long servicioId) {
        return reservaRepository.findByServicioId(servicioId);
    }

    @Override
    @Transactional
    public void cancelarReserva(Long id) {
        Reserva reserva = reservaRepository.findById(id)
                .orElse(null);

        if (reserva == null) {
            throw new EntityNotFoundException("Reserva no encontrada con ID: " + id);
        }

        reservaRepository.delete(reserva);

        // ✅ Registrar en el historial
        historialReservaService.registrarAccion(reserva, "CANCELADA");
    }


}
