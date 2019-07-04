package bonilla.mariela.tl;

import bonilla.mariela.bl.dao.DaoFactory;
import bonilla.mariela.bl.item.IItemDao;
import bonilla.mariela.bl.item.Item;
import bonilla.mariela.bl.oferta.IOfertaDao;
import bonilla.mariela.bl.subasta.ISubastaDao;
import bonilla.mariela.bl.subasta.InfoSubasta;
import bonilla.mariela.bl.subasta.Subasta;
import bonilla.mariela.bl.usuario.UsuarioIniciado;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.TreeMap;

public class ControllerSubasta extends Controller {


    //Registra la subasta de un usuario vendedor
    public boolean registrarSubastas(LocalDate fechaVencimiento,
                                  double precioMinimo, ArrayList<Item> items,
                                  int cantidad_items)  {
        UsuarioIniciado login = new UsuarioIniciado();
        boolean registrado = false;
         try {
             DaoFactory factory =  DaoFactory.getDaoFactory(DaoFactory.SQLSERVER);
             ISubastaDao dao = factory.getSubastaDao();

             registrado = dao.registrarSubasta(fechaVencimiento, precioMinimo, items, cantidad_items, login.getIdentificacion());

         }catch (Exception e) {
             System.out.println(e.getMessage());
         }

        return registrado;
    }

    //registra la subasta de un usuario coleccionista
    public boolean registrarSubastas(LocalDate fechaVencimiento,
                                  String numVendedor, double precioMinimo, int coleccionista,
                                  ArrayList<Item> items,
                                  int cantidad_items)  {
        boolean registrado = false;
        try {
            DaoFactory factory =  DaoFactory.getDaoFactory(DaoFactory.SQLSERVER);
            ISubastaDao dao = factory.getSubastaDao();

            registrado = dao.registrarSubasta(fechaVencimiento, precioMinimo, items, cantidad_items, numVendedor, coleccionista);

        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return registrado;
    }


    public TreeMap<String, Subasta> listarSubastasCreadas() {
        UsuarioIniciado tmpUsuario = new UsuarioIniciado();
        TreeMap<String, Subasta> listaSubastas = new TreeMap<>();
        try {
            DaoFactory factory = DaoFactory.getDaoFactory(DaoFactory.SQLSERVER);
            ISubastaDao dao = factory.getSubastaDao();
            for (Subasta dato: dao.listarSubastasUsuario(tmpUsuario.getIdentificacion())) {
                listaSubastas.put(dato.getIdentificacionVendedor(), dato);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return listaSubastas;
    }

    public ArrayList<Subasta> listarSubastas() {
        UsuarioIniciado tmpUsuario = new UsuarioIniciado();
        ArrayList<Subasta> listaSubastas = new ArrayList<>();
        try {
            DaoFactory factory = DaoFactory.getDaoFactory(DaoFactory.SQLSERVER);
            ISubastaDao dao = factory.getSubastaDao();
            for (Subasta dato: dao.listarSubastasUsuario(tmpUsuario.getIdentificacion())) {
                listaSubastas.add(dato);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return listaSubastas;
    }

    public ArrayList<Subasta> listarSubastasParticipadas() {
        UsuarioIniciado tmpUsuario = new UsuarioIniciado();
        ArrayList<Subasta> listaSubastas = new ArrayList<>();
        try {
            DaoFactory factory = DaoFactory.getDaoFactory(DaoFactory.SQLSERVER);
            ISubastaDao dao = factory.getSubastaDao();
            for (Subasta dato: dao.listarSubastasUsuario(tmpUsuario.getIdentificacion())) {
                listaSubastas.add(dato);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return listaSubastas;
    }


    public ArrayList<Subasta> listarSubastasDisponibles() {
        UsuarioIniciado tmpUsuario = new UsuarioIniciado();
        ArrayList<Subasta> listaSubastas = new ArrayList<>();
        try {
            DaoFactory factory = DaoFactory.getDaoFactory(DaoFactory.SQLSERVER);
            ISubastaDao dao = factory.getSubastaDao();
            for (Subasta dato: dao.listarSubastasDisponibles(tmpUsuario.getIdentificacion())) {
                listaSubastas.add(dato);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return listaSubastas;
    }

    public void vencerSubasta() {
        try {
            DaoFactory factory = DaoFactory.getDaoFactory(DaoFactory.SQLSERVER);
            ISubastaDao dao = factory.getSubastaDao();
            dao.vencerSubasta(InfoSubasta.getId());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }


    public boolean validarGanador() {
        UsuarioIniciado tmpUsuario = new UsuarioIniciado();
        boolean ganador = false;
        try {
            DaoFactory factory = DaoFactory.getDaoFactory(DaoFactory.SQLSERVER);
            ISubastaDao dao = factory.getSubastaDao();
            ganador = dao.validarEsGanador(InfoSubasta.getId(), tmpUsuario.getIdentificacion());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return ganador;
    }

    public String[] obtenerGanador() {
        UsuarioIniciado tmpUsuario = new UsuarioIniciado();
        String [] ganador = new String[6];
        try {
            DaoFactory factory = DaoFactory.getDaoFactory(DaoFactory.SQLSERVER);
            ISubastaDao dao = factory.getSubastaDao();
            ganador = dao.obtenerGanador(InfoSubasta.getIdentificacionGanador());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return ganador;
    }




    public boolean validarVendedor() {
        UsuarioIniciado tmpUsuario = new UsuarioIniciado();
        boolean ganador = false;
        try {
            DaoFactory factory = DaoFactory.getDaoFactory(DaoFactory.SQLSERVER);
            ISubastaDao dao = factory.getSubastaDao();
            ganador = dao.validarVendedor(InfoSubasta.getId(), tmpUsuario.getIdentificacion());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return ganador;
    }


}
