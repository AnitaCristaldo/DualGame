package com.example.dualgame.brailleviews;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dualgame.R;
import com.example.dualgame.games.ConstantsBraille;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class ResultActivityBraille extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_braille);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);

        // Obtener referencias a los elementos de la interfaz
        TextView tvScoreBraille = findViewById(R.id.tv_score);
        TextView tvCongratulations = findViewById(R.id.tv_congratulations);
        ImageView icTrophy = findViewById(R.id.ic_trophy); // Referencia al ImageView
        Button btnFinish = findViewById(R.id.btn_finish_braille);

        // Obtener los datos pasados desde la actividad anterior
        int totalQuestions = getIntent().getIntExtra(ConstantsBraille.TOTAL_QUESTIONS, 0);
        int correctAnswers = getIntent().getIntExtra(ConstantsBraille.CORRECT_ANSWERS, 0);

        // Configurar el texto del puntaje
        tvScoreBraille.setText("Tu puntaje es " + correctAnswers + " de " + totalQuestions);

        // Determinar el mensaje basado en el puntaje
        String message;
        int trophyImage; // Variable para determinar qué imagen mostrar
        final String medalla; // Cambiado a final
        if (correctAnswers <= 2) {
            message = "Debes esforzarte más, ¡tú puedes!";
            trophyImage = R.drawable.medalla_bronce; // Cambia este nombre según tu archivo
            medalla = "Bronce";  // Medalla bronce
        } else if (correctAnswers <= 4) {
            message = "¡Bien hecho! Estás mejorando.";
            trophyImage = R.drawable.medalla_plata; // Cambia este nombre según tu archivo
            medalla = "Plata";  // Medalla plata
        } else {
            message = "¡Felicidades! Excelente trabajo.";
            trophyImage = R.drawable.medalla_oro; // Cambia este nombre según tu archivo
            medalla = "Oro";  // Medalla oro
        }

        // Configurar el mensaje dinámico y la imagen
        tvCongratulations.setText(message);
        icTrophy.setImageResource(trophyImage);

        // Obtener el usuario autenticado
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser != null) {
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            // Definimos que la categoría es "vocales"
            DocumentReference categoriaRef = db.collection("usuarios")
                    .document(currentUser.getEmail()) // Aquí usas el correo del usuario como ID
                    .collection("categorias")
                    .document("vocales"); // Fijo como "vocales" para este módulo

            // Verificar si las categorías ya existen
            categoriaRef.get().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    if (!task.getResult().exists()) {
                        // Si no existen, inicializa las categorías
                        inicializarCategorias(categoriaRef);
                    } else {
                        // Si las categorías ya existen, actualizar la medalla
                        actualizarMedallas(categoriaRef, medalla);
                    }
                } else {
                    // Manejo de error si la verificación falla
                    // (Por ejemplo, si no se puede acceder a Firestore)
                    task.getException().printStackTrace();
                }
            });
        }

        // Configurar el listener para el botón de finalizar
        btnFinish.setOnClickListener(v -> {
            // Volver a SubVocalsActivity al presionar el botón
            Intent intent = new Intent(ResultActivityBraille.this, SubVocalsActivityBraille.class);
            startActivity(intent);
            finish();
        });
    }

    // Método para inicializar las categorías si no existen
    private void inicializarCategorias(DocumentReference categoriaRef) {
        Map<String, Object> categorias = new HashMap<>();
        categorias.put("medallasBronce", 0);
        categorias.put("medallasPlata", 0);
        categorias.put("medallasOro", 0);

        // Guardar las categorías en Firestore
        categoriaRef.set(categorias)
                .addOnSuccessListener(aVoid -> {
                    // Una vez inicializadas las categorías, actualizar la medalla
                    actualizarMedallas(categoriaRef, "Bronce");  // Se puede cambiar según el puntaje
                })
                .addOnFailureListener(e -> {
                    // Manejo de error si la inicialización falla
                    e.printStackTrace();
                });
    }

    // Método para actualizar las medallas
    private void actualizarMedallas(DocumentReference categoriaRef, String medalla) {
        switch (medalla) {
            case "Bronce":
                categoriaRef.update("medallasBronce", FieldValue.increment(1));
                break;
            case "Plata":
                categoriaRef.update("medallasPlata", FieldValue.increment(1));
                break;
            case "Oro":
                categoriaRef.update("medallasOro", FieldValue.increment(1));
                break;
        }
    }
}
