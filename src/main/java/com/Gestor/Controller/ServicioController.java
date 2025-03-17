package com.Gestor.Controller;

import com.Gestor.Model.Servicio;
import com.Gestor.Model.Usuario;
import com.Gestor.Repository.UsuarioRepository;
import com.Gestor.Service.ServicioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/servicios")
public class ServicioController {
    private final ServicioService servicioService;
    private final UsuarioRepository usuarioRepository;

    public ServicioController(ServicioService servicioService, UsuarioRepository usuarioRepository) {
        this.servicioService = servicioService;
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping("/crear")
    public ResponseEntity<Servicio> crearServicio(@RequestBody Servicio servicio, @RequestParam Long adminId) {
        Servicio nuevoServicio = servicioService.crearServicio(servicio, adminId);
        return new ResponseEntity<>(nuevoServicio, HttpStatus.CREATED);
    }




    @GetMapping("/{id}")
    public ResponseEntity<Servicio> obtenerServicioPorId(@PathVariable Long id) {
        Optional<Servicio> servicio = servicioService.obtenerServicioPorId(id);
        return servicio.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Servicio>> listarServicios() {
        List<Servicio> servicios = servicioService.listarServicios();
        return ResponseEntity.ok(servicios);
    }


    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarServicio(@PathVariable Long id) {
        servicioService.eliminarServicio(id);
        return ResponseEntity.noContent().build();
    }
}
