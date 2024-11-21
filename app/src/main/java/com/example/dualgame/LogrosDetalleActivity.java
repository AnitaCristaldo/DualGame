package com.example.dualgame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class LogrosDetalleActivity extends AppCompatActivity {

    private TextView tvMedallasBronce, tvMedallasPlata, tvMedallasOro;
    private String categoriaSeleccionada;
    private Button btnBack; // Declaración del botón

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logros_detalle);

        tvMedallasBronce = findViewById(R.id.tvMedallasBronce);
        tvMedallasPlata = findViewById(R.id.tvMedallasPlata);
        tvMedallasOro = findViewById(R.id.tvMedallasOro);
        btnBack = findViewById(R.id.btnBack); // Vincular el botón del layout

        categoriaSeleccionada = getIntent().getStringExtra("categoria");

        // Configurar el clic del botón "Regresar"
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirigir a la actividad de logros
                Intent intent = new Intent(LogrosDetalleActivity.this, LogrosActivity.class);
                startActivity(intent);
                finish(); // Cierra la actividad actual
            }
        });

        // Obtener los datos de Firestore para el usuario
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        String userEmail = mAuth.getCurrentUser().getEmail();

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference userRef = db.collection("usuarios").document(userEmail);

        // Escuchar los cambios en tiempo real para la categoría seleccionada
        userRef.collection("categorias").document(categoriaSeleccionada)
                .addSnapshotListener((documentSnapshot, error) -> {
                    if (error != null) {
                        // Manejar el error
                        return;
                    }

                    if (documentSnapshot != null && documentSnapshot.exists()) {
                        int medallasBronce = documentSnapshot.getLong("medallasBronce").intValue();
                        int medallasPlata = documentSnapshot.getLong("medallasPlata").intValue();
                        int medallasOro = documentSnapshot.getLong("medallasOro").intValue();

                        // Actualizar las vistas con los valores de las medallas
                        tvMedallasBronce.setText(String.valueOf(medallasBronce));
                        tvMedallasPlata.setText(String.valueOf(medallasPlata));
                        tvMedallasOro.setText(String.valueOf(medallasOro));
                    }
                });
    }
}
