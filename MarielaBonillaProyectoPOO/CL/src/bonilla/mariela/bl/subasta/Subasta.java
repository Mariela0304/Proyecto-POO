/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package bonilla.mariela.bl.subasta;

import bonilla.mariela.bl.item.Item;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;


/**
 * Clase que va a representar el objeto Subasta y sus atributos
 * @author Mariela Bonilla
 * @version 1.0
 */
public class Subasta {
    private int id;
    private LocalDate fechaInicio;
    private LocalDate fechaVencimiento;
    private String tiempoFaltante;
    private String nomVendedor;
    private String identificacionVendedor;
    private double precioMinimo;
    private int idColeccionista = 0;
    private ArrayList<Item> items_subastados;
    private int cantidad_items;
    private String estado;
    private String detalleAdjudicacion;

    /**
     * Constructor por defecto de Subasta
     * @author Mariela Bonilla
     * @version 1.0
     */
    public Subasta() {
    }


    /**
     * Constructor de Subasta con perímetros
     * Este constructor fue utilizado para poder listas las adjudicacion
     * @author Mariela Bonilla
     * @param detalleAdjudicacion : Representa un pequeño mensaje u aviso
     * @param idSubasta : Identificador de la subasta
     * @version 1.0
     */
    public Subasta(String detalleAdjudicacion, int idSubasta) {
        this.id = idSubasta;
        this.detalleAdjudicacion = detalleAdjudicacion;
    }


    /**
     * Constructor de Subasta con perímetros
     * @author Mariela Bonilla
     * @param fechaInicio : Fecha de inicio de la subasta
     * @param fechaVencimiento : Fecha de vencimiento de la subasta
     * @param nomVendedor : Nombre de quién hace la subasta
     * @param identificacionVendedor : Identificación de quien hace la subasta
     * @param precioMinimo : Precio mínimo de la subasta
     * @param cantidad_items : Cantidad de ítems subastados
     * @version 1.0
     */
    public Subasta(LocalDate fechaInicio, LocalDate fechaVencimiento, String nomVendedor, String identificacionVendedor,
                   double precioMinimo, int cantidad_items) {
        items_subastados = new ArrayList<>();
        this.fechaInicio = fechaInicio;
        this.fechaVencimiento = fechaVencimiento;
        this.nomVendedor = nomVendedor;
        this.identificacionVendedor = identificacionVendedor;
        this.precioMinimo = precioMinimo;
        this.cantidad_items = cantidad_items;
    }


    /**
     * Constructor de Subasta con perímetros
     * @author Mariela Bonilla
     * @param id : Identificador de la subasta
     * @param fechaInicio : Fecha de inicio de la subasta
     * @param fechaVencimiento : Fecha de vencimiento de la subasta
     * @param nomVendedor : Nombre de quién hace la subasta
     * @param identificacionVendedor : Identificación de quien hace la subasta
     * @param precioMinimo : Precio mínimo de la subasta
     * @param cantidad_items : Cantidad de ítems subastados
     * @version 1.0
     */
    public Subasta(int id, LocalDate fechaInicio, LocalDate fechaVencimiento, String nomVendedor, String identificacionVendedor,
                   double precioMinimo, int cantidad_items) {
        items_subastados = new ArrayList<>();
        this.id = id;
        this.fechaInicio = fechaInicio;
        this.fechaVencimiento = fechaVencimiento;
        this.nomVendedor = nomVendedor;
        this.identificacionVendedor = identificacionVendedor;
        this.precioMinimo = precioMinimo;
        this.cantidad_items = cantidad_items;
    }


    /**
     * Constructor de Subasta con perímetros
     * @author Mariela Bonilla
     * @param fechaInicio : Fecha de inicio de la subasta
     * @param fechaVencimiento : Fecha de vencimiento de la subasta
     * @param nomVendedor : Nombre de quién hace la subasta
     * @param identificacionVendedor : Identificación de quien hace la subasta
     * @param idColeccionista : Identificador del coleccionista
     * @param precioMinimo : Precio mínimo de la subasta
     * @param cantidad_items : Cantidad de ítems subastados
     * @version 1.0
     */
    public Subasta(LocalDate fechaInicio, LocalDate fechaVencimiento, String nomVendedor, String identificacionVendedor,
                   double precioMinimo, int idColeccionista, int cantidad_items ) {
        items_subastados = new ArrayList<>();
        this.fechaInicio = fechaInicio;
        this.fechaVencimiento = fechaVencimiento;
        this.nomVendedor = nomVendedor;
        this.precioMinimo = precioMinimo;
        this.idColeccionista = idColeccionista;
        this.cantidad_items = cantidad_items;
    }


