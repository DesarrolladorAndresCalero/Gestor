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
        try {
            if (reserva.getUsuario() == null || reserva.getUsuario().getId() == null) {
                throw new RuntimeException("El usuario de la reserva es nulo o no tiene ID");
            }

            if (reserva.getServicio() == null || reserva.getServicio().getId() == null) {
                throw new RuntimeException("El servicio de la reserva es nulo o no tiene ID");
            }

            if (reserva.getFechaReserva() == null) {
                throw new RuntimeException("La fecha de la reserva es nula");
            }

            Usuario usuario = usuarioRepository.findById(reserva.getUsuario().getId())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + reserva.getUsuario().getId()));

            Servicio servicio = servicioRepository.findById(reserva.getServicio().getId())
                    .orElseThrow(() -> new RuntimeException("Servicio no encontrado con ID: " + reserva.getServicio().getId()));

            reserva.setUsuario(usuario);
            reserva.setServicio(servicio);
            Reserva nuevaReserva = reservaRepository.save(reserva);

            // ✅ Registrar en el historial
            historialReservaService.registrarAccion(nuevaReserva, "CREADA");

            return nuevaReserva;

        } catch (Exception e) {
            System.err.println("Error al crear la reserva: " + e.getMessage());
            e.printStackTrace(); // 🔥 Esto imprimirá el error completo en la consola
            throw e;
        }
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


    @Override
    @Transactional
    public Reserva guardarReserva(Reserva reserva) {
        if (reserva.getServicio() == null || reserva.getServicio().getId() == null) {
            throw new RuntimeException("El servicio de la reserva es nulo o no tiene ID");
        }

        if (reserva.getCliente() == null || reserva.getCliente().getId() == null) {
            throw new RuntimeException("El cliente de la reserva es nulo o no tiene ID");
        }

        // 1️⃣ Buscar el servicio y su administrador
        Servicio servicio = servicioRepository.findById(reserva.getServicio().getId())
                .orElseThrow(() -> new RuntimeException("Servicio no encontrado con ID: " + reserva.getServicio().getId()));

        Usuario administrador = servicio.getAdministrador();
        if (administrador == null) {
            throw new RuntimeException("El servicio no tiene un administrador asignado");
        }

        // 2️⃣ Buscar el cliente en la base de datos
        Usuario cliente = usuarioRepository.findById(reserva.getCliente().getId())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + reserva.getCliente().getId()));

        // 3️⃣ Asignar correctamente los valores a la reserva
        reserva.setUsuario(administrador); // ✅ Asigna el ADMINISTRADOR del servicio como usuario de la reserva
        reserva.setCliente(cliente); // ✅ Asigna el CLIENTE que hizo la reserva

        // 4️⃣ Guardar la reserva con los valores correctos
        Reserva nuevaReserva = reservaRepository.save(reserva);

        // ✅ Registrar en el historial
        historialReservaService.registrarAccion(nuevaReserva, "CREADA");

        return nuevaReserva;
    }

    @Override
    public List<Reserva> listarReservasPorCliente(Long clienteId) {
        List<Reserva> reservas = reservaRepository.findByClienteId(clienteId);
        System.out.println("✅ Reservas encontradas para el cliente " + clienteId + ": " + reservas.size());
        return reservas;
    }

}
