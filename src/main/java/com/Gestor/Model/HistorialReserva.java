package com.Gestor.Model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "historial_reservas")
public class HistorialReserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "reserva_id", nullable = false)
    @JsonProperty("reserva")
    @JsonBackReference
    private Reserva reserva;

    @Column(name = "accion", nullable = false, length = 50)
    @JsonProperty("accion")
    private String accion; // En lugar de AccionReserva, ahora es String

    @Column(name = "fecha_accion", nullable = false)
    @JsonProperty("fechaAccion")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime fechaAccion = LocalDateTime.now();

    // Constructor vacío
    public HistorialReserva() {}

    // Constructor con parámetros
    public HistorialReserva(Reserva reserva, String accion) {
        this.reserva = reserva;
        this.accion = accion;
        this.fechaAccion = LocalDateTime.now();
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public LocalDateTime getFechaAccion() {
        return fechaAccion;
    }

    public void setFechaAccion(LocalDateTime fechaAccion) {
        this.fechaAccion = fechaAccion;
    }
}
