package com.example.dualgame.singviews;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dualgame.R;
import com.example.dualgame.brailleviews.AbecedarioActivityBraille;
import com.example.dualgame.brailleviews.ResultAbcBraille;
import com.example.dualgame.games.Constants;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class ResultAbcSenas extends AppCompatActivity {

    private static final String TAG = "ResultActivity"; // Etiqueta para los logs

    //ESTE RESULT O PANTALLA LE PERTENECE A CUESTIONARIO DE VOCALES DE LENGUA DE SEÑAS
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Llama al método onCreate de la superclase (AppCompatActivity) para realizar la inicialización estándar.

        setContentView(R.layout.activity_result);
        // Establece el diseño visual de la actividad usando el archivo de diseño XML activity_result.

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        // Configura la ventana para que la actividad se muestre en pantalla completa, ocultando la barra de estado.

        // Obtener referencias a las vistas en el diseño XML
        TextView tvScore = findViewById(R.id.tv_score);
        // Encuentra y guarda una referencia al TextView con el ID "tv_score" para mostrar el puntaje.

        TextView tvCongratulations = findViewById(R.id.tv_congratulations); // Referencia al TextView de Felicitaciones
        ImageView icTrophy = findViewById(R.id.ic_trophy); // Referencia al ImageView
        Button btnFinish = findViewById(R.id.btn_finish);
        // Encuentra y guarda una referencia al Button con el ID "btn_finish" para manejar la acción de finalizar.

        // Obtener los datos pasados desde la actividad anterior (QuizQuestionsActivity)
        int totalQuestions = getIntent().getIntExtra(ConstantsAbc.TOTAL_QUESTIONS, 0);
        // Obtiene el valor entero de "TOTAL_QUESTIONS" enviado por la actividad anterior. Si no se encuentra, el valor por defecto es 0.

        int correctAnswers = getIntent().getIntExtra(ConstantsAbc.CORRECT_ANSWERS, 0);
        // Obtiene el valor entero de "CORRECT_ANSWERS" enviado por la actividad anterior. Si no se encuentra, el valor por defecto es 0.

        // Configurar el texto del puntaje en la interfaz
        tvScore.setText("Tu puntaje es " + correctAnswers + " de " + totalQuestions);
        // Establece el texto del TextView para mostrar el puntaje obtenido, mostrando la cantidad de respuestas correctas de un total de preguntas.



        //REGLA DE TRES

  /*Cálculo de medallas con regla de tres:
Se calcula el porcentaje de respuestas correctas y se asigna una medalla según el rango de ese porcentaje.
Si el porcentaje es 0%, no se otorgan medallas, pero se muestra un mensaje motivacional.
Evitar medallas en caso de 0 respuestas correctas:
Si percentage == 0, se asigna un mensaje indicando que no se obtuvieron respuestas correctas y se muestra un ícono de "sin medalla".*/






        int percentage = (int) ((double) correctAnswers / totalQuestions * 100); // Calcular porcentaje

        String message;
        int trophyImage; // Variable para determinar qué imagen mostrar
        final String medalla; // Medalla asignada

        if (percentage == 0) {
            // Generar un número aleatorio entre 1 y 9 para seleccionar un sticker
            int randomSticker = (int) (Math.random() * 9) + 1;

            // Usar el sticker aleatorio
            String stickerName = "sticker" + randomSticker;  // Nombre del archivo del sticker
            trophyImage = getResources().getIdentifier(stickerName, "drawable", getPackageName()); // Obtener el identificador de recurso

            message = "No obtuviste respuestas correctas.\n¡Sigue practicando!";
            medalla = null;  // Sin medalla

        } else if (percentage <= 33) { // 0% - 33%
            message = "Debes esforzarte más, ¡tú puedes!";
            trophyImage = R.drawable.medalla_bronce; // Imagen de medalla de bronce
            medalla = "Bronce";
        } else if (percentage <= 66) { // 34% - 66%
            message = "¡Bien hecho! Estás mejorando.";
            trophyImage = R.drawable.medalla_plata; // Imagen de medalla de plata
            medalla = "Plata";
        } else if (percentage < 100) { // 67% - 99%
            message = "¡Muy buen trabajo! Estás cerca de la perfección.";
            trophyImage = R.drawable.medalla_oro; // Imagen de medalla de oro
            medalla = "Oro";
        } else { // 100%
            message = "¡Felicidades! Excelente trabajo, eres un experto.";
            trophyImage = R.drawable.medalla_oro; // Imagen de medalla de oro especial
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
                    .document("senas");   // Documento para el módulo "Braille"

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
            Intent intent = new Intent(ResultAbcSenas.this, SubAbcActivity.class);
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
            Log.e(TAG, "Error: medalla es null");
            return; // Si medalla es null, no hacemos nada
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
