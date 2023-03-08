
package com.estancias.estancias.repositorios;

import com.estancias.estancias.entidades.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface AdminRepositorio extends JpaRepository<Admin, Long> {
    
    @Query("SELECT u FROM Usuario u WHERE u.alias = :alias")
    public Admin buscarAlias(@Param("alias")String alias);
    
   
}
