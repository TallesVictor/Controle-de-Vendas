/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionFactory {

    public Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver encontrado com sucesso");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver de conexão mysql não encontrado");
            System.out.println(e.getStackTrace());
        }

        try {
            String url = "jdbc:mysql://localhost:3306/easyselling";
            String usuario = "root";
            String senha = "";
            Connection c = DriverManager.getConnection(url, usuario, senha);
            System.out.println("Conexão estabelecida com sucesso");
            return c;
        } catch (SQLException e) {
            System.out.println("Erro inesperado na conexão com banco de dados.");
            System.out.println(e.getStackTrace());
            throw new RuntimeException();
        }

    }

    public Connection criarBanco(String bd) throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver encontrado com sucesso");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver de conexão mysql não encontrado");
            System.out.println(e.getStackTrace());
        }

        try {
            String url = "jdbc:mysql://localhost:3306";
            String usuario = "root";
            String senha = "";
            Connection c = DriverManager.getConnection(url, usuario, senha);
            System.out.println("Conexão estabelecida com sucesso");
            Statement stmt = c.createStatement();
            //Toda operação que não for consulta, use executeUpdate, só assim
            //você poderá alterar algo no banco de dados
            stmt.executeUpdate(
                    "CREATE DATABASE IF NOT EXISTS " + bd + ";"
            );
            System.out.println("Final");
            return c;
        } catch (SQLException e) {
            System.out.println("Erro inesperado na conexão com banco de dados.");
            System.out.println(e.getStackTrace());
            throw new RuntimeException();
        }

    }

    public Connection criarTabela(String bd) throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver encontrado com sucesso");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver de conexão mysql não encontrado");
            System.out.println(e.getStackTrace());
        }

        try {
            String url = "jdbc:mysql://localhost:3306/" + bd;
            String usuario = "root";
            String senha = "";
            Connection c = DriverManager.getConnection(url, usuario, senha);
            System.out.println("Conexão estabelecida com sucesso");
            Statement stmt = c.createStatement();
            //Toda operação que não for consulta, use executeUpdate, só assim
            //você poderá alterar algo no banco de dados
      
            stmt.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS `produto` (\n"
                    + "  `id` int(11) NOT NULL AUTO_INCREMENT,\n"
                    + "  `nome` varchar(50) NOT NULL,\n"
                    + "  `marca` varchar(45) DEFAULT NULL,\n"
                    + "  `precoBruto` double NOT NULL,\n"
                    + "  `precoLiquido` double NOT NULL,\n"
                    + "  `dataCompra` varchar(10) NOT NULL,\n"
                    + "  `quantidade` int(11) NOT NULL,\n"
                    + "  `quantidadeLiquida` int(11) DEFAULT NULL,\n"
                    + "  PRIMARY KEY (`id`)\n"
                    + ") ENGINE=InnoDB DEFAULT CHARSET=latin1"
            );
            stmt.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS `usuario` (\n"
                    + "  `id` int(11) NOT NULL AUTO_INCREMENT,\n"
                    + "  `cpf` varchar(14) NOT NULL,\n"
                    + "  `nome` varchar(100) NOT NULL,\n"
                    + "  `usuario` varchar(50) NOT NULL,\n"
                    + "  `senha` varchar(50) NOT NULL,\n"
                    + "  PRIMARY KEY (`id`)\n"
                    + ") ENGINE=InnoDB AUTO_INCREMENT=251 DEFAULT CHARSET=latin1");     
            stmt.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS `vendas` (\n"
                    + "  `idVenda` int(11) NOT NULL,\n"
                    + "  `idUsuario` int(11) NOT NULL,\n"
                    + "  `idProduto` int(11) NOT NULL,\n"
                    + "  `data` varchar(11) NOT NULL,\n"
                    + "  PRIMARY KEY (`idVenda`),\n"
                    + "  KEY `produto` (`idProduto`),\n"
                    + "  KEY `usuario` (`idUsuario`),\n"
                    + "  CONSTRAINT `produto` FOREIGN KEY (`idProduto`) REFERENCES `produto` (`id`),\n"
                    + "  CONSTRAINT `usuario` FOREIGN KEY (`idUsuario`) REFERENCES `usuario` (`id`)\n"
                    + ") ENGINE=InnoDB DEFAULT CHARSET=latin1;"
            );
            System.out.println("Final");
            return c;
        } catch (SQLException e) {
            System.out.println("Erro inesperado na conexão com banco de dados.");
            System.out.println(e.getStackTrace());
            throw new RuntimeException();
        }

    }

    public Connection getConnectionUsuario(String bd) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver encontrado com sucesso");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver de conexão mysql não encontrado");
            System.out.println(e.getStackTrace());
        }

        try {
            String url = "jdbc:mysql://localhost:3306/" + bd;
            String usuario = "root";
            String senha = "";
            Connection c = DriverManager.getConnection(url, usuario, senha);
            System.out.println("Conexão estabelecida com sucesso");
            return c;
        } catch (SQLException e) {
            System.out.println("Erro inesperado na conexão com banco de dados.");
            System.out.println(e.getStackTrace());
            throw new RuntimeException();
        }

    }
}
