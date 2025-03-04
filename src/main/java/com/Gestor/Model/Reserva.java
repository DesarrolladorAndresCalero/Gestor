package com.Gestor.Model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import com.Gestor.Model.EstadoReserva;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "reservas")
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    @JsonProperty("usuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "servicio_id", nullable = false)
    @JsonProperty("servicio")
    private Servicio servicio;

    @Column(name = "fecha_reserva", nullable = false)
    @JsonProperty("fechaReserva")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime fechaReserva;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    @JsonProperty("estado")
    private EstadoReserva estado;

    @OneToMany(mappedBy = "reserva", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonProperty("historial")
    @JsonManagedReference
    private List<HistorialReserva> historial;

    // Constructor vacío
    public Reserva() {
    }

    // Constructor con parámetros
    public Reserva(Long id, Usuario usuario, Servicio servicio, LocalDateTime fechaReserva, EstadoReserva estado, List<HistorialReserva> historial) {
        this.id = id;
        this.usuario = usuario;
        this.servicio = servicio;
        this.fechaReserva = fechaReserva;
        this.estado = estado;
        this.historial = historial;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    public LocalDateTime getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(LocalDateTime fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public EstadoReserva getEstado() {
        return estado;
    }

    public void setEstado(EstadoReserva estado) {
        this.estado = estado;
    }

    public List<HistorialReserva> getHistorial() {
        return historial;
    }

    public void setHistorial(List<HistorialReserva> historial) {
        this.historial = historial;
    }
}
