/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Controller.ConnectionFactory;
import java.sql.Connection;

/**
 *
 * @author Dcxz
 */
public class VendasDao {
    private Connection connection;
    public VendasDao(){
    this.connection= new ConnectionFactory().getConnection();
}
    
}
