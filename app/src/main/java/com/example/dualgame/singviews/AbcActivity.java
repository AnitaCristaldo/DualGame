package com.example.dualgame.singviews;

import android.content.Intent;

import android.os.Bundle;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.dualgame.LogrosDetalleActivity;
import com.example.dualgame.LogrosDetalleActivitySenas;
import com.example.dualgame.R;
import com.example.dualgame.brailleviews.AbcActivityBraille;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import com.bumptech.glide.Glide; //para que funcione el gif
import android.widget.ImageView; // Importar ImageView para mi gif

public class AbcActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abc);


// Cargar el GIF en la ImageView para la letra "F"
        ImageView imageViewF = findViewById(R.id.image_view_f); // Debe estar dentro de onCreate()
        Glide.with(this)
                .asGif() // Carga como GIF
                .load(R.drawable.letra_f_senas) // Carga el GIF
                .into(imageViewF); // Muestra el GIF en el ImageView

// Cargar el GIF en la ImageView para la letra "G"
        ImageView imageViewG = findViewById(R.id.image_view_g); // Debe estar dentro de onCreate()
        Glide.with(this)
                .asGif() // Carga como GIF
                .load(R.drawable.letra_g_senas) // Carga el GIF
                .into(imageViewG); // Muestra el GIF en el ImageView

        // Cargar el GIF en la ImageView para la letra "H"
        ImageView imageViewH = findViewById(R.id.image_view_h); // Debe estar dentro de onCreate()
        Glide.with(this)
                .asGif() // Carga como GIF
                .load(R.drawable.letra_h_senas) // Carga el GIF
                .into(imageViewH); // Muestra el GIF en el ImageView


        // Cargar el GIF en la ImageView para la letra "J"
        ImageView imageViewJ = findViewById(R.id.image_view_j); // Debe estar dentro de onCreate()
        Glide.with(this)
                .asGif() // Carga como GIF
                .load(R.drawable.letra_j_senas) // Carga el GIF
                .into(imageViewJ); // Muestra el GIF en el ImageView



        // Cargar el GIF en la ImageView para la letra "Ñ"
        ImageView imageViewEnie = findViewById(R.id.image_view_enie); // Debe estar dentro de onCreate()
        Glide.with(this)
                .asGif() // Carga como GIF
                .load(R.drawable.letra_enie_senas) // Carga el GIF
                .into(imageViewEnie); // Muestra el GIF en el ImageView


        // Cargar el GIF en la ImageView para la letra "X"
        ImageView imageViewX = findViewById(R.id.image_view_x); // Debe estar dentro de onCreate()
        Glide.with(this)
                .asGif() // Carga como GIF
                .load(R.drawable.letra_x_senas) // Carga el GIF
                .into(imageViewX); // Muestra el GIF en el ImageView


        // Cargar el GIF en la ImageView para la letra "Z"
        ImageView imageViewZ = findViewById(R.id.image_view_z); // Debe estar dentro de onCreate()
        Glide.with(this)
                .asGif() // Carga como GIF
                .load(R.drawable.letra_z_senas) // Carga el GIF
                .into(imageViewZ); // Muestra el GIF en el ImageView




        // Set click listeners for all cards
        setCardClickListener(R.id.card_view_letra_a_senas, R.drawable.letra_a_senas);
        setCardClickListener(R.id.card_view_letra_b_senas, R.drawable.letra_b_senas);
        setCardClickListener(R.id.card_view_letra_c_senas, R.drawable.letra_c_senas);
        setCardClickListener(R.id.card_view_letra_d_senas, R.drawable.letra_d_senas);
        setCardClickListener(R.id.card_view_letra_e_senas, R.drawable.letra_e_senas);
        setCardClickListener(R.id.card_view_letra_f_senas, R.drawable.letra_f_senas); // Cambia aquí para usar el GIF correcto
        setCardClickListener(R.id.card_view_letra_g_senas, R.drawable.letra_g_senas);
        setCardClickListener(R.id.card_view_letra_h_senas, R.drawable.letra_h_senas);
        setCardClickListener(R.id.card_view_letra_i_senas, R.drawable.letra_i_senas);
        setCardClickListener(R.id.card_view_letra_j_senas, R.drawable.letra_j_senas);
        setCardClickListener(R.id.card_view_letra_k_senas, R.drawable.letra_k_senas);
        setCardClickListener(R.id.card_view_letra_l_senas, R.drawable.letra_l_senas);
        setCardClickListener(R.id.card_view_letra_m_senas, R.drawable.letra_m_senas);
        setCardClickListener(R.id.card_view_letra_n_senas, R.drawable.letra_n_senas);
        setCardClickListener(R.id.card_view_letra_enie_senas, R.drawable.letra_enie_senas);
        setCardClickListener(R.id.card_view_letra_o_senas, R.drawable.letra_o_senas);
        setCardClickListener(R.id.card_view_letra_p_senas, R.drawable.letra_p_senas);
        setCardClickListener(R.id.card_view_letra_q_senas, R.drawable.letra_q_senas);
        setCardClickListener(R.id.card_view_letra_r_senas, R.drawable.letra_r_senas);
        setCardClickListener(R.id.card_view_letra_s_senas, R.drawable.letra_s_senas);
        setCardClickListener(R.id.card_view_letra_t_senas, R.drawable.letra_t_senas);
        setCardClickListener(R.id.card_view_letra_u_senas, R.drawable.letra_u_senas);
        setCardClickListener(R.id.card_view_letra_v_senas, R.drawable.letra_v_senas);
        setCardClickListener(R.id.card_view_letra_w_senas, R.drawable.letra_w_senas);
        setCardClickListener(R.id.card_view_letra_x_senas, R.drawable.letra_x_senas);
        setCardClickListener(R.id.card_view_letra_y_senas, R.drawable.letra_y_senas);
        setCardClickListener(R.id.card_view_letra_z_senas, R.drawable.letra_z_senas);

        // Set click listener for the back button
        findViewById(R.id.card_view_backbutton).setOnClickListener(v -> {
            Intent intent = new Intent(AbcActivity.this, LevelsActivity.class);
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
                        startActivity(new Intent(AbcActivity.this, LanguageActivity.class));
                        return true;
                    } else if (itemId == R.id.navigation_back) {
                        finish(); // Vuelve a la actividad anterior
                        return true;
                    } else if (itemId == R.id.navigation_games) {
                        startActivity(new Intent(AbcActivity.this, GamesActivity.class));
                        return true;


                    } else if (itemId == R.id.navigation_logros) {  // Agregar el manejo para Logros
                        // Ir a LogrosActivity
                        startActivity(new Intent(AbcActivity.this, LogrosDetalleActivitySenas.class));
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
        Intent intent = new Intent(AbcActivity.this, FullScreenImageActivity.class);
        intent.putExtra("imageResId", imageResId);
        startActivity(intent);
    }
}
