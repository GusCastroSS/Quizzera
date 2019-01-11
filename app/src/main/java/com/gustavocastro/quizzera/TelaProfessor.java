package com.gustavocastro.quizzera;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TelaProfessor extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference registroRef;
    private EditText pergunta;
    private RadioGroup categorias;
    private RadioGroup vouf;
    private Button adicionar;
    private String categoriaSelecionada;
    private String resposta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_professor);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        String userId = currentUser.getUid();
        registroRef = mFirebaseDatabase.getReference().child("perguntas");

        pergunta = findViewById(R.id.pergunta);
        categorias = findViewById(R.id.categorias);
        vouf = findViewById(R.id.vouf);
        adicionar = findViewById(R.id.adicionar);

        categorias.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.geografia){
                    categoriaSelecionada = "Geografia";
                }else {
                    categoriaSelecionada = "Historia";
                }
            }
        });

        vouf.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.verdadeiro) {
                    resposta = "true";
                }else {
                    resposta = "false";
                }
            }
        });

        adicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cadastrar();
            }
        });
    }

    private void cadastrar(){

        String valor = pergunta.getText().toString();


    }
}
