
package BBDD;

import static BBDD.Conexiones.conectar;
import Modelo.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.lang.model.util.Types;


public class OperacionesBaseDatos {
    
    
      public static  void registrarUsuarios(Usuario usr) {
        
         
        Connection con =  conectar();
        String myQueryPrepared = "INSERT INTO  usuario(usuario,contraseña,nombre,apellido,correo,fechaN) VALUES (?,?,?,?,?,?);";

        try {

            PreparedStatement pstm = con.prepareStatement(myQueryPrepared);
          

    
            
            pstm.setString(1,usr.getUsuario() );
            pstm.setString(2, usr.getContraseña());
            pstm.setString(3, usr.getNombre());
            pstm.setString(4, usr.getApellido());
            pstm.setString(5,usr.getCorreo() );
            pstm.setString(6, usr.getFechaN());
            
            pstm.executeUpdate();       
            
            
        } catch (SQLException e) {
            
          System.out.println(e.getMessage());

        }

    }
      
    public static boolean loginJugador(String usuario, String contraseña){
        
       
        Connection con =  conectar();
         String myQueryPrepared ="SELECT * FROM usuario WHERE usuario = ? AND contraseña = ?;";
        
        
          try {
            
        PreparedStatement pstm = con.prepareStatement(myQueryPrepared);
        
            pstm.setString(1,usuario );
            pstm.setString(2, contraseña);
            
            
         ResultSet rs = pstm.executeQuery();
         boolean verificar = rs.next();
              
         
             return verificar;
            
            
         
         } catch (SQLException ex) {
             ex.getMessage();
          }
         
           return false;
            
          
    }
    public static boolean verificarUsuarioD(String nombreUsuario)  {
         Connection con =  conectar();
    String query = "SELECT COUNT(*) FROM usuario WHERE usuario = ?";
    try  {
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setString(1, nombreUsuario);
        ResultSet rs = stmt.executeQuery();
        rs.next();
        return rs.getInt(1) > 0; //true// si existe 
    }  catch (SQLException ex) {
                 Logger.getLogger(OperacionesBaseDatos.class.getName()).log(Level.SEVERE, null, ex);
             }
    return false;
}
   
    
    
}
