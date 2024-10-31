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
package com.example.dualgame.singviews;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dualgame.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

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

        // Inicialización de BottomNavigationView
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        if (bottomNavigationView != null) {
            bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    int itemId = item.getItemId();
                    if (itemId == R.id.navigation_home) {
                        startActivity(new Intent(VocalsActivity.this, LanguageActivity.class));
                        return true;
                    } else if (itemId == R.id.navigation_back) {
                        finish(); // Vuelve a la actividad anterior
                        return true;
                    } else if (itemId == R.id.navigation_games) {
                        startActivity(new Intent(VocalsActivity.this, GamesActivity.class));
                        return true;
                    }
                    return false;
                }
            });
        }
    }

    private void openFullScreenImage(int imageResId) {
        Intent intent = new Intent(VocalsActivity.this, FullScreenImageActivity.class);
        intent.putExtra("imageResId", imageResId);
        startActivity(intent);
    }
}


