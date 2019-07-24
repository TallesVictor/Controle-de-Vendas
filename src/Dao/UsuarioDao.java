/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Controller.ConnectionFactory;
import Model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Dcxz
 */
public class UsuarioDao {
     private Connection connection;

    public UsuarioDao() {
        this.connection = new ConnectionFactory().getConnection();
    }

    public ArrayList<Usuario> buscarTodos() {
        ArrayList<Usuario> listaUsuario = new ArrayList<>();
        String sql = "select * from usuario";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Usuario u = preencherUsuario(rs);
                listaUsuario.add(u);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Erro ao buscar: " + e.getMessage());
            throw new RuntimeException();
        }
        return listaUsuario;
    }
       public Usuario buscarUsuario(String usuario, String senha) {
        Usuario usua = null;
        String sql = "select * from usuario where usuario=? and senha=?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, usuario);
            ps.setString(2, senha);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
            usua = preencherUsuario(rs);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Erro ao buscar: " + e.getMessage());
            throw new RuntimeException();
        }
        return usua;
    }
      
       public Usuario buscarUsuarioCPF(String cpf, String data) {
        Usuario usua = null;
        String sql = "select * from usuario where cpf=? and  dataNascimento=?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, cpf);
            ps.setString(2, data);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
            usua = preencherUsuario(rs);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Erro ao buscar: " + e.getMessage());
            throw new RuntimeException();
        }
        return usua;
    }
      private Usuario preencherUsuario(ResultSet rs) throws SQLException{
        Integer id= rs.getInt("id");
        String usuario = rs.getString("usuario");
        String senha = rs.getString("senha");
        String empresa = rs.getString("empresa");
        Usuario u = new Usuario(id,usuario, senha,empresa);
        return u;
    }
     private Usuario preencherUsuarioCPF(ResultSet rs) throws SQLException{
        String cpf = rs.getString("cpf");
        String data = rs.getString("dataNascimento");
        Usuario u = new Usuario(cpf, data);
        return u;
    }
}
