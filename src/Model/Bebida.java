/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

public class Bebida extends Alimento {
    private double teorAlcoolico;
    private boolean alcoolica;

    public Bebida() {}

    public Bebida(int id, String nome, double preco, double teorAlcoolico, boolean alcoolica) {
        super(id, nome, preco);
        this.teorAlcoolico = teorAlcoolico;
        this.alcoolica = alcoolica;
    }

    public double getTeorAlcoolico() { return teorAlcoolico; }
    public void setTeorAlcoolico(double teorAlcoolico) { this.teorAlcoolico = teorAlcoolico; }

    public boolean isAlcoolica() { return alcoolica; }
    public void setAlcoolica(boolean alcoolica) { this.alcoolica = alcoolica; }

    public double calcularImposto() {
        if (!alcoolica) return 0;
        return preco * (teorAlcoolico / 100);
    }
}
