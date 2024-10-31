package com.example.dualgame.brailleviews;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.dualgame.R;
import com.example.dualgame.singviews.FullScreenImageActivity;
import com.example.dualgame.singviews.LanguageActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class AbcActivityBraille extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abc_braille);

        // Set click listeners for all cards
        setCardClickListener(R.id.card_view_a, R.drawable.default_icon);
        setCardClickListener(R.id.card_view_b, R.drawable.default_icon);
        setCardClickListener(R.id.card_view_c, R.drawable.default_icon);
        setCardClickListener(R.id.card_view_d, R.drawable.default_icon);
        setCardClickListener(R.id.card_view_e, R.drawable.default_icon);
        setCardClickListener(R.id.card_view_f, R.drawable.default_icon);
        setCardClickListener(R.id.card_view_g, R.drawable.default_icon);
        setCardClickListener(R.id.card_view_h, R.drawable.default_icon);
        setCardClickListener(R.id.card_view_i, R.drawable.default_icon);
        setCardClickListener(R.id.card_view_j, R.drawable.default_icon);
        setCardClickListener(R.id.card_view_k, R.drawable.default_icon);
        setCardClickListener(R.id.card_view_l, R.drawable.default_icon);
        setCardClickListener(R.id.card_view_m, R.drawable.default_icon);
        setCardClickListener(R.id.card_view_n, R.drawable.default_icon);
        setCardClickListener(R.id.card_view_o, R.drawable.default_icon);
        setCardClickListener(R.id.card_view_p, R.drawable.default_icon);
        setCardClickListener(R.id.card_view_q, R.drawable.default_icon);
        setCardClickListener(R.id.card_view_r, R.drawable.default_icon);
        setCardClickListener(R.id.card_view_s, R.drawable.default_icon);
        setCardClickListener(R.id.card_view_t, R.drawable.default_icon);
        setCardClickListener(R.id.card_view_u, R.drawable.default_icon);
        setCardClickListener(R.id.card_view_v, R.drawable.default_icon);
        setCardClickListener(R.id.card_view_w, R.drawable.default_icon);
        setCardClickListener(R.id.card_view_x, R.drawable.default_icon);
        setCardClickListener(R.id.card_view_y, R.drawable.default_icon);
        setCardClickListener(R.id.card_view_z, R.drawable.default_icon);

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
