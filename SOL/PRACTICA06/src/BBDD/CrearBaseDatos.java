package BBDD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CrearBaseDatos {

    private static Connection con = null;

    public static void crearBBBDD() {

        if (con == null) {

            try {

                String cadena_conexion = "jdbc:mysql://localhost:3307/";
                String nombre_bbdd = "bbdd_users";
                String usuario = "root";
                String contra = null;

                con = DriverManager.getConnection(cadena_conexion, usuario, contra);

                String myQueryPrepared = "CREATE DATABASE IF NOT EXISTS " + nombre_bbdd;

                PreparedStatement pstm = con.prepareStatement(myQueryPrepared);

                pstm.executeUpdate(myQueryPrepared);

                pstm.executeUpdate("USE " + nombre_bbdd);

                String CrearTablaJugador = "CREATE TABLE IF NOT EXISTS usuario("
                        + " id integer AUTO_INCREMENT ,"
                        + " usuario varchar(10) ,"
                        + " contraseña varchar(20) NOT NULL,"
                        + " nombre varchar(15),"
                        + " apellido varchar(15),"
                        + " fechaN varchar(6) DEFAULT NULL,"
                        + " correo varchar(30),"
                        + " PRIMARY KEY(id,usuario)"
                        + " );";
                pstm.executeUpdate(CrearTablaJugador);

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        }

    }

}
