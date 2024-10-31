package com.example.dualgame.games;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.example.dualgame.R;
import com.example.dualgame.singviews.SubVocalsActivity;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class VocalGuessingGameActivity extends AppCompatActivity {

    // Declaración de variables miembro
    private String word;
    private int maxGuesses;
    private List<String> incorrectLetters = new ArrayList<>();
    private List<String> correctLetters = new ArrayList<>();
    private List<WordHint> wordList = Arrays.asList(
            new WordHint("a", R.drawable.vocal_a),
            new WordHint("e", R.drawable.vocal_e),
            new WordHint("i", R.drawable.vocal_i),
            new WordHint("o", R.drawable.vocal_o),
            new WordHint("u", R.drawable.vocal_u)
    );

    // Declaración de variables para los elementos de la interfaz de usuario
    private ImageView hintImageView;
    private TextView guessLeftTextView;
    private TextView wrongLettersTextView;
    private EditText typingInput;
    private Button resetButton;

    // Variables para los sonidos
    private MediaPlayer correctSound;
    private MediaPlayer wrongSound;

    // Variable para TextToSpeech
    private TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guessinggame);

        // Inicializa TextToSpeech con tono y velocidad ajustados
        textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    // Configura el idioma español para el TTS
                    int langResult = textToSpeech.setLanguage(new Locale("es", "MX"));
                    if (langResult == TextToSpeech.LANG_MISSING_DATA || langResult == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Toast.makeText(VocalGuessingGameActivity.this, "Idioma no soportado para TTS", Toast.LENGTH_SHORT).show();
                    } else {
                        // Ajuste para un tono más infantil
                        textToSpeech.setPitch(1.5f);       // Aumenta el tono para que suene más aguda
                        textToSpeech.setSpeechRate(1.1f);  // Ajusta la velocidad ligeramente

                        // Reproduce el texto explicativo cuando TTS esté listo
                        playExplanation();
                    }
                } else {
                    Toast.makeText(VocalGuessingGameActivity.this, "Error en inicialización de TTS", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Asigna los elementos de la interfaz de usuario
        hintImageView = findViewById(R.id.hint_image_view);
        guessLeftTextView = findViewById(R.id.guess_left_text_view);
        wrongLettersTextView = findViewById(R.id.wrong_letters_text_view);
        typingInput = findViewById(R.id.typing_input);
        resetButton = findViewById(R.id.reset_button);

        // Inicializa los sonidos
        correctSound = MediaPlayer.create(this, R.raw.correct_sound);
        wrongSound = MediaPlayer.create(this, R.raw.wrong_sound);

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                randomWord();
            }
        });

        setUpKeyboard();
        randomWord();
    }

    // Método para reproducir la explicación con TTS
    private void playExplanation() {
        String explanationText = "Bienvenido al juego Adivina la Vocal en lengua de señas. Tu objetivo es adivinar la vocal correcta a partir de la imagen proporcionada. Tienes tres intentos para acertar. ¡Buena suerte!";
        textToSpeech.speak(explanationText, TextToSpeech.QUEUE_FLUSH, null, null);
    }

    private void setUpKeyboard() {
        Button buttonA = findViewById(R.id.button_a);
        Button buttonE = findViewById(R.id.button_e);
        Button buttonI = findViewById(R.id.button_i);
        Button buttonO = findViewById(R.id.button_o);
        Button buttonU = findViewById(R.id.button_u);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                String letter = button.getText().toString().toLowerCase();
                initGame(letter);
            }
        };

        buttonA.setOnClickListener(listener);
        buttonE.setOnClickListener(listener);
        buttonI.setOnClickListener(listener);
        buttonO.setOnClickListener(listener);
        buttonU.setOnClickListener(listener);
    }

    private void randomWord() {
        WordHint ranItem = wordList.get(new Random().nextInt(wordList.size()));
        word = ranItem.word;
        maxGuesses = 3;
        correctLetters.clear();
        incorrectLetters.clear();
        hintImageView.setImageResource(ranItem.hintResId);
        guessLeftTextView.setText("Intentos que te quedan: " + maxGuesses);
        wrongLettersTextView.setText("Vocales Incorrectas: " + String.join(", ", incorrectLetters));
    }

    private void initGame(String key) {
        if (key.matches("[aeiou]") && !incorrectLetters.contains(" " + key) && !correctLetters.contains(key)) {
            if (word.equals(key)) {
                correctLetters.add(key);
                Toast.makeText(this, "Felicidades! Encontraste la vocal " + word.toUpperCase(), Toast.LENGTH_SHORT).show();
                playCorrectSound(); // Reproduce el sonido correcto
                randomWord();
            } else {
                maxGuesses--;
                incorrectLetters.add(" " + key);
                guessLeftTextView.setText("Intentos que te quedan: " + maxGuesses);
                wrongLettersTextView.setText("Vocales Incorrectas: " + String.join(", ", incorrectLetters));
                playWrongSound(); // Reproduce el sonido incorrecto
            }
        }

        if (maxGuesses < 1) {
            showLossDialog();
        }
    }

    private void playCorrectSound() {
        if (correctSound != null) {
            correctSound.start(); // Reproduce el sonido de acierto
        }
    }

    private void playWrongSound() {
        if (wrongSound != null) {
            wrongSound.start(); // Reproduce el sonido de error
        }
    }

    private void showLossDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Perdiste!")
                .setMessage("La vocal era: " + word.toUpperCase())
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        goToSubVocalsActivity();
                    }
                })
                .setCancelable(false)
                .show();
    }

    private void goToSubVocalsActivity() {
        Intent intent = new Intent(VocalGuessingGameActivity.this, SubVocalsActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (correctSound != null) {
            correctSound.release(); // Libera los recursos del MediaPlayer
            correctSound = null;
        }
        if (wrongSound != null) {
            wrongSound.release(); // Libera los recursos del MediaPlayer
            wrongSound = null;
        }
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown(); // Libera los recursos de TTS
        }
    }

    public static class WordHint {
        public String word;
        public int hintResId;

        public WordHint(String word, int hintResId) {
            this.word = word;
            this.hintResId = hintResId;
        }
    }
}
