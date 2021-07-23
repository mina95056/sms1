package com.example.studentmanagementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;



public class Register extends AppCompatActivity {
    EditText fullName,Email,Password,Phone;
    Button registerRegister,registerLogin;
    boolean valid = true;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        fAuth=FirebaseAuth.getInstance();
        fStore=FirebaseFirestore.getInstance();

        fullName=findViewById(R.id.registerName);
        Email=findViewById(R.id.registeremail);
        Password=findViewById(R.id.registerPassword);
        Phone=findViewById(R.id.registerPhone);
        registerRegister=findViewById(R.id.registerLogin);
        registerLogin=findViewById(R.id.registerLogin);


        registerRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkField(fullName);
                checkField(Email);
                checkField(Password);
                checkField(Phone);


                if (valid){
                    //Start the user registration process
                    fAuth.createUserWithEmailAndPassword(Email.getText().toString(),Password.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            FirebaseUser user=fAuth.getCurrentUser();
                            Toast.makeText(Register.this,"Account Created",Toast.LENGTH_SHORT).show();
                            DocumentReference df=fStore.collection("Users").document(user.getUid());
                            Map<String,Object>userInfo=new HashMap<>();
                            userInfo.put("FullName",fullName.getText().toString());
                            userInfo.put("UserEmail",Email.getText().toString());
                            userInfo.put("PhoneNumber",Phone.getText().toString());
                            //Specify if the user is Admin


                            userInfo.put("isUser","1");

                            df.set(userInfo);

                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                            finish();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Register.this,"Failed to create Account",Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });


        registerLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Login.class));
            }
        });
        registerRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });

    }


    public boolean checkField(EditText textField) {
        if (textField.getText().toString().isEmpty()){
            textField.setError("Error");
            valid=false;
        }else{
            valid=true;
        }
        return valid;
    }
}