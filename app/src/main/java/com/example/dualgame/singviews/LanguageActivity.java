package com.example.dualgame.singviews;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dualgame.R;
import com.example.dualgame.brailleviews.LevelsActivityBraille;

public class LanguageActivity extends AppCompatActivity {

    Button botonIdiomaSenas;
    Button botonSistemaBraille;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);

        botonIdiomaSenas = findViewById(R.id.boton_idioma_senas);
        botonSistemaBraille = findViewById(R.id.boton_sistema_braille);

        botonIdiomaSenas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navegar a la actividad correspondiente
                Intent intent = new Intent(LanguageActivity.this, LevelsActivity.class);
                startActivity(intent);
            }
        });

        botonSistemaBraille.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Aquí puedes agregar la lógica para la actividad de Sistema Braille
                Intent intent = new Intent(LanguageActivity.this, LevelsActivityBraille.class);
                startActivity(intent);
            }
        });
    }
}
