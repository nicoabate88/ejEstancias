
package com.estancias.estancias.repositorios;

import com.estancias.estancias.entidades.Reserva;
import java.util.ArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaRepositorio extends JpaRepository<Reserva, Long>{
    
    @Query("SELECT r FROM Reserva r WHERE cliente_id = :id")
    public ArrayList<Reserva> buscarReservas(@Param("id") Long id);
    
}
