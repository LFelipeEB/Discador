package com.example.lfelipeeb.testes.logico;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

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
        db.execSQL("CREATE TABLE IF NOT EXISTS LOJAS (_ID INTEGER PRIMARY KEY AUTOINCREMENT, NOME TEXT,CATEGORIA LONG, DESCR TEXT, HORARIO TEXT, DIA TEXT, " +
                "ENDERECO TEXT, TELEFONE TEXT, CELULAR TEXT, TIM TEXT, VIVO TEXT, OI TEXT, CLARO TEXT);");
        Log.i(TAG, "BD LOJAS Criado");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }


    //INSERI OU ATUALIZAR ALGUM LOJA
    public long save(Lojas loja){
        long id = loja.getId();
        SQLiteDatabase db = getWritableDatabase();

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

            if(id != 0){
                String _id = String.valueOf(loja.getId());
                String[] whereArgs = new String[] {_id};
                return db.update("LOJAS", valores, "_id=?", whereArgs);
            }else{
                id = db.insert("LOJAS","",valores);
                return id;
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
}
