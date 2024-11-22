package com.example.dualgame;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class LogrosDetalleActivity extends AppCompatActivity {

    private TextView tvMedallasBronce, tvMedallasPlata, tvMedallasOro;
    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logros_detalle);

        tvMedallasBronce = findViewById(R.id.tvMedallasBronce);
        tvMedallasPlata = findViewById(R.id.tvMedallasPlata);
        tvMedallasOro = findViewById(R.id.tvMedallasOro);
        btnBack = findViewById(R.id.btnBack);

        // Configurar el clic del botón "Regresar"
        btnBack.setOnClickListener(v -> {
            // Redirigir a la actividad de logros
            Intent intent = new Intent(LogrosDetalleActivity.this, LogrosActivity.class);
            startActivity(intent);
            finish(); // Cierra la actividad actual
        });

        // Obtener los datos de Firestore para el usuario
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        String userEmail = mAuth.getCurrentUser().getEmail();

        // Log para verificar el correo del usuario
        Log.d("LogrosDetalleActivity", "Correo del usuario: " + userEmail);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference userRef = db.collection("usuarios").document(userEmail);

        // Acceder a la subcolección de "modulos" para obtener el módulo "braille"
        userRef.collection("modulos").document("braille")
                .collection("categorias")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Log.d("LogrosDetalleActivity", "Consulta exitosa. Número de categorías obtenidas: " + task.getResult().size());

                        // Iterar sobre las categorías dentro del módulo "braille"
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            // Obtienes el nombre de la categoría y las medallas de cada categoría
                            String categoriaName = document.getId();
                            int medallasBronce = document.getLong("medallasBronce").intValue();
                            int medallasPlata = document.getLong("medallasPlata").intValue();
                            int medallasOro = document.getLong("medallasOro").intValue();

                            // Actualizar la vista con los datos de las medallas
                            tvMedallasBronce.setText("Bronce: " + medallasBronce);
                            tvMedallasPlata.setText("Plata: " + medallasPlata);
                            tvMedallasOro.setText("Oro: " + medallasOro);

                            // Log para cada categoría
                            Log.d("LogrosDetalleActivity", "Categoría: " + categoriaName + " - Medallas: " +
                                    "Bronce: " + medallasBronce + ", Plata: " + medallasPlata + ", Oro: " + medallasOro);
                        }
                    } else {
                        Log.e("LogrosDetalleActivity", "Error al obtener los datos", task.getException());
                    }
                });
    }
}
