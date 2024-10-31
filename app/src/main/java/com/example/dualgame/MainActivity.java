package com.example.dualgame;

// Importaciones necesarias para la funcionalidad de la actividad.
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dualgame.brailleviews.LevelsActivityBraille;
import com.example.dualgame.singviews.LevelsActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

// La actividad MainActivity hereda de AppCompatActivity, permitiendo el uso de componentes modernos de la interfaz de usuario.
public class MainActivity extends AppCompatActivity {

    // Declaración de variables para la autenticación de Firebase y componentes de la interfaz.
    FirebaseAuth auth;
    Button logoutButton;
    Button botonIdiomaSenas;

    Button botonIdiomaBraille;
    TextView textView;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);
        // Establece el diseño de la actividad utilizando el archivo de diseño activity_language.xml.

        // Inicializa FirebaseAuth y obtiene una instancia de autenticación.
        auth = FirebaseAuth.getInstance();

        // Obtiene referencias a los elementos de la interfaz de usuario (botones y TextView).
        logoutButton = findViewById(R.id.logout); // Botón de cerrar sesión.
        textView = findViewById(R.id.user_details); // TextView para mostrar detalles del usuario.
        botonIdiomaSenas = findViewById(R.id.boton_idioma_senas); // Botón para iniciar otra actividad.
        botonIdiomaBraille = findViewById(R.id.boton_sistema_braille); // Botón para iniciar otra actividad.

        // Obtiene el usuario actualmente autenticado.
        user = auth.getCurrentUser();

        // Verifica si el usuario no está autenticado (es decir, si es null).
        if (user == null) {
            // Si no hay usuario autenticado, redirige a la pantalla de inicio de sesión.
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
            finish(); // Finaliza la actividad actual para que el usuario no pueda volver a ella usando el botón de retroceso.
        } else {
            // Si hay un usuario autenticado, muestra su correo electrónico en el TextView.
            textView.setText(user.getEmail());
        }

        // Configura un listener de clic para el botón de cerrar sesión.
        logoutButton.setOnClickListener(view -> {
            // Cierra sesión en Firebase.
            FirebaseAuth.getInstance().signOut();

            // Redirige al usuario a la pantalla de inicio de sesión.
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
            finish(); // Finaliza la actividad actual.
        });

        // Configura un listener de clic para el botón 'botonIdiomaSenas'.
        botonIdiomaSenas.setOnClickListener(v -> {
            // Crea un Intent para iniciar la actividad LevelsActivity.
            Intent intent = new Intent(MainActivity.this, LevelsActivity.class);
            startActivity(intent); // Inicia la actividad LevelsActivity.
        });

        // Configura un listener de clic para el botón 'botonIdiomaBraille'.
        botonIdiomaBraille.setOnClickListener(v -> {
            // Crea un Intent para iniciar la actividad LevelsActivity.
            Intent intent = new Intent(MainActivity.this, LevelsActivityBraille.class);
            startActivity(intent); // Inicia la actividad LevelsActivitybraille.
        });


    }
}
