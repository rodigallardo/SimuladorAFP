/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import Logica.Exceptions.SimuladorException;

import java.util.ArrayList;

/**
 *
 * @author admin
 */
public class Simulador {

    private Persona cliente;
    private Integer monto_aporte_voluntario;
    private ArrayList<Recargo> lista_recargos = new ArrayList<Recargo>();
    private ArrayList<Descuento> lista_descuentos = new ArrayList<Descuento>();
    private Double esperanza_de_vida;
    private Double valor_cuota_calculado;

    public Simulador() {
    }

    public Simulador(Persona cliente, Integer monto_aporte_voluntario, ArrayList<Recargo> lista_recargos, ArrayList<Descuento> lista_descuentos, Double esperanza_de_vida, Double valor_cuota_calculado) {
        this.cliente = cliente;
        this.monto_aporte_voluntario = monto_aporte_voluntario;
        this.lista_recargos = lista_recargos;
        this.lista_descuentos = lista_descuentos;
        this.esperanza_de_vida = esperanza_de_vida;
        this.valor_cuota_calculado = valor_cuota_calculado;
    }

    public Persona getCliente() {
        return cliente;
    }

    public void setCliente(Persona cliente) {
        this.cliente = cliente;
    }

    public Integer getMonto_aporte_voluntario() {
        return monto_aporte_voluntario;
    }

    public void setMonto_aporte_voluntario(Integer monto_aporte_voluntario) {
        this.monto_aporte_voluntario = monto_aporte_voluntario;
    }

    public ArrayList<Recargo> getLista_recargos() {
        return lista_recargos;
    }

    public void setLista_recargos(ArrayList<Recargo> lista_recargos) {
        this.lista_recargos = lista_recargos;
    }

    public ArrayList<Descuento> getLista_descuentos() {
        return lista_descuentos;
    }

    public void setLista_descuentos(ArrayList<Descuento> lista_descuentos) {
        this.lista_descuentos = lista_descuentos;
    }

    public Double getEsperanza_de_vida() throws SimuladorException {
        if (cliente.getTiene_enfermedad_base()) {
            esperanza_de_vida = 100 - (cliente.getEdad() * 0.15); // si tiene enfermedad es 15%
        } else {
            esperanza_de_vida = 100 - (cliente.getEdad() * 0.1); //no tiene enfermedad es 10%
        }
        if (esperanza_de_vida <= cliente.getEdad()) { //verificar
            throw new SimuladorException("La esperanza de vida no puede ser menor o igual a la edad actual.");
        }
        return esperanza_de_vida;
    }

    public void setEsperanza_de_vida(Double esperanza_de_vida) {
        this.esperanza_de_vida = esperanza_de_vida;
    }

    public Double getValor_cuota_calculado() {
        return valor_cuota_calculado;
    }

    public void setValor_cuota_calculado(Double valor_cuota_calculado) {
        this.valor_cuota_calculado = valor_cuota_calculado;
    }

    @Override
    public String toString() {
        return "Simulador{" + "cliente=" + cliente + ", monto_aporte_voluntario=" + monto_aporte_voluntario + ", lista_recargos=" + lista_recargos + ", lista_descuentos=" + lista_descuentos + '}';
    }

    //metodos de calculos
    public Double calcularValorCuota() throws SimuladorException {
        Double resp = 0.0;
        Double esperanza_de_vida = 0.0;

        //1.calcular esperanza de vida
        if (cliente.getTiene_enfermedad_base()) {
            esperanza_de_vida = 100 - (cliente.getEdad() * 0.15); // si tiene enfermedad es 15%
        } else {
            esperanza_de_vida = 100 - (cliente.getEdad() * 0.1); //no tiene enfermedad es 10%
        }
        if (esperanza_de_vida <= cliente.getEdad()) { //verificar
            throw new SimuladorException("La esperanza de vida no puede ser menor o igual a la edad actual.");
        }
        //2.calcular valor cuota
        Integer suma_montos = cliente.getMonto_en_cuenta() + this.monto_aporte_voluntario;
        Double valor_anual = suma_montos / (esperanza_de_vida - this.cliente.getEdad());
        Double valor_mensual = valor_anual / 12;

        //3.calcular comision mensual
        Double comision = valor_mensual * 0.05;
        Integer recargos = 0;
        Integer descuentos = 0;

        //4.Ajustes a la comision segun condiciones
        //DESCUENTOS Y RECARGOS
        if (cliente.getEs_jubilado()) {
            descuentos = descuentos + 10;
            Descuento d = new Descuento();
            d.setDescripcion("JUBILADO");
            d.setMonto(10);
            this.lista_descuentos.add(d);
        } else {
            recargos += 10;
            Recargo r = new Recargo();
            r.setDescripcion("JUBILADO");
            r.setMonto(10);
            this.lista_recargos.add(r);
        }
        
        if (cliente.getEsta_trabajando()) {
            recargos += 15;  
            Recargo r = new Recargo();
            r.setDescripcion("TRABAJANDO");
            r.setMonto(15);
            this.lista_recargos.add(r);
        } else {
            descuentos += 15;
            descuentos = descuentos + 15;
            Descuento d = new Descuento();
            d.setDescripcion("TRABAJANDO");
            d.setMonto(15);
            this.lista_descuentos.add(d);
       }
        
        if (cliente.getTiene_enfermedad_base()) {
            descuentos += 10;
            Descuento d = new Descuento();
            d.setDescripcion("ENFERMEDAD");
            d.setMonto(10);
            this.lista_descuentos.add(d);
        } else {
            recargos += 10;
            Recargo r = new Recargo();
            r.setDescripcion("ENFERMEDAD");
            r.setMonto(10);
            this.lista_recargos.add(r);
        }

        //5.Verificar si los descuentos exceden el 25%
        
        if (descuentos > 25){
        throw new SimuladorException("Los descuentos no pueden exceder el 25%");
        }
        Double total_descuento= (comision * ((double)descuentos/100));
        Double total_recargo= (comision * ((double)recargos/100));
        resp= valor_mensual - (total_descuento)+(total_recargo);
        //6.Resultado final
        return resp;
    }

    public Boolean verificarFirmarAcuerdoRecargos() {
        Boolean resp = false;
        Integer recargos = 0;
        if (!cliente.getEs_jubilado()) { // ! = NO
            recargos += 10;
        }
        if (!cliente.getEsta_trabajando()) {
            recargos += 15;
        }
        if (!cliente.getTiene_enfermedad_base()) {
            recargos += 10;
        }
        if (recargos > 15){
        resp = true;
        }
        return resp;
    }

    //metodo add arraylist
    public void addRecargo(Recargo recargo) {
        this.lista_recargos.add(recargo);
    }

    public void addDescuento(Descuento descuento) {
        this.lista_descuentos.add(descuento);
    }

}
