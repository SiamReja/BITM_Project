package com.example.siam.tourmate_bitm_project.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.siam.tourmate_bitm_project.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Registration_Activity extends AppCompatActivity {

    EditText email_ET;
    EditText pass_ET;
    Button signUP;
    FirebaseAuth firebaseAuth;

    String email="";
    String pass="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_);

        email_ET=findViewById(R.id.email_reg_ET_ID);
        pass_ET=findViewById(R.id.pass_reg_ET_ID);
        signUP=findViewById(R.id.sign_UP_btn);
        firebaseAuth=FirebaseAuth.getInstance();


        signUP.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                email=email_ET.getText().toString();
                pass=pass_ET.getText().toString();

                Log.e("             ", "onCreate: email"+email);
                Log.e("             ", "onCreate: email"+pass);
                firebaseAuth.createUserWithEmailAndPassword(email,pass)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                startActivity(new Intent(Registration_Activity.this,Login_Activity.class));
                            }
                        });
            }
        });
    }


}
