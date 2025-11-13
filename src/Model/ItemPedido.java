/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

public class ItemPedido {
    private int pedidoId;
    private int alimentoId;
    private int quantidade;
    private Alimento alimento;

    public ItemPedido() {}

    public ItemPedido(Alimento alimento, int quantidade) {
        this.alimento = alimento;
        this.alimentoId = alimento.getId();
        this.quantidade = quantidade;
    }

    public int getPedidoId() { return pedidoId; }
    public void setPedidoId(int pedidoId) { this.pedidoId = pedidoId; }

    public int getAlimentoId() { return alimentoId; }
    public void setAlimentoId(int alimentoId) { this.alimentoId = alimentoId; }

    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }

    public Alimento getAlimento() { return alimento; }
    public void setAlimento(Alimento alimento) { 
        this.alimento = alimento;
        if (alimento != null) this.alimentoId = alimento.getId();
    }
}
