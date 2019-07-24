/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

/**
 *
 * @author Dcxz
 */
public class VendasController {
   private int idVenda;
   private int idUsuario;
   private int idProduto;
   private String data;

    public VendasController(int idVenda, int idUsuario, int idProduto, String data) {
        this.idVenda = idVenda;
        this.idUsuario = idUsuario;
        this.idProduto = idProduto;
        this.data = data;
    }

    public int getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(int idVenda) {
        this.idVenda = idVenda;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

 
}
