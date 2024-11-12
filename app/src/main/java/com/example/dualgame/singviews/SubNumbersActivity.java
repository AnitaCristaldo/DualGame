package com.example.dualgame.singviews;




import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dualgame.ConfiguracionActivity;
import com.example.dualgame.R;


import com.example.dualgame.brailleviews.VocalGuessingGameActivityBraille;
import com.example.dualgame.games.QuizQuestionsNumbersSenas;
import com.example.dualgame.singviews.LanguageActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SubNumbersActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sub_numbers_senas);

        // Encontrar el CardView y configurar el onClickListener
        findViewById(R.id.card_numeros_cuest_senas).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Iniciar QuizQuestionsActivity cuando se presione el CardView
                Intent intent = new Intent(SubNumbersActivity.this, QuizQuestionsNumbersSenas.class);
                startActivity(intent);
            }
        });


        findViewById(R.id.card_subgame_numeros_adivina_senas).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Iniciar WordGuessingGameActivity cuando se presione el CardView
                Intent intent = new Intent(SubNumbersActivity.this, VocalGuessingGameActivityBraille.class);  //necesito cambiar cuandotengala actividad de numeros de braille
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
                        startActivity(new Intent(SubNumbersActivity.this, LanguageActivity.class));
                        return true;
                    } else if (itemId == R.id.navigation_back) {
                        finish(); // Vuelve a la actividad anterior
                        return true;
                    }
                    else if (itemId == R.id.navigation_config) {  // Manejar la selección del nuevo ítem
                        startActivity(new Intent(SubNumbersActivity.this, ConfiguracionActivity.class));  // Cambia por tu Activity de Ajustes
                        return true;
                    }

                    return false;
                }
            });
        }
    }
}
