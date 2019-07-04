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
import java.time.LocalTime;
import java.util.ArrayList;

public class InfoSubasta {
    private static int id;
    private static LocalDate fechaInicio;
    private static LocalDate fechaVencimiento;
    private static LocalTime tiempoInicio;
    private static LocalTime tiempoFin;
    private static LocalDateTime tiempoFaltante;
    private static String identificacionVendedor;
    private static String nomVendedor;
    private static double puntuacion;
    private static double precioMinimo;
    private static int idColeccionista = 0;
    private static ArrayList<Item> items_subastados;
    private static int cantidad_items;
    private static String estado;
    private static String identificacionGanador;
    private static int num_orden_compra;



    public InfoSubasta() {

    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        InfoSubasta.id = id;
    }

    public static LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public static void setFechaInicio(LocalDate fechaInicio) {
        InfoSubasta.fechaInicio = fechaInicio;
    }

    public static LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public static void setFechaVencimiento(LocalDate fechaVencimiento) {
        InfoSubasta.fechaVencimiento = fechaVencimiento;
    }

    public static LocalTime getTiempoInicio() {
        return tiempoInicio;
    }

    public static void setTiempoInicio(LocalTime tiempoInicio) {
        InfoSubasta.tiempoInicio = tiempoInicio;
    }

    public static LocalTime getTiempoFin() {
        return tiempoFin;
    }

    public static void setTiempoFin(LocalTime tiempoFin) {
        InfoSubasta.tiempoFin = tiempoFin;
    }

    public static LocalDateTime getTiempoFaltante() {
        return tiempoFaltante;
    }

    public static void setTiempoFaltante(LocalDateTime tiempoFaltante) {
        InfoSubasta.tiempoFaltante = tiempoFaltante;
    }

    public static String getNomVendedor() {
        return nomVendedor;
    }


    public static String getIdentificacionGanador() {
        return identificacionGanador;
    }

    public static void setIdentificacionGanador(String identificacionGanador) {
        InfoSubasta.identificacionGanador = identificacionGanador;
    }

    public static void setNomVendedor(String numVendedor) {
        InfoSubasta.nomVendedor = numVendedor;
    }

    public static double getPuntuacion() {
        return puntuacion;
    }

    public static void setPuntuacion(double puntuacion) {
        InfoSubasta.puntuacion = puntuacion;
    }

    public static double getPrecioMinimo() {
        return precioMinimo;
    }

    public static void setPrecioMinimo(double precioMinimo) {
        InfoSubasta.precioMinimo = precioMinimo;
    }

    public static int getIdColeccionista() {
        return idColeccionista;
    }

    public static void setIdColeccionista(int idColeccionista) {
        InfoSubasta.idColeccionista = idColeccionista;
    }

    public static ArrayList<Item> getItems_subastados() {
        return items_subastados;
    }

    public static void setItems_subastados(ArrayList<Item> items_subastados) {
        InfoSubasta.items_subastados = items_subastados;
    }

    public static int getCantidad_items() {
        return cantidad_items;
    }

    public static void setCantidad_items(int cantidad_items) {
        InfoSubasta.cantidad_items = cantidad_items;
    }

    public static String getEstado() {
        return estado;
    }

    public static void setEstado(String estado) {
        InfoSubasta.estado = estado;
    }

    public static int getNum_orden_compra() {
        return num_orden_compra;
    }

    public static void setNum_orden_compra(int num_orden_compra) {
        InfoSubasta.num_orden_compra = num_orden_compra;
    }


    public static String getIdentificacionVendedor() {
        return identificacionVendedor;
    }

    public static void setIdentificacionVendedor(String identificacionVendedor) {
        InfoSubasta.identificacionVendedor = identificacionVendedor;
    }



    @Override
    public String toString() {
        return "infoSubasta{}";
    }
}
