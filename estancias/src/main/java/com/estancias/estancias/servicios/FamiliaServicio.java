
package com.estancias.estancias.servicios;

import com.estancias.estancias.entidades.Familia;
import com.estancias.estancias.excepciones.MiException;
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
            Integer numHijos, String clave, String clave2) throws MiException{
        
        validar(alias, nombre, email, edadMin, edadMax, numHijos, clave, clave2);
        
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
    private void validar(String alias, String nombre, String email, Integer edadMin, Integer edadMax,
            Integer numHijos, String clave, String clave2) throws MiException{
        
        if(alias.isEmpty() || alias==null){
            throw new MiException("El Alias no puede estar vacio");
        }
        if(nombre.isEmpty() || nombre==null){
            throw new MiException("El Nombre no puede estar vacio");
        }
        if(email.isEmpty() || email==null){
            throw new MiException("El Email no puede estar vacio");
        }
          if(edadMin>edadMax){
            throw new MiException("Edad Minima no puede ser mayor que Edad Maxima");
        }
         if(clave.isEmpty() || clave==null){
            throw new MiException("La clave no puede estar vacia");
        }  
          if(!clave.equals(clave2)){
              throw new MiException("Las claves ingresadas deben ser iguales");
          }
        
    }
}
