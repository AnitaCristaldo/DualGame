package com.example.dualgame.brailleviews;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class VocalGuessingGameActivityBraille extends AppCompatActivity {

    private String word;
    private int maxGuesses;
    private List<String> incorrectLetters = new ArrayList<>();
    private List<String> correctLetters = new ArrayList<>();
    private List<WordHint> wordList = Arrays.asList(
            new WordHint("a", R.drawable.letra_a_braille),
            new WordHint("e", R.drawable.letra_e_braille),
            new WordHint("i", R.drawable.letra_i_braille),
            new WordHint("o", R.drawable.letra_o_braille),
            new WordHint("u", R.drawable.letra_u_braille)
    );

    private ImageView hintImageView;
    private TextView guessLeftTextView;
    private TextView wrongLettersTextView;
    private EditText typingInput;
    private Button confirmButton;
    private Button resetButton;

    private MediaPlayer correctSound;
    private MediaPlayer wrongSound;

    private TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guessinggame);

        // Inicializa el botón de regreso
        Button backButton = findViewById(R.id.btn_back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Regresa a la actividad anterior
            }
        });

        // Inicializa el TextToSpeech para la explicación
        textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int langResult = textToSpeech.setLanguage(new Locale("es", "MX"));
                    if (langResult == TextToSpeech.LANG_MISSING_DATA || langResult == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Toast.makeText(VocalGuessingGameActivityBraille.this, "Idioma no soportado para TTS", Toast.LENGTH_SHORT).show();
                    } else {
                        textToSpeech.setPitch(1.5f);
                        textToSpeech.setSpeechRate(1.1f);
                        playExplanation();
                    }
                } else {
                    Toast.makeText(VocalGuessingGameActivityBraille.this, "Error en inicialización de TTS", Toast.LENGTH_SHORT).show();
                }
            }
        });

        hintImageView = findViewById(R.id.hint_image_view);
        guessLeftTextView = findViewById(R.id.guess_left_text_view);
        wrongLettersTextView = findViewById(R.id.wrong_letters_text_view);
        typingInput = findViewById(R.id.input_letter);
        confirmButton = findViewById(R.id.submit_button);
        resetButton = findViewById(R.id.reset_button);

        correctSound = MediaPlayer.create(this, R.raw.correct_sound);
        wrongSound = MediaPlayer.create(this, R.raw.wrong_sound);

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = typingInput.getText().toString().trim().toLowerCase();
                if (!input.isEmpty()) {
                    initGame(input);
                    typingInput.setText("");
                } else {
                    Toast.makeText(VocalGuessingGameActivityBraille.this, "Por favor ingresa una vocal", Toast.LENGTH_SHORT).show();
                }
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                randomWord();
            }
        });

        randomWord();
    }

    private void playExplanation() {
        String explanationText = "Bienvenido al juego Adivina la Vocal en Braille. Tu objetivo es adivinar la vocal correcta a partir de la imagen proporcionada. Tienes tres intentos para acertar. ¡Buena suerte!";
        textToSpeech.speak(explanationText, TextToSpeech.QUEUE_FLUSH, null, null);
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
        if (!key.matches("[a e i o u]")) {
            Toast.makeText(this, "Debes ingresar una vocal", Toast.LENGTH_SHORT).show();
            typingInput.setText("");
            return;
        }

        if (!incorrectLetters.contains(" " + key) && !correctLetters.contains(key)) {
            if (word.equals(key)) {
                correctLetters.add(key);
                Toast.makeText(this, "Felicidades! Encontraste la vocal " + word.toUpperCase(), Toast.LENGTH_SHORT).show();
                playCorrectSound();
                randomWord();
            } else {
                maxGuesses--;
                incorrectLetters.add(" " + key);
                guessLeftTextView.setText("Intentos que te quedan: " + maxGuesses);
                wrongLettersTextView.setText("Vocales Incorrectas: " + String.join(", ", incorrectLetters));
                playWrongSound();
            }
        }

        if (maxGuesses < 1) {
            showLossDialog();
        }
    }

    private void playCorrectSound() {
        if (correctSound != null) {
            correctSound.start();
        }
    }

    private void playWrongSound() {
        if (wrongSound != null) {
            wrongSound.start();
        }
    }

    private void showLossDialog() {
        new AlertDialog.Builder(this)
                .setTitle("¡Perdiste!")
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
        Intent intent = new Intent(VocalGuessingGameActivityBraille.this, SubVocalsActivityBraille.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (correctSound != null) {
            correctSound.release();
            correctSound = null;
        }
        if (wrongSound != null) {
            wrongSound.release();
            wrongSound = null;
        }
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
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
