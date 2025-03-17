package com.Gestor.Service;

import com.Gestor.Model.Reserva;

import java.util.List;
import java.util.Optional;

public interface ReservaService {
    Reserva crearReserva(Reserva reserva);
    Optional<Reserva> obtenerReservaPorId(Long id);
    List<Reserva> listarReservas();
    List<Reserva> listarReservasPorUsuario(Long usuarioId);
    List<Reserva> listarReservasPorServicio(Long servicioId);
    void cancelarReserva(Long id);
    Reserva guardarReserva(Reserva reserva);
    List<Reserva> listarReservasPorCliente(Long clienteId);
}
