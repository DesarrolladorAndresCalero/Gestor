package com.Gestor.Model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;

import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "servicios")
public class Servicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("nombre")
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @JsonProperty("descripcion")
    @Column(name = "descripcion", nullable = false, length = 255)
    private String descripcion;

    @JsonProperty("duracion")
    @Column(name = "duracion", nullable = false)
    private int duracion;

    @JsonProperty("precio")
    @Column(name = "precio", nullable = false)
    private double precio;

    @JsonProperty("horaInicio")
    @Column(name = "hora_inicio", nullable = false)
    private LocalTime horaInicio;

    @JsonProperty("horaFin")
    @Column(name = "hora_fin", nullable = false)
    private LocalTime horaFin;

    @JsonIgnore // Evita la serialización de la relación inversa
    @OneToMany(mappedBy = "servicio")
    private List<Reserva> reservas;

    // Constructor vacío
    public Servicio() {
    }

    // Constructor con parámetros
    public Servicio(Long id, String nombre, String descripcion, int duracion, double precio, LocalTime horaInicio, LocalTime horaFin, List<Reserva> reservas) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.duracion = duracion;
        this.precio = precio;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.reservas = reservas;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalTime getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(LocalTime horaFin) {
        this.horaFin = horaFin;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }
}
