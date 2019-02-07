package com.example.sulim.stress;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrationActivity extends AppCompatActivity{

    Button btnSignup;
    TextView btnLogin, btnForgotPass;
    EditText input_email,input_pass;
    ProgressDialog progressDialog;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

     auth = FirebaseAuth.getInstance();
     btnSignup = (Button)findViewById(R.id.signup_btn_register);
     btnLogin = (TextView) findViewById(R.id.signup_btn_login);
     btnForgotPass = (TextView)findViewById(R.id.signup_btn_forgot_pass);
     input_email = (EditText)findViewById(R.id.signup_email);
     input_pass = (EditText)findViewById(R.id.signup_password);

     progressDialog = new ProgressDialog(this);

     btnSignup.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {

             String email = input_email.getText().toString();
             String password = input_pass.getText().toString();

             if(TextUtils.isEmpty(email))
             {
                 Toast.makeText(RegistrationActivity.this, "Email is Required...", Toast.LENGTH_SHORT).show();
             }
             if(TextUtils.isEmpty(password))
             {
                 Toast.makeText(RegistrationActivity.this, "Password is Required...", Toast.LENGTH_SHORT).show();
             }

             progressDialog.setMessage("Registering Please Wait...");
             progressDialog.show();

             auth.createUserWithEmailAndPassword(email,password)
                     .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                         @Override
                         public void onComplete(@NonNull Task<AuthResult> task) {

                             if(!task.isSuccessful())
                             {
                                 Toast.makeText(RegistrationActivity.this, "Error"+task.getException(), Toast.LENGTH_SHORT).show();
                             }
                             else
                             {
                                 //Toast.makeText(RegistrationActivity.this, "Register Successful..", Toast.LENGTH_SHORT).show();
                                 startActivity(new Intent(RegistrationActivity.this,Main2Activity.class));
                             }
                             progressDialog.dismiss();

                         }
                     });

         }
     });
     btnLogin.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             startActivity(new Intent(RegistrationActivity.this,MainActivity.class));
             finish();
         }
     });
     btnForgotPass.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             startActivity(new Intent(RegistrationActivity.this,ForgotPassword.class));
             finish();
         }
     });

     auth = FirebaseAuth.getInstance();
    }

/*    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.signup_btn_login)
        {
            startActivity(new Intent(RegistrationActivity.this,MainActivity.class));
            finish();
        }
        else if(v.getId() == R.id.signup_btn_forgot_pass)
        {
            startActivity(new Intent(RegistrationActivity.this,ForgotPassword.class));
            finish();
        }
        else if(v.getId() == R.id.signup_btn_register)
        {
           registerUser(input_email.getText().toString(),input_pass.getText().toString());
        }


    }

    private void registerUser(final String email, final String password) {
        auth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(!task.isSuccessful())
                        {
                            Toast.makeText(RegistrationActivity.this, "Error"+task.getException(), Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(RegistrationActivity.this, "Register Successful..", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegistrationActivity.this,Main2Activity.class));
                        }
                    }

                });
    }*/
}
