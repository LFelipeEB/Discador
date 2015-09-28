package com.example.lfelipeeb.testes.logico;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.lfelipeeb.testes.R;
import com.example.lfelipeeb.testes.loja.Lojas;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by evari_000 on 24/09/2015.
 */
public class BancoLojas extends SQLiteOpenHelper {
    private static final String TAG = "sql";
    private static final String NOME_BANCO = "discador.capelinha.sqlite";
    private static final int VERSAO_BD = 1;

    public BancoLojas(Context ctx){
        //context, nomeBanco, Factory(null==padrao), versaoDoBanco
        super(ctx, NOME_BANCO, null,VERSAO_BD);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG, "Criando o banco (entrou onCreate)");
        db.execSQL("CREATE TABLE if not exist LOJAS (_ID INTEGER PRIMARY KEY AUTOINCREMENT, NOME TEXT,CATEGORIA INTEGER, DESCR TEXT, HORARIO TEXT, DIA TEXT, " +
                "ENDERECO TEXT, TELEFONE TEXT, CELULAR TEXT, TIM TEXT, VIVO TEXT, OI TEXT, CLARO TEXT);");
        Log.i(TAG, "BD LOJAS Criado");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }


    //INSERI OU ATUALIZAR ALGUM LOJA

    /**
     * O metodo salva a loja na banco de dados
     * @param loja
     * @return o Id do item salvo.
     */
    public long save(Lojas loja){
        SQLiteDatabase db = getWritableDatabase();
        Log.i("BANCO","ENTROU NO SAVE");

        long query =  db.query("LOJAS", null, null, null, null, null, null).getCount();
        try{
            ContentValues valores = new ContentValues();
            valores.put("NOME", loja.getNome());
            valores.put("CATEGORIA", loja.getImgId());
            valores.put("DESCR", loja.getInfo());
            valores.put("HORARIO", loja.getHorario());
            valores.put("DIA", loja.getDia());
            valores.put("ENDERECO", loja.getEndereco());
            valores.put("TELEFONE", loja.getTelefone());
            valores.put("CELULAR", loja.getCelular());
            valores.put("TIM", loja.getTim());
            valores.put("VIVO", loja.getVivo());
            valores.put("OI", loja.getOi());
            valores.put("CLARO", loja.getClaro());

            if(query >= loja.getId()){
                String _id = String.valueOf(loja.getId());
                String[] whereArgs = new String[] {_id};
                Log.i("BANCO","ENTROU NO UPDATE");

                return db.update("LOJAS", valores, "_id=?", whereArgs);
            }else{
                query = db.insert("LOJAS","",valores);
                Log.i("BANCO","ENTROU NO INSERT");

                return query;
            }

        }finally {
            db.close();
        }
    }

    public int delete(Lojas loja){
        SQLiteDatabase db = getWritableDatabase();
        try {
            int count = db.delete("LOJAS","_id=?", new String[]{String.valueOf(loja.getId())});
            Log.i(TAG,"Deletou ["+count+"]registro.");
            return count;
        }finally {
            db.close();
        }
    }

    public List<Lojas> findAll(){
        SQLiteDatabase db = getWritableDatabase();
        try {
            //select * from LOJAS
            Cursor cursor = db.query("LOJAS", null, null, null, null, null, "NOME");
            return toList(cursor);
        }finally {
            db.close();
        }
    }

    private List<Lojas> toList(Cursor cursor) {
        List<Lojas> lojas = new ArrayList<>();
        if(cursor.moveToFirst()){
            do {
                Lojas loja = new Lojas();
                //recuperaos atributos do carro
                loja.setId(cursor.getLong(cursor.getColumnIndex("_ID")));
                loja.setNome(cursor.getString(cursor.getColumnIndex("NOME")));
                loja.setImg(Integer.parseInt(cursor.getString(cursor.getColumnIndex("CATEGORIA"))));
                //nomeDoIcone == descricao
                loja.setInfo(cursor.getString(cursor.getColumnIndex("DESCR")));
                loja.setHorario(cursor.getString(cursor.getColumnIndex("HORARIO")));
                loja.setDia(cursor.getString(cursor.getColumnIndex("DIA")));
                loja.setEndereco(cursor.getString(cursor.getColumnIndex("ENDERECO")));
                loja.setTelefone(cursor.getString(cursor.getColumnIndex("TELEFONE")));
                loja.setCelular(cursor.getString(cursor.getColumnIndex("CELULAR")));
                loja.setTim(cursor.getString(cursor.getColumnIndex("TIM")));
                loja.setVivo(cursor.getString(cursor.getColumnIndex("VIVO")));
                loja.setOi(cursor.getString(cursor.getColumnIndex("OI")));
                loja.setClaro(cursor.getString(cursor.getColumnIndex("CLARO")));
                lojas.add(loja);
            }while (cursor.moveToNext());
        }
        Log.d("ToList",String.valueOf(lojas.size()));
        cursor.close();
        return lojas;
    }

    public void execSQL(String sql){
        SQLiteDatabase db = getWritableDatabase();
        try{
            db.execSQL(sql);
        }finally {
            db.close();
        }
    }

    public void execSQL(String sql, Object[] agrs){
        SQLiteDatabase db = getWritableDatabase();
        try{
            db.execSQL(sql, agrs);
        }finally {
            db.close();
        }
    }

    public void inicializaBanco(){
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.query("LOJAS",null,null,null,null,null,null);
        if(c.getCount() < 13){
        save(new Lojas(1,"Na Lenha Pizzaria", R.drawable.pizza, null, null, "0,1,2,3,4,5,6", "Rua Maria Moreira de Andrade, 600",
                "03335163606", null, null, null, null, null));
        save(new Lojas(2,"Sushi House", R.drawable.fish, "O mais gostoso da culinária japonesa, agora em Capelinha!\nDelivery de comida Japonesa Sushi House!"
                , "19:00-23:30", "0,4,5,6", "Capelinha Minas Gerais, 39680-000 Capelinha", null, "03891492149", null, null, null, "03399151033"));
        save(new Lojas(3,"LiquiGas - Diego Fernandes e Familia",R.drawable.gas_e_agua,null,"8:00-18:00","1,2,3,4,5","Capelinha, Minas Gerais","08000392959","35163017","91041910",null,null,null));
        save(new Lojas(4,"The Dog's Fats Food", R.drawable.food, "Esta é a página do The Dog's Fast Food, uma lanchonete super diferente com opções de lanches variados, mas com foco nos hotdogs tamanho família e em sabores de molhos" +
                " super diferenciados como parmesão, cheddar, gorgonzola, bolonhesa, barbecue entre outros.", "10:00-23:59", "0,1,2,3,4,5,6", "RUA RIO BRANCO 695B, Capelinha", "35163919", "91260018", null, null, null, null));
        save(new Lojas(5,"Mix Pizzaria e Lanchonete", R.drawable.pizza, "MIX PIZZARIA A FÁBRICA DA PIZZA AS MELHORES E MAIS GOSTOSAS PIZZAS DA CIDADE"
                , "18:00-23:00", "0,1,3,4,5,6", "AV ITALIA Nº 355 BAIRRO JARDIM AEROPORTO, 39680-000 Capelinha", "35162384", null, "91245478", null, "87087211", null));
        save(new Lojas(6,"Drograria Monumento Rede Inova", R.drawable.ambulance, null, "00:00-23:59", "0,1,2,3,4,5,6", "Praça do Povo 52, Centro, Capelinha", "35163000", "91912839 ", null, null, null, null));
        save(new Lojas(7,"Ripa Pizzaria e Restaurante", R.drawable.restaurante, null, "18:00-23:59", "0,2,3,4,5,6", "RUA JOÃO ALVES SAMPAIO .445 B. Mª LUCIA ,Capelinha - MINAS GERAIS", "35163373", "91996983 ", null, null, null, null));
        save(new Lojas(8,"Capitania das Piazza", R.drawable.pizza, null, "18:00-23:59", "0,1,2,3,4,5,6", "Av Aeroporto 352 Jardim Aeroporto, Capelinha - MINAS GERAIS",
                "35163482", null, "91656583", null, null, null));
        save(new Lojas(9,"Lidergás", R.drawable.gas_e_agua, null, null, "1,2,3,4,5", null, "35161969", null, "91020305", null, null, null));
        save(new Lojas(10,"Miro Gás", R.drawable.gas_e_agua, null, null, "1,2,3,4,5", null, "35162454", null, "91140596", null, null, null));
        save(new Lojas(11,"Farmacia Indiana", R.drawable.ambulance, null, null, "0,1,2,3,4,5,6", "Praça do Povo, nº73, Centro", "35162334", null, null, null, null, null));
        save(new Lojas(12,"Bichoprapao Lanchonete", R.drawable.food, null, "07:00-4:00", "0,1,2,3,4,5,6", "Praça do Povo, Centro", "35161729", "91982741", "91429035", "91994362", null, null));
        save(new Lojas(13,"Emergencia", R.drawable.ambulance, "TELEFONES DE EMERGGENCIA", "00:00-23:59", "0,1,2,3,4,5,6", "Policia Militar\tSAMU\tDisque Denuncia\tPolicia Civil", "190", "192", "181", "197", null, null));
        }

        //lojas.add(new Lojas("Nome",R.drawable.ic_drawer_dark,null,"HORARIO","DIAS","ENDEREÇO",null,null,null,null,null,null));

    }

}
