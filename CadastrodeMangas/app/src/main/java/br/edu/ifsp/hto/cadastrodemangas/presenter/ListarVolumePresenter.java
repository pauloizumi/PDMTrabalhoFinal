package br.edu.ifsp.hto.cadastrodemangas.presenter;

import android.content.Context;
import android.widget.ListView;

import java.util.List;

import br.edu.ifsp.hto.cadastrodemangas.adapter.ColecaoAdapter;
import br.edu.ifsp.hto.cadastrodemangas.domain.Colecao;
import br.edu.ifsp.hto.cadastrodemangas.domain.Volume;
import br.edu.ifsp.hto.cadastrodemangas.service.ColecaoService;
import br.edu.ifsp.hto.cadastrodemangas.service.VolumeService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by paulo on 06/14/16.
 */
public class ListarVolumePresenter {
    public static String baseURL = "http://192.168.25.2:8090/";

    public void listarVolumes(final Context context, final ListView listView) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        VolumeService service = retrofit.create(VolumeService.class);
        Call<List<Volume>> call = service.listarvolumes();
        call.enqueue(new Callback<List<Volume>>() {
            @Override
            public void onResponse(Call<List<Volume>> call, Response<List<Volume>> response) {

                List<Volume> list = response.body();
                listView.setAdapter(new ColecaoAdapter(context, list));
            }

            @Override
            public void onFailure(Call<List<Volume>> call, Throwable t) {

            }


        });


    }
}

