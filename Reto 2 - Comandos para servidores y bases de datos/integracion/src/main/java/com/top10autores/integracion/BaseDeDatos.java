package com.top10autores.integracion;
import java.sql.*;


public class BaseDeDatos {
    final String NOMBRE_BD = "busquedas";
    private String url;
    private String usuario;
    private String contrasenia;
    private Connection conexion;
    private DriverManager driverManager;
    
    public BaseDeDatos() {
        super();
    }

    public BaseDeDatos(String url, String usuario, String contrasenia) {
        this.url = url;
        this.usuario = usuario;
        this.contrasenia = contrasenia;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public void probarConexion() {

        try (Connection con = DriverManager.getConnection(this.url, this.usuario, this.contrasenia);
            Statement declaracion = conexion.createStatement();) {
            
            System.out.println("Conexión exitosa a la Base de Datos.");
            conexion.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        
    }



    public void crearBasedeDatos() {
        
        if(existeBD()){
            try (Connection conexion = DriverManager.getConnection(this.url, this.usuario, this.contrasenia);
                Statement declaracion = conexion.createStatement();){
                
                String consulta = "CREATE DATABASE " + NOMBRE_BD;
                declaracion.executeUpdate(consulta);
                System.out.println("Base de datos creada");
                conexion.close();
            } 
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
    }

    /*
     * Este método es protected ya que no cualquiera debería poder acceder a él.
     */
    protected void borrarBaseDeDatos(String nombreBD) {
        try (Connection conexion = DriverManager.getConnection(this.url, this.usuario, this.contrasenia);
            Statement declaracion = conexion.createStatement();) {
            
            String consulta = "DROP DATABASE " + nombreBD;
            declaracion.executeUpdate(consulta);
            System.out.println("Se ha eliminado la base de datos " + nombreBD);
            conexion.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
    
    @SuppressWarnings("finally")
    public boolean existeBD() {

        boolean existe = false;
        try (Connection conexion = DriverManager.getConnection(this.url, this.usuario, this.contrasenia);
            Statement declaracion = conexion.createStatement();) {
            
            String consulta = "SELECT * from " + NOMBRE_BD;
            ResultSet rs = declaracion.executeQuery(consulta);
            
            if (rs.next()) {
                existe = true;
            }
            conexion.close();
        } catch (SQLException e) {
            existe = false;
        } finally {
            return existe;
        }
    }
    
    
}
