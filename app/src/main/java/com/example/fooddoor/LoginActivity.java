package com.example.fooddoor;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;   // ⭐ NEW
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.FirebaseApp;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class LoginActivity extends AppCompatActivity {

    EditText lusername, lpassword;
    Button loginBtn;
    TextView forgetbtn, signupbutton;
    ImageView imgGoogle, imgFacebook;

    FirebaseAuth mAuth;
    GoogleSignInClient mGoogleSignInClient;
    private static final int RC_SIGN_IN = 100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);
        setContentView(R.layout.activity_login);
        // Firebase Auth (Google ke liye)
        mAuth = FirebaseAuth.getInstance();

        // View Binding
        lusername = findViewById(R.id.lusername);
        lpassword = findViewById(R.id.lpassword);
        loginBtn = findViewById(R.id.loginbtn);
        forgetbtn = findViewById(R.id.forgetbtn);
        signupbutton = findViewById(R.id.signupbutton);
        imgGoogle = findViewById(R.id.imgGoogle);

        // ---------- GOOGLE SIGN-IN SETUP ----------
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        imgGoogle.setOnClickListener(v -> signInWithGoogle());

        // ---------- NORMAL LOGIN (ONLY VALIDATION + NAVIGATION) ----------
        loginBtn.setOnClickListener(v -> {
            String emailInput = lusername.getText().toString().trim();
            String passwordInput = lpassword.getText().toString().trim();

            if (validateInputs(emailInput, passwordInput)) {
                Toast.makeText(LoginActivity.this,
                        "Login Successfully Done", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // Signup Click
        signupbutton.setOnClickListener(v ->
                startActivity(new Intent(LoginActivity.this, SignupActivity.class)));

        // Forgot Password Click
        forgetbtn.setOnClickListener(v ->
                Toast.makeText(this, "Forgot password tapped", Toast.LENGTH_SHORT).show());
    }

    // -------- GOOGLE FLOW --------
    private void signInWithGoogle() {
        mGoogleSignInClient.signOut().addOnCompleteListener(this, task -> {
            Intent intent = mGoogleSignInClient.getSignInIntent();
            startActivityForResult(intent, RC_SIGN_IN);
        });
    }

    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnSuccessListener(authResult -> {
                    FirebaseUser user = mAuth.getCurrentUser();

                    // ⭐ YAHI SE PROFILE KE LIYE GOOGLE INFO SAVE HO RAHI HAI ⭐
                    if (user != null) {
                        SharedPreferences sp = getSharedPreferences("UserProfile", MODE_PRIVATE);
                        SharedPreferences.Editor ed = sp.edit();
                        ed.putString("name", user.getDisplayName());   // ProfileFragment -> name
                        ed.putString("email", user.getEmail());        // ProfileFragment -> email
                        // phone/address nahi hai Google se, to unko touch nahi kar rahe
                        ed.apply();
                    }

                    Toast.makeText(this,
                            "Welcome " + (user != null ? user.getEmail() : "user"),
                            Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                    finish();
                })
                .addOnFailureListener(e ->
                        Toast.makeText(this,
                                "Firebase Auth Failed: " + e.getMessage(),
                                Toast.LENGTH_SHORT).show());

    }

    // -------- ON ACTIVITY RESULT (ONLY GOOGLE) --------
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                Toast.makeText(this,
                        "Google Sign-In Failed: " + e.getStatusCode(),
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    // -------- VALIDATION (same as your old code) --------
    private boolean validateInputs(String username, String password) {
        if (username.isEmpty()) {
            lusername.setError("Please Enter Username");
            return false;
        }
        if (username.length() <= 8) {
            lusername.setError("Username must be more than 8 characters");
            return false;
        }
        if (!username.matches(".*[A-Z].*")) {
            lusername.setError("Username must contain at least one uppercase letter");
            return false;
        }
        if (!username.matches(".*[a-z].*")) {
            lusername.setError("Username must contain at least one lowercase letter");
            return false;
        }
        if (!username.matches(".*[!@#$%^&*()_+=|<>?{}\\[\\]~-].*")) {
            lusername.setError("Username must contain at least one special symbol");
            return false;
        }
        if (!username.matches(".*[0-9].*")) {
            lusername.setError("Username must contain at least one number");
            return false;
        }

        if (password.isEmpty()) {
            lpassword.setError("Please Enter Password");
            return false;
        }
        if (password.length() < 8) {
            lpassword.setError("Password must be at least 8 characters");
            return false;
        }
        if (!password.matches(".*[A-Z].*")) {
            lpassword.setError("Password must contain at least one uppercase letter");
            return false;
        }
        if (!password.matches(".*[a-z].*")) {
            lpassword.setError("Password must contain at least one lowercase letter");
            return false;
        }
        if (!password.matches(".*[!@#$%^&*()_+=|<>?{}\\[\\]~-].*")) {
            lpassword.setError("Password must contain at least one special symbol");
            return false;
        }
        if (!password.matches(".*[0-9].*")) {
            lpassword.setError("Password must contain at least one number");
            return false;
        }

        return true;
    }
}