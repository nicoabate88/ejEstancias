
package com.estancias.estancias.entidades;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Familia extends Usuario{
    
    private String nombre;
    private Integer edadMin;
    private Integer edadMax;
    private Integer numHijos;

    public Familia() {
    }

    public Familia(String nombre, Integer edadMin, Integer edadMax, Integer numHijos, Long id, String alias, String email, String clave, Date fechaAlta, Date fechaBaja) {
        super(id, alias, email, clave, fechaAlta, fechaBaja);
        this.nombre = nombre;
        this.edadMin = edadMin;
        this.edadMax = edadMax;
        this.numHijos = numHijos;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getEdadMin() {
        return edadMin;
    }

    public void setEdadMin(Integer edadMin) {
        this.edadMin = edadMin;
    }

    public Integer getEdadMax() {
        return edadMax;
    }

    public void setEdadMax(Integer edadMax) {
        this.edadMax = edadMax;
    }

    public Integer getNumHijos() {
        return numHijos;
    }

    public void setNumHijos(Integer numHijos) {
        this.numHijos = numHijos;
    }
    
    
    
}
