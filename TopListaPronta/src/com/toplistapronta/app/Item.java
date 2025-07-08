package com.toplistapronta.app;
package TopLista;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Item {
    private String nome;
    private int quantidade;
    private double preco;
    private String descricao;
    private LocalDate validade;

    private static List<Item> historicoCompras = new ArrayList<>();

    public Item(String nome, int quantidade, double preco, String descricao, LocalDate validade) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.preco = preco;
        this.descricao = descricao;
        this.validade = validade;

        historicoCompras.add(this); 
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getValidade() {
        return validade;
    }

    public void setValidade(LocalDate validade) {
        this.validade = validade;
    }

    public static List<Item> recomendarItens() {
        List<Item> recomendados = new ArrayList<>();
        for (Item item : historicoCompras) {
            boolean jaExiste = recomendados.stream()
                    .anyMatch(i -> i.getNome().equalsIgnoreCase(item.getNome()));
            if (!jaExiste) {
                recomendados.add(item);
            }
        }
        return recomendados;
    }

    @Override
    public String toString() {
        return String.format("Item: %s | Quantidade: %d | Preço: R$%.2f | Validade: %s | Descrição: %s",
                nome, quantidade, preco, validade, descricao);
    }
}
