package com.example.sulim.stress;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;


public class Main2Activity extends AppCompatActivity {

   // private TextView txtWelcome;
    Button record_vid;
    Button logoutbtn;

    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

       // txtWelcome = (TextView) findViewById(R.id.dashboard_welcome);
        logoutbtn =(Button)findViewById(R.id.btn_logout);
        record_vid = (Button) findViewById(R.id.RecordVid);

        logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent I = new Intent(Main2Activity.this, MainActivity.class);
                startActivity(I);
            }
        });

        record_vid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_vid = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                startActivity(intent_vid);
            }
        });
       // record_vid.setOnClickListener(this);

        //init Firebase


        //Session check
      /*  if (auth.getCurrentUser() != null) {
            txtWelcome.setText("Welcome, " + auth.getCurrentUser().getEmail());
        }
*/
    }

/*    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.RecordVid)
        {
            CaptureVideo();
        }
        else if(v.getId() == R.id.btn_logout)
        {
            auth.signOut();
            //logoutUser();
        }

    }

    private void CaptureVideo() {
        Intent intent_vid = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        startActivity(intent_vid);
    }*/

/*    private void logoutUser() {
        auth.signOut();
        if(auth.getCurrentUser() == null)
        {
            startActivity(new Intent(Main2Activity.this,MainActivity.class));
            finish();
        }
    }*/

/*    private void logout() {
        auth.signOut();
        if (auth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(Main2Activity.this, MainActivity.class));

        }
    }*/
}
