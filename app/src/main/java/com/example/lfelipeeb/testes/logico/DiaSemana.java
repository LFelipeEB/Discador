package com.example.lfelipeeb.testes.logico;

import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lfelipeeb.testes.R;
import com.example.lfelipeeb.testes.loja.Lojas;

import java.util.Calendar;

/**
 * Created by evari_000 on 18/09/2015.
 */
public class DiaSemana {
    private Lojas loja;
    private boolean hoje = false;
    public DiaSemana(Lojas loja){this.loja = loja;}

    public void setTextoDia(LinearLayout layout) {
        Calendar c = Calendar.getInstance();
        int diaWeek = c.get(Calendar.DAY_OF_WEEK);
        if(loja.getDia() != null) {
            String dias = loja.getDia();
            String[] dia = dias.split(",");
            for (String i : dia) {
                int d = Integer.parseInt(i);
                switch (d) {
                    case 0:
                        TextView semana0 = (TextView) layout.findViewById(R.id.domingo);
                        semana0.setTextColor(layout.getResources().getColor(R.color.verde));
                        if (diaWeek == 1) {
                            hoje = true;
                        }
                        break;
                    case 1:
                        TextView semana1 = (TextView) layout.findViewById(R.id.segunda);
                        semana1.setTextColor(layout.getResources().getColor(R.color.verde));
                        if (diaWeek == 2) {
                            hoje = true;
                        }
                        break;
                    case 2:
                        TextView semana2 = (TextView) layout.findViewById(R.id.terca);
                        semana2.setTextColor(layout.getResources().getColor(R.color.verde));
                        if (diaWeek == 3) {
                            hoje = true;
                        }
                        break;
                    case 3:
                        TextView semana3 = (TextView) layout.findViewById(R.id.quarta);
                        semana3.setTextColor(layout.getResources().getColor(R.color.verde));
                        if (diaWeek == 4) {
                            hoje = true;
                        }
                        break;
                    case 4:
                        TextView semana4 = (TextView) layout.findViewById(R.id.quinta);
                        semana4.setTextColor(layout.getResources().getColor(R.color.verde));
                        if (diaWeek == 5) {
                            hoje = true;
                        }
                        break;
                    case 5:
                        TextView semana5 = (TextView) layout.findViewById(R.id.sexta);
                        semana5.setTextColor(layout.getResources().getColor(R.color.verde));
                        if (diaWeek == 6) {
                            hoje = true;
                        }
                        break;
                    case 6:
                        TextView semana6 = (TextView) layout.findViewById(R.id.sabado);
                        semana6.setTextColor(layout.getResources().getColor(R.color.verde));
                        if (diaWeek == 7) {
                            hoje = true;
                        }
                        break;
                }
            }
        }
    }

    public boolean isAberto() {
        Calendar c = Calendar.getInstance();
        boolean isHoje = hoje;
        boolean isHorario = false;

        if (loja.getHorario() != null) {
            String[] hm = loja.getHorario().split("-");
            String[] abre = hm[0].split(":");
            int hAbre = Integer.parseInt(abre[0]);
            int mAbre = Integer.parseInt(abre[1]);
            String[] fecha = hm[1].split(":");
            int hfecha = Integer.parseInt(fecha[0]);
            int mFecha = Integer.parseInt(fecha[1]);
            int horarioAgora = c.get(Calendar.HOUR_OF_DAY);
            int minutosAgora = c.get(Calendar.MINUTE);

            if (hAbre < horarioAgora && hfecha > horarioAgora) {
                isHorario = true;
            }

            if (hAbre == horarioAgora && hfecha > horarioAgora) {
                if(mAbre < minutosAgora){
                    isHorario = true;
                }
            }

            if (hfecha == horarioAgora) {
                if(mFecha > minutosAgora){
                    isHorario = true;
                }
            }
        }

        return (isHorario && isHoje);

    }

    public static boolean isAberto(String horario, String dia) {


        Calendar c = Calendar.getInstance();
        boolean isHoje = isHoje(dia);
        boolean isHorario = false;

        if (horario != null) {
            String[] hm = horario.split("-");
            String[] abre = hm[0].split(":");
            int hAbre = Integer.parseInt(abre[0]);
            int mAbre = Integer.parseInt(abre[1]);
            String[] fecha = hm[1].split(":");
            int hfecha = Integer.parseInt(fecha[0]);
            int mFecha = Integer.parseInt(fecha[1]);
            int horarioAgora = c.get(Calendar.HOUR_OF_DAY);
            int minutosAgora = c.get(Calendar.MINUTE);

            if (hAbre < horarioAgora && hfecha > horarioAgora) {
                isHorario = true;
            }

            if (hAbre == horarioAgora && hfecha > horarioAgora) {
                if(mAbre < minutosAgora){
                    isHorario = true;
                }
            }

            if (hfecha == horarioAgora) {
                if(mFecha > minutosAgora){
                    isHorario = true;
                }
            }
        }

        return (isHorario && isHoje);

    }

    private static boolean isHoje(String dia){
        if(dia != null){
            String[] diaP = dia.split(",");
            Calendar c = Calendar.getInstance();
            int diaSemana = c.get(Calendar.DAY_OF_WEEK);

            switch (diaSemana){
                case 1:
                    for(String d : diaP){
                        if (Integer.parseInt(d) == 0){
                            return true;
                        }
                    }
                    break;
                case 2:
                    for(String d : diaP){
                        if (Integer.parseInt(d) == 1){
                            return true;
                        }
                    }
                    break;
                case 3:
                    for(String d : diaP){
                        if (Integer.parseInt(d) == 2){
                            return true;
                        }
                    }
                    break;
                case 4:
                    for(String d : diaP){
                        if (Integer.parseInt(d) == 3){
                            return true;
                        }
                    }
                    break;
                case 5:
                    for(String d : diaP){
                        if (Integer.parseInt(d) == 4){
                            return true;
                        }
                    }
                    break;
                case 6:
                    for(String d : diaP){
                        if (Integer.parseInt(d) == 5){
                            return true;
                        }
                    }
                    break;
                case 7:
                    for(String d : diaP){
                        if (Integer.parseInt(d) == 6){
                            return true;
                        }
                    }
                    break;
                default:
                    return false;
            }
        }
        return false;
    }
}
