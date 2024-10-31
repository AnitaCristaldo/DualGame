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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {

    TextInputEditText editTextEmail;
    TextInputEditText editTextPassword;
    Button buttonReg;
    FirebaseAuth mAuth;
    ProgressBar progressBar;
    TextView textView;
    ImageView togglePasswordVisibility;
    FirebaseFirestore db; // Inicializa Firestore

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class); // Register
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance(); // Inicializa Firestore
        editTextEmail = findViewById(R.id.email);
        editTextPassword = findViewById(R.id.password);
        buttonReg = findViewById(R.id.btn_register);
        progressBar = findViewById(R.id.progressBar);
        textView = findViewById(R.id.loginNow);
        togglePasswordVisibility = findViewById(R.id.togglePasswordVisibility);

        textView.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
            finish();
        });

        buttonReg.setOnClickListener(view -> {
            progressBar.setVisibility(View.VISIBLE);
            String email, password;
            email = String.valueOf(editTextEmail.getText());
            password = String.valueOf(editTextPassword.getText());

            if (TextUtils.isEmpty(email)) {
                Toast.makeText(Register.this, "Ingresa el email", Toast.LENGTH_SHORT).show();
                return;
            }

            if (TextUtils.isEmpty(password)) {
                Toast.makeText(Register.this, "Ingresa la contraseña", Toast.LENGTH_SHORT).show();
                return;
            }

            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(task -> {
                        progressBar.setVisibility(View.GONE);
                        if (task.isSuccessful()) {
                            // Guardar el email en Firestore
                            FirebaseUser user = mAuth.getCurrentUser();
                            saveUserEmailToFirestore(user.getUid(), email);

                            Toast.makeText(Register.this, "Cuenta creada.", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), Login.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(Register.this, "Correo o Contraseña Incorrecta", Toast.LENGTH_SHORT).show();
                        }
                    });
        });

        togglePasswordVisibility.setOnClickListener(v -> {
            if (editTextPassword.getInputType() == (android.text.InputType.TYPE_CLASS_TEXT | android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD)) {
                // Cambiar a texto visible
                editTextPassword.setInputType(android.text.InputType.TYPE_CLASS_TEXT | android.text.InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                togglePasswordVisibility.setImageResource(R.drawable.eye_on); // Cambiar a eye_on.png
                togglePasswordVisibility.clearColorFilter(); // Remover el color gris cuando es visible
            } else {
                // Cambiar a texto oculto
                editTextPassword.setInputType(android.text.InputType.TYPE_CLASS_TEXT | android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD);
                togglePasswordVisibility.setImageResource(R.drawable.eye_off); // Cambiar a eye_off.png
                togglePasswordVisibility.setColorFilter(ContextCompat.getColor(this, R.color.gray)); // Aplicar color gris
            }
            // Mueve el cursor al final del texto
            editTextPassword.setSelection(editTextPassword.getText().length());
        });
    }

    // Método para guardar el email en Firestore
    private void saveUserEmailToFirestore(String userId, String email) {
        // Crear un mapa para almacenar los datos del usuario
        Map<String, Object> user = new HashMap<>();
        user.put("email", email);

        // Guardar el mapa en la colección "usuarios"
        db.collection("usuarios").document(userId)
                .set(user)
                .addOnSuccessListener(aVoid -> {
                    // Éxito al guardar
                    Toast.makeText(Register.this, "Email guardado en Firestore.", Toast.LENGTH_SHORT).show();
                })
                .addOnFailureListener(e -> {
                    // Error al guardar
                    Toast.makeText(Register.this, "Error al guardar el email en Firestore: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }
}
