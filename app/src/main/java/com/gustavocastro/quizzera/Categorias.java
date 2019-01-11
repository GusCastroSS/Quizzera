package com.gustavocastro.quizzera;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Categorias extends AppCompatActivity {

    private Button categoriaUm;
    private Button categoriaDois;
    private Button score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorias);

        categoriaUm = findViewById(R.id.button1);
        categoriaDois = findViewById(R.id.button2);
        score = findViewById(R.id.scoreButton);

        categoriaUm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Categorias.this, MainActivity.class);
                startActivity(i);
            }
        });

        categoriaDois.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j = new Intent(Categorias.this, MainActivity2.class);
                startActivity(j);
            }
        });

        score.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent k = new Intent(Categorias.this, Score.class);
                startActivity(k);
            }
        });
    }
}
