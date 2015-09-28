package com.example.lfelipeeb.testes.logico;

import android.content.Context;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;

import com.example.lfelipeeb.testes.loja.Lojas;
import com.example.lfelipeeb.testes.loja.LojasAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by evari_000 on 20/09/2015.
 */
public class PesquisaLoja implements SearchView.OnQueryTextListener, SearchView.OnCloseListener {
    static private List<Lojas> lojasOriginais, lojasTemp;
    private RecyclerView lista;
    private Context context;
    private LojasAdapter.LojaOnClickListener onClick;

    public PesquisaLoja(List<Lojas> lojasOriginais, RecyclerView lista, Context contexto, LojasAdapter.LojaOnClickListener onClick) {
        this.lojasOriginais = lojasOriginais;
        this.lista = lista;
        this.context = contexto;
        this.onClick = onClick;
        lojasTemp = new ArrayList<>();
    }

    @Override
    public boolean onClose() {
        lista.setAdapter(new LojasAdapter(context, lojasOriginais, onClick));
        return false;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        lojasTemp.clear();

        for (Lojas temp : lojasOriginais) {
            if (temp.getNome().toUpperCase().contains(newText.toUpperCase())) {
                lojasTemp.add(temp);
            }
        }
        lista.setAdapter(new LojasAdapter(context, lojasTemp, onClick));
        return false;
    }

    public static List<Lojas> getLojas(){
        return lojasTemp;
    }
}