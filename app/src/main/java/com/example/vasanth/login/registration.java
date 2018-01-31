package com.example.vasanth.login;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class registration extends AppCompatActivity {
    private EditText userName,userPassword,userEmail;
    private Button regbutton;
    private TextView userLogin;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        setupUIViews();
        firebaseAuth = FirebaseAuth.getInstance();

        regbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()) {
                    String user_email = userEmail.getText().toString().trim();
                    String user_password = userPassword.getText().toString().trim();
                    firebaseAuth.createUserWithEmailAndPassword(user_email,user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(registration.this, "registration sucessfull", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(registration.this, MainActivity.class));
                            } else {
                                Toast.makeText(registration.this, "registration sucessfull", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }
            }
        });
        userLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(registration.this,MainActivity.class));
            }
        });


    }
    private  void  setupUIViews(){
        userName = (EditText)findViewById(R.id.etusername);
        userPassword = (EditText)findViewById(R.id.etpassword);
        userEmail = (EditText)findViewById(R.id.etuseremail);
        regbutton= (Button)findViewById(R.id.btnregister);
        userLogin=(TextView)findViewById(R.id.tvlogin);

    }
    private  Boolean validate()
    {
        Boolean result = false;
        String name = userName.getText().toString();
        String password = userPassword.getText().toString();
        String email = userEmail.getText().toString();

        if(name.isEmpty() ||  password.isEmpty() || email.isEmpty()){
            Toast.makeText(this,"please enter all the details",Toast.LENGTH_SHORT).show();
        }
        else{
            result=true;
        }
        return result;
    }
}
