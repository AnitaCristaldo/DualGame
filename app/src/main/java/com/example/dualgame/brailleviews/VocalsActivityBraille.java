/*package com.example.dualgame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class VocalsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocals);

        // Configurar el botón de retroceso
        findViewById(R.id.card_view_backbutton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crear un Intent para ir a LevelsActivity
                Intent intent = new Intent(VocalsActivity.this, LevelsActivity.class);
                startActivity(intent);
                // Finalizar la actividad actual
                finish();
            }
        });

        // Configurar el clic en las imágenes dentro de los CardViews
        findViewById(R.id.card_view_vocal_a).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFullScreenImage(R.drawable.vocal_a);
            }
        });

        findViewById(R.id.card_view_vocal_e).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFullScreenImage(R.drawable.vocal_e);
            }
        });

        findViewById(R.id.card_view_vocal_i).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFullScreenImage(R.drawable.vocal_i);
            }
        });

        findViewById(R.id.card_view_vocal_o).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFullScreenImage(R.drawable.vocal_o);
            }
        });

        findViewById(R.id.card_view_vocal_u).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFullScreenImage(R.drawable.vocal_u);
            }
        });
    }

    private void openFullScreenImage(int imageResId) {
        Intent intent = new Intent(VocalsActivity.this, FullScreenImageActivity.class);
        intent.putExtra("imageResId", imageResId);
        startActivity(intent);
    }
}*/
package com.example.dualgame.brailleviews;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dualgame.ConfiguracionActivity;
import com.example.dualgame.LogrosActivity;
import com.example.dualgame.R;
import com.example.dualgame.singviews.FullScreenImageActivity;
import com.example.dualgame.singviews.LanguageActivity;
import com.example.dualgame.singviews.LevelsActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class VocalsActivityBraille extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocals_braille);

        // Configurar el botón de retroceso
        findViewById(R.id.card_view_backbutton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crear un Intent para ir a LevelsActivity
                Intent intent = new Intent(VocalsActivityBraille.this, LevelsActivityBraille.class);
                startActivity(intent);
                // Finalizar la actividad actual
                finish();
            }
        });

        // Configurar el clic en las imágenes dentro de los CardViews
        findViewById(R.id.card_view_vocal_a_braille).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFullScreenImage(R.drawable.letra_a_braille);
            }
        });

        findViewById(R.id.card_view_vocal_e_braille).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFullScreenImage(R.drawable.letra_e_braille);
            }
        });

        findViewById(R.id.card_view_vocal_i_braille).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFullScreenImage(R.drawable.letra_i_braille);
            }
        });

        findViewById(R.id.card_view_vocal_o_braille).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFullScreenImage(R.drawable.letra_o_braille);
            }
        });

        findViewById(R.id.card_view_vocal_u_braille).setOnClickListener(new View.OnClickListener() {                 //VER SI HAY QUE CAMBIAR VARIABLE PARA LAS IMAGENES DE LAS VOCALES
            @Override
            public void onClick(View v) {
                openFullScreenImage(R.drawable.letra_u_braille);
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
                        startActivity(new Intent(VocalsActivityBraille.this, LanguageActivity.class));
                        return true;
                    } else if (itemId == R.id.navigation_back) {
                        finish(); // Vuelve a la actividad anterior
                        return true;
                    } else if (itemId == R.id.navigation_games) {
                        startActivity(new Intent(VocalsActivityBraille.this, GamesActivityBraille.class));
                        return true;
                    }
                    else if (itemId == R.id.navigation_config) {  // Manejar la selección del nuevo ítem
                        startActivity(new Intent(VocalsActivityBraille.this, ConfiguracionActivity.class));  // Cambia por tu Activity de Ajustes
                        return true;

                    } else if (itemId == R.id.navigation_logros) {  // Agregar el manejo para Logros
                        // Ir a LogrosActivity
                        startActivity(new Intent(VocalsActivityBraille.this, LogrosActivity.class));
                        return true;



                    }
                    return false;
                }
            });
        }
    }

    private void openFullScreenImage(int imageResId) {
        Intent intent = new Intent(VocalsActivityBraille.this, FullScreenImageActivity.class);
        intent.putExtra("imageResId", imageResId);
        startActivity(intent);
    }
}


