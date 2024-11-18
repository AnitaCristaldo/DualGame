package com.example.dualgame.brailleviews;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.dualgame.R;
import com.example.dualgame.games.ConstantsAbcBraille;

public class ResultAbcBraille extends AppCompatActivity {

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



            // Configurar el mensaje dinámico y la imagen
            tvCongratulations.setText(message);


            btnFinish.setOnClickListener(v -> {
                // Volver a SubAbcsActivity al presionar el botón
                Intent intent = new Intent(ResultAbcBraille.this, AbecedarioActivityBraille.class);
                startActivity(intent);
                finish();
            });
        }
    }

