package com.example.dualgame.singviews;

// Importaciones necesarias para la funcionalidad de la actividad.
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.dualgame.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

// La actividad NumbersActivity hereda de AppCompatActivity, lo que le permite utilizar características modernas de compatibilidad en la interfaz.
public class NumbersActivity extends AppCompatActivity {

    // Definición de una constante para usar en los mensajes de Log.
    private static final String TAG = "NumbersActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);
        // Establece el diseño de la actividad usando el archivo de diseño activity_numbers.xml.

        Log.d(TAG, "onCreate: NumbersActivity started");
        // Escribe un mensaje en el log indicando que la actividad ha comenzado.

        // Establecer listeners de clic para todas las tarjetas (CardViews).
        for (int i = 1; i <= 53; i++) {
            // Itera sobre los números del 1 al 53 para buscar las CardViews correspondientes en el diseño.
            int cardViewId = getResources().getIdentifier("card_view_" + i, "id", getPackageName());
            // Utiliza getIdentifier() para obtener el ID de la vista basada en el nombre de recurso ("card_view_1", "card_view_2", etc.).

            if (cardViewId != 0) { // Verifica si el recurso existe.
                setCardClickListener(cardViewId, R.drawable.default_icon);
                // Si existe, asigna un listener de clic a la CardView y asocia una imagen predeterminada (default_icon).
                Log.d(TAG, "onCreate: Set click listener for card_view_" + i);
                // Escribe en el log que se ha asignado un listener para esa CardView.
            } else {
                Log.e(TAG, "onCreate: Resource ID not found for card_view_" + i);
                // Si no se encuentra el ID del recurso, escribe un error en el log.
            }
        }

        // Establecer listener de clic para el botón de retroceso (Back Button).
        findViewById(R.id.card_view_backbutton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Back button clicked");
                // Escribe en el log que se ha presionado el botón de retroceso.

                Intent intent = new Intent(NumbersActivity.this, LevelsActivity.class);
                // Crea un Intent para iniciar la actividad LevelsActivity.

                startActivity(intent);
                // Inicia la actividad LevelsActivity.

                finish();
                // Finaliza la actividad actual para que no se pueda volver a ella usando el botón de retroceso.
            }
        });

        // Inicialización de BottomNavigationView (Menú de navegación inferior).
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        // Encuentra la vista de BottomNavigationView en el diseño.

        if (bottomNavigationView != null) {
            // Verifica si la vista de BottomNavigationView no es nula.
            bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    // Configura un listener para los elementos seleccionados en el menú de navegación.
                    int itemId = item.getItemId();
                    // Obtiene el ID del elemento seleccionado.

                    if (itemId == R.id.navigation_home) {
                        // Si el elemento seleccionado es "navigation_home", inicia LanguageActivity.
                        startActivity(new Intent(NumbersActivity.this, LanguageActivity.class));
                        return true;
                    } else if (itemId == R.id.navigation_back) {
                        // Si el elemento seleccionado es "navigation_back", finaliza la actividad actual.
                        finish();
                        return true;
                    } else if (itemId == R.id.navigation_games) {
                        // Si el elemento seleccionado es "navigation_games", inicia GamesActivity.
                        startActivity(new Intent(NumbersActivity.this, GamesActivity.class));
                        return true;
                    }
                    return false;
                    // Retorna false si ninguna opción coincide.
                }
            });
        }
    }

    // Método para establecer un listener de clic en una CardView específica.
    private void setCardClickListener(int cardViewId, int imageResId) {
        CardView cardView = findViewById(cardViewId);
        // Encuentra la CardView por su ID.

        if (cardView != null) {
            // Verifica si la CardView no es nula.
            cardView.setOnClickListener(v -> {
                Log.d(TAG, "Card clicked: " + cardViewId);
                // Escribe en el log que se ha hecho clic en la CardView.
                openFullScreenImage(imageResId);
                // Llama al método para abrir la imagen en pantalla completa.
            });
        } else {
            Log.e(TAG, "CardView not found for id: " + cardViewId);
            // Si la CardView no se encuentra, escribe un error en el log.
        }
    }

    // Método para abrir una imagen en pantalla completa.
    private void openFullScreenImage(int imageResId) {
        Log.d(TAG, "Opening full screen image with resource id: " + imageResId);
        // Escribe en el log que se está abriendo una imagen en pantalla completa.

        Intent intent = new Intent(NumbersActivity.this, FullScreenImageActivity.class);
        // Crea un Intent para iniciar FullScreenImageActivity.

        intent.putExtra("imageResId", imageResId);
        // Pasa el ID de la imagen como un extra en el Intent.

        startActivity(intent);
        // Inicia FullScreenImageActivity.
    }
}
