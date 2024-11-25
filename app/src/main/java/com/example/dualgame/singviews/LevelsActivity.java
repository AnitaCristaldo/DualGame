package com.example.dualgame.singviews;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import com.example.dualgame.ConfiguracionActivity;
import com.example.dualgame.LogrosActivity;
import com.example.dualgame.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class LevelsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels);

        // Referencias a los CardView
        CardView cardViewVocales = findViewById(R.id.card_view_vocales);
        CardView cardViewAbecedario = findViewById(R.id.card_view_abecedario);
        CardView cardViewNumeros = findViewById(R.id.card_view_numeros);
        CardView cardViewGames = findViewById(R.id.card_view_juegos);
        CardView cardViewHome = findViewById(R.id.card_view_home);

        // Establecer OnClickListener para cada CardView
        cardViewVocales.setOnClickListener(v -> {
            Intent intent = new Intent(LevelsActivity.this, VocalsActivity.class);
            startActivity(intent);
        });

        cardViewAbecedario.setOnClickListener(v -> {
            Intent intent = new Intent(LevelsActivity.this, AbcActivity.class);
            startActivity(intent);
        });

        cardViewNumeros.setOnClickListener(v -> {
            Intent intent = new Intent(LevelsActivity.this, NumbersActivity.class);
            startActivity(intent);
        });

        cardViewGames.setOnClickListener(v -> {
            Intent intent = new Intent(LevelsActivity.this, GamesActivity.class);
            startActivity(intent);
        });

        cardViewHome.setOnClickListener(v -> navigateToLanguageActivity());

        // Inicialización de BottomNavigationView
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigationtwo);
        if (bottomNavigationView != null) {
            bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    int itemId = item.getItemId();
                    if (itemId == R.id.navigation_home) {
                        startActivity(new Intent(LevelsActivity.this, LanguageActivity.class));
                        return true;
                    }  else if (itemId == R.id.navigation_games) {
                        startActivity(new Intent(LevelsActivity.this, GamesActivity.class));
                        return true;
                    }
                    else if (itemId == R.id.navigation_config) {  // Manejar la selección del nuevo ítem
                        startActivity(new Intent(LevelsActivity.this, ConfiguracionActivity.class));  // Cambia por tu Activity de Ajustes
                        return true;


                    } else if (itemId == R.id.navigation_logros) {  // Agregar el manejo para Logros
                        // Ir a LogrosActivity
                        startActivity(new Intent(LevelsActivity.this, LogrosActivity.class));
                        return true;
                    }
                    return false;
                }
            });
        }
    }
    private void navigateToLanguageActivity() {
        Intent intent = new Intent(LevelsActivity.this, LanguageActivity.class);
        startActivity(intent);
    }
}
