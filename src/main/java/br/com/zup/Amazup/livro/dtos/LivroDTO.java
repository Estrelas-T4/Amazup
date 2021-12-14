package br.com.zup.Amazup.livro.dtos;

import br.com.zup.Amazup.autor.Autor;
import br.com.zup.Amazup.autor.dtos.AutorUriDTO;
import br.com.zup.Amazup.enums.Genero;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

public class LivroDTO {
    private int id;
    private String nome;
    private Genero genero;
    private AutorUriDTO autor;
    private double preco;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public AutorUriDTO getAutor() {
        return autor;
    }

    public void setAutor(AutorUriDTO autor) {
        this.autor = autor;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
}
