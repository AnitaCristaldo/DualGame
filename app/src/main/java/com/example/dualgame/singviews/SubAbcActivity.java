package com.example.dualgame.singviews;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.dualgame.ConfiguracionActivity;
import com.example.dualgame.games.QuizQuestionsActivity;
import com.example.dualgame.R;
import com.example.dualgame.games.VocalGuessingGameActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SubAbcActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sub_abc);

        // Encontrar el CardView y configurar el onClickListener
        findViewById(R.id.card_abc_cuest_señas).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Iniciar QuizQuestionsAbcSenas y cuando se presione el CardView
                Intent intent = new Intent(SubAbcActivity.this, QuizQuestionsAbcSenas.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.card_subgame_abc_adivina).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Iniciar WordGuessingGameActivity cuando se presione el CardView
                Intent intent = new Intent(SubAbcActivity.this, AbcGuessingSenas.class);
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
                        startActivity(new Intent(SubAbcActivity.this, LanguageActivity.class));
                        return true;
                    } else if (itemId == R.id.navigation_back) {
                        finish(); // Vuelve a la actividad anterior
                        return true;
                    }

                    else if (itemId == R.id.navigation_config) {  // Manejar la selección del nuevo ítem
                        startActivity(new Intent(SubAbcActivity.this, ConfiguracionActivity.class));  // Cambia por tu Activity de Ajustes
                        return true;
                    }
                    return false;
                }
            });
        }
    }
}
