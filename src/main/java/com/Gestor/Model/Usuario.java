package com.Gestor.Model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name ="users")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("nombre")
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @JsonProperty("email")
    @Column(name = "email", nullable = false, unique = true, length = 150)
    private String email;

    @JsonProperty("contrasena")
    @Column(name = "contrasena", nullable = false, length = 255)
    private String contrasena;

    @JsonProperty("tipoUsuario")
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_usuario", nullable = false, length = 20)
    private TipoUsuario tipoUsuario;

    @JsonIgnore
    @OneToMany(mappedBy = "usuario")
    private List<Reserva> reservas;

    // Constructor vacío
    public Usuario() {
    }

    // Constructor con parámetros
    public Usuario(Long id, String nombre, String email, String contrasena, TipoUsuario tipoUsuario, List<Reserva> reservas) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.contrasena = contrasena;
        this.tipoUsuario = tipoUsuario;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }

    public enum TipoUsuario {
        ADMIN, CLIENTE
    }
}
