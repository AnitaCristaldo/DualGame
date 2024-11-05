package com.example.dualgame.brailleviews;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.dualgame.ConfiguracionActivity;
import com.example.dualgame.R;
import com.example.dualgame.singviews.FullScreenImageActivity;
import com.example.dualgame.singviews.LanguageActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class  AbcActivityBraille extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abc_braille);

        // Set click listeners for all cards
        setCardClickListener(R.id.card_view_a, R.drawable.letra_a_braille);
        setCardClickListener(R.id.card_view_b, R.drawable.letra_b_braille);
        setCardClickListener(R.id.card_view_c, R.drawable.letra_c_braille);
        setCardClickListener(R.id.card_view_d, R.drawable.letra_d_braille);
        setCardClickListener(R.id.card_view_e, R.drawable.letra_e_braille);
        setCardClickListener(R.id.card_view_f, R.drawable.letra_f_braille);
        setCardClickListener(R.id.card_view_g, R.drawable.letra_g_braille);
        setCardClickListener(R.id.card_view_h, R.drawable.letra_h_braille);
        setCardClickListener(R.id.card_view_i, R.drawable.letra_i_braille);
        setCardClickListener(R.id.card_view_j, R.drawable.letra_j_braille);
        setCardClickListener(R.id.card_view_k, R.drawable.letra_k_braille);
        setCardClickListener(R.id.card_view_l, R.drawable.letra_l_braille);
        setCardClickListener(R.id.card_view_m, R.drawable.letra_m_braille);
        setCardClickListener(R.id.card_view_n, R.drawable.letra_n_braille);
        setCardClickListener(R.id.card_view_enie, R.drawable.letra_enie_braille);
        setCardClickListener(R.id.card_view_o, R.drawable.letra_o_braille);
        setCardClickListener(R.id.card_view_p, R.drawable.letra_p_braille);
        setCardClickListener(R.id.card_view_q, R.drawable.letra_q_braille);
        setCardClickListener(R.id.card_view_r, R.drawable.letra_r_braille);
        setCardClickListener(R.id.card_view_s, R.drawable.letra_s_braille);
        setCardClickListener(R.id.card_view_t, R.drawable.letra_t_braille);
        setCardClickListener(R.id.card_view_u, R.drawable.letra_u_braille);
        setCardClickListener(R.id.card_view_v, R.drawable.letra_v_braille);
        setCardClickListener(R.id.card_view_w, R.drawable.letra_w_braille);
        setCardClickListener(R.id.card_view_x, R.drawable.letra_x_braille);
        setCardClickListener(R.id.card_view_y, R.drawable.letra_y_braille);
        setCardClickListener(R.id.card_view_z, R.drawable.letra_z_braille);

        // Set click listener for the back button
        findViewById(R.id.card_view_backbutton).setOnClickListener(v -> {
            Intent intent = new Intent(AbcActivityBraille.this, LevelsActivityBraille.class);
            startActivity(intent);
            finish();
        });

        // Inicialización de BottomNavigationView
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        if (bottomNavigationView != null) {
            bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    int itemId = item.getItemId();
                    if (itemId == R.id.navigation_home) {
                        startActivity(new Intent(AbcActivityBraille.this, LanguageActivity.class));
                        return true;
                    } else if (itemId == R.id.navigation_back) {
                        finish(); // Vuelve a la actividad anterior
                        return true;
                    } else if (itemId == R.id.navigation_games) {
                        startActivity(new Intent(AbcActivityBraille.this, GamesActivityBraille.class));
                        return true;
                    }
                    else if (itemId == R.id.navigation_config) {  // Manejar la selección del nuevo ítem
                        startActivity(new Intent(AbcActivityBraille.this, ConfiguracionActivity.class));  // Cambia por tu Activity de Ajustes
                        return true;
                    }
                      return false;
                }
            });
        }
    }

    private void setCardClickListener(int cardViewId, int imageResId) {
        CardView cardView = findViewById(cardViewId);

        cardView.setOnClickListener(v -> {
            openFullScreenImage(imageResId);
        });
    }

    private void openFullScreenImage(int imageResId) {
        Intent intent = new Intent(AbcActivityBraille.this, FullScreenImageActivity.class);
        intent.putExtra("imageResId", imageResId);
        startActivity(intent);
    }
}
