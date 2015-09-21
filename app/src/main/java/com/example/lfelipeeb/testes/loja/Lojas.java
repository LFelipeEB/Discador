package com.example.lfelipeeb.testes.loja;

import android.support.annotation.NonNull;

import com.example.lfelipeeb.testes.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 * Created by lfelipeeb on 14/09/15.
 */
public class Lojas implements Serializable{

    private String nome;
    private int img;
    private String info;
    private String horario;
    private String dia;
    private String endereco;
    private String telefone;
    private String celular;
    private String tim;
    private String vivo;
    private String oi;
    private String claro;

    public void setNome(String nome) {this.nome = nome;}

    public String getInfo() {return info;}

    public void setInfo(String info) {this.info = info;}

    public String getHorario() {return horario;}

    public void setHorario(String horario) {this.horario = horario;}

    public String getDia() {return dia;}

    public void setDia(String dia) {this.dia = dia;}

    public String getEndereco() {return endereco;}

    public void setEndereco(String endereco) {this.endereco = endereco;}

    public String getTelefone() {return telefone;}

    public void setTelefone(String telefone) {this.telefone = telefone;}

    public String getCelular() {return celular;}

    public void setCelular(String celular) {this.celular = celular;}

    public String getTim() {return tim;}

    public void setTim(String tim) {this.tim = tim;}

    public String getVivo() {return vivo;}

    public void setVivo(String vivo) {this.vivo = vivo;}

    public String getOi() {return oi;}

    public void setOi(String oi) {this.oi = oi;}

    public String getClaro() {return claro;}

    public void setClaro(String claro) {this.claro = claro;}



    public Lojas (){}

    public Lojas(String nome, int img, String info, String horario, String dia, String endereco,
                 String telefone, String celular, String tim, String vivo, String oi, String claro)
    {
        this.nome = nome; this.img =img; this.info = info; this.horario = horario; this.dia = dia;
    this.endereco = endereco; this.telefone = telefone; this.celular = celular; this.tim = tim;
        this.vivo = vivo; this.oi = oi; this.claro = claro;
    }

    public static List<Lojas> getLojas(){
        List<Lojas> lojas = new ArrayList<>();
        lojas.add(new Lojas("Na Lenha Pizzaria", R.drawable.pizza, null, null, "0,1,2,3,4,5,6","Rua Maria Moreira de Andrade, 600",
                "03335163606", null, null, null, null, null));
        lojas.add(new Lojas("Sushi House", R.drawable.fish, "O mais gostoso da culinária japonesa, agora em Capelinha!\nDelivery de comida Japonesa Sushi House!"
                ,"19:00-23:30", "0,4,5,6","Capelinha Minas Gerais, 39680-000 Capelinha",null,"03891492149", null, null, null, "03399151033"));
        lojas.add(new Lojas("LiquiGas - Diego Fernandes e Familia",R.drawable.gas_e_agua,null,"8:00-18:00","1,2,3,4,5","Capelinha, Minas Gerais","35164070","35161508","91244664","91041910",null,null));
        lojas.add(new Lojas("The Dog's Fats Food",R.drawable.food,"Esta é a página do The Dog's Fast Food, uma lanchonete super diferente com opções de lanches variados, mas com foco nos hotdogs tamanho família e em sabores de molhos" +
                " super diferenciados como parmesão, cheddar, gorgonzola, bolonhesa, barbecue entre outros." ,"10:00-23:59","0,1,2,3,4,5,6","RUA RIO BRANCO 695B, Capelinha","35163919","91260018",null,null,null,null));
        lojas.add(new Lojas("Mix Pizzaria e Lanchonete", R.drawable.pizza,"MIX PIZZARIA A FÁBRICA DA PIZZA AS MELHORES E MAIS GOSTOSAS PIZZAS DA CIDADE"
                ,"18:00-23:00", "0,1,3,4,5,6","AV ITALIA Nº 355 BAIRRO JARDIM AEROPORTO, 39680-000 Capelinha","35162384",null,"91245478", null, "87087211", null));
        lojas.add(new Lojas("Drograria Monumento Rede Inova", R.drawable.ambulance,null, "00:00-23:59", "0,1,2,3,4,5,6","Praça do Povo 52, Centro, Capelinha","35163000","91912839 ",null, null, null, null));
        lojas.add(new Lojas("Ripa Pizzaria e Restaurante", R.drawable.restaurante, null, "18:00-23:59", "0,2,3,4,5,6", "RUA JOÃO ALVES SAMPAIO .445 B. Mª LUCIA ,Capelinha - MINAS GERAIS", "35163373", "91996983 ", null, null, null, null));
        lojas.add(new Lojas("CAPITANIA DAS PIZZAS", R.drawable.pizza, null, "18:00-23:59", "0,1,2,3,4,5,6", "Av Aeroporto 352 Jardim Aeroporto, Capelinha - MINAS GERAIS",
                "35163482" , null, "91656583", null, null, null));
        lojas.add(new Lojas("LIDERGÁS",R.drawable.gas_e_agua,null,null,"1,2,3,4,5",null,"35161969",null,"91020305",null,null,null));
        lojas.add(new Lojas("Miro Gás",R.drawable.gas_e_agua,null,null,"1,2,3,4,5",null,"35162454",null,"91140596",null,null,null));


        //lojas.add(new Lojas("Nome",R.drawable.ic_drawer_dark,null,"HORARIO","DIAS","ENDEREÇO",null,null,null,null,null,null));


        return lojas;
        //return sortLojasByName(lojas); //Busca e retorna as lojas cadastradas.
    }

    private static List<Lojas> sortLojasByName(List<Lojas> lojas){
        List<Lojas> ordenado = new ArrayList<>();

        return ordenado;
    }

    public String getNome(){return nome;}
    public int getImgId(){return img;}

    public String toString(){
        return new String(nome+" "+info+" "+horario+" "+dia+" "+endereco+" "+telefone+" "+celular+" "+tim+" "+vivo+" "+oi+" "+claro);
    }


}
