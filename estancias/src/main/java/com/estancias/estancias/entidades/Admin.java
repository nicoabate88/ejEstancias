
package com.estancias.estancias.entidades;

import java.util.Date;
import javax.persistence.Entity;

@Entity
public class Admin extends Usuario{
    
    private String nombre;

    public Admin() {
    }
    
    public Admin(String nombre, Long id, String alias, String email, String clave, Date fechaAlta, Date fechaBaja) {
        super(id, alias, email, clave, fechaAlta, fechaBaja);
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
}
