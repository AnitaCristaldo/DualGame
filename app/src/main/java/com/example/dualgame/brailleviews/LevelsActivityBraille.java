package com.example.dualgame.brailleviews;


import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.dualgame.ConfiguracionActivity;
import com.example.dualgame.LogrosActivity;
import com.example.dualgame.LogrosDetalleActivity;
import com.example.dualgame.R;
import com.example.dualgame.singviews.LanguageActivity;
import com.example.dualgame.singviews.LevelsActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
public class LevelsActivityBraille extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels_braille);

        // Referencias a los CardView
        CardView cardViewVocalesBraille = findViewById(R.id.card_view_vocales_braille);
        CardView cardViewAbecedarioBraille = findViewById(R.id.card_view_abecedario_braille);
        CardView cardViewNumerosBraille = findViewById(R.id.card_view_numeros_braille);
      //  CardView cardViewPalabras = findViewById(R.id.card_view_palabras_braille);
        CardView cardViewGamesBraille = findViewById(R.id.card_view_juegos_braille);
        CardView cardViewHomeBraille = findViewById(R.id.card_view_home_braille);

        // Establecer OnClickListener para cada CardView
        cardViewVocalesBraille.setOnClickListener(v -> {
            Intent intent = new Intent(LevelsActivityBraille.this, VocalsActivityBraille.class); //CAMBIAR
            startActivity(intent);
        });

        cardViewAbecedarioBraille.setOnClickListener(v -> {
            Intent intent = new Intent(LevelsActivityBraille.this, AbcActivityBraille.class);  //CAMBIAR
            startActivity(intent);
        });

        cardViewNumerosBraille.setOnClickListener(v -> {
            Intent intent = new Intent(LevelsActivityBraille.this, NumbersActivityBraille.class); // CAMBIAR
            startActivity(intent);
        });

      /*  cardViewPalabras.setOnClickListener(v -> {
            Intent intent = new Intent(LevelsActivityBraille.this, WordsActivityBraille.class);  //CAMBIAR
            startActivity(intent);
        });*/

        cardViewGamesBraille.setOnClickListener(v -> {
            Intent intent = new Intent(LevelsActivityBraille.this, GamesActivityBraille.class);   //CAMBIAR
            startActivity(intent);
        });

        cardViewHomeBraille.setOnClickListener(v -> navigateToLanguageActivity());

        // Inicialización de BottomNavigationView
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigationtwo);
        if (bottomNavigationView != null) {
            bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    int itemId = item.getItemId();
                    if (itemId == R.id.navigation_home) {
                        startActivity(new Intent(LevelsActivityBraille.this, LanguageActivity.class));
                        return true;
                    }  else if (itemId == R.id.navigation_games) {
                        startActivity(new Intent(LevelsActivityBraille.this, GamesActivityBraille.class));  // CAMBIAR
                        return true;
                    }
                    else if (itemId == R.id.navigation_config) {  // Manejar la selección del nuevo ítem
                        startActivity(new Intent(LevelsActivityBraille.this, ConfiguracionActivity.class));  // Cambia por tu Activity de Ajustes
                        return true;

                    } else if (itemId == R.id.navigation_logros) {  // Agregar el manejo para Logros
                        // Ir a LogrosActivity
                        startActivity(new Intent(LevelsActivityBraille.this, LogrosDetalleActivity.class));
                        return true;


                    }
                    return false;
                }
            });
        }

    }

    private void navigateToLanguageActivity() {
        Intent intent = new Intent(LevelsActivityBraille.this, LanguageActivity.class);
        startActivity(intent);
    }
}
