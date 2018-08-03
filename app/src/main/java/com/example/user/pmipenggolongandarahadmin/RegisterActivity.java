package com.example.user.pmipenggolongandarahadmin;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.pmipenggolongandarahadmin.mDatas.Admin;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    private EditText username;
    private EditText email;
    private EditText password;
    private EditText passwordEmail;
    private Button btnRegister;

    private FirebaseAuth mAuth;
    private DatabaseReference reference;

    private Admin admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getSupportActionBar().setTitle("Register");

        username = (EditText) findViewById(R.id.username);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password_admin);
        passwordEmail = (EditText) findViewById(R.id.password);
        btnRegister = (Button)findViewById(R.id.register);

        mAuth = FirebaseAuth.getInstance();
        reference = FirebaseDatabase.getInstance().getReference("admin/");

        registerAkun();
    }

    private void registerAkun() {
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username_admin = username.getText().toString();
                String email_admin = email.getText().toString();
                String password_admin = password.getText().toString();
                String password_email = passwordEmail.getText().toString();

                admin = new Admin(username_admin, password_admin, email_admin, password_email);

                if(TextUtils.isEmpty(username_admin)){
                    Toast.makeText(getApplicationContext(), "Anda lupa isi username", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(email_admin)){
                    Toast.makeText(getApplicationContext(), "Anda lupa isi email", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(password_admin)){
                    Toast.makeText(getApplicationContext(), "Anda lupa isi password admin", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(password_email)){
                    Toast.makeText(getApplicationContext(), "Anda lupa isi password email", Toast.LENGTH_SHORT).show();
                    return;
                }

                String adminId = reference.push().getKey();
                reference.child(adminId).setValue(admin);

                Toast.makeText(getApplicationContext(), email_admin, Toast.LENGTH_SHORT).show();

                mAuth.createUserWithEmailAndPassword(email_admin, password_email)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(getApplicationContext(), "Pembuatan akun admin berhasil", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                    startActivity(intent);
                                }
                                else{
                                    Toast.makeText(getApplicationContext(), "Pembuatan akun admin gagal", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}
