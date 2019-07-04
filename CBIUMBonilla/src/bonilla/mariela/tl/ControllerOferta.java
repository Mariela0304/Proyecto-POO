package bonilla.mariela.tl;

import bonilla.mariela.bl.dao.DaoFactory;
import bonilla.mariela.bl.oferta.IOfertaDao;
import bonilla.mariela.bl.oferta.Oferta;
import bonilla.mariela.bl.subasta.ISubastaDao;
import bonilla.mariela.bl.subasta.InfoSubasta;
import bonilla.mariela.bl.subasta.Subasta;
import bonilla.mariela.bl.usuario.UsuarioIniciado;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.TreeMap;

public class ControllerOferta extends Controller {

    public ControllerOferta() {
    }

    public void registrarOferta(Double oferta) {
        UsuarioIniciado tmpUsuario = new UsuarioIniciado();
        ControllerInfoSubasta gestorInfoSubasta = new ControllerInfoSubasta();

        String [] datosSubasta = gestorInfoSubasta.obtenerDatosSubasta();

        try {
            DaoFactory factory = DaoFactory.getDaoFactory(DaoFactory.SQLSERVER);
            IOfertaDao dao = factory.getOfertaDao();
            dao.registrarOferta(Integer.parseInt(datosSubasta[0]),tmpUsuario.getIdentificacion(),oferta );


        }catch (Exception e) {

        }

    }


    public ArrayList<Oferta> listarOfertasSubasta() {
        InfoSubasta tmpSubasta = new InfoSubasta();
        ArrayList<Oferta> listaOfertas = new ArrayList<>();
        try {
            DaoFactory factory = DaoFactory.getDaoFactory(DaoFactory.SQLSERVER);
            IOfertaDao dao = factory.getOfertaDao();
            for (Oferta dato : dao.listarOfertasSubasta(InfoSubasta.getId())) {
                listaOfertas.add(dato);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return listaOfertas;
    }

    public boolean validarOferente() {
        UsuarioIniciado tmpUsuario = new UsuarioIniciado();
        ControllerInfoSubasta gestorInfoSubasta = new ControllerInfoSubasta();
        boolean subasta_ofertada = false;
        String [] datosSubasta = gestorInfoSubasta.obtenerDatosSubasta();

        try {
            DaoFactory factory = DaoFactory.getDaoFactory(DaoFactory.SQLSERVER);
            IOfertaDao dao = factory.getOfertaDao();
            subasta_ofertada = dao.validarOfertaColeccionista(tmpUsuario.getIdentificacion(), InfoSubasta.getId());


        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return subasta_ofertada;
    }




    public String[] obtenerMejorOferta() {
        String [] mejorOferta = new String[6];
        try {
            DaoFactory factory = DaoFactory.getDaoFactory(DaoFactory.SQLSERVER);
            IOfertaDao dao = factory.getOfertaDao();
            mejorOferta = dao.obtenerMejorOferta(InfoSubasta.getId());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return mejorOferta;
    }
}
