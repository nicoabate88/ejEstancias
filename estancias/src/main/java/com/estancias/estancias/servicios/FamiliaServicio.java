
package com.estancias.estancias.servicios;

import com.estancias.estancias.entidades.Familia;
import com.estancias.estancias.repositorios.FamiliaRepositorio;
import java.util.Date;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class FamiliaServicio {
    
    @Autowired
    private FamiliaRepositorio familiaRepositorio;
    
    @Transactional
    public void crearFamilia(String alias, String nombre, String email, Integer edadMin, Integer edadMax,
            Integer numHijos, String clave, String clave2){
        
        Familia familia = new Familia();
        
        familia.setAlias(alias);
        familia.setNombre(nombre);
        familia.setEmail(email);
        familia.setEdadMin(edadMin);
        familia.setEdadMax(edadMax);
        familia.setNumHijos(numHijos);
        familia.setFechaAlta(new Date());
        familia.setClave(new BCryptPasswordEncoder().encode(clave));
        
        familiaRepositorio.save(familia);
        
    }
}
