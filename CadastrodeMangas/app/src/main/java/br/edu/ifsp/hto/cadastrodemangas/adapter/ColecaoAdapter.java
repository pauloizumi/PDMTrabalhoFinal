package br.edu.ifsp.hto.cadastrodemangas.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.List;

import br.edu.ifsp.hto.cadastrodemangas.R;
import br.edu.ifsp.hto.cadastrodemangas.domain.Colecao;
import br.edu.ifsp.hto.cadastrodemangas.presenter.ListarColecaoPresenter;

/**
 * Created by paulo on 06/14/16.
 */
public class ColecaoAdapter extends BaseAdapter {

    private final Context context;
    private final List<Colecao> colecoes;

    public ColecaoAdapter(Context context, List colecoes) {
        this.context = context;
        this.colecoes = colecoes;
    }
    public int getItemCount() {

        return this.colecoes != null ? this.colecoes.size() : 0;
    }

    @Override
    public int getCount() {

        return this.colecoes.size();
    }

    @Override
    public Object getItem(int position) {

        return this.colecoes.get(position);
    }

    @Override
    public long getItemId(int position) {

        return this.colecoes.get(position).getId();
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_colecoes, parent, false);

        TextView tNome = (TextView) view.findViewById(R.id.tNome);
        TextView tCategoria = (TextView) view.findViewById(R.id.tCategoria);
        ImageView ivFoto = (ImageView) view.findViewById(R.id.ivFoto);

        Colecao colecao = colecoes.get(position);
        tNome.setText(colecao.getNome());
        tCategoria.setText(colecao.getCategoria());

        baixarImagem(colecao, ivFoto);

        return view;
    }
    private void baixarImagem(final Colecao colecao, final ImageView imageView) {

        final Target target = new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                colecao.setFoto(bitmap);
                imageView.setImageBitmap(bitmap);
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        };

        imageView.setTag(target);

        Picasso.with(context)
                .load(ListarColecaoPresenter.baseURL + "images/" + colecao.getId() + ".png")
                .into(target);
    }
}
