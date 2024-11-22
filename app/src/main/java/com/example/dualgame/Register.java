package com.example.dualgame;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {

    TextInputEditText editTextEmail, editTextPassword, editTextName, editTextEdad;
    Button buttonReg;
    FirebaseAuth mAuth;
    ProgressBar progressBar;
    TextView textView;
    ImageView togglePasswordVisibility;
    FirebaseFirestore db;

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        editTextName = findViewById(R.id.name);
        editTextEdad = findViewById(R.id.edad);
        editTextEmail = findViewById(R.id.email);
        editTextPassword = findViewById(R.id.password);
        buttonReg = findViewById(R.id.btn_register);
        progressBar = findViewById(R.id.progressBar);
        textView = findViewById(R.id.loginNow);
        togglePasswordVisibility = findViewById(R.id.togglePasswordVisibility);

        textView.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), Login.class));
            finish();
        });

        buttonReg.setOnClickListener(view -> {
            progressBar.setVisibility(View.VISIBLE);
            String nombre = editTextName.getText().toString();
            String email = editTextEmail.getText().toString();
            String password = editTextPassword.getText().toString();
            String edadStr = editTextEdad.getText().toString();

            if (TextUtils.isEmpty(nombre)) {
                showToast("Ingresa tu nombre");
                return;
            }
            if (TextUtils.isEmpty(edadStr)) {
                showToast("Ingresa tu edad");
                return;
            }
            if (TextUtils.isEmpty(email)) {
                showToast("Ingresa el email");
                return;
            }
            if (TextUtils.isEmpty(password)) {
                showToast("Ingresa la contraseña");
                return;
            }

            int edad = Integer.parseInt(edadStr);

            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(task -> {
                        progressBar.setVisibility(View.GONE);
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            saveUserDataToFirestore(user.getUid(), new Usuario(nombre, edad, email, Timestamp.now()));

                            showToast("Cuenta creada.");
                            startActivity(new Intent(getApplicationContext(), Login.class));
                            finish();
                        } else {
                            showToast("Error al registrarse");
                        }
                    });
        });

        togglePasswordVisibility.setOnClickListener(v -> togglePasswordVisibility());
    }

    private void togglePasswordVisibility() {
        if (editTextPassword.getInputType() == (android.text.InputType.TYPE_CLASS_TEXT | android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD)) {
            editTextPassword.setInputType(android.text.InputType.TYPE_CLASS_TEXT | android.text.InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            togglePasswordVisibility.setImageResource(R.drawable.eye_on);
            togglePasswordVisibility.clearColorFilter();
        } else {
            editTextPassword.setInputType(android.text.InputType.TYPE_CLASS_TEXT | android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD);
            togglePasswordVisibility.setImageResource(R.drawable.eye_off);
            togglePasswordVisibility.setColorFilter(ContextCompat.getColor(this, R.color.gray));
        }
        editTextPassword.setSelection(editTextPassword.getText().length());
    }

    private void saveUserDataToFirestore(String userId, Usuario usuario) {
        db.collection("usuarios").document(userId)
                .set(usuario)
                .addOnSuccessListener(aVoid -> showToast("Datos guardados en Firestore."))
                .addOnFailureListener(e -> showToast("Error al guardar en Firestore: " + e.getMessage()));
    }

    private void showToast(String message) {
        Toast.makeText(Register.this, message, Toast.LENGTH_SHORT).show();
        progressBar.setVisibility(View.GONE);
    }
}
