package com.example.dualgame.singviews;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dualgame.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class WordsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words);

        // Configurar el botón de retroceso
        findViewById(R.id.card_view_backbutton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crear un Intent para ir a LevelsActivity
                Intent intent = new Intent(WordsActivity.this, LevelsActivity.class);
                startActivity(intent);
                // Finalizar la actividad actual
                finish();
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
                        startActivity(new Intent(WordsActivity.this, LanguageActivity.class));
                        return true;
                    } else if (itemId == R.id.navigation_back) {
                        finish(); // Vuelve a la actividad anterior
                        return true;
                    } else if (itemId == R.id.navigation_games) {
                        startActivity(new Intent(WordsActivity.this, GamesActivity.class));
                        return true;
                    }
                    return false;
                }
            });
        }
    }
}
