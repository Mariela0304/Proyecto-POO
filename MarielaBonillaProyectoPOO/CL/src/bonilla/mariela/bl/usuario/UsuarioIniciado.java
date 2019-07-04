package bonilla.mariela.bl.usuario;

import java.time.LocalDate;
import java.util.ArrayList;

public class UsuarioIniciado {
    private static String tipoUsuario;
    private static String nombre;
    private static String tipoIdentificacion;
    private static String identificacion;
    private static LocalDate fechaNacimiento;
    private static int edad;
    private static String correo;
    private static String contrasenna;
    private static double puntuacion;
    private static String provincia;
    private static String canton;
    private static  String distrito;
    private static String direccion;
    private static ArrayList<String> intereses;
    private static String estado;
    private static int idColeccionista;


    public UsuarioIniciado() {
    }

    //Inicio sesión de un moderador
    public UsuarioIniciado(String estado, String tipoUsuario, String nombre, String tipoIdentificacion, String identificacion,
                           LocalDate fechaNacimiento, int edad, String correo, String contrasenna) {
        this.estado = estado;
        this.tipoUsuario = tipoUsuario;
        this.nombre = nombre;
        this.tipoIdentificacion = tipoIdentificacion;
        this.identificacion = identificacion ;
        this.fechaNacimiento = fechaNacimiento;
        this.edad = edad;
        this.correo = correo;
        this.contrasenna = contrasenna;
    }

    //Inicio sesión de un vendedor
    public UsuarioIniciado(String estado, String tipoUsuario, String nombre, String tipoIdentificacion, String identificacion,
                           LocalDate fechaNacimiento, int edad, String correo,
                           String contrasenna, String provincia,
                           String canton,
                           String distrito, String direccion, double puntuacion) {
        this.estado = estado;
        this.tipoUsuario = tipoUsuario;
        this.nombre = nombre;
        this.tipoIdentificacion = tipoIdentificacion;
        this.identificacion = identificacion ;
        this.fechaNacimiento = fechaNacimiento;
        this.edad = edad;
        this.correo = correo;
        this.contrasenna = contrasenna;
        this.provincia = provincia;
        this.canton = canton;
        this.distrito = distrito;
        this.direccion = direccion;
        this.puntuacion = puntuacion;
    }


    //Inicio sesión de un coleccionista
    public UsuarioIniciado(String estado, String tipoUsuario, String nombre, String tipoIdentificacion, String identificacion,
                           LocalDate fechaNacimiento, int edad, String correo, String contrasenna, String provincia, String canton,
                           String distrito, String direccion, double puntuacion, ArrayList<String> intereses) {
        this.estado = estado;
        this.tipoUsuario = tipoUsuario;
        this.nombre =nombre;
        this.tipoIdentificacion = tipoIdentificacion;
        this.identificacion = identificacion ;
        this.fechaNacimiento = fechaNacimiento;
        this.edad = edad;
        this.correo = correo;
        this.contrasenna = contrasenna;
        this.provincia = provincia;
        this.canton = canton;
        this.distrito = distrito;
        this.direccion = direccion;
        this.puntuacion = puntuacion;
        this.intereses = intereses;
    }



    public  String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(String tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasenna() {
        return contrasenna;
    }

    public void setContrasenna(String contrasenna) {
        this.contrasenna = contrasenna;
    }

    public double getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(double puntuacion) {
        this.puntuacion = puntuacion;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getCanton() {
        return canton;
    }

    public void setCanton(String canton) {
        this.canton = canton;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public ArrayList<String> getIntereses() {
        return intereses;
    }

    public void setIntereses(ArrayList<String> intereses) {
        this.intereses = intereses;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        UsuarioIniciado.estado = estado;
    }

    public int getIdColeccionista() {
        return idColeccionista;
    }

    public void setIdColeccionista(int idColeccionista) {
        UsuarioIniciado.idColeccionista = idColeccionista;
    }
}

