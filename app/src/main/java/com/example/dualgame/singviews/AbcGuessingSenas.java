package com.example.dualgame.singviews;

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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class AbcGuessingSenas extends AppCompatActivity {

    // Constantes
    private static final int MAX_GUESSES = 3;

    // Variables del juego
    private String word;
    private Button submitButton, resetButton, btnBack; // botón de retroceso
    private int remainingGuesses;
    private final List<String> incorrectLetters = new ArrayList<>();
    private final List<String> correctLetters = new ArrayList<>();
    private final List<WordHint> wordList = Arrays.asList(
            new WordHint("a", R.drawable.letra_a_senas),
            new WordHint("b", R.drawable.letra_b_senas),
            new WordHint("c", R.drawable.letra_c_senas),
            new WordHint("d", R.drawable.letra_d_senas),
            new WordHint("e", R.drawable.letra_e_senas),
            new WordHint("f", R.drawable.letra_f_senas),
            new WordHint("g", R.drawable.letra_g_senas),
            new WordHint("h", R.drawable.letra_h_senas),
            new WordHint("i", R.drawable.letra_i_senas),
            new WordHint("j", R.drawable.letra_j_senas),
            new WordHint("k", R.drawable.letra_k_senas),
            new WordHint("l", R.drawable.letra_l_senas),
            new WordHint("m", R.drawable.letra_m_senas),
            new WordHint("n", R.drawable.letra_n_senas),
            new WordHint("ñ", R.drawable.letra_enie_senas),
            new WordHint("o", R.drawable.letra_o_senas),
            new WordHint("p", R.drawable.letra_p_senas),
            new WordHint("q", R.drawable.letra_q_senas),
            new WordHint("r", R.drawable.letra_r_senas),
            new WordHint("s", R.drawable.letra_s_senas),
            new WordHint("t", R.drawable.letra_t_senas),
            new WordHint("u", R.drawable.letra_u_senas),
            new WordHint("v", R.drawable.letra_v_senas),
            new WordHint("w", R.drawable.letra_w_senas),
            new WordHint("x", R.drawable.letra_x_senas),
            new WordHint("y", R.drawable.letra_y_senas),
            new WordHint("z", R.drawable.letra_z_senas)
    );

    // Elementos de la interfaz
    private ImageView hintImageView;
    private TextView guessLeftTextView;
    private TextView wrongLettersTextView;
    private EditText inputLetter;

    // Sonidos
    private MediaPlayer correctSound;
    private MediaPlayer wrongSound;

    // TextToSpeech
    private TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guessinggame_abc);

        // Inicializar TextToSpeech
        initializeTextToSpeech();

        // Vincular elementos de la interfaz
        bindUI();

        // Inicializar sonidos
        initializeSounds();

        // Configurar botones
        setUpListeners();

        // Generar palabra aleatoria
        randomWord();

        // Inicializar el botón de retroceso y su listener
        btnBack = findViewById(R.id.btn_back);
        btnBack.setOnClickListener(view -> finish()); // Esto cierra la actividad actual
    }

    private void initializeTextToSpeech() {
        textToSpeech = new TextToSpeech(this, status -> {
            if (status == TextToSpeech.SUCCESS) {
                int langResult = textToSpeech.setLanguage(new Locale("es", "ES"));
                if (langResult == TextToSpeech.LANG_MISSING_DATA || langResult == TextToSpeech.LANG_NOT_SUPPORTED) {
                    Toast.makeText(this, "Idioma no soportado para TTS", Toast.LENGTH_SHORT).show();
                } else {
                    playExplanation();
                }
            } else {
                Toast.makeText(this, "Error en inicialización de TTS", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void bindUI() {
        hintImageView = findViewById(R.id.hint_image_view);
        guessLeftTextView = findViewById(R.id.guess_left_text_view);
        wrongLettersTextView = findViewById(R.id.wrong_letters_text_view);
        inputLetter = findViewById(R.id.input_letter);
        submitButton = findViewById(R.id.submit_button);
        resetButton = findViewById(R.id.reset_button);
    }

    private void initializeSounds() {
        correctSound = MediaPlayer.create(this, R.raw.correct_sound);
        wrongSound = MediaPlayer.create(this, R.raw.wrong_sound);
    }

    private void setUpListeners() {
        submitButton.setOnClickListener(v -> {
            String letter = inputLetter.getText().toString().toLowerCase();
            if (letter.length() == 1 && Character.isLetter(letter.charAt(0))) {
                processGuess(letter);
            } else {
                Toast.makeText(this, "Por favor, ingresa una sola letra", Toast.LENGTH_SHORT).show();
            }
            inputLetter.setText("");
        });

        resetButton.setOnClickListener(v -> randomWord());
    }

    private void randomWord() {
        WordHint randomItem = wordList.get(new Random().nextInt(wordList.size()));
        word = randomItem.word;
        remainingGuesses = MAX_GUESSES;
        correctLetters.clear();
        incorrectLetters.clear();
        hintImageView.setImageResource(randomItem.hintResId);
        updateUI();
    }

    private void processGuess(String letter) {
        if (!incorrectLetters.contains(letter) && !correctLetters.contains(letter)) {
            if (word.equals(letter)) {
                playCorrectSound();
                Toast.makeText(this, "¡Correcto! La letra es " + letter.toUpperCase(), Toast.LENGTH_SHORT).show();
                randomWord();
            } else {
                playWrongSound();
                remainingGuesses--;
                incorrectLetters.add(letter);
                updateUI();
            }
        }

        if (remainingGuesses < 1) {
            showLossDialog();
        }
    }

    private void updateUI() {
        guessLeftTextView.setText("Intentos restantes: " + remainingGuesses);
        wrongLettersTextView.setText("Letras incorrectas: " + String.join(", ", incorrectLetters));
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
                .setMessage("La letra era: " + word.toUpperCase())
                .setPositiveButton("OK", (dialog, which) -> randomWord())
                .setCancelable(false)
                .show();
    }

    private void playExplanation() {
        String explanationText = "Bienvenido al juego Adivina la letra en lengua de señas. Intenta adivinar la letra correspondiente al símbolo.";
        textToSpeech.speak(explanationText, TextToSpeech.QUEUE_FLUSH, null, null);
    }

    @Override
    protected void onDestroy() {
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        if (correctSound != null) {
            correctSound.release();
        }
        if (wrongSound != null) {
            wrongSound.release();
        }
        super.onDestroy();
    }

    // Clase auxiliar para mapear palabras y sus recursos de imagen
    private static class WordHint {
        String word;
        int hintResId;

        WordHint(String word, int hintResId) {
            this.word = word;
            this.hintResId = hintResId;
        }
    }
}
