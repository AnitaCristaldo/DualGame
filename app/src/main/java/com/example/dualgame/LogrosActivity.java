package com.example.dualgame;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dualgame.brailleviews.GamesActivityBraille;
import com.example.dualgame.singviews.GamesActivity;
import com.example.dualgame.singviews.LanguageActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class LogrosActivity extends AppCompatActivity {

    // Variable para determinar el modo actual: true = Braille, false = Lengua de Señas
    private boolean isBrailleMode = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logros);

        // Botones para navegar a los logros de Lengua de Señas y Braille
        Button btnLenguaDeSenias = findViewById(R.id.btnLenguaDeSenias);
        Button btnSistemaBraille = findViewById(R.id.btnSistemaBraille);

        btnLenguaDeSenias.setOnClickListener(v -> {
            // Cambiar a modo Lengua de Señas y navegar a la pantalla correspondiente
            isBrailleMode = false;
            Intent intent = new Intent(LogrosActivity.this, LogrosDetalleActivitySenas.class);
            startActivity(intent);
        });

        btnSistemaBraille.setOnClickListener(v -> {
            // Cambiar a modo Braille y navegar a la pantalla correspondiente
            isBrailleMode = true;
            Intent intent = new Intent(LogrosActivity.this, LogrosDetalleActivity.class);
            startActivity(intent);
        });

        // Inicialización de BottomNavigationView
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigationtwo);
        if (bottomNavigationView != null) {
            bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    int itemId = item.getItemId();
                    if (itemId == R.id.navigation_home) {
                        startActivity(new Intent(LogrosActivity.this, LanguageActivity.class));
                        return true;
                    } else if (itemId == R.id.navigation_games) {
                        // Dirigir a la actividad correspondiente según el modo actual
                        if (isBrailleMode) {
                            startActivity(new Intent(LogrosActivity.this, GamesActivityBraille.class));
                        } else {
                            startActivity(new Intent(LogrosActivity.this, GamesActivity.class)); // Actividad para juegos de lengua de señas
                        }
                        return true;
                    } else if (itemId == R.id.navigation_config) {
                        startActivity(new Intent(LogrosActivity.this, ConfiguracionActivity.class));
                        return true;
                    } else if (itemId == R.id.navigation_logros) {
                        // Ya estamos en LogrosActivity, no hacemos nada
                        return true;
                    }
                    return false;
                }
            });
        }
    }
}
