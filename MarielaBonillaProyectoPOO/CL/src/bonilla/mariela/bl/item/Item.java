package bonilla.mariela.bl.item;

import java.time.LocalDate;

/**
 * Esta clase va a representar al objeto Item, además de contener todos los atributos
 * de un ítem
 * @author Mariela Bonilla
 * @version 1.0
 */
public class Item {
    private int id;
    private String nombre;
    private String descripcion;
    private String estado;
    private String antiguedad;
    private LocalDate fechaCompra;
    private int annoAntiguedad;
    private int mesAntiguedad;
    private int diaAntiguedad;

    /**
     * Contructor por defecto del objeto Item
     * @author Mariela Bonilla
     * @version 1.0
     */
    public Item() {

    }

    /**
     * Constructor del objeto ítem que recibe parámetros
     * @author Mariela Bonilla
     * @param id : Identificador del ítem
     * @param nombre : nombre del ítem
     * @param descripcion : Descripción del ítem
     * @param estado : Estado del ítem
     * @param fechaCompra : Fecha de cuando el ítem fue comprado por el dueño
     * @version 1.0
     */
    public Item(int id, String nombre, String descripcion, String estado, LocalDate fechaCompra) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estado = estado;
        this.fechaCompra = fechaCompra;
    }

    /**
     * Constructor del objeto ítem que recibe parámetros
     * @author Mariela Bonilla
     * @param nombre : nombre del ítem
     * @param descripcion : Descripción del ítem
     * @param estado : Estado del ítem
     * @param fechaCompra : Fecha de cuando el ítem fue comprado por el dueño
     * @version 1.0
     */
    public Item( String nombre, String descripcion, String estado, LocalDate fechaCompra) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estado = estado;
        this.fechaCompra = fechaCompra;
    }

    /**
     * Constructor del objeto ítem que recibe parámetros
     * @author Mariela Bonilla
     * @param nombre : nombre del ítem
     * @param descripcion : Descripción del ítem
     * @param estado : Estado del ítem
     * @param fechaCompra : Fecha de cuando el ítem fue comprado por el dueño
     * @param anno : Años que tiene de existir desde el día de su compra
     * @param mes : Meses del año que tiene de existir desde el día de su compra
     * @param dia : Días del mes que tiene de existir desde el día de su compra
     * @version 1.0
     */
    public Item(String nombre, String descripcion, String estado, LocalDate fechaCompra, int anno, int mes, int dia) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estado = estado;
        this.fechaCompra = fechaCompra;
        this.annoAntiguedad = anno;
        this.mesAntiguedad = mes;
        this.diaAntiguedad = dia;
    }


    /**
     * Método get del id del ítem
     * @author Mariela Bonilla
     * @return Retorna de id del ítem
     * @version 1.0
     */
    public int getId() {
        return id;
    }


    /**
     * Método get del nombre del ítem
     * @author Mariela Bonilla
     * @return Retorna  el nombre del ítem
     * @version 1.0
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Método get la descripción del ítem
     * @author Mariela Bonilla
     * @return Retorna la descripción del ítem
     * @version 1.0
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Método get el estado del ítem
     * @author Mariela Bonilla
     * @return Retorna el estado del ítem
     * @version 1.0
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Método get de la fecha de compra del ítem
     * @author Mariela Bonilla
     * @return Retorna la fecha de la compra del ítem
     * @version 1.0
     */
    public LocalDate getFechaCompra() {
        return fechaCompra;
    }


    /**
     * Método get de los años de antigüedad que tiene el ítem
     * @author Mariela Bonilla
     * @return Retorna los años de antigüedad del ítem
     * @version 1.0
     */
    public int getAnnoAntiguedad() {
        return annoAntiguedad;
    }


    /**
     * Método get de los meses de antigüedad que tiene el ítem
     * @author Mariela Bonilla
     * @return Retorna los meses del año de antigüedad del ítem
     * @version 1.0
     */
    public int getMesAntiguedad() {
        return mesAntiguedad;
    }

    /**
     * Método get de los días del mes de antigüedad que tiene el ítem
     * @author Mariela Bonilla
     * @return Retorna los días del mes de antigüedad del ítem
     * @version 1.0
     */
    public int getDiaAntiguedad() {
        return diaAntiguedad;
    }

   /*
    Metodos de set
     */

    /**
     * Método set del id del ítem, dónde se va a insertar un nuevo valor al id
     * @author Mariela
     * @param id : Identificador del ítem
     * @version 1.0
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Método set del nombre del ítem, dónde se va a insertar un nuevo valor al nombre
     * @author Mariela
     * @param nombre : Nombre del ítem
     * @version 1.0
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Método set de la descripción del ítem, dónde se va a insertar un nuevo valor a
     * la descripción
     * @author Mariela
     * @param descripcion : Descripción del ítem
     * @version 1.0
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Método set del estado del ítem, dónde se va a insertar un nuevo valor al estado
     * @author Mariela
     * @param estado : Estado del ítem
     * @version 1.0
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }


    /**
     * Método set de la compra del ítem, dónde se va a insertar un nuevo valor a la fecha de
     * la compra
     * @author Mariela
     * @param fechaCompra : Fecha de compra del ítem
     * @version 1.0
     */
    public void setFechaCompra(LocalDate fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    /**
     * Método set del año de antigüedad del ítem, dónde se va a insertar un nuevo valor al año
     * de antigüedad.
     * @author Mariela
     * @param annoAntiguedad : Años de antigüedad del ítem
     * @version 1.0
     */
    public void setAnnoAntiguedad(int annoAntiguedad) {
        this.annoAntiguedad = annoAntiguedad;
    }

    /**
     * Método set del mes de antigüedad del ítem, dónde se va a insertar un nuevo valor al mes
     * de antigüedad.
     * @author Mariela
     * @param mesAntiguedad : Meses de antigüedad del ítem
     * @version 1.0
     */
    public void setMesAntiguedad(int mesAntiguedad) {
        this.mesAntiguedad = mesAntiguedad;
    }


    /**
     * Método set del día de antigüedad del ítem, dónde se va a insertar un nuevo valor al día
     * de antigüedad.
     * @author Mariela
     * @param diaAntiguedad : Días de antigüedad del ítem
     * @version 1.0
     */
    public void setDiaAntiguedad(int diaAntiguedad) {
        this.diaAntiguedad = diaAntiguedad;
    }


    /**
     * Método toString() de la clase Item
     * @author Mariela Bonilla
     * @return
     * @version 1.0
     */
    @Override
    public String toString() {
        return "-------- Item: -------------------------------------------\n" +
                "\n Nombre de ítem: " + nombre +
                "\n Descripción: " + descripcion +
                "\n Estado: " + estado +
                "\n Fecha de compra: " + fechaCompra +
                "\n Años de antigüedad: " + annoAntiguedad +
                "\n Meses de antigüedad: " + mesAntiguedad +
                "\n Días de antigüedad: " + diaAntiguedad +
                "\n\n-------------------------------------------------------------\n\n";
    }
}
