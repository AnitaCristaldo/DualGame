package com.example.dualgame.singviews;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.dualgame.games.QuizQuestionsActivity;
import com.example.dualgame.R;
import com.example.dualgame.games.VocalGuessingGameActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SubVocalsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sub_vocals);

        // Encontrar el CardView y configurar el onClickListener
        findViewById(R.id.card_subgame_cuest).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Iniciar QuizQuestionsActivity cuando se presione el CardView
                Intent intent = new Intent(SubVocalsActivity.this, QuizQuestionsActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.card_subgame_vocal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Iniciar WordGuessingGameActivity cuando se presione el CardView
                Intent intent = new Intent(SubVocalsActivity.this, VocalGuessingGameActivity.class);
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
                        startActivity(new Intent(SubVocalsActivity.this, LanguageActivity.class));
                        return true;
                    } else if (itemId == R.id.navigation_back) {
                        finish(); // Vuelve a la actividad anterior
                        return true;
                    }
                    return false;
                }
            });
        }
    }
}