package com.example.keteringapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    private FirebaseAuth mAuth;
    Toast back;
    private EditText etUsername,etPassword;
    Button Btnregis,Btnlogin;
    long timepress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Btnregis = findViewById(R.id.btnregis);
        Btnlogin = findViewById(R.id.btn_login);
        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
        mAuth = FirebaseAuth.getInstance();

        Btnregis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(Login.this, Register.class);
                startActivity(a);
                finish();
            }
        });
        Btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();


                if (username.equals("")) {
                    Toast.makeText(Login.this, "Silahkan isi username Anda", Toast.LENGTH_SHORT).show();
                } else if (password.equals("")) {
                    Toast.makeText(Login.this, "Silahkan isi password Anda", Toast.LENGTH_SHORT).show();
                } else {
                    mAuth.signInWithEmailAndPassword(username, password)
                            .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        Toast.makeText(Login.this, "Login Success.",
                                                Toast.LENGTH_SHORT).show();
                                        SaveLogin();
                                        Intent i = new Intent(Login.this,MainActivity.class);
                                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(i);
//                                        Intent inhome = new Intent(Login.this, MainActivity.class);
//                                        startActivity(inhome);
                                        finish();
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Toast.makeText(Login.this, "Login Failed.",
                                                Toast.LENGTH_SHORT).show();
                                    }

                                    // ...
                                }
                            });
                }
            }
        });

    }
    @Override
    public void onStart() {
        super.onStart();
        if ( Preferences.getInstance(this).login())
        {
            Intent i = new Intent(Login.this,MainActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
        }
        // Check if user is signed in (non-null) and update UI accordingly.
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        if (Preferences.getLoggedInStatus(getBaseContext())){
//            startActivity(new Intent(getBaseContext(),MainActivity.class));
//            finish();
//        }

    }

    @Override
    public void onBackPressed() {

        if (timepress + 2000>System.currentTimeMillis())
        {
            back.cancel();
            Intent i = new Intent(Intent.ACTION_MAIN);
            i.addCategory(Intent.CATEGORY_HOME);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);

        }
        else
        {
            back = Toast.makeText(getApplicationContext(),"Back press again to exit",Toast.LENGTH_LONG);
            back.show();
        }

        timepress = System.currentTimeMillis();
    }
    public void SaveLogin ()
    {
        String email = etUsername.getText().toString();
        String password =  etPassword.getText().toString();
        Preferences.getInstance(Login.this).SaveUser(email,password);
    }
    /** Menuju ke MainActivity dan Set User dan Status sedang login, di Preferences */
//    private void masuk(){
//        Preferences.setLoggedInUser(getBaseContext(),Preferences.getRegisteredUser(getBaseContext()));
//        Preferences.setLoggedInStatus(getBaseContext(),true);
//        startActivity(new Intent(getBaseContext(),MainActivity.class));finish();
//    }

    /** True jika parameter password sama dengan data password yang terdaftar dari Preferences */
//    private boolean cekPassword(String password){
//        return password.equals(Preferences.getRegisteredPass(getBaseContext()));
//    }

    /** True jika parameter user sama dengan data user yang terdaftar dari Preferences */
//    private boolean cekUser(String user){
//        return user.equals(Preferences.getRegisteredUser(getBaseContext()));
//    }
}
