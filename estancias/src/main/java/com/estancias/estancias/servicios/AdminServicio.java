
package com.estancias.estancias.servicios;

import com.estancias.estancias.entidades.Admin;
import com.estancias.estancias.repositorios.AdminRepositorio;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminServicio {
    
    @Autowired
    private AdminRepositorio adminRepositorio;
    
    @Transactional
    public void crearAdmin(String alias, String nombre, String email, String clave, String clave2){
        
        Admin admin = new Admin();
        
        admin.setAlias(alias);
        admin.setNombre(nombre);
        admin.setEmail(email);
        
        admin.setClave(new BCryptPasswordEncoder().encode(clave)); //al setear clave se encripta
        
        adminRepositorio.save(admin);
        
    }

 
}
