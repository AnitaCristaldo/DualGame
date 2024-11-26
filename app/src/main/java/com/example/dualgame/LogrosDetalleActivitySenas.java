package com.example.dualgame;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dualgame.singviews.LevelsActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class LogrosDetalleActivitySenas extends AppCompatActivity {

    private TextView tvCategoryTitle, tvMedallasVocalesBronceSenas, tvMedallasVocalesPlataSenas, tvMedallasVocalesOroSenas;
    private TextView tvMedallasAbecedarioBronceSenas, tvMedallasAbecedarioPlataSenas, tvMedallasAbecedarioOroSenas;
    private TextView tvMedallasNumerosBronceSenas, tvMedallasNumerosPlataSenas, tvMedallasNumerosOroSenas;
    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logros_detalle_senas);

        // Referenciar los TextViews de medallas
        tvMedallasVocalesBronceSenas = findViewById(R.id.tvMedallasVocalesBronceSenas);
        tvMedallasVocalesPlataSenas = findViewById(R.id.tvMedallasVocalesPlataSenas);
        tvMedallasVocalesOroSenas = findViewById(R.id.tvMedallasVocalesOroSenas);

        tvMedallasAbecedarioBronceSenas = findViewById(R.id.tvMedallasAbecedarioBronceSenas);
        tvMedallasAbecedarioPlataSenas = findViewById(R.id.tvMedallasAbecedarioPlataSenas);
        tvMedallasAbecedarioOroSenas = findViewById(R.id.tvMedallasAbecedarioOroSenas);

        tvMedallasNumerosBronceSenas = findViewById(R.id.tvMedallasNumerosBronceSenas);
        tvMedallasNumerosPlataSenas = findViewById(R.id.tvMedallasNumerosPlataSenas);
        tvMedallasNumerosOroSenas = findViewById(R.id.tvMedallasNumerosOroSenas);

        btnBack = findViewById(R.id.btnBack);

        // Configurar el clic del botón "Regresar"
        btnBack.setOnClickListener(v -> {
            // Redirigir a la actividad de logros
            Intent intent = new Intent(LogrosDetalleActivitySenas.this, LevelsActivity.class);
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
        userRef.collection("modulos").document("senas")
                .collection("categorias")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Log.d("LogrosDetalleActivitySenas", "Consulta exitosa. Número de categorías obtenidas: " + task.getResult().size());

                        // Iterar sobre las categorías dentro del módulo "braille"
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            String categoriaName = document.getId();
                            int medallasBronce = document.getLong("medallasBronce").intValue();
                            int medallasPlata = document.getLong("medallasPlata").intValue();
                            int medallasOro = document.getLong("medallasOro").intValue();

                            // Actualizar la vista con los datos de las medallas según la categoría
                            if (categoriaName.equals("vocales")) {
                                tvMedallasVocalesBronceSenas.setText("Bronce: " + medallasBronce);
                                tvMedallasVocalesPlataSenas.setText("Plata: " + medallasPlata);
                                tvMedallasVocalesOroSenas.setText("Oro: " + medallasOro);
                            } else if (categoriaName.equals("abecedario")) {
                                tvMedallasAbecedarioBronceSenas.setText("Bronce: " + medallasBronce);
                                tvMedallasAbecedarioPlataSenas.setText("Plata: " + medallasPlata);
                                tvMedallasAbecedarioOroSenas.setText("Oro: " + medallasOro);
                            } else if (categoriaName.equals("numeros")) {
                                tvMedallasNumerosBronceSenas.setText("Bronce: " + medallasBronce);
                                tvMedallasNumerosPlataSenas.setText("Plata: " + medallasPlata);
                                tvMedallasNumerosOroSenas.setText("Oro: " + medallasOro);
                            }

                            // Log para cada categoría
                            Log.d("LogrosDetalleActivitySenas", "Categoría: " + categoriaName + " - Medallas: " +
                                    "Bronce: " + medallasBronce + ", Plata: " + medallasPlata + ", Oro: " + medallasOro);
                        }
                    } else {
                        Log.e("LogrosDetalleActivitySenas", "Error al obtener los datos", task.getException());
                    }
                });
    }
}
