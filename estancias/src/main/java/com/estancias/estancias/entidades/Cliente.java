
package com.estancias.estancias.entidades;


import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Cliente extends Usuario{
    
    private String nombre;
    private String calle;
    private Integer numero;
    private String codPostal;
    private String ciudad;
    private String pais;

    public Cliente() {
    }

    public Cliente(String nombre, String calle, Integer numero, String codPostal, String ciudad, String pais, Long id, String alias, String email, String clave, Date fechaAlta, Date fechaBaja) {
        super(id, alias, email, clave, fechaAlta, fechaBaja);
        this.nombre = nombre;
        this.calle = calle;
        this.numero = numero;
        this.codPostal = codPostal;
        this.ciudad = ciudad;
        this.pais = pais;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getCodPostal() {
        return codPostal;
    }

    public void setCodPostal(String codPostal) {
        this.codPostal = codPostal;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

   
    
    
    

}