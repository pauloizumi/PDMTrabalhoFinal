package br.edu.ifsp.hto.cadastrodemangas.service;

import java.util.List;

import br.edu.ifsp.hto.cadastrodemangas.domain.Colecao;
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
public interface ColecaoService {
    @GET("colecao/list")
    Call<List<Colecao>> listarcolecao();


    @Multipart
    @POST("colecao/new")
    Call<Colecao> criarcolecao(@Part("foto") RequestBody image,
                                @Part("colecao") Colecao colecao);

}



