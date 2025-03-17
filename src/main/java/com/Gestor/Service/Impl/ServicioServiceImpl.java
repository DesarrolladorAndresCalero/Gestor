package com.Gestor.Service.Impl;
import com.Gestor.Model.Servicio;
import com.Gestor.Repository.ServicioRepository;
import com.Gestor.Repository.UsuarioRepository;
import com.Gestor.Service.ServicioService;
import com.Gestor.Model.Usuario;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ServicioServiceImpl implements ServicioService {
    private final ServicioRepository servicioRepository;
    private final UsuarioRepository usuarioRepository;

    public ServicioServiceImpl(ServicioRepository servicioRepository, UsuarioRepository usuarioRepository) {
        this.servicioRepository = servicioRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Servicio crearServicio(Servicio servicio, Long adminId) {
        Optional<Usuario> administradorOpt = usuarioRepository.findById(adminId);

        if (administradorOpt.isEmpty()) {
            throw new RuntimeException("Administrador no encontrado con ID: " + adminId);
        }

        Usuario administrador = administradorOpt.get();
        servicio.setAdministrador(administrador); // Asigna el administrador al servicio
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
