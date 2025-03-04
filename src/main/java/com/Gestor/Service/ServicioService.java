package com.Gestor.Service;
import com.Gestor.Model.Servicio;
import java.util.List;
import java.util.Optional;

public interface ServicioService {
    Servicio crearServicio(Servicio servicio);
    Optional<Servicio> obtenerServicioPorId(Long id);
    List<Servicio> listarServicios();
    void eliminarServicio(Long id);
}