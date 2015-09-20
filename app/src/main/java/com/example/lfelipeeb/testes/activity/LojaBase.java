package com.example.lfelipeeb.testes.activity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lfelipeeb.testes.logico.DiaSemana;
import com.example.lfelipeeb.testes.loja.Lojas;
import com.example.lfelipeeb.testes.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by lfelipeeb on 14/09/15.
 */
public class LojaBase extends AppCompatActivity {
    private Lojas loja;
    private Bundle args;

    @Override
    public void onCreate(Bundle savedInstace){
        super.onCreate(savedInstace);
        setContentView(R.layout.modelo_loja);

        //Bundle Recebendo os parametros;
        args = getIntent().getExtras();
        loja = (Lojas) args.getSerializable("loja");

        //FAB BOTAO LIGAR
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(ligaListener());
        fab.setElevation(Float.valueOf(50));

        //TOOLBAR;
        Toolbar toolbar= (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(R.color.primaryText);
        toolbar.setTitle(loja.getNome());
        toolbar.setSubtitle(loja.getHorario());
        toolbar.setElevation(new Float(20));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //TextViews;
        TextView TvInfor = (TextView) findViewById(R.id.descricao);
        String temp = loja.getInfo() != loja.getInfo() ? loja.getInfo() : ("Informações sobre a empresa : VAZIO");
        TvInfor.setText(temp);

        TextView Tvendereco = (TextView) findViewById(R.id.endereco);
        temp = loja.getEndereco() != null ? loja.getEndereco() : ("Endereço : VAZIO");
        Tvendereco.setText(temp);
        mostraTelefone();
        LinearLayout layoutDia =(LinearLayout) findViewById(R.id.layoutDiaSemana);
        DiaSemana diaSemana1 = new DiaSemana(loja);
        diaSemana1.setTextoDia(layoutDia);

        if(diaSemana1.isAberto()){
            TextView aberto = (TextView) findViewById(R.id.aberto);
            aberto.setText("ABERTO");
            aberto.setTextColor(Color.GREEN);
        }
        if(! diaSemana1.isAberto()){
            TextView aberto = (TextView) findViewById(R.id.aberto);
            aberto.setText("FECHADO");
            aberto.setTextColor(Color.RED);
        }
    }

    private void mostraTelefone(){
        RadioButton telefones = (RadioButton) findViewById(R.id.telefone);
        telefones.setText(loja.getTelefone());
        if(loja.getTelefone() == null){
            telefones.setVisibility(View.GONE);
        }
        telefones = (RadioButton) findViewById(R.id.celular);
        telefones.setText(loja.getCelular());
        if(loja.getCelular() == null){
            telefones.setVisibility(View.GONE);
        }
        telefones = (RadioButton) findViewById(R.id.tim);
        telefones.setText(loja.getTim());
        if( loja.getTim() == null){
            telefones.setVisibility(View.GONE);
        }
        telefones = (RadioButton) findViewById(R.id.vivo);
        telefones.setText(loja.getVivo());
        if(loja.getVivo() == null){
            telefones.setVisibility(View.GONE);
        }
        telefones = (RadioButton) findViewById(R.id.oi);
        telefones.setText(loja.getOi());
        if(loja.getOi() == null){
            telefones.setVisibility(View.GONE);
        }
        telefones = (RadioButton) findViewById(R.id.claro);
        telefones.setText(loja.getClaro());
        if(loja.getClaro() == null){
            telefones.setVisibility(View.GONE);
        }
    }

    private View.OnClickListener ligaListener(){
        return  new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                RadioGroup grup = (RadioGroup) findViewById(R.id.radioGrup);
                int idSelected = grup.getCheckedRadioButtonId();
                String telefone = null;
                if(idSelected == R.id.telefone){
                    RadioButton rb = (RadioButton) findViewById(idSelected);
                    telefone = rb.getText().toString();
                }
                if(idSelected == R.id.celular){
                    RadioButton rb = (RadioButton) findViewById(idSelected);
                    telefone = rb.getText().toString();
                }
                if(idSelected == R.id.tim){
                    RadioButton rb = (RadioButton) findViewById(idSelected);
                    telefone = rb.getText().toString();
                }
                if(idSelected == R.id.vivo){
                    RadioButton rb = (RadioButton) findViewById(idSelected);
                    telefone = rb.getText().toString();
                }
                if(idSelected == R.id.oi){
                    RadioButton rb = (RadioButton) findViewById(idSelected);
                    telefone = rb.getText().toString();
                }
                if(idSelected == R.id.claro){
                    RadioButton rb = (RadioButton) findViewById(idSelected);
                    telefone = rb.getText().toString();
                }

                if(telefone != null){
                    Uri uri = Uri.parse("tel:"+telefone);
                    Intent intent = new Intent(Intent.ACTION_DIAL, uri);
                    startActivity(intent);
                }
                if(telefone == null){
                    Toast.makeText(LojaBase.this, "Você não selecionou nenhuma telefone !", Toast.LENGTH_SHORT).show();
                }
            }
        };
    }
}
