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
        if (correctAnswers <= 2) {
            message = "Debes esforzarte más, ¡tú puedes!";
            trophyImage = R.drawable.medalla_bronce; // Cambia este nombre según tu archivo
        } else if (correctAnswers <= 4) {
            message = "¡Bien hecho! Estás mejorando.";
            trophyImage = R.drawable.medalla_plata; // Cambia este nombre según tu archivo
        } else {
            message = "¡Felicidades! Excelente trabajo.";
            trophyImage = R.drawable.medalla_oro; // Cambia este nombre según tu archivo
        }

        // Configurar el mensaje dinámico y la imagen
        tvCongratulations.setText(message);
        icTrophy.setImageResource(trophyImage);






        // Configurar el listener para el botón de finalizar
        btnFinish.setOnClickListener(v -> {
            // Volver a SubVocalsActivity al presionar el botón
            Intent intent = new Intent(ResultActivityBraille.this, SubVocalsActivityBraille.class);
            startActivity(intent);
            finish();
        });
    }
}
