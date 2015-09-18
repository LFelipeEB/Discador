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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_web_view, menu);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
