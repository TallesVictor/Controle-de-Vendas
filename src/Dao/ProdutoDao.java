/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Controller.ConnectionFactory;
import Model.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Publico
 */
public class ProdutoDao {

    private Connection connection;

    public ProdutoDao(String empresa) {
        this.connection = new ConnectionFactory().getConnectionUsuario(empresa);
    }

    public ArrayList<Produto> buscarTodos() {
        ArrayList<Produto> listaProduto = new ArrayList<>();
        String sql = "select * from produto";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Produto p = preencherProduto(rs);
                listaProduto.add(p);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Erro ao buscar: " + e.getMessage());
            throw new RuntimeException();
        }
        return listaProduto;
    }

    public ArrayList<Produto> buscarProdutosID(Integer id) {
        ArrayList<Produto> listaProduto = new ArrayList<>();
        String sql = "select * from produto where id = ? limit 1";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, String.valueOf(id));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Produto p = preencherProduto(rs);
                listaProduto.add(p);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Erro ao buscar: " + e.getMessage());
            throw new RuntimeException();
        }
        return listaProduto;
    }

    public Produto buscarPorId(Integer id) {
        Produto p = null;
        String sql = "select * from produto where id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, String.valueOf(id));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                p = preencherProduto(rs);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Erro ao buscar: " + e.getMessage());
            throw new RuntimeException();
        }
        return p;
    }

    public void atualizarProduto(Produto p) {
        String sql = "update produto set "
                + "id=?, nome=?, marca=?, precoBruto=?, precoLiquido=?, dataCompra=?,quantidade=?,quantidadeLiquida=quantidade "
                + "where id = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, p.getId());
            ps.setString(2, p.getNome());
            ps.setString(3, p.getMarca());
            ps.setDouble(4, p.getPrecoBruto());
            ps.setDouble(5, p.getPrecoLiquido());
            ps.setString(6, p.getDataCompra());
            ps.setInt(8, p.getId());
            ps.setInt(7, p.getQuantidade());;
            ps.execute();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar: " + e.getMessage());
            throw new RuntimeException();
        }

    }

    public void excluirProduto(Integer id) {
        String sql = "delete from produto where id=?;";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Erro ao ataualizar: " + e.getMessage());
            throw new RuntimeException();
        }

    }

    public boolean salvar(Produto p) {
        String sql = "INSERT INTO produto (id,nome, marca, precoBruto, precoLiquido, dataCompra,quantidade,quantidadeLiquida) "
                + "VALUES ( ?, ?, ?, ?, ?, ?,?,quantidade);";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, p.getId());
            ps.setString(2, p.getNome());
            ps.setString(3, p.getMarca());
            ps.setDouble(4, p.getPrecoBruto());
            ps.setDouble(5, p.getPrecoLiquido());
            ps.setString(6, p.getDataCompra());
            ps.setInt(7, p.getQuantidade());
            ps.execute();
            ps.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao salvar: " + e.getMessage());
            throw new RuntimeException();
        }
    }

    private Produto preencherProduto(ResultSet rs) throws SQLException {
        Integer id = rs.getInt("id");
        Integer quantidade = rs.getInt("quantidade");
        Integer quantidadeLiquida = rs.getInt("quantidadeLiquida");
        String nome = rs.getString("nome");
        String marca = rs.getString("marca");
        double precoBruto = rs.getDouble("precoBruto");
        double precoLiquido = rs.getDouble("precoLiquido");
        String dataCompra = rs.getString("dataCompra");

        Produto p = new Produto(id, nome, quantidade, quantidadeLiquida, marca, precoBruto, precoLiquido, dataCompra);
        return p;
    }

    public ArrayList<Produto> filtrarProdutos(Integer id, String nome) {
        id = 0;
        ArrayList<Produto> listaProduto = new ArrayList<>();
        String sql = "select * from produto where 1=1";

        try {
            if (id != 0) {
                sql += " AND id =" + id + "";
            }
            if (!"".equals(nome)) {
                sql += " AND nome like '%" + nome + "%'";
            }

            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Produto p = preencherProduto(rs);
                listaProduto.add(p);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Erro ao filtrar: " + e.getMessage());
            throw new RuntimeException();
        }
        return listaProduto;
    }

    public ArrayList<Produto> filtrarProdutosHistorico(Integer id, String nome, String de, String ate) {
        ArrayList<Produto> listaProduto = new ArrayList<>();
        String sql = "select * from produto ";

        try {

            if (id != 0) {
                sql += " AND id =" + id;
            }
            if (!"".equals(nome)) {
                sql += " AND nome like '%" + nome + "%'";
            }
            if (!"".equals(de)) {
                sql += " AND DATE_FORMAT(STR_TO_DATE(dataCompra, '%d/%m/%Y'), '%Y-%m-%d')>='" + de
                        + "'";
            }
            if (!"".equals(ate)) {
                sql += " AND DATE_FORMAT(STR_TO_DATE(dataCompra, '%d/%m/%Y'), '%Y-%m-%d')<='" + ate
                        + "'";
            }
            sql += " order by id";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Produto p = preencherProduto(rs);
                listaProduto.add(p);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Erro ao filtrar: " + e.getMessage());
            throw new RuntimeException();
        }
        return listaProduto;
    }

}
