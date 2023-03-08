
package com.estancias.estancias.controladores;

import com.estancias.estancias.entidades.Casa;
import com.estancias.estancias.entidades.Usuario;
import com.estancias.estancias.servicios.CasaServicio;
import java.util.ArrayList;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class UsuarioControlador {
    
    @Autowired
    private CasaServicio casaServicio;
    
    @GetMapping("/login")
    public String login(){
        
        return "login.html";
        
    }
    
    //@PreAuthorize("hasAnyRole('ROLE_Admin','ROLE_Familia',ROLE_Cliente)") //solo permite acceder a /inicio estando logueados
    @GetMapping("/inicio")
    public String inicio(HttpSession session, ModelMap modelo){
       
        Usuario logueado =  (Usuario) session.getAttribute("usuariosession"); //recibo el usuario logueado desde usuarioServicio
                
        modelo.addAttribute("usuario", logueado); //paso el usuario logueado al html
         
        ArrayList<Casa> listaCasa = new ArrayList();
        
        listaCasa = casaServicio.listarCasas();
        
        modelo.addAttribute("lista", listaCasa);
        
        
       
        return "inicio.html";
    }
    
}
