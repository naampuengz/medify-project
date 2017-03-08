package com.example.airy.medifyseniorproject;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextPaint;
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
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;


/**
 * Created by airy on 3/7/2017.
 */
public class AuthenticationActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "SignInEmailPassword";



    private TextView mStatusTextview;
    private TextView mDetailTextview;


    private EditText emailField;
    private EditText passwordField;

    private FirebaseAuth mAuth;

    private FirebaseAuth.AuthStateListener mAuthListener;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentification);

        //show status & detail
        mStatusTextview = (TextView)findViewById(R.id.status);
        mDetailTextview = (TextView)findViewById(R.id.detail);


        //email & password field
        emailField = (EditText)findViewById(R.id.field_email);
        passwordField = (EditText)findViewById(R.id.field_password);


        //button
        findViewById(R.id.email_sign_in_button).setOnClickListener(this);
        findViewById(R.id.email_create_account_button).setOnClickListener(this);

        //initial auth firebase
        mAuth = FirebaseAuth.getInstance();


        //auth listener
        mAuthListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user != null){
                    //user signed in
                    Log.d(TAG,"onAuthStateChanged:signed_in:"+user.getUid());
                } else {
                    //user signed out
                    Log.d(TAG,"onAuthStateChanged:signed_out:");
                }

                updateUI(user);
            }
        };

    }

    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }


    //if user didn't have an account  then sign up
    public void createAccount(String email, String password){
        Log.d(TAG,"create Account for medify : "+email);

        if(!validateForm()){
            return;
        }



        //create user with email
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG,"createUser with email-passworg: Complete : "+ task.isSuccessful());


                        if(!task.isSuccessful()){
                            Toast.makeText(AuthenticationActivity.this,R.string.auth_failed,
                                    Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }

    private void signIn(String email,String password){
        Log.d(TAG,"SIGN IN:"+email);
        if(!validateForm()){
            return;
        }


        //sign in with e-mail
        mAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG,"signIn with Email : onComplete:"+task.isSuccessful());


                        if(!task.isSuccessful()){
                            Log.w(TAG,"signIn with Email : failed",task.getException());
                            Toast.makeText(AuthenticationActivity.this,R.string.auth_failed,
                                Toast.LENGTH_SHORT).show();
                        }

                        if(!task.isSuccessful()){
                            mStatusTextview.setText(R.string.auth_failed);
                        }

                    }
                });

    }

    private void signOut(){
        mAuth.signOut();
        updateUI(null);
    }

    private void sendEmailVeification(){

        findViewById(R.id.verify_email_button).setEnabled(false);

        //send email verification
        final FirebaseUser user = mAuth.getCurrentUser();
        user.sendEmailVerification()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        findViewById(R.id.verify_email_button).setEnabled(false);

                        if(task.isSuccessful()){
                            Toast.makeText(AuthenticationActivity.this,
                                    "Verification email sent to :"+user.getEmail(),
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Log.e(TAG,"send email validateion",task.getException());
                            Toast.makeText(AuthenticationActivity.this,
                                    "Failed to send varification to : "+user.getEmail(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private boolean validateForm() {

        boolean validate = true;

        String email = emailField.getText().toString();
        if(TextUtils.isEmpty(email)){
            emailField.setError("Email Required!");
            validate = false;
        } else {
            emailField.setError(null);
        }

        String password = passwordField.getText().toString();
        if(TextUtils.isEmpty(password)){
            passwordField.setError("Password Required!");
            validate = false;
        } else {
            passwordField.setError(null);
        }
        return validate;
    }

    private void updateUI(FirebaseUser user) {

        if (user != null) {
            mStatusTextview.setText(getString(R.string.emailpassword_status_fmt,
                    user.getEmail(), user.isEmailVerified()));
            mDetailTextview.setText(getString(R.string.firebase_status_fmt, user.getUid()));

            findViewById(R.id.email_password_buttons).setVisibility(View.GONE);
            findViewById(R.id.email_password_fields).setVisibility(View.GONE);
            findViewById(R.id.signed_in_buttons).setVisibility(View.VISIBLE);

            findViewById(R.id.verify_email_button).setEnabled(!user.isEmailVerified());
        } else {
            mStatusTextview.setText(R.string.signed_out);
            mDetailTextview.setText(null);

            findViewById(R.id.email_password_buttons).setVisibility(View.VISIBLE);
            findViewById(R.id.email_password_fields).setVisibility(View.VISIBLE);
            findViewById(R.id.signed_in_buttons).setVisibility(View.GONE);
        }
    }


    @Override
    public void onClick(View view) {

        int i = view.getId();
        if (i == R.id.email_create_account_button) {
            createAccount(emailField.getText().toString(), passwordField.getText().toString());
        } else if (i == R.id.email_sign_in_button) {
            signIn(emailField.getText().toString(), passwordField.getText().toString());
        } else if (i == R.id.sign_out_button) {
            signOut();
        } else if (i == R.id.verify_email_button) {
            sendEmailVeification();
        }
    }
}