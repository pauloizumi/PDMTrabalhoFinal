package br.edu.ifsp.hto.cadastrodemangas.domain;

import android.graphics.Bitmap;

/**
 * Created by paulo on 06/10/16.
 */
public class Volume {
    private long id;
    private String volume;
    private int id_colecao;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public int getId_colecao() {
        return id_colecao;
    }

    public void setId_colecao(int id_colecao) {
        this.id_colecao = id_colecao;
    }
}
