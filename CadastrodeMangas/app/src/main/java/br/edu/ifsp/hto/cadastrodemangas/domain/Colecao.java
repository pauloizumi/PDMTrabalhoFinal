package br.edu.ifsp.hto.cadastrodemangas.domain;

import android.graphics.Bitmap;

/**
 * Created by paulo on 06/10/16.
 */
public class Colecao {
    private long id;
    private String nome;
    private String categoria;
    private Bitmap foto;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Bitmap getFoto() {
        return foto;
    }

    public void setFoto(Bitmap foto) {
        this.foto = foto;
    }
}
