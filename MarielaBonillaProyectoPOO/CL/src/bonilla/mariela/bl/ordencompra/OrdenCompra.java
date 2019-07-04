package bonilla.mariela.bl.ordencompra;

import java.time.LocalDate;
import java.util.Arrays;

/**
 * Esta clase va a representar el objeto de Orden de compra y sus atributos
 * @author Mariela Bonilla
 * @version 1.0
 */
public class OrdenCompra {
    private String nomGanador;
    private LocalDate fechaOrden;
    private String [] detalleItems;
    private double precioTotal;


    /**
     * Constructor por defecto de OrdenCompra
     * @author Mariela Bonilla
     * @version 1.0
     */
    public OrdenCompra() {

    }


    /**
     * Constructor de OrdenCompra con parámetros
     * @author Mariela Bonilla
     * @param nomGanador : Nombre del ganador
     * @param fechaOrden : Fecha de generada la órden
     * @param detalleItems : Lista de los detalles de los ítems
     * @param precioTotal : Precio a pagar
     * @version 1.0
     */
    public OrdenCompra(String nomGanador, LocalDate fechaOrden, String[] detalleItems, double precioTotal) {
        this.nomGanador = nomGanador;
        this.fechaOrden = fechaOrden;
        this.detalleItems = detalleItems;
        this.precioTotal = precioTotal;
    }


    /**
     * Método get del nombre del ganador
     * @author Mariela Bonilla
     * @return Retorna el nombre del ganador
     * @version 1.0
     */
    public String getNomGanador() {
        return nomGanador;
    }

    /**
     * Método set del nombre del ganador
     * @author Mariela Bonilla
     * @param nomGanador : nuevo nombre del ganador
     * @version 1.0
     */
    public void setNomGanador(String nomGanador) {
        this.nomGanador = nomGanador;
    }

    /**
     * Método get de la fecha de la orden
     * @author Mariela Bonilla
     * @return Retorna la fecha de la orden
     * @version 1.0
     */
    public LocalDate getFechaOrden() {
        return fechaOrden;
    }

    /**
     * Método set de la fecha de la orden
     * @author Mariela Bonilla
     * @param fechaOrden : nueva fecha de la orden
     * @version 1.0
     */
    public void setFechaOrden(LocalDate fechaOrden) {
        this.fechaOrden = fechaOrden;
    }

    /**
     * Método get de los detalles de los ítems
     * @author Mariela Bonilla
     * @return Retorna los detalles de los ítems
     * @version 1.0
     */
    public String[] getDetalleItems() {
        return detalleItems;
    }


    /**
     * Método set de los detalles de los ítems
     * @author Mariela Bonilla
     * @param detalleItems : nuevos detalles de los ítems
     * @version 1.0
     */
    public void setDetalleItems(String[] detalleItems) {
        this.detalleItems = detalleItems;
    }

    /**
     * Método get del precio total
     * @author Mariela Bonilla
     * @return Retorna el precio total
     * @version 1.0
     */
    public double getPrecioTotal() {
        return precioTotal;
    }


    /**
     * Método set del precio total
     * @author Mariela Bonilla
     * @param precioTotal : nuevo precio total
     * @version 1.0
     */
    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }


    /**
     * Método toString() de la clase OrdenCompra
     * @author Mariela Bonilla
     * @return
     * @version 1.0
     */
    @Override
    public String toString() {
        return "------- ORDEN DE COMPRA: --------------------------\n" +
                "\n Nombre de ganador: " + nomGanador +
                "\n Fecha de orden de compra: " + fechaOrden +
                "\n Detalle de ítems: " + Arrays.toString(detalleItems) +
                "\n Precio total de compra: " + precioTotal +
                "\n\n---------------------------------------------------\n\n";
    }
}
