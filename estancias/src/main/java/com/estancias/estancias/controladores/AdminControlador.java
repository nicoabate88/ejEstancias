
package com.estancias.estancias.controladores;

import com.estancias.estancias.servicios.AdminServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
public class AdminControlador {
    
    @Autowired
    private AdminServicio adminServicio;
    
    @GetMapping("/registro")
    public String registro(){
        
        return "registro_admin.html";
    }
    
    @PostMapping("/registrar")
    public String registrar(@RequestParam String alias, @RequestParam String nombre, 
            @RequestParam String email, @RequestParam String clave, @RequestParam String clave2){
        
        adminServicio.crearAdmin(alias, nombre, email, clave, clave2);
        
        return "registro_admin.html";
    }
    
}
