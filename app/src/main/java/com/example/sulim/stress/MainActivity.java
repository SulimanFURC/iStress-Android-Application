package com.example.sulim.stress;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity{

    Button btnlogin;
    EditText input_email,input_password;
    TextView btnsignup,btnForgotPass;

    RelativeLayout activity_main;
    FirebaseAuth auth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();

        btnlogin = (Button) findViewById(R.id.login_btn_login);
        input_email = (EditText)findViewById(R.id.login_email);
        input_password = (EditText)findViewById(R.id.login_password);
        btnsignup = (TextView) findViewById(R.id.login_btn_signup);
        btnForgotPass = (TextView)findViewById(R.id.login_btn_forgot_password);

        progressDialog = new ProgressDialog(this);

        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,RegistrationActivity.class));

            }
        });

        btnForgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ForgotPassword.class));
                finish();
            }
        });
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = input_email.getText().toString();
               final String password = input_password.getText().toString();
                if(TextUtils.isEmpty(email))
                {
                    Toast.makeText(MainActivity.this, "Email is Requiered", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password))
                {
                    Toast.makeText(MainActivity.this, "Password is Requiered", Toast.LENGTH_SHORT).show();
                    return;
                }
                //Display ProgressDialog here
                progressDialog.setMessage("Logging...");
                progressDialog.show();

                auth.signInWithEmailAndPassword(email,password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressDialog.dismiss();
                                if(!task.isSuccessful())
                                {
                                    if(password.length() < 6)
                                    {
                                        Toast.makeText(MainActivity.this, "Password should be greater than 6", Toast.LENGTH_SHORT).show();
                                    }
                                }
                                else
                                {
                                    startActivity(new Intent(MainActivity.this,Main2Activity.class));
                                }
                            }
                        });

            }
        });

      //  auth = FirebaseAuth.getInstance();
     //   if(auth.getCurrentUser()!=null)
     //   {
     //       startActivity(new Intent(MainActivity.this,Main2Activity.class));
     //   }
    }

/*    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.login_btn_forgot_password)
        {
            startActivity(new Intent(MainActivity.this,ForgotPassword.class));
            finish();
        }
        else if(view.getId() == R.id.login_btn_signup)
        {
            startActivity(new Intent(MainActivity.this,RegistrationActivity.class));
        }
        else if (view.getId() == R.id.login_btn_login)
        {
            loginuser(input_email.getText().toString(),input_password.getText().toString());
        }

    }*/

/*    private void loginuser(final String email, final String password) {
        auth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(!task.isSuccessful())
                        {
                            if(password.length() < 6)
                            {
                                Toast.makeText(MainActivity.this, "Password should be greater than 6", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else
                        {
                            startActivity(new Intent(MainActivity.this,Main2Activity.class));
                        }
                    }
                });

    }*/
}
