package com.example.lfelipeeb.testes.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.lfelipeeb.testes.loja.Lojas;
import com.example.lfelipeeb.testes.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.AbstractExecutorService;

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
        fab.setElevation(new Float(50));

        //TOOLBAR;
        Toolbar toolbar= (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(R.color.primaryText);
        toolbar.setTitle(loja.getNome());
        toolbar.setSubtitle(loja.getHorario());
        toolbar.setElevation(new Float(20));
        setSupportActionBar(toolbar);

        //TextViews;
        TextView TvInfor = (TextView) findViewById(R.id.descricao);
        String temp = loja.getInfo() != loja.getInfo() ? loja.getInfo() : ("Informações sobre a empresa : VAZIO");
        TvInfor.setText(temp);

        TextView Tvendereco = (TextView) findViewById(R.id.endereco);
        temp = loja.getEndereco() != null ? loja.getEndereco() : ("Endereço : VAZIO");
        Tvendereco.setText(temp);
        mostraTelefone();
        modificaDia();
        isAberto();


    }

    private void modificaDia(){
        String dias = loja.getDia();
        String[] dia = dias.split(",");
        for(String i : dia){
            int d = Integer.parseInt(i);
            switch(d) {
                case 0:
                    TextView semana0 = (TextView) findViewById(R.id.domingo);
                    semana0.setTextColor(getResources().getColor(R.color.verde));
                    break;
                case 1:
                    TextView semana1 = (TextView) findViewById(R.id.segunda);
                    semana1.setTextColor(getResources().getColor(R.color.verde));
                    break;
                case 2:
                    TextView semana2 = (TextView) findViewById(R.id.terca);
                    semana2.setTextColor(getResources().getColor(R.color.verde));
                    break;
                case 3:
                    TextView semana3 = (TextView) findViewById(R.id.quarta);
                    semana3.setTextColor(getResources().getColor(R.color.verde));
                    break;
                case 4:
                    TextView semana4 = (TextView) findViewById(R.id.quinta);
                    semana4.setTextColor(getResources().getColor(R.color.verde));
                    break;
                case 5:
                    TextView semana5 = (TextView) findViewById(R.id.sexta);
                    semana5.setTextColor(getResources().getColor(R.color.verde));
                    break;
                case 6:
                    TextView semana6 = (TextView) findViewById(R.id.sabado);
                    semana6.setTextColor(getResources().getColor(R.color.verde));
                    break;

            }
        }
    }

    private void isAberto(){
        Calendar c = Calendar.getInstance();
        TextView aberto = (TextView) findViewById(R.id.aberto);
        boolean isHoje = false;
        boolean isHorario = false;
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        String[] dayS = loja.getDia().split(",");
        List<Integer> daysI = new ArrayList<>();
        for (String d : dayS){
            daysI.add(Integer.parseInt(d));
        }
        switch (dayOfWeek){
            case 1:
               isHoje = daysI.contains(0);
                break;
            case 2:
                isHoje = daysI.contains(1);
                break;
            case 3:
                isHoje = daysI.contains(2);
                break;
            case 4:
                isHoje = daysI.contains(3);
                break;
            case 5:
                isHoje = daysI.contains(4);
                break;
            case 6:
                isHoje = daysI.contains(5);
                break;
            case 7:
                isHoje = daysI.contains(6);
                break;

        }
        if(loja.getHorario() != null) {
            String[] hm = loja.getHorario().split("-");
            String[] abre = hm[0].split(":");
            int hAbre = Integer.parseInt(abre[0]);
            int mAbre = Integer.parseInt(abre[1]);
            String[] fecha = hm[1].split(":");
            int hfecha = Integer.parseInt(fecha[0]);
            int mFecha = Integer.parseInt(fecha[1]);
            int horarioAgora = c.get(Calendar.HOUR);
            int minutosAgora = c.get(Calendar.MINUTE);
            if(hAbre < horarioAgora && hAbre > horarioAgora){
                isHorario = true;
            }
            if(hAbre == horarioAgora){
                if(mAbre >= minutosAgora){
                    isHorario = true;
                }
            }
            if(hfecha == horarioAgora){
                if(mFecha <= minutosAgora){
                    isHorario = true;
                }
            }
            if(!(isHoje && isHorario)){
                aberto.setText("FECHADO");
                aberto.setTextColor(getResources().getColor(R.color.red));
            }
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
            }
        };
    }
}
