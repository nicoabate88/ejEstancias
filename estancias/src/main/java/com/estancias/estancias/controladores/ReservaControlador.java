
package com.estancias.estancias.controladores;

import com.estancias.estancias.entidades.Casa;
import com.estancias.estancias.entidades.Reserva;
import com.estancias.estancias.entidades.Usuario;
import com.estancias.estancias.excepciones.MiException;
import com.estancias.estancias.servicios.CasaServicio;
import com.estancias.estancias.servicios.ReservaServicio;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/reserva")
public class ReservaControlador {
    
    @Autowired
    private ReservaServicio reservaServicio;
    @Autowired
    private CasaServicio casaServicio;
    
    @PreAuthorize("hasAnyRole('ROLE_Admin', 'ROLE_Cliente')")
    @GetMapping("/registro/{id}")
    public String registro(@PathVariable Long id, HttpSession session, ModelMap modelo){
        
        modelo.put("casa", casaServicio.mostrarCasa(id));
        
        Usuario logueado =  (Usuario) session.getAttribute("usuariosession");
        modelo.addAttribute("usuario", logueado);
                
        return "registro_reserva.html";
    }
    
    @PostMapping("/registrar")
    public String registrar(@RequestParam String fechaDesde, @RequestParam String fechaHasta, @RequestParam Integer huesped,
            @RequestParam Long idCliente, @RequestParam Long idCasa, ModelMap modelo, HttpSession session) throws ParseException, MiException {
            
             Usuario logueado =  (Usuario) session.getAttribute("usuariosession");
             modelo.addAttribute("usuario", logueado);
        try {
            reservaServicio.crearReserva(fechaDesde, fechaHasta, huesped, idCliente, idCasa);
            
            return "mensaje_reserva.html";
        } catch (MiException ex) {
            modelo.put("error", ex.getMessage());
            modelo.put("casa", casaServicio.mostrarCasa(idCasa));
           
            modelo.addAttribute("usuario", logueado);
            return "registro_reserva.html";
        }
    }
    
    @GetMapping("/mostrarReservas/{id}")
    public String mostarReservas(@PathVariable Long id, ModelMap modelo){
        
        ArrayList<Reserva> listaReservas = new ArrayList();
       
        listaReservas = reservaServicio.buscarReservas(id);
        
        modelo.addAttribute("listaR", listaReservas);
        
        ArrayList<Casa> listaCasas = casaServicio.listarCasas();
        modelo.addAttribute("listaC", listaCasas);
        
        return "mostrar_reservas.html";
    }
}
