package BBDD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class bbdd {

    private static Connection con = null;
    private  int  id_estatico =obtener_ultimo_id();
    private int  id = id_estatico;

    public static void crearBBBDD() {

        if (con == null) {
            String cadena_conexion = "jdbc:mysql://localhost:3307/";
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

    public  void registrarUsuarios(String nombre, String password) {
        
        this.id++;
        
        

        String myQueryPrepared2 = "INSERT INTO  usuarios  VALUES (?,?,?);";

        try {

            PreparedStatement pstm = con.prepareStatement(myQueryPrepared2);
            pstm.setInt(1,this.id);
            pstm.setString(2, nombre);
            pstm.setString(3, password);
            
            pstm.executeUpdate();
            
            
        } catch (SQLException e) {
            
            e.printStackTrace();

        }

    }
    public int obtener_ultimo_id() {
    
        try {

            String myQueryPrepared = "SELECT MAX(id) FROM usuarios";
            PreparedStatement pstm = con.prepareStatement(myQueryPrepared);
            ResultSet rst = pstm.executeQuery();
            if (rst.next()) {
                return rst.getInt(1);
            }
        } catch (SQLException ex) {
            System.out.println("Error al obtener el último ID: " + ex.getMessage());
        }
        return 0;

    }

}
