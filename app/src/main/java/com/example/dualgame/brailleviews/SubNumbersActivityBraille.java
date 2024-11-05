package com.example.dualgame.brailleviews;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dualgame.ConfiguracionActivity;
import com.example.dualgame.R;
import com.example.dualgame.games.QuizQuestionsNumbersBraille;
import com.example.dualgame.singviews.LanguageActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SubNumbersActivityBraille extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sub_numbers_braille);

        // Encontrar el CardView y configurar el onClickListener
        findViewById(R.id.card_numeros_cuest_braille).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Iniciar QuizQuestionsActivity cuando se presione el CardView
                Intent intent = new Intent(SubNumbersActivityBraille.this, QuizQuestionsNumbersBraille.class);
                startActivity(intent);
            }
        });


        findViewById(R.id.card_subgame_numeros_adivina_braille).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Iniciar WordGuessingGameActivity cuando se presione el CardView
                Intent intent = new Intent(SubNumbersActivityBraille.this, VocalGuessingGameActivityBraille.class);  //necesito cambiar cuandotengala actividad de numeros de braille
                startActivity(intent);
            }
        });


        // Inicialización de BottomNavigationView
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        if (bottomNavigationView != null) {
            bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    int itemId = item.getItemId();
                    if (itemId == R.id.navigation_home) {
                        startActivity(new Intent(SubNumbersActivityBraille.this, LanguageActivity.class));
                        return true;
                    } else if (itemId == R.id.navigation_back) {
                        finish(); // Vuelve a la actividad anterior
                        return true;
                    }
                    else if (itemId == R.id.navigation_config) {  // Manejar la selección del nuevo ítem
                        startActivity(new Intent(SubNumbersActivityBraille.this, ConfiguracionActivity.class));  // Cambia por tu Activity de Ajustes
                        return true;
                    }

                    return false;
                }
            });
        }
    }
}