    /**
     * Constructor de Subasta con perímetros
     * @author Mariela Bonilla
     * @param  id : Identificador de la subasta
     * @param fechaInicio : Fecha de inicio de la subasta
     * @param fechaVencimiento : Fecha de vencimiento de la subasta
     * @param nomVendedor : Nombre de quién hace la subasta
     * @param precioMinimo : Precio mínimo de la subasta
     * @param cantidad_items : Cantidad de ítems subastados
     * @version 1.0
     */
    public Subasta(int id,LocalDate fechaInicio, LocalDate fechaVencimiento,
                   double precioMinimo, int cantidad_items, String nomVendedor) {
        this.id = id;
        items_subastados = new ArrayList<>();
        this.fechaInicio = fechaInicio;
        this.fechaVencimiento = fechaVencimiento;
        this.nomVendedor = nomVendedor;
        this.precioMinimo = precioMinimo;
        this.cantidad_items = cantidad_items;
    }


    /**
     * Constructor de Subasta con perímetros
     * @author Mariela Bonilla
     * @param id : Identificador de la subasta
     * @param fechaInicio : Fecha de inicio de la subasta
     * @param fechaVencimiento : Fecha de vencimiento de la subasta
     * @param tiempoFaltante : Define el tiempo que falta para que termine la subasta
     * @param nomVendedor : Nombre de quién hace la subasta
     * @param idColeccionista : Identificador del coleccionista
     * @param precioMinimo : Precio mínimo de la subasta
     * @param cantidad_items : Cantidad de ítems subastados
     * @version 1.0
     */
    public Subasta(int id, LocalDate fechaInicio, LocalDate fechaVencimiento, String tiempoFaltante,
                   String nomVendedor, int idColeccionista, int cantidad_items, double precioMinimo) {
        items_subastados = new ArrayList<>();
        this.id = id;
        this.fechaInicio = fechaInicio;
        this.fechaVencimiento = fechaVencimiento;
        this.tiempoFaltante = tiempoFaltante;
        this.nomVendedor = nomVendedor;
        this.precioMinimo = precioMinimo;
        this.idColeccionista = idColeccionista;
        this.cantidad_items = cantidad_items;
    }

    /**
     * Constructor de Subasta con perímetros
     * @author Mariela Bonilla
     * @param id : Identificador de la subasta
     * @param fechaInicio : Fecha de inicio de la subasta
     * @param fechaVencimiento : Fecha de vencimiento de la subasta
     * @param nomVendedor : Nombre de quién hace la subasta
     * @param identificacionVendedor : Identificación de quien hace la subasta
     * @param precioMinimo : Precio mínimo de la subasta
     * @param cantidad_items : Cantidad de ítems subastados
     * @param estado : Estado de la subasta
     * @version 1.0
     */
    public Subasta(int id, LocalDate fechaInicio, LocalDate fechaVencimiento,
                   String nomVendedor,String identificacionVendedor, double precioMinimo,
                   int cantidad_items, String estado) {
        this.id = id;
        this.fechaInicio = fechaInicio;
        this.fechaVencimiento = fechaVencimiento;
        this.identificacionVendedor = identificacionVendedor;
        this.nomVendedor = nomVendedor;
        this.precioMinimo = precioMinimo;
        this.cantidad_items = cantidad_items;
        this.estado = estado;
    }

    /*
    Metodos get
     */

    public String getDetalleAdjudicacion() {
        return detalleAdjudicacion;
    }

    public void setDetalleAdjudicacion(String detalleAdjudicacion) {
        this.detalleAdjudicacion = detalleAdjudicacion;
    }

    /**
     * Método get del identificador de la subasta
     * @author Mariela Bonilla
     * @return Retorna del identificador de la subasta
     * @version 1.0
     */
    public int getId() {
        return id;
    }

