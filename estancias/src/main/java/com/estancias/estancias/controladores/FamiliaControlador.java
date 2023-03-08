
package com.estancias.estancias.controladores;

import com.estancias.estancias.servicios.FamiliaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/flia")
public class FamiliaControlador {

    @Autowired
    private FamiliaServicio familiaServicio;
    
@GetMapping("/registro")
public String registro(){
    
    return "registro_familia.html";
}    

@PostMapping("/registrar")
public String registrar(@RequestParam String alias, @RequestParam String nombre, @RequestParam String email,
        @RequestParam Integer edadMin, @RequestParam Integer edadMax, @RequestParam Integer numHijos, 
        @RequestParam String clave, @RequestParam String clave2){
 
        familiaServicio.crearFamilia(alias, nombre, email, edadMin, edadMax, numHijos, clave, clave2);
    
    return "login.html";
}
    
}
