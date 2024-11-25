/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

/**
 *
 * @author admin
 */
public class Recargo {
    private Integer monto;
    private String descripcion;

    public Recargo() {
    }

    public Recargo(Integer monto, String descripcion) {
        this.monto = monto;
        this.descripcion = descripcion;
    }

    public Integer getMonto() {
        return monto;
    }

    public void setMonto(Integer monto) {
        this.monto = monto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Recargo{" + "monto=" + monto + ", descripcion=" + descripcion + '}';
    }
    
    
}
