package br.edu.ifsp.hto.cadastrodemangas.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.edu.ifsp.hto.cadastrodemangas.R;
import br.edu.ifsp.hto.cadastrodemangas.domain.Colecao;
import br.edu.ifsp.hto.cadastrodemangas.domain.Volume;

/**
 * Created by paulo on 06/14/16.
 */
public class VolumeAdapter extends BaseAdapter {
    private final Context context;
    private final List<Volume> volumes;

    public VolumeAdapter(Context context, List volumes) {
        this.context = context;
        this.volumes = volumes;

    }
    @Override
    public int getCount() {
        return volumes != null ? volumes.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return volumes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return volumes.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_volumes, parent, false);

        TextView tVolume = (TextView) view.findViewById(R.id.tVolume);


        Volume volume = volumes.get(position);
        tVolume.setText(volume.getVolume());
        return view;
    }



}
