package com.example.demofirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    EditText mEdtEmail,mEditPassword;
    Button mBtnLogin;
    TextView mTxtRegister;
    FirebaseAuth mFireAuth;
    FirebaseUser mFireUser;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEdtEmail = findViewById(R.id.edt_email);
        mEditPassword = findViewById(R.id.edt_password);
        mBtnLogin = findViewById(R.id.btn_login);
        mTxtRegister = findViewById(R.id.text_register);
        mFireAuth = FirebaseAuth.getInstance();
        if(mFireAuth.getCurrentUser() != null)
        {
            Toast.makeText(MainActivity.this,"login success",Toast.LENGTH_LONG).show();
            Intent i = new Intent(MainActivity.this,MainPage.class);
            startActivity(i);
        }


        mTxtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,RegistrationActivity.class);
                startActivity(i);
            }
        });
        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(mEdtEmail.getText().toString()))
                {
                    mEdtEmail.setError("please enter email");
                    return;
                }
                if(TextUtils.isEmpty(mEditPassword.getText().toString()))
                {
                    mEditPassword.setError("please enter password");
                    return;
                }
                String mStrEmail = mEdtEmail.getText().toString();
                String mStrPassword = mEditPassword.getText().toString();

                mFireAuth.signInWithEmailAndPassword(mStrEmail,mStrPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {
                            Toast.makeText(MainActivity.this,"Login Successful",Toast.LENGTH_LONG).show();
                            Intent i = new Intent(MainActivity.this,MainPage.class);
                            startActivity(i);
                        }
                        else
                        {
                            Toast.makeText(MainActivity.this,"Login unSuccessful",Toast.LENGTH_LONG).show();
                        }

                    }
                });
            }
        });
    }
}
