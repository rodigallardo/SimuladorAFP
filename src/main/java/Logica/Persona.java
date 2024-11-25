/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import Logica.Exceptions.PersonaException;

/**
 *
 * @author admin
 */

public class Persona {

    private String nombre;
    private Integer edad;
    private Integer monto_en_cuenta;
    private Boolean tiene_enfermedad_base;
    private Boolean esta_trabajando;
    private Boolean es_jubilado;

    public Persona() {
    }

    public Persona(String nombre, Integer edad, Integer monto_en_cuenta, Boolean tiene_enfermedad_base, Boolean esta_trabajando, Boolean es_jubilado) {
        this.nombre = nombre;
        this.edad = edad;
        this.monto_en_cuenta = monto_en_cuenta;
        this.tiene_enfermedad_base = tiene_enfermedad_base;
        this.esta_trabajando = esta_trabajando;
        this.es_jubilado = es_jubilado;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) throws PersonaException {
        // Si el nombre está vacío, lanza una PersonaException
        //.trim elimina espacios en blanco al inicio y final, .isEmpty devuelve true si la cadena esta vacia
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new PersonaException("El nombre no puede estar vacío.");
        }else if (nombre.matches(".*\\d+.*")){ //Verifica si hay al menos un dígito en la cadena
            throw new PersonaException("El nombre no puede contener números");
        } else {
            // Si pasa las validaciones, asigna el nombre
            this.nombre = nombre;
        }
    }
    
    public Integer getEdad() {
        return edad;
    }
    public void setEdad(Integer edad) throws PersonaException {// lanzar la excepción con el mensaje correspondiente
        if (edad < 0) {
            throw new PersonaException("La edad debe ser MAYOR A 0");
        } else if (edad > 150) {
            throw new PersonaException("La edad debe ser MENOR A 150");
        } else {
            this.edad = edad;
        }

    }

    public Integer getMonto_en_cuenta() {
        return monto_en_cuenta;
    }
    public void setMonto_en_cuenta(Integer monto_en_cuenta) throws PersonaException {
         if (monto_en_cuenta<0){
             throw new PersonaException ("El monto en cuenta debe ser igual o mayor a 0");
         }
         else{
         this.monto_en_cuenta = monto_en_cuenta;
         }
    }

    public Boolean getTiene_enfermedad_base() {
        return tiene_enfermedad_base;
    }
    public void setTiene_enfermedad_base(Boolean tiene_enfermedad_base) {
        this.tiene_enfermedad_base = tiene_enfermedad_base;
    }

    public Boolean getEsta_trabajando() {
        return esta_trabajando;
    }
    public void setEsta_trabajando(Boolean esta_trabajando) {
        this.esta_trabajando = esta_trabajando;
    }

    public Boolean getEs_jubilado() {
        return es_jubilado;
    }
    public void setEs_jubilado(Boolean es_jubilado) {
        this.es_jubilado = es_jubilado;
    }

    @Override
    public String toString() {
        return "Persona{" + "nombre=" + nombre + ", edad=" + edad + ", monto_en_cuenta=" + monto_en_cuenta + ", tiene_enfermedad_base=" + tiene_enfermedad_base + ", esta_trabajando=" + esta_trabajando + ", es_jubilado=" + es_jubilado + '}';
    }
    
}
