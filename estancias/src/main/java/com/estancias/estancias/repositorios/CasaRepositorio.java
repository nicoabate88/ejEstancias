
package com.estancias.estancias.repositorios;

import com.estancias.estancias.entidades.Casa;
import java.util.ArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CasaRepositorio extends JpaRepository<Casa, Long> {
    
    @Query("SELECT c FROM Casa c WHERE familia_id = :id")
    public ArrayList<Casa> buscarCasas(@Param("id") Long id);
}
