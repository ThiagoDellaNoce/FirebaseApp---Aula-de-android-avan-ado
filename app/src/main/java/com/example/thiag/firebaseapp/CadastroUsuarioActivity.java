package com.example.thiag.firebaseapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class CadastroUsuarioActivity extends AppCompatActivity {

    private EditText edtEmailCadastro;
    private EditText edtSenhaCadastro;
    private Button btnCadastrar;
    private Toast mToastToShow;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);

        FirebaseApp.initializeApp(CadastroUsuarioActivity.this);

        mAuth = FirebaseAuth.getInstance();
        edtEmailCadastro = (EditText) findViewById(R.id.edtEmailSignup);
        edtSenhaCadastro = (EditText) findViewById(R.id.edtSenhaSignup);
        btnCadastrar = (Button) findViewById(R.id.btnSignup);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String email = edtEmailCadastro.getText().toString();
                String senha = edtSenhaCadastro.getText().toString();
                createAccount(email, senha);
            }
        });
    }

    private void createAccount(String email, String senha)
    {
        mToastToShow = Toast.makeText(CadastroUsuarioActivity.this, "Processando o cadastro do usuário...", Toast.LENGTH_LONG);
        mToastToShow.setGravity(Gravity.CENTER, 0, 0);
        mToastToShow.show();
        mAuth.createUserWithEmailAndPassword(email, senha)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(CadastroUsuarioActivity.this, "Usuário criado com sucesso!!!",
                                    Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(CadastroUsuarioActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        } else
                        {
                            Toast.makeText(CadastroUsuarioActivity.this, "Erro ao criar usuário!",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}