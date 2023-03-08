
package com.estancias.estancias.repositorios;

import com.estancias.estancias.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Long>{
    
    @Query("SELECT u FROM Usuario u WHERE u.alias = :alias")
    public Usuario buscarAlias(@Param("alias")String alias);
    
   // @Query("SELECT u.dtype FROM Usuario u WHERE u.alias = :alias")
   // public String tipoUsuario(@Param("alias")String alias);
    
}
