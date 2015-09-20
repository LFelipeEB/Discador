package com.example.lfelipeeb.testes.activity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.lfelipeeb.testes.loja.Lojas;
import com.example.lfelipeeb.testes.loja.LojasAdapter;
import com.example.lfelipeeb.testes.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar= (Toolbar) findViewById(R.id.toolbar);
        toolbar.setElevation(Float.valueOf(20));
        toolbar.setTitle(R.string.app_name);
        setSupportActionBar(toolbar);

        //RecyclerView
        recyclerView = (RecyclerView) findViewById(R.id.recyclerMain);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        //Lojas Adapter
        List<Lojas> lojas = Lojas.getLojas();
        recyclerView.setAdapter(new LojasAdapter(this, lojas, onClickLojas()));
    }

    //OnClickLojas
    private LojasAdapter.LojaOnClickListener onClickLojas(){
        return new LojasAdapter.LojaOnClickListener(){
            @Override
            public void onClickLoja(View view, int idx) {
                List<Lojas> loja = Lojas.getLojas();
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
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        Menu mMenu = toolbar.getMenu();

//        MenuItem searchItem =  mMenu.findItem(R.id.action_search);
//        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        //searchView.setOnQueryTextListener(onSearch());

/*
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
*/

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if(id == R.id.sobre){
            Intent it = new Intent(MainActivity.this, Sobre.class);
            startActivity(it);
        }

        return super.onOptionsItemSelected(item);
    }

    public SearchView.OnQueryTextListener onSearch(){
        return new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(MainActivity.this,"Pesquisando ... ", Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                List<Lojas> lojas = Lojas.getLojas();
                List<Lojas> pesquisadas = new ArrayList<>();
                for(Lojas loja : lojas){
                    if(loja.getNome().contains(newText)){
                        pesquisadas.add(loja);
                    }
                }
                recyclerView.setAdapter(new LojasAdapter(MainActivity.this,pesquisadas,onClickLojas()));

                return false;
            }
        };
    }
}
