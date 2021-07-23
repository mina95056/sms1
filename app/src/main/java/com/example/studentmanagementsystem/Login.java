package com.example.studentmanagementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class Login extends AppCompatActivity {
    EditText Email,Password;
    Button loginLogin,loginCreateAccount;
    boolean valid=true;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Email=findViewById(R.id.loginEmail);
        Password=findViewById(R.id.loginPassword);
        loginLogin=findViewById(R.id.loginLogin);
        loginCreateAccount=findViewById(R.id.loginCreateAccount);

        fAuth=FirebaseAuth.getInstance();
        fStore=FirebaseFirestore.getInstance();

            loginLogin.setOnClickListener(new View.OnClickListener(){
               // public void onClick(View)
         //   });

           @Override
           public void onClick(View v) {
               fAuth.signInWithEmailAndPassword(loginEmail,loginPassword)
                       .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                   @Override
                   public void onComplete(@NonNull Task<AuthResult> task) {
                       if(task.isSuccessful()){
                           startActivity(new Intent(getApplicationContext(),MainActivity.class));
                       }
                   }
               });
           }
//                checkField(Email);
//                checkField(Password);
//               // if (valid){
//                   // fAuth.signInWithEmailAndPassword(Email.getText().toString(),Password.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
//                        @Override
//                  //      public void onSuccess(AuthResult authResult) {
//
//                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
//
//                        }
//                    }).addOnFailureListener(new OnFailureListener() {
//                        @Override
//                        public void onFailure(@NonNull Exception e) {
//
//                        }
//                    });
//                }
//            }
//        });


        loginCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Register.class));
            }
        });




    public boolean checkField(EditText textField) {
        if(textField.getText().toString().isEmpty()){
            textField.setError("Error");
            valid=false;
        }else{
            valid=true;
        }
        return valid;
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (FirebaseAuth.getInstance().getCurrentUser()!=null){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }
    }
}
    }
}