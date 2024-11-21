package com.example.dualgame;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dualgame.brailleviews.GamesActivityBraille;
import com.example.dualgame.singviews.LanguageActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class LogrosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logros);

        // Botones para navegar a los logros de Lengua de Señas y Braille
        Button btnLenguaDeSenias = findViewById(R.id.btnLenguaDeSenias);
        Button btnSistemaBraille = findViewById(R.id.btnSistemaBraille);

        btnLenguaDeSenias.setOnClickListener(v -> {
            // Ir a la pantalla de logros de Lengua de Señas
            Intent intent = new Intent(LogrosActivity.this, LogrosDetalleActivity.class);
            intent.putExtra("categoria", "Lengua de Señas");
            startActivity(intent);
        });

        btnSistemaBraille.setOnClickListener(v -> {
            // Ir a la pantalla de logros de Sistema Braille
            Intent intent = new Intent(LogrosActivity.this, LogrosDetalleActivity.class);
            intent.putExtra("categoria", "Sistema Braille");
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
                        startActivity(new Intent(LogrosActivity.this, GamesActivityBraille.class));
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
