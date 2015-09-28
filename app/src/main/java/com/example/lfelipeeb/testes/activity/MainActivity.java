package com.example.lfelipeeb.testes.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.lfelipeeb.testes.logico.BancoLojas;
import com.example.lfelipeeb.testes.logico.PesquisaLoja;
import com.example.lfelipeeb.testes.loja.Lojas;
import com.example.lfelipeeb.testes.loja.LojasAdapter;
import com.example.lfelipeeb.testes.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private List<Lojas> lojas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar= (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);
        setSupportActionBar(toolbar);

        ViewCompat.setElevation(toolbar, 20);

        //RecyclerView
        recyclerView = (RecyclerView) findViewById(R.id.recyclerMain);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        lojas = new ArrayList<>();
        //Lojas Adapter
        //lojas = Lojas.getLojas();
        BancoLojas bd = new BancoLojas(this);
        bd.inicializaBanco();
        lojas = bd.findAll();
        Log.i("LOJAS", String.valueOf(lojas.size()));

        recyclerView.setAdapter(new LojasAdapter(this, lojas, onClickLojas()));

        View toolbarBottom = findViewById(R.id.bottomMain);
        toolbarBottom.setOnClickListener(toolbarBottomListener());
    }

    //OnClickLojasMAIN
    private LojasAdapter.LojaOnClickListener onClickLojas(){
        return new LojasAdapter.LojaOnClickListener(){
            @Override
            public void onClickLoja(View view, int idx) {
                List<Lojas> loja = lojas;
                Lojas l = loja.get(idx);
                Bundle params = new Bundle();
                params.putSerializable("loja",l);
                Intent intent = new Intent(MainActivity.this, LojaBase.class);
                intent.putExtras(params);
                startActivity(intent);
            }
        };

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        Menu mMenu = toolbar.getMenu();

        MenuItem searchItem =  mMenu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(new PesquisaLoja(lojas,recyclerView,MainActivity.this, onClickLojasPesquisa()));

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.sobre){
            Intent it = new Intent(MainActivity.this, Sobre.class);
            startActivity(it);
        }

        if(id == R.id.emailMain){
            Intent email = new Intent(Intent.ACTION_SEND);
            email.putExtra(Intent.EXTRA_EMAIL, new String[]{"lfelipeeb@gmail.com"});
            email.putExtra(Intent.EXTRA_SUBJECT, "[DISCADOR DELIVERY]");
            email.setType("message/rfc822");
            startActivity(Intent.createChooser(email, "Escolha um cliente de email :"));
        }

        return super.onOptionsItemSelected(item);
    }

    private View.OnClickListener toolbarBottomListener(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent email = new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_EMAIL, new String[]{"lfelipeeb@gmail.com"});
                email.putExtra(Intent.EXTRA_SUBJECT, "[DISCADOR DELIVERY]");
                email.setType("message/rfc822");
                startActivity(Intent.createChooser(email, "Escolha um cliente de email :"));
            }
        };
    }


    private LojasAdapter.LojaOnClickListener onClickLojasPesquisa() {
        return new LojasAdapter.LojaOnClickListener() {
            @Override
            public void onClickLoja(View view, int idx) {
                List<Lojas> loja = PesquisaLoja.getLojas();
                Lojas l = loja.get(idx);
                Bundle params = new Bundle();
                params.putSerializable("loja", l);
                Intent intent = new Intent(MainActivity.this, LojaBase.class);
                intent.putExtras(params);
                startActivity(intent);
            }
        };

    }}