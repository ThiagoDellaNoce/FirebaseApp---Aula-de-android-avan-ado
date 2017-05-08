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

public class MainActivity extends AppCompatActivity {

    private EditText edtEmail;
    private EditText edtSenha;
    private Button btnLogin;
    private Button btnCadastro;
    private FirebaseAuth mAuth;
    private Toast mToastToShow;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//FirebaseCrash.report(new Exception("Meu primeiro erro fatal pelo Firebase!!!"));
        FirebaseApp.initializeApp(MainActivity.this);

        mAuth = FirebaseAuth.getInstance();
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtSenha = (EditText) findViewById(R.id.edtSenha);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnCadastro = (Button) findViewById(R.id.btnCadastro);

        btnLogin.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v)
            {
                String edtEmail = MainActivity.this.edtEmail.getText().toString();
                String edtSenha = MainActivity.this.edtSenha.getText().toString();

                serviceSignin(edtEmail.trim(), edtSenha);
            }
        });

        btnCadastro.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this,
                        CadastroUsuarioActivity.class);
                startActivity(intent);
            }
        });
    }

    public void serviceSignin(String email, String password)
    {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        if(!task.isSuccessful())
                        {
                            mToastToShow = Toast.makeText(MainActivity.this, "Erro ao tentar logar! =/",
                                    Toast.LENGTH_LONG);
                            mToastToShow.setGravity(Gravity.CENTER, 0, 0);
                            mToastToShow.show();
                        } else
                        {
                            mToastToShow = Toast.makeText(MainActivity.this, "Login Realizado com sucesso!!! =D",
                                    Toast.LENGTH_LONG);
                            mToastToShow.setGravity(Gravity.CENTER, 0, 0);
                            mToastToShow.show();
                        }
                    }
                });
    }
}
