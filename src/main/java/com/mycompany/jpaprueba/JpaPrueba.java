
package com.mycompany.jpaprueba;

import javax.swing.JFrame;


public class JpaPrueba {

    public static void main(String[] args) {  
            JFrame frame = new JFrame("Clientes CRUD");
            FormularioCRUD objetoFormularioCRUD = new FormularioCRUD();
            frame.add(objetoFormularioCRUD); 
            frame.setSize(1000, 380); 
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
            frame.setVisible(true);
        }
       
   
}
