package br.edu.ifsp.hto.cadastrodemangas.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import br.edu.ifsp.hto.cadastrodemangas.R;
import br.edu.ifsp.hto.cadastrodemangas.presenter.ListarColecaoPresenter;
import br.edu.ifsp.hto.cadastrodemangas.presenter.ListarVolumePresenter;

public class ListarVolumeFragment extends Fragment {
    private ListView mList;

    public ListarVolumeFragment(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_listar_volume, container, false);

        mList = (ListView) view.findViewById(R.id.listViewVolume);


        ListarVolumePresenter listarVolumePresenter = new ListarVolumePresenter();
        listarVolumePresenter.listarVolumes(getContext(), mList);

        return view;
    }

}