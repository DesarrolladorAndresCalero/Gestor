package com.Gestor.Service.Impl;

import com.Gestor.Model.HistorialReserva;
import com.Gestor.Model.Reserva;
import com.Gestor.Repository.HistorialReservaRepository;
import com.Gestor.Service.HistorialReservaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class HistorialReservaServiceImpl implements HistorialReservaService {

    private final HistorialReservaRepository historialReservaRepository;

    public HistorialReservaServiceImpl(HistorialReservaRepository historialReservaRepository) {
        this.historialReservaRepository = historialReservaRepository;
    }

    @Override
    @Transactional
    public HistorialReserva registrarAccion(Reserva reserva, String accion) {
        HistorialReserva historial = new HistorialReserva(reserva, accion);
        return historialReservaRepository.save(historial);
    }

    @Override
    public List<HistorialReserva> obtenerHistorialPorReserva(Long reservaId) {
        return historialReservaRepository.findByReservaId(reservaId);
    }

    @Override
    public List<HistorialReserva> obtenerTodoElHistorial() {
        return historialReservaRepository.findAll();
    }
}
