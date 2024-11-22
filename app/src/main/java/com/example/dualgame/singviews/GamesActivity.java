package com.example.dualgame.singviews;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import com.example.dualgame.ConfiguracionActivity;
import com.example.dualgame.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class GamesActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games);

        // Inicialización de BottomNavigationView
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        if (bottomNavigationView != null) {
            bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    int itemId = item.getItemId();
                    if (itemId == R.id.navigation_home) {
                        startActivity(new Intent(GamesActivity.this, LanguageActivity.class));
                        return true;
                    } else if (itemId == R.id.navigation_back) {
                        finish(); // Vuelve a la actividad anterior
                        return true;
                    }
                    else if (itemId == R.id.navigation_config) {  // Manejar la selección del nuevo ítem
                        startActivity(new Intent(GamesActivity.this, ConfiguracionActivity.class));  // Cambia por tu Activity de Ajustes
                        return true;
                    }
                    return false;
                }
            });
        }

        // Configurar el CardView para Vocales
        CardView cardGameVocales = findViewById(R.id.card_game_vocales);
        cardGameVocales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Inicia la actividad SubVocalsActivity
                Intent intent = new Intent(GamesActivity.this, SubVocalsActivity.class);
                startActivity(intent);
            }
        });
        // Configurar el CardView para Abecedario
        CardView cardGameAbecedario = findViewById(R.id.card_game_abecedario);
        cardGameAbecedario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Inicia la actividad SubAbcsActivity
                Intent intent = new Intent(GamesActivity.this, SubAbcActivity.class);
                startActivity(intent);
            }
        });
        // Configurar el CardView para Vocales
        CardView cardGameNumeros = findViewById(R.id.card_game_numeros);
        cardGameNumeros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Inicia la actividad SubNumbersActivity
                Intent intent = new Intent(GamesActivity.this, SubNumbersActivity.class);
                startActivity(intent);
            }
        });


    }
}
