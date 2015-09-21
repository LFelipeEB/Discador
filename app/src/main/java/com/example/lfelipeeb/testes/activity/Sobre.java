package com.example.lfelipeeb.testes.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lfelipeeb.testes.BuildConfig;
import com.example.lfelipeeb.testes.R;

public class Sobre extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sobre);
        //TOOLBAR
        Toolbar toolbar= (Toolbar) findViewById(R.id.toolbar);
//        toolbar.setElevation(new Float(20));
        toolbar.setTitle(R.string.app_name);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        LinearLayout github = (LinearLayout) findViewById(R.id.github);
        github.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://github.com/LFelipeEB/Discador");
                Intent it = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(it);
            }
        });

        LinearLayout wordpress = (LinearLayout) findViewById(R.id.wordpress);
        wordpress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("http://lfelipeeb.wordpress.com/");
                Intent it = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(it);
            }
        });

        View bottomBar = findViewById(R.id.bottomSobre);
        bottomBar.setOnClickListener(toolbarBottomListener());
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
}
