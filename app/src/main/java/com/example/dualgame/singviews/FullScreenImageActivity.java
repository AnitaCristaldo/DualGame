/*
package com.example.dualgame;

import android.os.Bundle;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;

public class FullScreenImageActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen_image);

        ImageView imageView = findViewById(R.id.full_screen_image);

        // Obtener el recurso de la imagen desde el Intent
        int imageResId = getIntent().getIntExtra("imageResId", -1);

        // Mostrar la imagen usando Glide o cualquier otra biblioteca de carga de imágenes
        Glide.with(this).load(imageResId).into(imageView);
    }
}
*/
package com.example.dualgame.singviews;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.example.dualgame.R;

public class FullScreenImageActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen_image);

        ImageView imageView = findViewById(R.id.full_screen_image);
        FrameLayout frameLayout = findViewById(R.id.full_screen_image_frame);

        // Obtener el recurso de la imagen desde el Intent
        int imageResId = getIntent().getIntExtra("imageResId", -1);

        // Mostrar la imagen usando Glide o cualquier otra biblioteca de carga de imágenes
        Glide.with(this).load(imageResId).into(imageView);

        // Establecer el OnClickListener en el FrameLayout
        frameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Salir de la actividad al hacer clic fuera de la imagen
                finish();
            }
        });
    }
}
