
package com.estancias.estancias.controladores;

import com.estancias.estancias.servicios.ClienteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/cliente")
public class ClienteControlador {
    
    @Autowired
    private ClienteServicio clienteServicio;
    
    @GetMapping("/registro")
    public String registro(){
        
        return "registro_cliente.html";
        
    }
    
    @PostMapping("/registrar")
    public String registrar(@RequestParam String alias, @RequestParam String nombre, @RequestParam String email,
            @RequestParam String calle, @RequestParam Integer numero, @RequestParam String codPostal, 
            @RequestParam String ciudad, @RequestParam String pais, @RequestParam String clave, @RequestParam String clave2){
        
        clienteServicio.crearCliente(alias, nombre, email, calle, numero, codPostal, ciudad, pais, clave, clave2);
        
        return "login.html";
    }
    
}
