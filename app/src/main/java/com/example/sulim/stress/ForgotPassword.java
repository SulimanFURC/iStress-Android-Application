package com.example.sulim.stress;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

public class ForgotPassword extends AppCompatActivity {

    EditText input_email;
    Button btnResetPass;
    TextView btnBack;

    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        auth = FirebaseAuth.getInstance();

        input_email = (EditText)findViewById(R.id.forgot_email);
        btnResetPass = (Button) findViewById(R.id.forgot_btn_reset);
        btnBack = (TextView)findViewById(R.id.forgot_btn_back);

        btnResetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              final String email = input_email.getText().toString().trim();
              if(TextUtils.isEmpty(email))
              {
                  Toast.makeText(ForgotPassword.this, "Your Must Enter Your Email", Toast.LENGTH_SHORT).show();
              }
              auth.sendPasswordResetEmail(input_email.getText().toString())
                      .addOnCompleteListener(new OnCompleteListener<Void>() {
                          @Override
                          public void onComplete(@NonNull Task<Void> task) {
                              if(task.isSuccessful())
                              {
                                  Toast.makeText(ForgotPassword.this, "We Have Sent password to"+email, Toast.LENGTH_LONG).show();
                              }
                              else{
                                  Toast.makeText(ForgotPassword.this, "Failed to send password", Toast.LENGTH_LONG).show();
                              }
                          }
                      });
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ForgotPassword.this,MainActivity.class));
            }
        });

    }

/*    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.forgot_btn_back)
        {
            startActivity(new Intent(this,MainActivity.class));
            finish();
        }
        else  if(v.getId() == R.id.forgot_btn_reset)
        {
            resetPassword(input_email.getText().toString());
        }

    }

    private void resetPassword(final String email) {
        auth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(ForgotPassword.this, "We Have Sent password to email:"+email, Toast.LENGTH_LONG).show();
                        }
                        else{
                            Toast.makeText(ForgotPassword.this, "Failed to send password", Toast.LENGTH_LONG).show();

                        }
                    }
                });
    }*/
}
