package com.estancias.estancias.servicios;

import com.estancias.estancias.entidades.Casa;
import com.estancias.estancias.entidades.Familia;
import com.estancias.estancias.repositorios.CasaRepositorio;
import com.estancias.estancias.repositorios.FamiliaRepositorio;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CasaServicio {
    
    @Autowired
    private CasaRepositorio casaRepositorio;
    @Autowired
    private FamiliaRepositorio familiaRepositorio;
    
    @Transactional
    public void crearCasa(String calle, Integer numero, String codPostal, String ciudad, String pais, String fechaDesde, String fechaHasta,
            Integer minDias, Integer maxDias, Double precio, String tipoVivienda, Integer huespedes, String obs, MultipartFile imagen, Long idFamilia) throws ParseException{
        
        Date fechaD = convertirFecha(fechaDesde); //convirte fecha String a Fecha Date
        Date fechaH = convertirFecha(fechaHasta);
        
        Familia flia = new Familia();
        Optional<Familia> familia = familiaRepositorio.findById(idFamilia);
        if(familia.isPresent()){
            flia = familia.get();
        }
        
        Casa casa = new Casa();
        
        casa.setCalle(calle);
        casa.setNumero(numero);
        casa.setCodPostal(codPostal);
        casa.setCiudad(ciudad);
        casa.setPais(pais);
        casa.setFechaDesde(fechaD);
        casa.setFechaHasta(fechaH);
        casa.setMinDias(minDias);
        casa.setMaxDias(maxDias);
        casa.setPrecio(precio);
        casa.setTipoVivienda(tipoVivienda);
        casa.setHuespedes(huespedes);
        casa.setObservacion(obs);
        casa.setImagen(imagen.getOriginalFilename());
        casa.setFamilia(flia);
        casa.setReserva(false);
        casa.setFechaAlta(new Date());
        
        casaRepositorio.save(casa);
        
    }
    
    public ArrayList<Casa> listarCasas(){
        
        ArrayList<Casa> listaCasa = new ArrayList();
        
        listaCasa = (ArrayList<Casa>) casaRepositorio.findAll();
        
        return listaCasa;
    }
    
    public Casa mostrarCasa(Long id){
        
        return casaRepositorio.getById(id);
        
    }
    
    public void casaReservada(Long id, Integer huesped){ //metodo para modificar boolean y cantidad de huespedes
        
        Optional<Casa> buscarCasa = casaRepositorio.findById(id);
        int huespedes;
        
        if(buscarCasa.isPresent()){
            Casa casa = buscarCasa.get();
            
            huespedes = casa.getHuespedes();
            
            if(huespedes>=huesped){
                casa.setHuespedes(huespedes-huesped);
            } 
            if(huespedes==huesped){
                casa.setReserva(true);
            }
            
            casaRepositorio.save(casa);
        }
    }
    
    public ArrayList<Casa> mostrarCasas(Long id){
        
        ArrayList<Casa> listaCasa = new ArrayList();
        
        listaCasa = casaRepositorio.buscarCasas(id);
        
        return listaCasa;
        
    }
    
    public void modificar(Long id, String calle, Integer numero, String codPostal, String ciudad, String pais, String fechaDesde, 
    String fechaHasta, Integer minDias, Integer maxDias, Double precio, String tipoVivienda, Integer huespedes, 
    String obs) throws ParseException{
        
        Date fechaD = convertirFecha(fechaDesde); //convirte fecha String a Fecha Date
        Date fechaH = convertirFecha(fechaHasta);
        
        Optional<Casa> buscarCasa = casaRepositorio.findById(id);
        
        if(buscarCasa.isPresent()){
            
        Casa casa = buscarCasa.get();
        
        casa.setCalle(calle);
        casa.setNumero(numero);
        casa.setCodPostal(codPostal);
        casa.setCiudad(ciudad);
        casa.setPais(pais);
        casa.setFechaDesde(fechaD);
        casa.setFechaHasta(fechaH);
        casa.setMinDias(minDias);
        casa.setMaxDias(maxDias);
        casa.setPrecio(precio);
        casa.setTipoVivienda(tipoVivienda);
        casa.setHuespedes(huespedes);
        casa.setObservacion(obs);
        
        casaRepositorio.save(casa);
        
        }
    }
       
    public Date convertirFecha(String fecha) throws ParseException { //convierte fecha String a fecha Date
    SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
    return formato.parse(fecha);
   
}
    
}
