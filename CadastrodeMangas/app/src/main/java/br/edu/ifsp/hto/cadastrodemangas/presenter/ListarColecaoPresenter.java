package br.edu.ifsp.hto.cadastrodemangas.presenter;

import android.content.Context;
import android.widget.ListView;

import java.util.List;

import br.edu.ifsp.hto.cadastrodemangas.adapter.ColecaoAdapter;
import br.edu.ifsp.hto.cadastrodemangas.domain.Colecao;
import br.edu.ifsp.hto.cadastrodemangas.service.ColecaoService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by paulo on 06/14/16.
 */
public class ListarColecaoPresenter {
    public static String baseURL = "http://192.168.25.2:8090/";

    public static void listarcolecoes(final Context context, final ListView listView) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        ColecaoService service = retrofit.create(ColecaoService.class);
        Call<List<Colecao>> call = service.listarcolecao();

        call.enqueue(new retrofit2.Callback<List<Colecao>>() {

            @Override
            public void onResponse(Call<List<Colecao>> call, Response<List<Colecao>> response) {

                List<Colecao> list = response.body();
                listView.setAdapter(new ColecaoAdapter(context, list));
            }

            @Override
            public void onFailure(Call<List<Colecao>> call, Throwable t) {
                t.printStackTrace();
            }


    });


    }
}