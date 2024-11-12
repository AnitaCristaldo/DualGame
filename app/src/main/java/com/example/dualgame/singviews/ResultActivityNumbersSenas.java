package com.example.dualgame.singviews;



import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.dualgame.R;

import com.example.dualgame.games.Constants;

public class ResultActivityNumbersSenas extends AppCompatActivity {

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
        Button btnFinish = findViewById(R.id.btn_finish);
        // Encuentra y guarda una referencia al Button con el ID "btn_finish" para manejar la acción de finalizar.

        // Obtener los datos pasados desde la actividad anterior (QuizQuestionsActivity)
        int totalQuestions = getIntent().getIntExtra(Constants.TOTAL_QUESTIONS, 0);
        // Obtiene el valor entero de "TOTAL_QUESTIONS" enviado por la actividad anterior. Si no se encuentra, el valor por defecto es 0.

        int correctAnswers = getIntent().getIntExtra(Constants.CORRECT_ANSWERS, 0);
        // Obtiene el valor entero de "CORRECT_ANSWERS" enviado por la actividad anterior. Si no se encuentra, el valor por defecto es 0.

        // Configurar el texto del puntaje en la interfaz
        tvScore.setText("Tu puntaje es " + correctAnswers + " de " + totalQuestions);
        // Establece el texto del TextView para mostrar el puntaje obtenido, mostrando la cantidad de respuestas correctas de un total de preguntas.


        // Determine the message based on the score
        String message;
        if (correctAnswers <= 2) {
            message = "Debes esforzarte más\n          ¡Tú puedes!";
        } else if (correctAnswers == 3) {
            message = "     \t¡Bien hecho!\n Estás mejorando";
        } else {
            message = "        ¡Felicidades!\n    Excelente trabajo";
        }


        // Set the dynamic message
        tvCongratulations.setText(message);
        // Configurar el listener para el botón de finalizar
        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Definir la acción al hacer clic en el botón btnFinish

                // Crear un nuevo Intent para iniciar la actividad SubnUMBERSActivity
                Intent intent = new Intent(ResultActivityNumbersSenas.this, SubNumbersActivity.class);
                startActivity(intent);
                // Inicia la actividad SubNumbersActivity

                finish();
                // Finaliza la actividad actual (ResultActivityNUMBERS), para que no se pueda volver a ella usando el botón de retroceso.
            }
        });
    }
}