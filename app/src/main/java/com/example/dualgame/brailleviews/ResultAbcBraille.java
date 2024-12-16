package com.example.dualgame.brailleviews;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.dualgame.R;
import com.example.dualgame.games.ConstantsAbcBraille;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class ResultAbcBraille extends AppCompatActivity {
    private static final String TAG = "ResultAbcBraille";

    // Etiqueta para los logs
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_result_braille);
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);

            // Obtener referencias a los elementos de la interfaz
            TextView tvScoreBraille = findViewById(R.id.tv_score);
            TextView tvCongratulations = findViewById(R.id.tv_congratulations); // Referencia al TextView de Felicitaciones
            Button btnFinish = findViewById(R.id.btn_finish_braille);
            ImageView icTrophy = findViewById(R.id.ic_trophy); // Referencia al ImageView

            // Obtener los datos pasados desde la actividad anterior
            int totalQuestions = getIntent().getIntExtra(ConstantsAbcBraille.TOTAL_QUESTIONS, 0);
            int correctAnswers = getIntent().getIntExtra(ConstantsAbcBraille.CORRECT_ANSWERS, 0);

            // Configurar el texto del puntaje
            tvScoreBraille.setText("Tu puntaje es " + correctAnswers + " de " + totalQuestions);


            //REGLA DE TRES

  /*Cálculo de medallas con regla de tres:
Se calcula el porcentaje de respuestas correctas y se asigna una medalla según el rango de ese porcentaje.
Si el porcentaje es 0%, no se otorgan medallas, pero se muestra un mensaje motivacional.
Evitar medallas en caso de 0 respuestas correctas:
Si percentage == 0, se asigna un mensaje indicando que no se obtuvieron respuestas correctas y se muestra un ícono de "sin medalla".*/





            // Determinar el mensaje basado en el puntaje

            int percentage = (int) ((double) correctAnswers / totalQuestions * 100);
            String message;
            int trophyImage; // Variable para determinar qué imagen mostrar
            final String medalla; // Cambiado a final


            if (percentage == 0) {
                // Generar un número aleatorio entre 1 y 9 para seleccionar un sticker
                int randomSticker = (int) (Math.random() * 9) + 1;

                // Usar el sticker aleatorio
                String stickerName = "sticker" + randomSticker;  // Nombre del archivo del sticker
                trophyImage = getResources().getIdentifier(stickerName, "drawable", getPackageName()); // Obtener el identificador de recurso

                message = "No obtuviste respuestas correctas.\n¡Sigue practicando!";
                medalla = null;  // Sin medalla

            } else if (percentage <= 33) {
                message = "Debes esforzarte más\n          ¡Tú puedes!";
                trophyImage = R.drawable.medalla_bronce; // Medalla bronce
                medalla = "Bronce";
            } else if (percentage <= 66) {
                message = "     \t¡Bien hecho!\n Estás mejorando";
                trophyImage = R.drawable.medalla_plata; // Medalla plata
                medalla = "Plata";
            } else if (percentage < 100) {
                message = "        ¡Felicidades!\n    Excelente trabajo";
                trophyImage = R.drawable.medalla_oro; // Medalla oro
                medalla = "Oro";
            } else { // 100% correcto
                message = "        ¡Perfecto!\n    ¡Eres un maestro!";
                trophyImage = R.drawable.medalla_oro; // Medalla oro
                medalla = "Oro";
            }


            // Configurar el mensaje dinámico y la imagen
            tvCongratulations.setText(message);
            icTrophy.setImageResource(trophyImage);


// Obtener el usuario autenticado
            FirebaseAuth mAuth = FirebaseAuth.getInstance();
            FirebaseUser currentUser = mAuth.getCurrentUser();

            if (currentUser != null) {
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                // Ahora creamos el módulo "Braille" si no existe y agregamos categorías dentro de él
                DocumentReference moduloRef = db.collection("usuarios")
                        .document(currentUser.getEmail()) // Aquí usas el correo del usuario como ID
                        .collection("modulos")  // Subcolección de módulos
                        .document("braille");   // Documento para el módulo "Braille"

                Log.d(TAG, "Referencia del módulo: " + moduloRef.getPath());  // Log para ver la ruta del módulo

                // Accedemos a la subcolección "categorias" dentro del módulo "Braille"
                DocumentReference categoriaRef = moduloRef.collection("categorias")
                        .document("abecedario");  // Esta categoría específica (como "vocales")

                Log.d(TAG, "Referencia de la categoría: " + categoriaRef.getPath());  // Log para ver la ruta de la categoría

                // Verificar si la categoría "vocales" ya existe
                categoriaRef.get().addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        if (!task.getResult().exists()) {
                            Log.d(TAG, "La categoría 'abecedario' no existe, inicializando...");
                            // Si la categoría no existe, inicializamos las categorías
                            inicializarCategorias(categoriaRef, medalla);
                        } else {
                            Log.d(TAG, "La categoría 'abecedario' ya existe, actualizando medallas...");
                            // Si la categoría ya existe, actualizamos la medalla
                            actualizarMedallas(categoriaRef, medalla);
                        }
                    } else {
                        // Manejo de error si la verificación falla
                        Log.e(TAG, "Error al verificar la categoría 'abecedario'", task.getException());
                    }
                });
            }

            // Configurar el listener para el botón de finalizar
            btnFinish.setOnClickListener(v -> {
                // Volver a SubVocalsActivity al presionar el botón
                Intent intent = new Intent(ResultAbcBraille.this, AbecedarioActivityBraille.class);
                startActivity(intent);
                finish();
            });
        }

    // Método para inicializar las categorías si no existen
    private void inicializarCategorias(DocumentReference categoriaRef, String medalla) {
        Map<String, Object> categorias = new HashMap<>();
        categorias.put("medallasBronce", 0);
        categorias.put("medallasPlata", 0);
        categorias.put("medallasOro", 0);

        // Guardar las categorías en Firestore
        categoriaRef.set(categorias)
                .addOnSuccessListener(aVoid -> {
                    // Una vez inicializadas las categorías, actualizar la medalla
                    Log.d(TAG, "Categorías inicializadas correctamente.");
                    actualizarMedallas(categoriaRef, medalla);  // Se puede cambiar según el puntaje
                })
                .addOnFailureListener(e -> {
                    // Manejo de error si la inicialización falla
                    Log.e(TAG, "Error al inicializar las categorías", e);
                });
    }

    // Método para actualizar las medallas
    private void actualizarMedallas(DocumentReference categoriaRef, String medalla) {

        if (medalla == null) {
            // Si no se asigna medalla, asignar un valor predeterminado
            medalla = "Ninguna";
            Log.d(TAG, "La medalla es null, se asigna 'Ninguna'.");
        }
        switch (medalla) {
            case "Bronce":
                categoriaRef.update("medallasBronce", FieldValue.increment(1));
                Log.d(TAG, "Medalla Bronce incrementada.");
                break;
            case "Plata":
                categoriaRef.update("medallasPlata", FieldValue.increment(1));
                Log.d(TAG, "Medalla Plata incrementada.");
                break;
            case "Oro":
                categoriaRef.update("medallasOro", FieldValue.increment(1));
                Log.d(TAG, "Medalla Oro incrementada.");
                break;

            default:
                Log.e(TAG, "Medalla desconocida: " + medalla);
                break;
        }
    }
}
