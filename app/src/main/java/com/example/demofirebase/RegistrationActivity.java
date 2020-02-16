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

public class RegistrationActivity extends AppCompatActivity {
    EditText mEdtEmailRegister,mEditPasswordRegister,mEditConfirmPassRegister;
    Button mBtnRegister;
    TextView mTxtLogin;
    FirebaseAuth mFireAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_actvity);

        mEdtEmailRegister = findViewById(R.id.edt_register);
        mEditPasswordRegister = findViewById(R.id.edt_password_register);
        mEditConfirmPassRegister = findViewById(R.id.edt_password_confirm);
        mBtnRegister = findViewById(R.id.btn_login);
        mTxtLogin = findViewById(R.id.text_login);

        mFireAuth = FirebaseAuth.getInstance();

        mTxtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RegistrationActivity.this,MainActivity.class);
                startActivity(i);
            }
        });

        mBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(mEdtEmailRegister.getText().toString()))
                {
                    mEdtEmailRegister.setError("enter email id...");
                    return;
                }
                if(TextUtils.isEmpty(mEditPasswordRegister.getText().toString()))
                {
                    mEditPasswordRegister.setError("enter password id...");
                    return;
                }
                if(TextUtils.isEmpty(mEditConfirmPassRegister.getText().toString()))
                {
                    mEditConfirmPassRegister.setError("enter email id...");
                    return;
                }
                if (mEditPasswordRegister.getText().toString() == mEditConfirmPassRegister.getText().toString())
                {
                    Toast.makeText(RegistrationActivity.this,"password matched",Toast.LENGTH_LONG).show();
                    return;

                }


                String mStrEmail = mEdtEmailRegister.getText().toString();
                String mStrPassword = mEditPasswordRegister.getText().toString();
                mFireAuth.createUserWithEmailAndPassword(mStrEmail,mStrPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            finish();
                        }
                        else
                        {
                           Toast.makeText(RegistrationActivity.this,"registration unsuccessful",Toast.LENGTH_LONG).show();
                        }


                    }
                });
            }
        });






    }
}
