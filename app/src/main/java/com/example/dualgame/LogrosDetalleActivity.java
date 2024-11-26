package com.example.dualgame;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dualgame.brailleviews.LevelsActivityBraille;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class LogrosDetalleActivity extends AppCompatActivity {

    private TextView tvCategoryTitle, tvMedallasVocalesBronce, tvMedallasVocalesPlata, tvMedallasVocalesOro;
    private TextView tvMedallasAbecedarioBronce, tvMedallasAbecedarioPlata, tvMedallasAbecedarioOro;
    private TextView tvMedallasNumerosBronce, tvMedallasNumerosPlata, tvMedallasNumerosOro;
    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logros_detalle);

        // Referenciar los TextViews de medallas
        tvMedallasVocalesBronce = findViewById(R.id.tvMedallasVocalesBronce);
        tvMedallasVocalesPlata = findViewById(R.id.tvMedallasVocalesPlata);
        tvMedallasVocalesOro = findViewById(R.id.tvMedallasVocalesOro);

        tvMedallasAbecedarioBronce = findViewById(R.id.tvMedallasAbecedarioBronce);
        tvMedallasAbecedarioPlata = findViewById(R.id.tvMedallasAbecedarioPlata);
        tvMedallasAbecedarioOro = findViewById(R.id.tvMedallasAbecedarioOro);

        tvMedallasNumerosBronce = findViewById(R.id.tvMedallasNumerosBronce);
        tvMedallasNumerosPlata = findViewById(R.id.tvMedallasNumerosPlata);
        tvMedallasNumerosOro = findViewById(R.id.tvMedallasNumerosOro);

        btnBack = findViewById(R.id.btnBack);

        // Configurar el clic del botón "Regresar"
        btnBack.setOnClickListener(v -> {
            // Redirigir a la actividad de logros
            Intent intent = new Intent(LogrosDetalleActivity.this, LevelsActivityBraille.class);
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
                            String categoriaName = document.getId();
                            int medallasBronce = document.getLong("medallasBronce").intValue();
                            int medallasPlata = document.getLong("medallasPlata").intValue();
                            int medallasOro = document.getLong("medallasOro").intValue();

                            // Actualizar la vista con los datos de las medallas según la categoría
                            if (categoriaName.equals("vocales")) {
                                tvMedallasVocalesBronce.setText("Bronce: " + medallasBronce);
                                tvMedallasVocalesPlata.setText("Plata: " + medallasPlata);
                                tvMedallasVocalesOro.setText("Oro: " + medallasOro);
                            } else if (categoriaName.equals("abecedario")) {
                                tvMedallasAbecedarioBronce.setText("Bronce: " + medallasBronce);
                                tvMedallasAbecedarioPlata.setText("Plata: " + medallasPlata);
                                tvMedallasAbecedarioOro.setText("Oro: " + medallasOro);
                            } else if (categoriaName.equals("numeros")) {
                                tvMedallasNumerosBronce.setText("Bronce: " + medallasBronce);
                                tvMedallasNumerosPlata.setText("Plata: " + medallasPlata);
                                tvMedallasNumerosOro.setText("Oro: " + medallasOro);
                            }

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
