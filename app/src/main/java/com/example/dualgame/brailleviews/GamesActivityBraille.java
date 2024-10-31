package com.example.dualgame.brailleviews;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.dualgame.R;
import com.example.dualgame.singviews.LanguageActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class GamesActivityBraille extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games_braille);


        // Inicialización de BottomNavigationView
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        if (bottomNavigationView != null) {
            bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    int itemId = item.getItemId();
                    if (itemId == R.id.navigation_home) {
                        startActivity(new Intent(GamesActivityBraille.this, LanguageActivity.class));
                        return true;
                    } else if (itemId == R.id.navigation_back) {
                        finish(); // Vuelve a la actividad anterior
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
                // Inicia la actividad SubVocalsActivityBraille
                Intent intent = new Intent(GamesActivityBraille.this, SubVocalsActivityBraille.class);
                startActivity(intent);
            }
        });

        // Configurar el CardView para Abecedario
        CardView cardGameAbecedario = findViewById(R.id.card_game_abecedario);
        cardGameAbecedario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Inicia la actividad para el Abecedario
                Intent intent = new Intent(GamesActivityBraille.this, AbecedarioActivityBraille.class);
                startActivity(intent);
            }
        });


        // Configurar el CardView para Numeros
        CardView cardGameNumeros = findViewById(R.id.card_game_numeros);
        cardGameNumeros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Inicia la actividad para Numeros
                Intent intent = new Intent(GamesActivityBraille.this, SubNumbersActivityBraille.class);  //poner subnumbers
                startActivity(intent);
            }
        });



    }

}
