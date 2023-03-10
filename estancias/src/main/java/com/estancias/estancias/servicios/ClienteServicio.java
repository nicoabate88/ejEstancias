
package com.estancias.estancias.servicios;

import com.estancias.estancias.entidades.Cliente;
import com.estancias.estancias.repositorios.ClienteRepositorio;
import java.util.Date;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ClienteServicio {
    
    @Autowired
    private ClienteRepositorio clienteRepositorio;
    
    @Transactional
    public void crearCliente(String alias,String nombre, String email, String calle, Integer numero,
            String codPostal, String ciudad, String pais, String clave, String clave2){
        
        Cliente cliente = new Cliente();
        
        cliente.setAlias(alias);
        cliente.setNombre(nombre);
        cliente.setEmail(email);
        cliente.setCalle(calle);
        cliente.setNumero(numero);
        cliente.setCodPostal(codPostal);
        cliente.setCiudad(ciudad);
        cliente.setPais(pais);
        cliente.setFechaAlta(new Date());
        cliente.setClave(new BCryptPasswordEncoder().encode(clave));
                         
        clienteRepositorio.save(cliente);
        
    }
    
  
    
}
