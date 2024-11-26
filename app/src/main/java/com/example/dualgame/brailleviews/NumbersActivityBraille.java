package com.example.dualgame.brailleviews;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.dualgame.ConfiguracionActivity;
import com.example.dualgame.LogrosActivity;
import com.example.dualgame.LogrosDetalleActivity;
import com.example.dualgame.R;
import com.example.dualgame.singviews.FullScreenImageActivity;
import com.example.dualgame.singviews.LanguageActivity;
import com.example.dualgame.singviews.LevelsActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class NumbersActivityBraille extends AppCompatActivity {

    private static final String TAG = "NumbersActivityBraille";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers_braille);
        Log.d(TAG, "onCreate: NumbersActivity started");

        // Configurar el clic en las imágenes dentro de los CardViews
        findViewById(R.id.card_view_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFullScreenImage(R.drawable.numero_uno_braille);
            }
        });

        findViewById(R.id.card_view_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFullScreenImage(R.drawable.numero_dos_braille);
            }
        });

        findViewById(R.id.card_view_3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFullScreenImage(R.drawable.numero_tres_braille);
            }
        });

        findViewById(R.id.card_view_4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFullScreenImage(R.drawable.numero_cuatro_braille);
            }
        });

        findViewById(R.id.card_view_5).setOnClickListener(new View.OnClickListener() {                 //VER SI HAY QUE CAMBIAR VARIABLE PARA LAS IMAGENES DE LAS VOCALES
            @Override
            public void onClick(View v) {
                openFullScreenImage(R.drawable.numero_cinco_braille);
            }
        });

        findViewById(R.id.card_view_6).setOnClickListener(new View.OnClickListener() {                 //VER SI HAY QUE CAMBIAR VARIABLE PARA LAS IMAGENES DE LAS VOCALES
            @Override
            public void onClick(View v) {
                openFullScreenImage(R.drawable.numero_seis_braille);
            }
        });

        findViewById(R.id.card_view_7).setOnClickListener(new View.OnClickListener() {                 //VER SI HAY QUE CAMBIAR VARIABLE PARA LAS IMAGENES DE LAS VOCALES
            @Override
            public void onClick(View v) {
                openFullScreenImage(R.drawable.numero_siete_braille);
            }
        });
        findViewById(R.id.card_view_8).setOnClickListener(new View.OnClickListener() {                 //VER SI HAY QUE CAMBIAR VARIABLE PARA LAS IMAGENES DE LAS VOCALES
            @Override
            public void onClick(View v) {
                openFullScreenImage(R.drawable.numero_ocho_braille);
            }
        });

        findViewById(R.id.card_view_9).setOnClickListener(new View.OnClickListener() {                 //VER SI HAY QUE CAMBIAR VARIABLE PARA LAS IMAGENES DE LAS VOCALES
            @Override
            public void onClick(View v) {
                openFullScreenImage(R.drawable.numero_nueve_braille);
            }
        });

        findViewById(R.id.card_view_0).setOnClickListener(new View.OnClickListener() {                 //VER SI HAY QUE CAMBIAR VARIABLE PARA LAS IMAGENES DE LAS VOCALES
            @Override
            public void onClick(View v) {
                openFullScreenImage(R.drawable.numero_cero_braille);
            }
        });

        findViewById(R.id.card_view_signo_numerico).setOnClickListener(new View.OnClickListener() {                 //VER SI HAY QUE CAMBIAR VARIABLE PARA LAS IMAGENES DE LAS VOCALES
            @Override
            public void onClick(View v) {
                openFullScreenImage(R.drawable.signo_numerico_braille);
            }
        });
        // Set click listener for the back button
        findViewById(R.id.card_view_backbutton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Back button clicked");
                Intent intent = new Intent(NumbersActivityBraille.this, LevelsActivityBraille.class);
                startActivity(intent);
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
                        startActivity(new Intent(NumbersActivityBraille.this, LanguageActivity.class));
                        return true;
                    } else if (itemId == R.id.navigation_back) {
                        finish(); // Vuelve a la actividad anterior
                        return true;
                    } else if (itemId == R.id.navigation_games) {
                        startActivity(new Intent(NumbersActivityBraille.this, GamesActivityBraille.class));
                        return true;
                    }
                    else if (itemId == R.id.navigation_config) {  // Manejar la selección del nuevo ítem
                        startActivity(new Intent(NumbersActivityBraille.this, ConfiguracionActivity.class));  // Cambia por tu Activity de Ajustes
                        return true;

                    } else if (itemId == R.id.navigation_logros) {  // Agregar el manejo para Logros
                        // Ir a LogrosActivity
                        startActivity(new Intent(NumbersActivityBraille.this, LogrosDetalleActivity.class));
                        return true;



                    }
                    return false;
                }
            });
        }

    }

    private void setCardClickListener(int cardViewId, int imageResId) {
        CardView cardView = findViewById(cardViewId);
        if (cardView != null) {
            cardView.setOnClickListener(v -> {
                Log.d(TAG, "Card clicked: " + cardViewId);
                openFullScreenImage(imageResId);
            });
        } else {
            Log.e(TAG, "CardView not found for id: " + cardViewId);
        }
    }

    private void openFullScreenImage(int imageResId) {
        Log.d(TAG, "Opening full screen image with resource id: " + imageResId);
        Intent intent = new Intent(NumbersActivityBraille.this, FullScreenImageActivity.class);
        intent.putExtra("imageResId", imageResId);
        startActivity(intent);
    }
}