    /**
     * Método get de la fecha de inicio de la subasta
     * @author Mariela Bonilla
     * @return Retorna la fecha de inicio de la subasta
     * @version 1.0
     */
    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    /**
     * Método get de la fecha de vencimiento de la subasta
     * @author Mariela Bonilla
     * @return Retorna la fecha de vencimiento de la subasta
     * @version 1.0
     */
    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }


    /**
     * Método get del tiempo faltante de la subasta
     * @author Mariela Bonilla
     * @return Retorna el tiempo faltante de la subasta
     * @version 1.0
     */
    public String getTiempoFaltante() {
        return tiempoFaltante;
    }

    /**
     * Método get del nombre del vendedor de la subasta
     * @author Mariela Bonilla
     * @return Retorna el nombre del vendedor de la subasta
     * @version 1.0
     */
    public String getNomVendedor() {
        return nomVendedor;
    }

    /**
     * Método get de la identificación del vendedor de la subasta
     * @author Mariela Bonilla
     * @return Retorna la identificación del vendedor de la subasta
     * @version 1.0
     */
    public String getIdentificacionVendedor() {
        return identificacionVendedor;
    }

    /**
     * Método get del precio mínimo de la subasta
     * @author Mariela Bonilla
     * @return Retorna el precio mínimo del vendedor de la subasta
     * @version 1.0
     */
    public double getPrecioMinimo() {
        return precioMinimo;
    }

    /**
     * Método get del id del coleccionista de la subasta
     * @author Mariela Bonilla
     * @return Retorna el id del coleccionista de la subasta
     * @version 1.0
     */
    public int getIdColeccionista() {
        return idColeccionista;
    }

    /**
     * Método get de los ítems subastasdos de la subasta
     * @author Mariela Bonilla
     * @return Retorna los ítems subastasdos de la subasta
     * @version 1.0
     */
    public ArrayList<Item> getItems_subastados() {
        return items_subastados;
    }

    /**
     * Método get de la cantidad de ítems de la subasta
     * @author Mariela Bonilla
     * @return Retorna la cantidad de ítems de la subasta
     * @version 1.0
     */
    public int getCantidad_items() {
        return cantidad_items;
    }

    /**
     * Método get del estado de la subasta
     * @author Mariela Bonilla
     * @return Retorna el estado de la subasta
     * @version 1.0
     */
    public String getEstado() {
        return estado;
    }

        /*
        Metodos set
        */

    /**
     * Método set del id para la subasta
     * @author Mariela Bonilla
     * @param id : nuevo id para la subasta
     * @version 1.0
     */
    public void setId(int id) {
        this.id = id;
    }


    /**
     * Método set de la fecha de inicio para la subasta
     * @author Mariela Bonilla
     * @param fechaInicio : nueva  fecha de inicio para la subasta
     * @version 1.0
     */
    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    /**
     * Método set de la fecha de vencimiento para la subasta
     * @author Mariela Bonilla
     * @param fechaVencimiento : nueva fecha de vencimiento para la subasta
     * @version 1.0
     */
    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    /**
     * Método set del tiempo faltante para la subasta
     * @author Mariela Bonilla
     * @param tiempoFaltante : nuevo para la subasta
     * @version 1.0
     */
    public void setTiempoFaltante(String tiempoFaltante) {
        this.tiempoFaltante = tiempoFaltante;
    }

    /**
     * Método set del nombre del vendedor para la subasta
     * @author Mariela Bonilla
     * @param nomVendedor : nuevo nombre del vendedor para la subasta
     * @version 1.0
     */
    public void setNomVendedor(String nomVendedor) {
        this.nomVendedor = nomVendedor;
    }

    /**
     * Método set de la identificacion del vendedor para la subasta
     * @author Mariela Bonilla
     * @param identificacionVendedor : nueva identificacion del vendedor para la subasta
     * @version 1.0
     */
    public void setIdentificacionVendedor(String identificacionVendedor) {
        this.identificacionVendedor = identificacionVendedor;
    }

    /**
     * Método set del precio mínimo para para la subasta
     * @author Mariela Bonilla
     * @param precioMinimo : nuevo precio mínimo para la subasta
     * @version 1.0
     */
    public void setPrecioMinimo(double precioMinimo) {
        this.precioMinimo = precioMinimo;
    }

    /**
     * Método set del id del coleccionista para para la subasta
     * @author Mariela Bonilla
     * @param idColeccionista : nuevo id del coleccionista para la subasta
     * @version 1.0
     */
    public void setIdColeccionista(int idColeccionista) {
        this.idColeccionista = idColeccionista;
    }


    /**
     * Método set de items para la subasta
     * @author Mariela Bonilla
     * @param items_subastado : nuevos items para la subasta
     * @version 1.0
     */
    public void setItems_subastados(Item items_subastado) {
        items_subastados.add(items_subastado);
    }

    /**
     * Método set de cantidad de ítems para la subasta
     * @author Mariela Bonilla
     * @param cantidad_items : nueva cantidad de ítems para la subasta
     * @version 1.0
     */
    public void setCantidad_items(int cantidad_items) {
        this.cantidad_items = cantidad_items;
    }

    /**
     * Método set de estado para la subasta
     * @author Mariela Bonilla
     * @param estado : nuevo estado para la subasta
     * @version 1.0
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }


    /**
     * Método toString() de la clase Subasta
     * @author Mariela Bonilla
     * @return
     * @version 1.0
     */
    @Override
    public String toString() {
        return "-------- SUBASTA: ----------------------------------\n" +
                "\n Id: " + id +
                "\n Fecha de vencimiento: " + fechaVencimiento +
                "\n Tiempo restante:" + tiempoFaltante +
                "\n Nombre del vendedor: " + nomVendedor +
                "\n Identificación del vendedor: " + identificacionVendedor +
                "\n Precio mínimo de ítems: " + precioMinimo +
                "\n Esta subasta tiene " + cantidad_items + " ítems subastados" +
                "\n Si este usuario es coleccionista, se mostrará su identificador\n" +
                "a continuación: " + idColeccionista +
                "\n\n------------------------------------------------------\n\n";
    }

}
