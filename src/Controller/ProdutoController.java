/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Produto;
import Dao.ProdutoDao;
import java.util.ArrayList;

/**
 *
 * @author Senai.info
 */
public class ProdutoController {
    String emp;
    public ProdutoController(String empresa){
        emp=empresa;
    }
    ProdutoDao dao;
     public ArrayList<Produto> buscarTodosProdutos() {
        dao = new ProdutoDao(emp);
        return dao.buscarTodos();
    }

    public Produto buscarProdutoPorID(Integer id) {
       dao= new ProdutoDao(emp);
       return dao.buscarPorId(id);
    }

     public boolean salvarProduto(Produto p){
        dao = new ProdutoDao(emp);
        return dao.salvar(p);
    }

     public void atualizarProduto(Produto p){
         dao= new ProdutoDao(emp);
         dao.atualizarProduto(p);
     }
     public void excluirProduto(Integer id){
         dao= new ProdutoDao(emp);
         dao.excluirProduto(id);
     }
      public ArrayList<Produto> filtrarProdutos(Integer codigo, String nome) {
        dao = new ProdutoDao(emp);
        if (codigo==0 && "".equals(nome)) {
            return dao.buscarTodos();
        }else if (codigo!=0){
            return dao.buscarProdutosID(codigo);
        } 
        else {
            return dao.filtrarProdutos(codigo, nome);
        }
    }

           public ArrayList<Produto>  filtrarProdutosHistorico(Integer codigo, String nome,String de, String ate) {
        dao = new ProdutoDao(emp);
        if (codigo==0 && "".equals(nome)&& "".equals(de)&& "".equals(ate)) {
            return dao.buscarTodos();
        } else {
            return dao.filtrarProdutosHistorico(codigo, nome,de,ate);
        }
    }

}
