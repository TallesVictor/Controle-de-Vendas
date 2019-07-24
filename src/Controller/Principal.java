/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import view.TelaLoginPRI;
import view.Vendas;



/**
 *
 * @author Bruno Andrade
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       mostrarLogin();
    }
    
    public static void mostrarLogin(){
        TelaLoginPRI tl = new TelaLoginPRI();
        tl.setTitle("Login - Easy Selling");
        tl.setLocationRelativeTo(null);
        tl.setResizable(false);
        tl.setVisible(true);
    }
     public static void mostrarVendas(){
        Vendas tl = new Vendas(null);
        tl.setTitle("Vendas - Easy Selling");
        tl.setLocationRelativeTo(null);
        tl.setResizable(false);
        tl.setVisible(true);
    }
   
}
