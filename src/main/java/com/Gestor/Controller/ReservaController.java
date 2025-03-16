package com.Gestor.Controller;
import com.Gestor.Model.Reserva;
import com.Gestor.Model.Servicio;
import com.Gestor.Model.Usuario;
import com.Gestor.Service.ReservaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/reservas")
public class ReservaController {

    private final ReservaService reservaService;

    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    // Crear una nueva reserva
    @PostMapping("/crear")
    public ResponseEntity<Reserva> crearReserva(@RequestBody Reserva reserva) {
        Reserva nuevaReserva = reservaService.crearReserva(reserva);
        return ResponseEntity.ok(nuevaReserva);
    }

    // Obtener una reserva por ID
    @GetMapping("/{id}")
    public ResponseEntity<Reserva> obtenerReservaPorId(@PathVariable Long id) {
        Optional<Reserva> reserva = reservaService.obtenerReservaPorId(id);
        return reserva.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Listar todas las reservas
    @GetMapping
    public ResponseEntity<List<Reserva>> listarReservas() {
        List<Reserva> reservas = reservaService.listarReservas();
        return ResponseEntity.ok(reservas);
    }

    // Listar reservas de un usuario
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Reserva>> listarReservasPorUsuario(@PathVariable Long usuarioId) {
        List<Reserva> reservas = reservaService.listarReservasPorUsuario(usuarioId);
        return ResponseEntity.ok(reservas);
    }

    // Listar reservas de un servicio
    @GetMapping("/servicio/{servicioId}")
    public ResponseEntity<List<Reserva>> listarReservasPorServicio(@PathVariable Long servicioId) {
        List<Reserva> reservas = reservaService.listarReservasPorServicio(servicioId);
        return ResponseEntity.ok(reservas);
    }

    @DeleteMapping("/cancelar/{id}")
    public ResponseEntity<Map<String, String>> cancelarReserva(@PathVariable Long id) {
        reservaService.cancelarReserva(id);
        Map<String, String> response = new HashMap<>();
        response.put("mensaje", "La reserva ha sido cancelada exitosamente.");
        return ResponseEntity.ok(response);
    }

}
