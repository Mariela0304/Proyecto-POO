package bonilla.mariela.tl;

import bonilla.mariela.bl.dao.DaoFactory;
import bonilla.mariela.bl.item.Item;
import bonilla.mariela.bl.ordencompra.IOrdenCompraDao;
import bonilla.mariela.bl.subasta.InfoSubasta;
import bonilla.mariela.bl.usuario.UsuarioIniciado;

import java.time.LocalDate;
import java.util.ArrayList;

public class ControllerOrdenCompra extends Controller {


    public int registrarOrdenCompra(ArrayList<Item> detalleItems) {
        ControllerSubasta gestorSubasta = new ControllerSubasta();
        ControllerOferta gestorOferta = new ControllerOferta();
        UsuarioIniciado login = new UsuarioIniciado();
        String detallesItems = "";
        int num_ordenCompra = 0;
        String[] datosGanador = gestorOferta.obtenerMejorOferta();


        for (Item dato: detalleItems) {
            detallesItems = detallesItems+dato.getNombre()+","+dato.getDescripcion()+"/";
        }

        try {
            DaoFactory factory = DaoFactory.getDaoFactory(DaoFactory.SQLSERVER);
            IOrdenCompraDao dao = factory.getOrdenCompraDao();

            num_ordenCompra = dao.guardarOrdenCompra(login.getIdColeccionista(), LocalDate.now(), detallesItems,
                    Double.parseDouble(datosGanador[1]));


        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return num_ordenCompra;
    }

}
