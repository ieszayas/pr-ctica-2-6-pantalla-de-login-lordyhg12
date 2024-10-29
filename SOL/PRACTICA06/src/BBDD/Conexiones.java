package BBDD;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexiones {

    private static Connection con = null;

  

    public static Connection conectar() {

        if (con == null) {
            try {
                File archivo = new File("src/fichero_config/config.properties");

                if (archivo.exists()) {
                    String arr[] = leerFichero(archivo);

                    String usuario = arr[0];
                    String password = arr[1];
                    String url = arr[2];

                    con = DriverManager.getConnection(url, usuario, password);

                } else {
                    System.out.println("No existe el archivo de configuración");
                }
            } catch (SQLException ex) {
                System.out.println("Error: Fallo de conexion interna xd");
                ex.printStackTrace();
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

    public static String[] leerFichero(File archivo) {
        Properties propiedades = new Properties();

        try ( FileInputStream input = new FileInputStream(archivo)) {
            propiedades.load(input);
            String usuario = propiedades.getProperty("db.user");
            String password = propiedades.getProperty("db.password");
            String conexion = propiedades.getProperty("db.url");

            String arr[] = {usuario, password, conexion};
            return arr;
        } catch (IOException e) {
            System.out.println("Error al cargar el archivo: " + e.getMessage());
        }
        return new String[]{"Error al cargar el archivo"};
    }

}
