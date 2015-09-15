package com.example.lfelipeeb.testes.loja;

import com.example.lfelipeeb.testes.R;

import java.io.Serializable;
import java.util.ArrayList;
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
        lojas.add(new Lojas("Sushi House", R.drawable.pizza, "O mais gostoso da culinária japonesa, agora em Capelinha!\nDelivery de comida Japonesa Sushi House!"
                ,"19:00-23:30", "0,4,5,6","Capelinha Minas Gerais, 39680-000 Capelinha",null,"03891492149", null, null, null, "03399151033"));


        //AUMENTANDO A LISTA
        lojas.addAll(lojas);
        lojas.addAll(lojas);
        lojas.addAll(lojas);

        return lojas; //Busca e retorna as lojas cadastradas.
    }

    public String getNome(){return nome;}
    public int getImgId(){return img;}

    public String toString(){
        return new String(nome+" "+info+" "+horario+" "+dia+" "+endereco+" "+telefone+" "+celular+" "+tim+" "+vivo+" "+oi+" "+claro);
    }


}
