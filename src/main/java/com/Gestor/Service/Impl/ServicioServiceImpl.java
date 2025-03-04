package com.Gestor.Service.Impl;
import com.Gestor.Model.Servicio;
import com.Gestor.Repository.ServicioRepository;
import com.Gestor.Service.ServicioService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ServicioServiceImpl implements ServicioService {
    private final ServicioRepository servicioRepository;

    public ServicioServiceImpl(ServicioRepository servicioRepository) {
        this.servicioRepository = servicioRepository;
    }

    @Override
    public Servicio crearServicio(Servicio servicio) {
        return servicioRepository.save(servicio);
    }

    @Override
    public Optional<Servicio> obtenerServicioPorId(Long id) {
        return servicioRepository.findById(id);
    }

    @Override
    public List<Servicio> listarServicios() {
        return servicioRepository.findAll();
    }

    @Override
    public void eliminarServicio(Long id) {
        servicioRepository.deleteById(id);
    }
}
