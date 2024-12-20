package com.example.dualgame.games;


import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.example.dualgame.R;
import com.example.dualgame.brailleviews.SubNumbersActivityBraille;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class NumberGuessingActivitySenas extends AppCompatActivity {
    // Variables de juego
    private String number;
    private int maxGuesses;
    private final List<String> incorrectNumbers = new ArrayList<>();
    private final List<String> correctNumbers = new ArrayList<>();
    private final List<WordHint> numberList = Arrays.asList(
            new WordHint("0", R.drawable.numero_cero_senas),
            new WordHint("1", R.drawable.numero_uno_senas),
            new WordHint("2", R.drawable.numero_dos_senas),
            new WordHint("3", R.drawable.numero_tres_senas),
            new WordHint("4", R.drawable.numero_cuatro_senas),
            new WordHint("5", R.drawable.numero_cinco_senas),
            new WordHint("6", R.drawable.numero_seis_senas),
            new WordHint("7", R.drawable.numero_siete_senas),
            new WordHint("8", R.drawable.numero_ocho_senas),
            new WordHint("9", R.drawable.numero_nueve_senas),
            new WordHint("10", R.drawable.numero_diez_senas)
    );

    // Elementos UI
    private ImageView hintImageView;
    private TextView guessLeftTextView;
    private TextView wrongNumbersTextView;
    private EditText typingInput;
    private Button confirmButton;
    private Button resetButton;

    // Recursos de audio y TTS
    private MediaPlayer correctSound;
    private MediaPlayer wrongSound;
    private TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guessinggame_number);


        // Inicializa el botón de regreso
        Button backButton = findViewById(R.id.btn_back);
        backButton.setOnClickListener(v -> finish());

        // Inicializar TextToSpeech
        textToSpeech = new TextToSpeech(this, status -> {
            if (status == TextToSpeech.SUCCESS) {
                int langResult = textToSpeech.setLanguage(new Locale("es", "MX"));
                if (langResult == TextToSpeech.LANG_MISSING_DATA || langResult == TextToSpeech.LANG_NOT_SUPPORTED) {
                    Toast.makeText(NumberGuessingActivitySenas.this, "Idioma no soportado para TTS", Toast.LENGTH_SHORT).show();
                } else {
                    textToSpeech.setPitch(1.5f);
                    textToSpeech.setSpeechRate(1.1f);
                    playExplanation();
                }
            } else {
                Toast.makeText(NumberGuessingActivitySenas.this, "Error en inicialización de TTS", Toast.LENGTH_SHORT).show();
            }
        });

        // Asignar elementos UI
        hintImageView = findViewById(R.id.hint_image_view);
        guessLeftTextView = findViewById(R.id.guess_left_text_view);
        wrongNumbersTextView = findViewById(R.id.wrong_numbers_text_view);
        typingInput = findViewById(R.id.input_number);
        confirmButton = findViewById(R.id.submit_button);
        resetButton = findViewById(R.id.reset_button);

        // Inicializar sonidos
        correctSound = MediaPlayer.create(this, R.raw.correct_sound);
        wrongSound = MediaPlayer.create(this, R.raw.wrong_sound);

        confirmButton.setOnClickListener(v -> {
            String input = typingInput.getText().toString().trim();
            if (!input.isEmpty()) {
                processGuess(input);
                typingInput.setText("");
            } else {
                Toast.makeText(this, "Por favor ingresa un número", Toast.LENGTH_SHORT).show();
            }
        });

        resetButton.setOnClickListener(v -> randomNumber());

        randomNumber();
    }

    private void playExplanation() {
        String explanation = "Bienvenido al juego Adivina el número en lengua de señas. Tu objetivo es adivinar el número correcto a partir de la imagen proporcionada. Tienes tres intentos para acertar. ¡Buena suerte!";
        textToSpeech.speak(explanation, TextToSpeech.QUEUE_FLUSH, null, null);
    }

    private void randomNumber() {
        WordHint ranItem = numberList.get(new Random().nextInt(numberList.size()));
        number = ranItem.word;
        maxGuesses = 3;
        correctNumbers.clear();
        incorrectNumbers.clear();
        hintImageView.setImageResource(ranItem.hintResId);
        guessLeftTextView.setText("Intentos restantes: " + maxGuesses);
        wrongNumbersTextView.setText("Números incorrectos: " + String.join(", ", incorrectNumbers));
    }

    private void processGuess(String input) {
        if (!input.matches("\\d{1,2}") || Integer.parseInt(input) > 10) {//\\d{1,2} permite uno o dos dígitos. Integer.parseInt(input) > 10 asegura que el número no exceda 10
            Toast.makeText(this, "Debes ingresar un número del 0 al 10", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!incorrectNumbers.contains(input) && !correctNumbers.contains(input)) {
            if (number.equals(input)) {
                correctNumbers.add(input);
                Toast.makeText(this, "¡Correcto! El número era " + number, Toast.LENGTH_SHORT).show();
                playCorrectSound();
                randomNumber();
            } else {
                maxGuesses--;
                incorrectNumbers.add(input);
                guessLeftTextView.setText("Intentos restantes: " + maxGuesses);
                wrongNumbersTextView.setText("Números incorrectos: " + String.join(", ", incorrectNumbers));
                playWrongSound();
            }
        }

        if (maxGuesses < 1) {
            showLossDialog();
        }
    }

    private void playCorrectSound() {
        if (correctSound != null) correctSound.start();
    }

    private void playWrongSound() {
        if (wrongSound != null) wrongSound.start();
    }

    private void showLossDialog() {
        new AlertDialog.Builder(this)
                .setTitle("¡Perdiste!")
                .setMessage("El número era: " + number)
                .setPositiveButton("OK", (dialog, which) -> goToSubNumbersActivityBraille())
                .setCancelable(false)
                .show();
    }

    private void goToSubNumbersActivityBraille() {
        Intent intent = new Intent(this, SubNumbersActivityBraille.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (correctSound != null) correctSound.release();
        if (wrongSound != null) wrongSound.release();
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
