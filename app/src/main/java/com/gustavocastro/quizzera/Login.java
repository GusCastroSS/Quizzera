package com.gustavocastro.quizzera;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.gustavocastro.quizzera.R;

public class Login extends AppCompatActivity {

    private EditText emailLogin;
    private EditText senhaLogin;
    private Button loginButton;
    private Button registrarButton;
    private FirebaseAuth mAuth;
    private String email;
    private String senha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        emailLogin = (EditText) findViewById(R.id.emaileditText);
        senhaLogin = (EditText) findViewById(R.id.senhaeditText);
        loginButton = (Button) findViewById(R.id.loginbutton);
        registrarButton = (Button) findViewById(R.id.registrarbutton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = emailLogin.getText().toString();
                senha = senhaLogin.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(Login.this, "Falhou", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(senha)) {
                    Toast.makeText(Login.this, "Falhou", Toast.LENGTH_SHORT).show();
                } else {
                    validarLogin();
                }

            }
        });

        registrarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j = new Intent(Login.this, Registro.class);
                startActivity(j);
            }
        });
    }

    private void validarLogin(){
        mAuth.signInWithEmailAndPassword(email, senha).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Intent i = new Intent(Login.this, Categorias.class);
                    startActivity(i);
                }else {
                    Toast.makeText(Login.this, "Falhou", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
