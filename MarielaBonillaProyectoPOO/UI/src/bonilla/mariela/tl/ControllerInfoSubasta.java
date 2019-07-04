package bonilla.mariela.tl;

import bonilla.mariela.bl.item.Item;
import bonilla.mariela.bl.subasta.InfoSubasta;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Esta clase controller es la que va a permitir que la información de la
 * subasta actual se guarde y se muestren sus datos
 * @author Mariela Bonilla
 * @version 1.0
 */
public class ControllerInfoSubasta extends Controller {

    /**
     * Constructor por defector de ControllerInfoSubasta
     * @author Mariela Bonilla
     */
    public ControllerInfoSubasta() {
    }

    public void guardarDatos(int id,LocalDate fechaVencimiento,
                             String numVendedor, double precioMinimo,
                             int cantidad_items, String estado) {

        InfoSubasta.setId(id);
        InfoSubasta.setFechaVencimiento(fechaVencimiento);
        InfoSubasta.setNumVendedor(numVendedor);
        InfoSubasta.setPrecioMinimo(precioMinimo);
        InfoSubasta.setCantidad_items(cantidad_items);
        InfoSubasta.setEstado(estado);
    }


    public String [] obtenerDatosSubasta(){

        String[] datosSubasta = new String[6];

        datosSubasta[0] = Integer.toString(InfoSubasta.getId());
        datosSubasta[1] = String.valueOf(InfoSubasta.getFechaVencimiento());
        datosSubasta[2] = InfoSubasta.getNumVendedor();
        datosSubasta[3] = Double.toString(InfoSubasta.getPrecioMinimo());
        datosSubasta[4] = Integer.toString(InfoSubasta.getCantidad_items());
        datosSubasta[5] = InfoSubasta.getEstado();

        return datosSubasta;
    }


}
