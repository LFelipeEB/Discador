package com.example.lfelipeeb.testes.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.example.lfelipeeb.testes.R;

public class WebViewDoar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        android.webkit.WebView  view = (android.webkit.WebView) findViewById(R.id.webView);
        String botaoPag = new String("<html><title></title><body><!-- INICIO FORMULARIO BOTAO PAGSEGURO -->\n" +
                "<form action=\"https://pagseguro.uol.com.br/checkout/v2/donation.html\" method=\"post\">\n" +
                "<!-- NÃO EDITE OS COMANDOS DAS LINHAS ABAIXO -->\n" +
                "<input type=\"hidden\" name=\"currency\" value=\"BRL\" />\n" +
                "<input type=\"hidden\" name=\"receiverEmail\" value=\"lfelipeeb@gmail.com\" />\n" +
                "<input type=\"image\" src=\"https://p.simg.uol.com.br/out/pagseguro/i/botoes/doacoes/164x37-doar-assina.gif\" name=\"submit\" alt=\"Pague com PagSeguro - é rápido, grátis e seguro!\" />\n" +
                "</form>\n" +
                "<!-- FINAL FORMULARIO BOTAO PAGSEGURO --></body></html>");
        view.loadData(botaoPag,"text/html","UTF-8");

    }
}
