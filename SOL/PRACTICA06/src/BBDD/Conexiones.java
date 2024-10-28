
package BBDD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;


public class Conexiones {
    
    
    private static Connection con = null;
    
    private static Scanner sc =null;
    
    
    public static Connection conectar() {

        if (con == null) {
            String cadena_conexion = "jdbc:mysql://localhost:3307/";
            String nombre_bbdd = "bbdd_users";
            String usuario = "root";
            String contra = null;

            try {

                con = DriverManager.getConnection(cadena_conexion + nombre_bbdd, usuario, contra);

            } catch (SQLException e) {
                System.out.println("Error: Fallo de conexion interna");
                System.out.println(e.getMessage());
            }

        }
        return con;

    }
    public static void cerrarConexion() {

        if (con != null) {
            try {
                con.close();
                System.out.println("Conexion cerrada con exito.");
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexion: " + e.getMessage());
            }
        } else {
            System.out.println("No hay conexion activa para cerrar.");
        }
    }
    
}
