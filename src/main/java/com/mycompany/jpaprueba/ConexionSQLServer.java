
package com.mycompany.jpaprueba;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;


public class ConexionSQLServer {
    
    Connection conexion = null;
     String usuario = "sqlUsuario";
     String contrasena = "root";
     String db = "jpaPractica";
     String ip = "localhost";
     String puerto = "1433";
    
     
     public Connection obtenerConexion(){
         try{
             String cadena = "jdbc:sqlserver://localhost:"+puerto+";"+"databaseName="+db + ";user="+usuario+";password="+contrasena+";encrypt=true;trustServerCertificate=true;" ;
             conexion = DriverManager.getConnection(cadena,usuario,contrasena);
         } catch(Exception e){
             JOptionPane.showMessageDialog(null, "Error: " + e.toString());
         }
         return conexion;
     };
     
  
}
