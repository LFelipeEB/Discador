package com.example.lfelipeeb.testes.loja;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lfelipeeb.testes.R;

import java.util.List;

/**
 * Created by lfelipeeb on 14/09/15.
 */
public class LojasAdapter extends RecyclerView.Adapter <LojasAdapter.LojasViewHolder> {
    private final List<Lojas> lojas;
    private final Context context;
    private final LojaOnClickListener onClickListener;

    public LojasAdapter(Context context, List<Lojas> lojas, LojaOnClickListener onClickListener) {
        this.context = context;
        this.lojas = lojas;
        this.onClickListener = onClickListener;
    }
    public interface LojaOnClickListener{void onClickLoja(View view, int idx);}

    @Override
    public LojasViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //Este metodo cria uma subclasse de RecyclerView.ViewHolder
        //Infla as view do layout
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_lojas, parent,false);
        //Cria classe do ViewHolder
        LojasViewHolder holder = new LojasViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final LojasViewHolder holder, final int position) {
        //Este metodo recebe o indice do elemento, e atualiza as views que estao dentro do ViewHolder
        Lojas l = lojas.get(position);
        holder.tNome.setText(l.getNome());
        holder.img.setImageResource(l.getImgId());
        //Click
        if(onClickListener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Chama o Listener para informar que clicou na loja
                    onClickListener.onClickLoja(holder.view, position);


                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return this.lojas != null ? this.lojas.size() : 0;
    }





    public static class LojasViewHolder extends RecyclerView.ViewHolder{
        public TextView tNome;
        ImageView img;
        private View view;

        public LojasViewHolder(View view) {
            super(view);
            this.view = view;
            //Cria as view
            tNome = (TextView) view.findViewById(R.id.tNome);
            img = (ImageView) view.findViewById(R.id.img);
        }
    }
}
