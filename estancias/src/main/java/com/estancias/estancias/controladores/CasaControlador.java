
package com.estancias.estancias.controladores;

import com.estancias.estancias.entidades.Casa;
import com.estancias.estancias.entidades.Usuario;
import com.estancias.estancias.servicios.CasaServicio;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/casa")
public class CasaControlador {
    
    @Autowired
    private CasaServicio casaServicio;
    
    @GetMapping("/registro")
    public String registro(HttpSession session, ModelMap modelo){
        
        Usuario logueado =  (Usuario) session.getAttribute("usuariosession"); //recibo el usuario logueado desde usuarioServicio
                
        modelo.addAttribute("usuario", logueado); //paso el usuario logueado al html
        
        return "registro_casa.html";
    }
    
    @PostMapping("/registrar")
    public String registrar(@RequestParam String calle,@RequestParam Integer numero, @RequestParam String codPostal, 
    @RequestParam String ciudad, @RequestParam String pais, @RequestParam String fechaDesde, @RequestParam String fechaHasta,
    @RequestParam Integer minDias, @RequestParam Integer maxDias, @RequestParam Double precio, @RequestParam String tipoVivienda, 
    @RequestParam Integer huespedes, @RequestParam String obs, @RequestParam() MultipartFile imagen, @RequestParam Long idFamilia) throws ParseException{
        
         if (!imagen.isEmpty()) {
            Path directorioImagenes = Paths.get("src//main//resources//static/images");
            String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();
            try {
                byte[] bytesImg = imagen.getBytes();
                Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + imagen.getOriginalFilename());
                Files.write(rutaCompleta, bytesImg);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        casaServicio.crearCasa(calle, numero, codPostal, ciudad, pais, fechaDesde, fechaHasta, minDias, maxDias, precio, tipoVivienda, huespedes, obs, imagen, idFamilia);
     
        return "registro_casa.html";
    }
    
    @GetMapping("/lista")
    public String listar(ModelMap modelo){
        
        ArrayList<Casa> listaCasa = new ArrayList();
        
        listaCasa = casaServicio.listarCasas();
        
        modelo.addAttribute("lista", listaCasa);
        
        return "inicio.html";
    }
    
    @GetMapping("/mostrar/{id}") //muestra la casa segun ID para luego poder reservar
    public String mostrar(@PathVariable Long id, ModelMap modelo){
    
        modelo.put("casa", casaServicio.mostrarCasa(id));
        
        return "mostrar_casa.html";
    }
    
    @GetMapping("/mostrarCasas/{id}") //muestra listado de casas segun id de familia
    public String mostrarCasas(@PathVariable Long id, ModelMap modelo){
        
        ArrayList<Casa> listaCasas = new ArrayList();
        
        listaCasas = casaServicio.mostrarCasas(id);
        
        modelo.addAttribute("lista", listaCasas);
        
        return "mostrar_casas.html";
    }
    
    @GetMapping("/modifica/{id}")
    public String modifica(@PathVariable Long id, ModelMap modelo){
        
       modelo.put("casa", casaServicio.mostrarCasa(id));
        
        return "modificar_casa.html";
    }
    
    @PostMapping("/modificar/{id}")
    public String modificar(@RequestParam Long id, @RequestParam String calle, @RequestParam Integer numero, @RequestParam String codPostal, 
    @RequestParam String ciudad, @RequestParam String pais, @RequestParam String fechaDesde, @RequestParam String fechaHasta,
    @RequestParam Integer minDias, @RequestParam Integer maxDias, @RequestParam Double precio, @RequestParam String tipoVivienda, 
    @RequestParam Integer huespedes, @RequestParam String obs) throws ParseException{
        
        casaServicio.modificar(id, calle, numero, codPostal, ciudad, pais, fechaDesde, fechaHasta, minDias, maxDias, precio, tipoVivienda, huespedes, obs);
        
        return "mostrar_casas.html";
    }
    
}
