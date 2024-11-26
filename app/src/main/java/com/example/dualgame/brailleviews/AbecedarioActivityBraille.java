package com.example.dualgame.brailleviews;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dualgame.ConfiguracionActivity;
import com.example.dualgame.LogrosDetalleActivity;
import com.example.dualgame.R;
import android.content.Intent;
import android.view.MenuItem;
import android.view.View;
import androidx.annotation.NonNull;
import com.example.dualgame.games.QuizQuestionsAbcBraille;
import com.example.dualgame.singviews.LanguageActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
public class AbecedarioActivityBraille extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sub_abc_braille);

        // Encontrar el CardView y configurar el onClickListener
        findViewById(R.id.card_abc_cuest_braille).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Iniciar QuizQuestionsActivity cuando se presione el CardView
                Intent intent = new Intent(AbecedarioActivityBraille.this, QuizQuestionsAbcBraille.class);
                startActivity(intent);
            }
        });


        // Encontrar el CardView y configurar el onClickListener
        findViewById(R.id.card_subgame_abc_braille).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Iniciar QuizQuestionsActivity cuando se presione el CardView
                Intent intent = new Intent(AbecedarioActivityBraille.this, AbcGuessingBraille.class);
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
                        startActivity(new Intent(AbecedarioActivityBraille.this, LanguageActivity.class));
                        return true;
                    } else if (itemId == R.id.navigation_back) {
                        finish(); // Vuelve a la actividad anterior
                        return true;
                    }
                    else if (itemId == R.id.navigation_config) {  // Manejar la selección del nuevo ítem
                        startActivity(new Intent(AbecedarioActivityBraille.this, ConfiguracionActivity.class));  // Cambia por tu Activity de Ajustes
                        return true;

                    } else if (itemId == R.id.navigation_logros) {  // Agregar el manejo para Logros
                        // Ir a LogrosActivity
                        startActivity(new Intent(AbecedarioActivityBraille.this, LogrosDetalleActivity.class));
                        return true;



                    }

                    return false;
                }
            });
        }

    }
}
