package com.example.user.pmipenggolongandarahadmin;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.pmipenggolongandarahadmin.mDatas.Admin;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {
    private EditText username;
    private EditText password;
    private Button btnRegister;
    private Button btnLogin;

    private FirebaseAuth mAuth;
    private DatabaseReference referenceRegister;
    private DatabaseReference referenceLogin;

    private Admin admin;
    private boolean isAkunAda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().setTitle("Login");

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        btnRegister = (Button)findViewById(R.id.register);
        btnLogin = (Button)findViewById(R.id.login);

        mAuth = FirebaseAuth.getInstance();
        referenceRegister = FirebaseDatabase.getInstance().getReference();
        referenceLogin = FirebaseDatabase.getInstance().getReference("admin/");

        registerAkunClick();

        loginAkun();
    }

    private void loginAkun() {
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        referenceLogin.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    admin = snapshot.getValue(Admin.class);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username_admin = username.getText().toString();
                String password_admin = password.getText().toString();
                String email_admin =  admin.getEmail();
                String password_email = admin.getPassword_email();


                if(TextUtils.isEmpty(username_admin)){
                    Toast.makeText(getApplicationContext(), "Anda lupa isi username", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(password_admin)){
                    Toast.makeText(getApplicationContext(), "Anda lupa isi password", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.signInWithEmailAndPassword(email_admin, password_email)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(getApplicationContext(), "Login berhasil", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    startActivity(intent);
                                }else{
                                    Toast.makeText(getApplicationContext(), "Login gagal", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }

    private void registerAkunClick() {
        isAkunAda = false;

        referenceRegister.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("admin")){
                    isAkunAda = true;
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isAkunAda == false){
                    Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(),"Anda sudah memiliki akun admin!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
