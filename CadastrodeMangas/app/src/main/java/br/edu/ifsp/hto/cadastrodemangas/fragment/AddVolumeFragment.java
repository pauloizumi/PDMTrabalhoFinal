package br.edu.ifsp.hto.cadastrodemangas.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.List;

import br.edu.ifsp.hto.cadastrodemangas.R;
import br.edu.ifsp.hto.cadastrodemangas.domain.Colecao;
import br.edu.ifsp.hto.cadastrodemangas.domain.Volume;
import br.edu.ifsp.hto.cadastrodemangas.service.ColecaoService;
import br.edu.ifsp.hto.cadastrodemangas.service.VolumeService;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class AddVolumeFragment extends Fragment {
    private Context context;
    private ListView listView;
    private String baseURL = "http://192.168.120.9:8090/";
    private Spinner deps;

    public AddVolumeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_volume, container, false);
        listView = (ListView) view.findViewById(R.id.listViewAddVolume);

        Button btAddVolume = (Button) view.findViewById(R.id.btAddVolume);
        btAddVolume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gravarVolume();
            }
        });

        context = container.getContext();

        listarColecoes();

        return view;
    }
    private void listarColecoes() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ColecaoService service = retrofit.create(ColecaoService.class);
        Call<List<Colecao>> listCall = service.listarcolecao();
        listCall.enqueue(new Callback<List<Colecao>>() {
            @Override
            public void onResponse(Call<List<Colecao>> call, Response<List<Colecao>> response) {
                final List<Colecao> colecoes = response.body();
                String array_spinner[] = new String[colecoes.size()];

                for (int i = 0; i < colecoes.size(); i++) {
                    Colecao colecao = colecoes.get(i);
                    array_spinner[i] = colecao.getNome();

                }

                deps = (Spinner) getView().findViewById(R.id.sDep);
                ArrayAdapter adap = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, array_spinner);
                deps.setAdapter(adap);

            }

            @Override
            public void onFailure(Call<List<Colecao>> call, Throwable t) {

            }
        });
    }
    public void gravarVolume() {
        String baseURL =  "http://192.168.120.9:8090/";

        EditText eVolume = (EditText) getView().findViewById(R.id.eVolume);

        Volume volume = new Volume();
        volume.setVolume(eVolume.getText().toString());

        eVolume.setText("");

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

        VolumeService service = retrofit.create(VolumeService.class);

        Call<Volume> call = service.criarVolume(volume.getVolume());

        call.enqueue(new Callback<Volume>() {
            @Override
            public void onResponse(Call<Volume> call, Response<Volume> response) {

            }

            @Override
            public void onFailure(Call<Volume> call, Throwable t) {

            }
        });
    }
}
