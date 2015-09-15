package com.example.lfelipeeb.testes.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.lfelipeeb.testes.loja.Lojas;
import com.example.lfelipeeb.testes.loja.LojasAdapter;
import com.example.lfelipeeb.testes.R;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar= (Toolbar) findViewById(R.id.toolbar);
        toolbar.setElevation(new Float(20));
        toolbar.setTitle(R.string.app_name);
        setSupportActionBar(toolbar);

        //RecyclerView
        recyclerView = (RecyclerView) findViewById(R.id.recyclerMain);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        //Lojas Adapter
        List<Lojas> lojas = Lojas.getLojas();
        recyclerView.setAdapter(new LojasAdapter(this,lojas, onClickLojas()));
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

    private Activity getActivity(){return this;}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Toast.makeText(MainActivity.this, "SETTINGS", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public View.OnClickListener onClickFAB(){
        return  new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"VOCE ESTA TENTANDO LIGAR",Toast.LENGTH_SHORT).show();
            }
        };
    }
}
