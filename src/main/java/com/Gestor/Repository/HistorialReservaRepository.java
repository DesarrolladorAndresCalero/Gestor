package com.Gestor.Repository;

import com.Gestor.Model.HistorialReserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistorialReservaRepository extends JpaRepository<HistorialReserva, Long> {
    List<HistorialReserva> findByReservaId(Long reservaId);
}
