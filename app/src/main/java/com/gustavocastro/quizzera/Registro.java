package com.gustavocastro.quizzera;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Registro extends AppCompatActivity {

    private EditText emailRegistro;
    private EditText senhaRegistro;
    private Button rButton;
    private Button lButton;
    private FirebaseAuth mAuth;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference registroRef;
    private String email;
    private String senha;
    private EditText codProfessor;
    private RadioButton professorButton;
    private RadioButton alunoButton;
    private RadioGroup select;
    private DatabaseReference pontuacaoRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        String userId = currentUser.getUid();
        registroRef = mFirebaseDatabase.getReference().child(userId);

        emailRegistro = findViewById(R.id.emaileditText2);
        senhaRegistro = findViewById(R.id.senhaeditText2);
        lButton = findViewById(R.id.loginbutton2);
        rButton = findViewById(R.id.registrarbutton2);
        codProfessor = findViewById(R.id.codProfessor);
        professorButton = findViewById(R.id.professorButton);
        alunoButton = findViewById(R.id.alunoButton);
        select = findViewById(R.id.select);

        select.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.professorButton){

                    codProfessor.setVisibility(View.VISIBLE);

                }else{

                    codProfessor.setVisibility(View.INVISIBLE);
                }
            }
        });

        lButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Registro.this, Login.class);
                startActivity(i);
            }
        });

        rButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = emailRegistro.getText().toString();
                senha = senhaRegistro.getText().toString();
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(Registro.this, "Falhou", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(senha)) {
                    Toast.makeText(Registro.this, "Falhou", Toast.LENGTH_SHORT).show();
                } else {
                    registrar();
                }
            }
        });
    }

    private void registrar(){

        if (codProfessor.getText().toString().equals("12345") || codProfessor.getVisibility() == View.INVISIBLE){

            mAuth.createUserWithEmailAndPassword(email, senha).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){

                            if (codProfessor.getVisibility() == View.VISIBLE){
                                registroRef.child("professor").setValue("sim");
                                registroRef.child("Pontuacao").setValue(0);

                            }else{

                                registroRef.child("professor").setValue("nao");
                                registroRef.child("Pontuacao").setValue(0);
                            }

                        Intent j = new Intent(Registro.this, Categorias.class);
                        startActivity(j);
                    }else {
                        Toast.makeText(Registro.this, "Falhou", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }else {
            Toast.makeText(Registro.this, "Falhou", Toast.LENGTH_SHORT).show();
        }
    }
}
