
package com.estancias.estancias.servicios;

import com.estancias.estancias.entidades.Casa;
import com.estancias.estancias.entidades.Cliente;
import com.estancias.estancias.entidades.Reserva;
import com.estancias.estancias.excepciones.MiException;
import com.estancias.estancias.repositorios.CasaRepositorio;
import com.estancias.estancias.repositorios.ClienteRepositorio;
import com.estancias.estancias.repositorios.ReservaRepositorio;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservaServicio {
    
    @Autowired
    private ReservaRepositorio reservaRepositorio;
    @Autowired
    private ClienteRepositorio clienteRepositorio;
    @Autowired
    private CasaRepositorio casaRepositorio;
    @Autowired
    private CasaServicio casaServicio;
    
    public void crearReserva(String fechaDesde, String fechaHasta, Integer huesped, Long idCliente, Long idCasa) throws ParseException, MiException{
        
        Casa casa = new Casa();
        Optional<Casa> home = casaRepositorio.findById(idCasa);
        if(home.isPresent()){
            casa = home.get();
        }
        
        if(huesped>casa.getHuespedes()){
            throw new MiException("La cantidad de huesped/es ingresado no puede ser mayor que " + casa.getHuespedes());
        }
        
        Date fechaD = convertirFecha(fechaDesde);
        Date fechaH = convertirFecha(fechaHasta);
        
        if(fechaD.before(casa.getFechaDesde())==true){
            throw new MiException("Fecha Desde debe ser desde " +casa.getFechaDesde());
        }
        if(fechaH.after(casa.getFechaHasta())==true){
            throw new MiException("Fecha Hasta desde ser hasta " +casa.getFechaHasta());
        }
        
        Reserva reserva = new Reserva();
        
        Cliente cliente = new Cliente();
        Optional<Cliente> cte = clienteRepositorio.findById(idCliente);
        if(cte.isPresent()){
            cliente = cte.get();
        }
        
        reserva.setFechaDesde(fechaD);
        reserva.setFechaHasta(fechaH);
        reserva.setFechaReserva(new Date());
        reserva.setHuesped(huesped);
        reserva.setCasa(casa);
        reserva.setCliente(cliente);
        
        reservaRepositorio.save(reserva);
    
        casaServicio.casaReservada(idCasa, huesped);
        
    }
    
    public ArrayList<Reserva> buscarReservas(Long id){
        
        ArrayList<Reserva> listaReservas = new ArrayList();
        
        listaReservas = reservaRepositorio.buscarReservas(id);
        
        return listaReservas;
        
    }
    
    public Date convertirFecha(String fecha) throws ParseException { //convierte fecha String a fecha Date
    SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
    return formato.parse(fecha);
    }
    
}
