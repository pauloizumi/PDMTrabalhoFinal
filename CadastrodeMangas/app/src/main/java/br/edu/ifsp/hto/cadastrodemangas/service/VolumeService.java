package br.edu.ifsp.hto.cadastrodemangas.service;

import java.util.List;


import br.edu.ifsp.hto.cadastrodemangas.domain.Volume;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by paulo on 06/14/16.
 */
public interface VolumeService {

    @GET("volume/list")
    Call<List<Volume>> listarvolumes();

    @FormUrlEncoded
    @POST("volume/new")
    Call<Volume> criarVolume(@Field("volume")String volume);
}

