package com.example.dualgame.brailleviews;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.dualgame.R;
import com.example.dualgame.singviews.LanguageActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SubVocalsActivityBraille extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sub_vocals_braille);

        // Encontrar el CardView y configurar el onClickListener
        findViewById(R.id.card_subgame_cuest_braille).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Iniciar QuizQuestionsActivity cuando se presione el CardView
                Intent intent = new Intent(SubVocalsActivityBraille.this, QuizQuestionsActivityBraille.class);
                startActivity(intent);
            }
        });


        findViewById(R.id.card_subgame_vocal_braille).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Iniciar WordGuessingGameActivity cuando se presione el CardView
                Intent intent = new Intent(SubVocalsActivityBraille.this, VocalGuessingGameActivityBraille.class);
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
                        startActivity(new Intent(SubVocalsActivityBraille.this, LanguageActivity.class));
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



