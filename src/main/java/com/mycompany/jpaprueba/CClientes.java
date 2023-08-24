
package com.mycompany.jpaprueba;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


public class CClientes {
    
    public void mostrarClientes(JTable paramTablaClientes){
        
        ConexionSQLServer objetoConexion = new ConexionSQLServer();
        
        DefaultTableModel modelo = new DefaultTableModel();
        
        String sql="";
        
        modelo.addColumn("ID");
        modelo.addColumn("CC");
        modelo.addColumn("Nombres");
        modelo.addColumn("Apellidos");
        modelo.addColumn("Edad");
        
        paramTablaClientes.setModel(modelo);
        
        sql = "select * from clientes;";
        
        String [] datos = new String[5];
        
        Statement st;
        
        try {          
            st = objetoConexion.obtenerConexion().createStatement();    
            ResultSet rs = st.executeQuery(sql);
            
            while (rs.next()) {
               datos[0] = rs.getString(1);
               datos[1] = rs.getString(2);
               datos[2] = rs.getString(3);
               datos[3] = rs.getString(4);
               datos[4] = rs.getString(5);   
               
               modelo.addRow(datos);
            }
            
            paramTablaClientes.setModel(modelo);
          
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, "No se mostraron los registros, error: " + e.toString());
        }
    }
    
    public void seleccionarClientes(JTable paramTablaClientes, JTextField paramID, JTextField paramCC, JTextField paramNombres, JTextField paramApellidos, JTextField paramEdad){
            
        try {
            
            int fila = paramTablaClientes.getSelectedRow();
            
            if (fila>=0){
                paramID.setText(paramTablaClientes.getValueAt(fila, 0).toString());
                paramCC.setText(paramTablaClientes.getValueAt(fila, 1).toString());
                paramNombres.setText(paramTablaClientes.getValueAt(fila, 2).toString());
                paramApellidos.setText(paramTablaClientes.getValueAt(fila, 3).toString());
                paramEdad.setText(paramTablaClientes.getValueAt(fila, 4).toString());
            } else{
                JOptionPane.showMessageDialog(null, "No se selecciono registros");
            }
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, "Error de seleccion, error: " + e.toString());
        }
    }
    
    public void guardarRegistrosClientes(JTextField paramCC, JTextField paramNombres, JTextField paramApellidos, JTextField paramEdad){
            
        ConexionSQLServer objetoConexion = new ConexionSQLServer();
        
        String consulta = "insert into clientes (CC, nombres, apellidos, edad) values (?, ?, ?, ?);";
        
        try {
            CallableStatement cs = objetoConexion.obtenerConexion().prepareCall(consulta);
            
            cs.setString(1, paramCC.getText());
            cs.setString(2, paramNombres.getText());
            cs.setString(3, paramApellidos.getText());
            cs.setInt(4, Integer.parseInt(paramEdad.getText()));
            
            cs.execute();
            
            
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, "Error de insercion, error: " + e.toString());
        }
        
    }
    
    public void modificarClientes(JTextField paramID,JTextField paramCC, JTextField paramNombres, JTextField paramApellidos, JTextField paramEdad){
            ConexionSQLServer objetoConexion = new ConexionSQLServer();
            String consulta = "update clientes set clientes.CC = ?, clientes.nombres = ?, clientes.apellidos = ?, clientes.edad = ? where clientes.id = ?;";
            
            try {
            
                CallableStatement cs = objetoConexion.obtenerConexion().prepareCall(consulta);
                
                cs.setString(1, paramCC.getText());
                cs.setString(2, paramNombres.getText());
                cs.setString(3, paramApellidos.getText());
                cs.setInt(4, Integer.parseInt(paramEdad.getText()));
                cs.setInt(5, Integer.parseInt(paramID.getText()));
                
                cs.execute();
                JOptionPane.showMessageDialog(null, "Se modifico correctamente");
                
        } catch (Exception e) {
            
           JOptionPane.showMessageDialog(null, "Error de modifcacion, error: " + e.toString());
            
        }
          
    }
    
    public void eliminarClientes(JTextField paramID){
        ConexionSQLServer objetoConexion = new ConexionSQLServer();
        String consulta = "delete from clientes where clientes.id = ?;";
        
        try {
            CallableStatement cs = objetoConexion.obtenerConexion().prepareCall(consulta);
            cs.setInt(1, Integer.parseInt(paramID.getText()));
            cs.execute();
            JOptionPane.showMessageDialog(null, "Se elimino correctamente");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error de eliminacion, error: " + e.toString());
        }
        
    }
}
