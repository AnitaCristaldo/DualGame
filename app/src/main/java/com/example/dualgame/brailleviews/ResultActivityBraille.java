package com.example.dualgame.brailleviews;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.dualgame.R;
import com.example.dualgame.games.ConstantsBraille;
public class ResultActivityBraille  extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_braille);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);

        // Get references to the views
        TextView tvScoreBraille = findViewById(R.id.tv_score);
        TextView tvCongratulations = findViewById(R.id.tv_congratulations); // Referencia al TextView de Felicitaciones
        Button btnFinish = findViewById(R.id.btn_finish);

        // Get the data passed from the previous activity
        int totalQuestions = getIntent().getIntExtra(ConstantsBraille.TOTAL_QUESTIONS, 0);
        int correctAnswers = getIntent().getIntExtra(ConstantsBraille.CORRECT_ANSWERS, 0);

        // Set the score text
        tvScoreBraille.setText("Tu puntaje es " + correctAnswers + " de " + totalQuestions);

        // Determine the message based on the score
        String message;
        if (correctAnswers <= 2) {
            message = "Debes esforzarte más, ¡tú puedes!";
        } else if (correctAnswers == 3) {
            message = "¡Bien hecho! Estás mejorando.";
        } else {
            message = "¡Felicidades! Excelente trabajo.";
        }

        // Set the dynamic message
        tvCongratulations.setText(message);

        // Set the click listener for the finish button
        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Volver a SubVocalsActivity al presionar el botón
                Intent intent = new Intent(ResultActivityBraille.this, SubVocalsActivityBraille.class);
                startActivity(intent);
                finish();
            }
        });
    }
}