package com.gustavocastro.quizzera;

public class Pergunta {

    String perguntas;
    boolean valor;

    public Pergunta(){


    }

    public Pergunta(String perguntas, boolean valor){

        this.perguntas = perguntas;
        this.valor = valor;
    }

    public String getPergunta(){

        return perguntas;
    }

    public boolean getValor(){


        return valor;
    }
}
