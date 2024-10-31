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

public class Login extends AppCompatActivity {

    TextInputEditText editTextEmail;
    TextInputEditText editTextPassword;
    Button buttonLogin;
    ProgressBar progressBar;
    TextView textView;
    ImageView togglePasswordVisibility;

    FirebaseAuth mAuth;

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        editTextEmail = findViewById(R.id.email);
        editTextPassword = findViewById(R.id.password);
        buttonLogin = findViewById(R.id.btn_login);
        progressBar = findViewById(R.id.progressBar);
        textView = findViewById(R.id.registerNow);
        togglePasswordVisibility = findViewById(R.id.togglePasswordVisibility);

        textView.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), Register.class);
            startActivity(intent);
            finish();
        });

        buttonLogin.setOnClickListener(view -> {
            progressBar.setVisibility(View.VISIBLE);
            String email = String.valueOf(editTextEmail.getText());
            String password = String.valueOf(editTextPassword.getText());
            if (TextUtils.isEmpty(email)) {
                Toast.makeText(Login.this, "Ingresa el email", Toast.LENGTH_SHORT).show();
                return;
            }

            if (TextUtils.isEmpty(password)) {
                Toast.makeText(Login.this, "Ingresa la contraseña", Toast.LENGTH_SHORT).show();
                return;
            }

            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(task -> {
                        progressBar.setVisibility(View.GONE);
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Inicio de sesión Exitosa", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(Login.this, "Correo o contraseña incorrecta", Toast.LENGTH_SHORT).show();
                        }
                    });
        });

        togglePasswordVisibility.setOnClickListener(v -> {
            if (editTextPassword.getInputType() == (android.text.InputType.TYPE_CLASS_TEXT | android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD)) {
                // Cambiar a texto visible
                editTextPassword.setInputType(android.text.InputType.TYPE_CLASS_TEXT | android.text.InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                togglePasswordVisibility.setImageResource(R.drawable.eye_on); // Cambiar a eye_on.png
                // Quitar tintado si el ícono eye on no debería tenerlo
                togglePasswordVisibility.clearColorFilter();
            } else {
                // Cambiar a texto oculto
                editTextPassword.setInputType(android.text.InputType.TYPE_CLASS_TEXT | android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD);
                togglePasswordVisibility.setImageResource(R.drawable.eye_off); // Cambiar a eye_off.png
                // Aplicar el color gris al ícono eye off
                togglePasswordVisibility.setColorFilter(ContextCompat.getColor(this, R.color.gray));
            }
            // Mueve el cursor al final del texto
            editTextPassword.setSelection(editTextPassword.getText().length());

        });



}}