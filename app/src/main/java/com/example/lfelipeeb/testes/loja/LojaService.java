package com.example.lfelipeeb.testes.loja;

import android.content.Context;
import android.widget.Toast;

import com.example.lfelipeeb.testes.R;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import livroandroid.lib.utils.FileUtils;
import livroandroid.lib.utils.XMLUtils;

/**
 * Created by lfelipeeb on 14/09/15.
 */
public class LojaService {
    private static final boolean LOG_ON = false;
    private  static final String TAG = "LojaService";

    public static List<Lojas> getLojas(Context context){
        try{
            String xml = readFile(context);
            List<Lojas> loja = parserXML(xml);
            return loja;
        }catch (Exception e){
            Toast.makeText(context,"Não conseguimos obter a List", Toast.LENGTH_LONG).show();
            return null;
        }
    }

    private static String readFile(Context context)throws IOException{
            return FileUtils.readRawFileString(context, R.raw.lojas_parser, "UTF-8");
    }

    private static List<Lojas> parserXML(String xml){
        List<Lojas> loja =  new ArrayList<>();
        Element root = XMLUtils.getRoot(xml, "UTF-8");
        //Le todas as tags de Lojas
        List<Node> nodeLoja = XMLUtils.getChildren(root,"loja");
        //Inseri cada Loja na Lista
        for(Node node : nodeLoja){
            Lojas l = new Lojas();
            l.setNome(XMLUtils.getText(node,"nome"));
            l.setInfo(XMLUtils.getText(node, "inf"));
            l.setHorario(XMLUtils.getText(node, "horario"));
            l.setDia(XMLUtils.getText(node, "dias"));
            l.setEndereco(XMLUtils.getText(node, "endereco"));
            l.setTelefone(XMLUtils.getText(node, "telefones"));
            l.setTim(XMLUtils.getText(node, "tim"));
            l.setVivo(XMLUtils.getText(node, "vivo"));
            l.setOi(XMLUtils.getText(node, "oi"));
            l.setClaro(XMLUtils.getText(node, "claro"));
            l.setCelular(XMLUtils.getText(node,"celular"));

            loja.add(l);
        }
        return loja;
    }

}
