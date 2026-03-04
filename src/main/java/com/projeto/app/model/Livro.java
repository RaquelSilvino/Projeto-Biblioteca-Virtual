package com.projeto.app.model;

import jakarta.persistence.*;
import org.springframework.web.multipart.MultipartFile;

@Entity
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String autor;
    private Double preco;

    private String imagem; // nome salvo no banco

    @Transient
    private MultipartFile arquivoImagem; // usado apenas no upload

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public MultipartFile getArquivoImagem() {
        return arquivoImagem;
    }

    public void setArquivoImagem(MultipartFile arquivoImagem) {
        this.arquivoImagem = arquivoImagem;
    }
}