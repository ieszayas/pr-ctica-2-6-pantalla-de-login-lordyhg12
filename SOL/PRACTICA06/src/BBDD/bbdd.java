
package BBDD;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class bbdd {
      private static java.sql.Connection con = null;

    public static void crearBBBDD() {

        if (con == null) {
            String cadena_conexion = "jdbc:mysql://localhost:3306/";
            String nombre_bbdd = "bbdd_users";
            String usuario = "root";
            String contra = null;

            try {
                con = DriverManager.getConnection(cadena_conexion, usuario, contra);
                String myQueryPrepared = "CREATE DATABASE IF NOT EXISTS " + nombre_bbdd;
                PreparedStatement pstm = con.prepareStatement(myQueryPrepared);
                pstm.executeUpdate(myQueryPrepared);

                pstm.executeUpdate("USE " + nombre_bbdd);

                String CrearTablaJugador = "CREATE TABLE IF NOT EXISTS usuarios ("
                        + "id INT  PRIMARY KEY, "
                        + "nombre VARCHAR(10) NOT NULL, "
                    
                        + "password VARCHAR(10) NOT NULL"
                        + ")";
                pstm.executeUpdate(CrearTablaJugador);
                

              
        

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

    }
    
}
