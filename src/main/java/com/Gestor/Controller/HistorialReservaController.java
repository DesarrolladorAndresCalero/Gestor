package com.Gestor.Controller;

import com.Gestor.Model.HistorialReserva;
import com.Gestor.Service.HistorialReservaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/historial")
public class HistorialReservaController {

    private final HistorialReservaService historialReservaService;
    private static final Logger logger = LoggerFactory.getLogger(HistorialReservaController.class);

    public HistorialReservaController(HistorialReservaService historialReservaService) {
        this.historialReservaService = historialReservaService;
    }

    // Obtener historial por ID de reserva
    @GetMapping("/reserva/{reservaId}")
    public ResponseEntity<List<HistorialReserva>> obtenerHistorialPorReserva(@PathVariable Long reservaId) {
        logger.info("Consultando historial de la reserva con ID: {}", reservaId);
        List<HistorialReserva> historial = historialReservaService.obtenerHistorialPorReserva(reservaId);

        if (historial.isEmpty()) {
            logger.warn("No se encontró historial para la reserva con ID: {}", reservaId);
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(historial);
    }

    // Obtener todo el historial de reservas
    @GetMapping
    public ResponseEntity<List<HistorialReserva>> obtenerTodoElHistorial() {
        logger.info("Consultando todo el historial de reservas");
        List<HistorialReserva> historial = historialReservaService.obtenerTodoElHistorial();

        if (historial.isEmpty()) {
            logger.warn("No se encontraron registros en el historial de reservas");
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(historial);
    }
}
