/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Usuario;
import Dao.UsuarioDao;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Dcxz
 */
public class UsuarioController {
   UsuarioDao dao;
     public Usuario BuscarUsuario(String usuario, String senha){
        dao = new UsuarioDao();
        return dao.buscarUsuario(usuario, senha);
    }   
      public Usuario BuscarUsuarioCPF(String cpf, String data){
        dao = new UsuarioDao();
        return dao.buscarUsuarioCPF(cpf, data);
    }    

}
