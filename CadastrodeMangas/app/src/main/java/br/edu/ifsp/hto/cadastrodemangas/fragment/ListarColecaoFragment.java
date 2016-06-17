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


public class ListarColecaoFragment extends Fragment {
    private ListView mList;

    public ListarColecaoFragment(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_listar_colecao, container, false);

        ListarColecaoPresenter listarcolecaopresenter = new ListarColecaoPresenter();
        listarcolecaopresenter.listarcolecoes(getContext(), mList);

        return view;
    }

}

