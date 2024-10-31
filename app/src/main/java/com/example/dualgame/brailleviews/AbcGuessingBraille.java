package com.example.dualgame.brailleviews;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.speech.tts.TextToSpeech;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.example.dualgame.R;
import com.example.dualgame.games.QuizQuestionsActivity;
import com.example.dualgame.games.VocalGuessingGameActivity;
import com.example.dualgame.singviews.SubVocalsActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class AbcGuessingBraille extends AppCompatActivity{


        private String word; // Palabra actual a adivinar
        private int maxGuesses; // Número máximo de intentos
        private List<String> incorrectLetters = new ArrayList<>(); // Letras incorrectas adivinadas
        private List<String> correctLetters = new ArrayList<>(); // Letras correctas adivinadas

        private List<WordHint> wordList = Arrays.asList(
                new WordHint("a", R.drawable.letra_a_braille),
                new WordHint("b", R.drawable.letra_b_braille),
                new WordHint("c", R.drawable.letra_c_braille),
                new WordHint("d", R.drawable.letra_d_braille),
                new WordHint("e", R.drawable.letra_e_braille),
                new WordHint("f", R.drawable.letra_f_braille),
                new WordHint("g", R.drawable.letra_g_braille),
                new WordHint("h", R.drawable.letra_h_braille),
                new WordHint("i", R.drawable.letra_i_braille),
                new WordHint("j", R.drawable.letra_j_braille),
                new WordHint("k", R.drawable.letra_k_braille),
                new WordHint("l", R.drawable.letra_l_braille),
                new WordHint("m", R.drawable.letra_m_braille),
                new WordHint("n", R.drawable.letra_n_braille),
                new WordHint("ñ", R.drawable.letra_enie_braille),
                new WordHint("o", R.drawable.letra_o_braille),
                new WordHint("p", R.drawable.letra_p_braille),
                new WordHint("q", R.drawable.letra_q_braille),
                new WordHint("r", R.drawable.letra_r_braille),
                new WordHint("s", R.drawable.letra_s_braille),
                new WordHint("t", R.drawable.letra_t_braille),
                new WordHint("u", R.drawable.letra_u_braille),
                new WordHint("v", R.drawable.letra_v_braille),
                new WordHint("w", R.drawable.letra_w_braille),
                new WordHint("x", R.drawable.letra_x_braille),
                new WordHint("y", R.drawable.letra_y_braille),
                new WordHint("z", R.drawable.letra_z_braille)
        );

        private ImageView hintImageView;
        private TextView guessLeftTextView;
        private TextView wrongLettersTextView;
        private Button resetButton, btn_back;


    // Variables para los sonidos
    private MediaPlayer correctSound;
    private MediaPlayer wrongSound;

    // Variable para TextToSpeech
    private TextToSpeech textToSpeech;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_guessinggame_braille);

            // Inicializa TextToSpeech
            textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
                @Override
                public void onInit(int status) {
                    if (status == TextToSpeech.SUCCESS) {
                        // Configura el idioma español para el TTS
                        int langResult = textToSpeech.setLanguage(new Locale("es", "ES"));
                        if (langResult == TextToSpeech.LANG_MISSING_DATA || langResult == TextToSpeech.LANG_NOT_SUPPORTED) {
                            Toast.makeText(AbcGuessingBraille.this, "Idioma no soportado para TTS", Toast.LENGTH_SHORT).show();
                        } else {
                            // Reproduce el texto explicativo cuando TTS esté listo
                            playExplanation();
                        }
                    } else {
                        Toast.makeText(AbcGuessingBraille.this, "Error en inicialización de TTS", Toast.LENGTH_SHORT).show();
                    }
                }
            });





            // Asigna los elementos de la interfaz de usuario
            hintImageView = findViewById(R.id.hint_image_view);
            guessLeftTextView = findViewById(R.id.guess_left_text_view);
            wrongLettersTextView = findViewById(R.id.wrong_letters_text_view);
            btn_back = findViewById(R.id.btn_back);
            resetButton = findViewById(R.id.reset_button);

            // Listener específico para el botón de retroceso.
            btn_back.setOnClickListener(v -> {
                Intent intent = new Intent(AbcGuessingBraille.this, AbecedarioActivityBraille.class);
                startActivity(intent);
                finish(); // Finaliza la actividad actual para no regresar a ella.
            });




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
        String explanationText = "Bienvenido al juego Adivina la letra en sitema Braille. Tu objetivo es adivinar la letra correcta a partir de la imagen proporcionada. Tienes tres intentos para acertar. ¡Buena suerte!";
        textToSpeech.speak(explanationText, TextToSpeech.QUEUE_FLUSH, null, null);
    }
        private void setUpKeyboard() {
            // Asignación de botones para cada letra del abecedario
            Button[] buttons = new Button[]{
                    findViewById(R.id.button_a),
                    findViewById(R.id.button_b),
                    findViewById(R.id.button_c),
                    findViewById(R.id.button_d),
                    findViewById(R.id.button_e),
                    findViewById(R.id.button_f),
                    findViewById(R.id.button_g),
                    findViewById(R.id.button_h),
                    findViewById(R.id.button_i),
                    findViewById(R.id.button_j),
                    findViewById(R.id.button_k),
                    findViewById(R.id.button_l),
                    findViewById(R.id.button_m),
                    findViewById(R.id.button_n),
                    findViewById(R.id.button_ñ),
                    findViewById(R.id.button_o),
                    findViewById(R.id.button_p),
                    findViewById(R.id.button_q),
                    findViewById(R.id.button_r),
                    findViewById(R.id.button_s),
                    findViewById(R.id.button_t),
                    findViewById(R.id.button_u),
                    findViewById(R.id.button_v),
                    findViewById(R.id.button_w),
                    findViewById(R.id.button_x),
                    findViewById(R.id.button_y),
                    findViewById(R.id.button_z)
            };

            // Listener para todos los botones
            View.OnClickListener listener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Button button = (Button) v;
                    String letter = button.getText().toString().toLowerCase();
                    initGame(letter);
                }
            };

            // Asignar el listener a cada botón
            for (Button button : buttons) {
                button.setOnClickListener(listener);
            }
        }

        private void randomWord() {
            WordHint ranItem = wordList.get(new Random().nextInt(wordList.size()));
            word = ranItem.word;
            maxGuesses = 3;
            correctLetters.clear();
            incorrectLetters.clear();
            hintImageView.setImageResource(ranItem.hintResId);
            guessLeftTextView.setText("Intentos que te quedan: " + maxGuesses);
            wrongLettersTextView.setText("Letras Incorrectas: " + String.join(", ", incorrectLetters));
        }

        private void initGame(String key) {
            if (!incorrectLetters.contains(" " + key) && !correctLetters.contains(key)) {
                if (word.equals(key)) {
                    correctLetters.add(key);
                    Toast.makeText(this, "Felicidades! Encontraste la letra " + word.toUpperCase(), Toast.LENGTH_SHORT).show();
                    randomWord();
                } else {
                    maxGuesses--;
                    incorrectLetters.add(" " + key);
                    guessLeftTextView.setText("Intentos que te quedan: " + maxGuesses);
                    wrongLettersTextView.setText("Letras Incorrectas: " + String.join(", ", incorrectLetters));
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
                    .setMessage("La letra era: " + word.toUpperCase())
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            goToAbcActivity();
                        }
                    })
                    .setCancelable(false)
                    .show();
        }

        private void goToAbcActivity() {
            Intent intent = new Intent(AbcGuessingBraille.this, AbecedarioActivityBraille.class);
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


