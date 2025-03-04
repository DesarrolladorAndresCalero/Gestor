package com.Gestor.Service;

import com.Gestor.Model.HistorialReserva;
import com.Gestor.Model.Reserva;

import java.util.List;

public interface HistorialReservaService {
    HistorialReserva registrarAccion(Reserva reserva, String accion);
    List<HistorialReserva> obtenerHistorialPorReserva(Long reservaId);

    // Nuevo método para obtener todo el historial
    List<HistorialReserva> obtenerTodoElHistorial();
}
