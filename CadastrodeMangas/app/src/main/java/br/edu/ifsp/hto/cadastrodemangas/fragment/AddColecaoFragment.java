package br.edu.ifsp.hto.cadastrodemangas.fragment;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.edu.ifsp.hto.cadastrodemangas.MainActivity;
import br.edu.ifsp.hto.cadastrodemangas.R;
import br.edu.ifsp.hto.cadastrodemangas.domain.Colecao;
import br.edu.ifsp.hto.cadastrodemangas.service.ColecaoService;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by paulo on 06/14/16.
 */
public class AddColecaoFragment extends Fragment {
    private ImageView mImageView;
    private Context context;

    public AddColecaoFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_colecao, container, false);
        Button btGravarColecao = (Button) view.findViewById(R.id.btGravarColecao);
        btGravarColecao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gravarColecao();
            }
        });
        Button btTirarFoto = (Button) view.findViewById(R.id.btTirarFoto);
        btTirarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });

        mImageView = (ImageView) view.findViewById(R.id.ivFoto);
        context = container.getContext();

       return view;
    }
    public void gravarColecao() {
        String baseURL =  "http://192.168.25.2:8090/";

        EditText eNome =(EditText) getView().findViewById(R.id.eNome);
        EditText eCategoria = (EditText) getView().findViewById(R.id.eCategoria);

        Colecao colecao = new Colecao();
        colecao.setNome(eNome.getText().toString());
        colecao.setCategoria(eCategoria.getText().toString());

        File file = new File(mCurrentPhotoPath);
        RequestBody fbody = RequestBody.create(MediaType.parse("image/*"), file);

        eNome.setText("");
        eCategoria.setText("");

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

        ColecaoService service = retrofit.create(ColecaoService.class);

        Call<Colecao> call = service.criarColecao(colecao.getNome(),colecao.getCategoria());

        call.enqueue(new Callback<Colecao>() {
            @Override
            public void onResponse(Call<Colecao> call, Response<Colecao> response) {

            }

            @Override
            public void onFailure(Call<Colecao> call, Throwable t) {

            }
        });
    }

    //Foto
    String mCurrentPhotoPath;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    int MY_PERMISSIONS_REQUEST_READ_AND_WRITE_EXTERNAL_STORAGE;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == MainActivity.RESULT_OK) {

            File imgFile = new  File(mCurrentPhotoPath);

            if(imgFile.exists()){
                Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());

                mImageView.setImageBitmap(myBitmap);
            }
        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    private void dispatchTakePictureIntent() {
        if((ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
                || (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED))

        {
            ActivityCompat.requestPermissions
                    (getActivity(), new String[]{
                            Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE
                    }, MY_PERMISSIONS_REQUEST_READ_AND_WRITE_EXTERNAL_STORAGE);
        }


        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                        Uri.fromFile(photoFile));
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }
    }
}