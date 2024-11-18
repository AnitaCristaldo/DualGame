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
import com.example.dualgame.games.ConstantsNumbersBraille;

///verificar porque al finalizar cuestionario de números te lleva a SubAbcActivityBraille si está bien la navegación
public class ResultActivityNumbersBraille extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Llama al método onCreate de la superclase (AppCompatActivity) para realizar la inicialización estándar.

        setContentView(R.layout.activity_result_numbers_braille);
        // Establece el diseño visual de la actividad usando el archivo de diseño XML activity_result.

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        // Configura la ventana para que la actividad se muestre en pantalla completa, ocultando la barra de estado.

        // Obtener referencias a las vistas en el diseño XML
        TextView tvScore = findViewById(R.id.tv_score_numbers_braille);
        // Encuentra y guarda una referencia al TextView con el ID "tv_score" para mostrar el puntaje.

        TextView tvCongratulations = findViewById(R.id.tv_congratulations_numbers_braille); // Referencia al TextView de Felicitaciones
        Button btnFinishNumbers = findViewById(R.id.btn_finish_numbers_braille);
        // Encuentra y guarda una referencia al Button con el ID "btn_finish" para manejar la acción de finalizar.

        ImageView icTrophy = findViewById(R.id.ic_trophy); // Referencia al ImageView


        // Obtener los datos pasados desde la actividad anterior (QuizQuestionsActivity)
        int totalQuestions = getIntent().getIntExtra(ConstantsNumbersBraille.TOTAL_QUESTIONS, 0);
        // Obtiene el valor entero de "TOTAL_QUESTIONS" enviado por la actividad anterior. Si no se encuentra, el valor por defecto es 0.

        int correctAnswers = getIntent().getIntExtra(ConstantsNumbersBraille.CORRECT_ANSWERS, 0);
        // Obtiene el valor entero de "CORRECT_ANSWERS" enviado por la actividad anterior. Si no se encuentra, el valor por defecto es 0.

        // Configurar el texto del puntaje en la interfaz
        tvScore.setText("Tu puntaje es " + correctAnswers + " de " + totalQuestions);
        // Establece el texto del TextView para mostrar el puntaje obtenido, mostrando la cantidad de respuestas correctas de un total de preguntas.

        // Determinar el mensaje basado en el puntaje
        String message;
        int trophyImage; // Variable para determinar qué imagen mostrar

        if (correctAnswers <= 2) { // 0 a 2 respuestas correctas
            message = "Debes esforzarte más, ¡tú puedes!";
            trophyImage = R.drawable.medalla_bronce; // Cambia según tu archivo
        } else if (correctAnswers <= 4) { // 3 o 4 respuestas correctas
            message = "¡Bien hecho! Estás mejorando.";
            trophyImage = R.drawable.medalla_plata; // Cambia según tu archivo
        } else if (correctAnswers <= 6) { // 5 o 6 respuestas correctas
            message = "¡Muy buen trabajo! Estás cerca de la perfección.";
            trophyImage = R.drawable.medalla_plata; // Cambia según tu archivo (puede ser plata o algo especial)
        } else { // 7 u 8 respuestas correctas
            message = "¡Felicidades! Excelente trabajo, eres un experto.";
            trophyImage = R.drawable.medalla_oro; // Cambia según tu archivo
        }

// Configurar el mensaje dinámico y la imagen
        tvCongratulations.setText(message);
        icTrophy.setImageResource(trophyImage);





        // Configurar el listener para el botón de finalizar
        btnFinishNumbers.setOnClickListener(v -> {
            // Definir la acción al hacer clic en el botón btnFinish
            Log.d("Navigation", "Navigating to SubNumbersActivityBraille");
            Intent intent = new Intent(ResultActivityNumbersBraille.this, SubNumbersActivityBraille.class);
            startActivity(intent);
            finish();
        });
    }
}