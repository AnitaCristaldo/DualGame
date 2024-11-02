package com.example.dualgame;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.Voice;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import com.example.dualgame.brailleviews.LevelsActivityBraille;
import com.example.dualgame.singviews.LevelsActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth auth;
    Button logoutButton;
    Button botonIdiomaSenas;
    Button botonIdiomaBraille;
    TextView textView;
    TextView loginCountTextView;
    FirebaseUser user;
    FirebaseFirestore db;
    TextView userWelcomeMessage;

    // Variable para TextToSpeech
    private TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);

        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        user = auth.getCurrentUser();

        logoutButton = findViewById(R.id.logout);
        textView = findViewById(R.id.user_details);
        botonIdiomaSenas = findViewById(R.id.boton_idioma_senas);
        botonIdiomaBraille = findViewById(R.id.boton_sistema_braille);
        loginCountTextView = findViewById(R.id.login_count_textview);
        userWelcomeMessage = findViewById(R.id.user_welcome_message);

        if (user == null) {
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
            finish();
        } else {
            displayUserInfo();
        }

        // Configuración de TextToSpeech
        textToSpeech = new TextToSpeech(this, status -> {
            if (status == TextToSpeech.SUCCESS) {
                textToSpeech.setLanguage(new Locale("es", "US"));  // Configuración en español neutro

                // Intenta establecer la voz de niño/niña
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    setChildlikeVoice();
                } else {
                    Toast.makeText(MainActivity.this, "El dispositivo no soporta selección de voces específicas", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(MainActivity.this, "Error en inicialización de TTS", Toast.LENGTH_SHORT).show();
            }
        });

        logoutButton.setOnClickListener(view -> {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
            finish();
        });

        botonIdiomaSenas.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, LevelsActivity.class);
            startActivity(intent);
        });

        botonIdiomaBraille.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, LevelsActivityBraille.class);
            startActivity(intent);
        });
    }

    private void displayUserInfo() {
        SharedPreferences preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        int loginCount = preferences.getInt("loginCount", 0);
        loginCount++;  // Incrementa el contador de inicio de sesión

        // Guarda el nuevo valor de loginCount en preferencias
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("loginCount", loginCount);

        long lastLoginTime = preferences.getLong("lastLoginTime", System.currentTimeMillis());
        editor.putLong("lastLoginTime", System.currentTimeMillis());
        editor.apply();

        // Mensaje de inicio de sesión que incluye loginCount
        final String loginCountMessage = "Has jugado " + loginCount + " vez/veces.";
        long daysSinceLastLogin = getDaysSinceLastLogin(lastLoginTime);

        DocumentReference userRef = db.collection("usuarios").document(user.getUid());
        userRef.get().addOnSuccessListener(documentSnapshot -> {
            if (documentSnapshot.exists()) {
                String name = documentSnapshot.getString("name");
                String welcomeMessage = "¡Hola " + name + "! Bienvenido de nuevo";
                String lastLoginMessage = daysSinceLastLogin == 0
                        ? "¡Qué bueno verte de nuevo hoy!"
                        : "Hace " + daysSinceLastLogin + " día(s) desde tu última visita.";

                // Se actualiza el TextView con el mensaje
                textView.setText(welcomeMessage + "\n" + lastLoginMessage + "\n" + loginCountMessage);

                // Llama a TextToSpeech para reproducir el mensaje de bienvenida
                speakWelcomeMessage(welcomeMessage, lastLoginMessage, loginCountMessage);
            }
        });
    }

    private long getDaysSinceLastLogin(long lastLoginTime) {
        long currentTime = System.currentTimeMillis();
        long timeDiff = currentTime - lastLoginTime;
        return TimeUnit.MILLISECONDS.toDays(timeDiff);
    }

    // Configura una voz de estilo infantil en español, si está disponible
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setChildlikeVoice() {
        for (Voice voice : textToSpeech.getVoices()) {
            if (voice.getName().toLowerCase().contains("es") && voice.getName().toLowerCase().contains("child")) {
                textToSpeech.setVoice(voice);
                break;
            }
        }
    }

    private void speakWelcomeMessage(String welcome, String lastLogin, String loginCount) {
        String fullMessage = welcome + " " + lastLogin + " " + loginCount;
        textToSpeech.speak(fullMessage, TextToSpeech.QUEUE_FLUSH, null, null);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
    }
}
