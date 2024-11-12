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

import com.bumptech.glide.Glide;
import com.example.dualgame.R;
import com.example.dualgame.brailleviews.SubNumbersActivityBraille;
import com.example.dualgame.singviews.SubNumbersActivity;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class NumberGuessingActivityBraille extends AppCompatActivity {

    // Declaración de variables miembro
    private String number;
    private int maxGuesses;
    private List<String> incorrectNumbers = new ArrayList<>();
    private List<String> correctNumbers = new ArrayList<>();
    private List<WordHint> numberList = Arrays.asList(
            new WordHint("0", R.drawable.numero_cero_braille),
            new WordHint("1", R.drawable.numero_uno_braille),
            new WordHint("2", R.drawable.numero_dos_braille),
            new WordHint("3", R.drawable.numero_tres_braille),
            new WordHint("4", R.drawable.numero_cuatro_braille),
            new WordHint("5", R.drawable.numero_cinco_braille),
            new WordHint("6", R.drawable.numero_seis_braille),
            new WordHint("7", R.drawable.numero_siete_braille),
            new WordHint("8", R.drawable.numero_ocho_braille),
            new WordHint("9", R.drawable.numero_nueve_braille)

    );

    // Declaración de variables para los elementos de la interfaz de usuario
    private ImageView hintImageView;
    private TextView guessLeftTextView;
    private TextView wrongNumbersTextView;
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
        setContentView(R.layout.activity_guessinggame_number);

        // Inicializa TextToSpeech con tono y velocidad ajustados
        textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    // Configura el idioma español para el TTS
                    int langResult = textToSpeech.setLanguage(new Locale("es", "MX"));
                    if (langResult == TextToSpeech.LANG_MISSING_DATA || langResult == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Toast.makeText(NumberGuessingActivityBraille.this, "Idioma no soportado para TTS", Toast.LENGTH_SHORT).show();
                    } else {
                        // Ajuste para un tono más infantil
                        textToSpeech.setPitch(1.5f);       // Aumenta el tono para que suene más aguda
                        textToSpeech.setSpeechRate(1.1f);  // Ajusta la velocidad ligeramente

                        // Reproduce el texto explicativo cuando TTS esté listo
                        playExplanation();
                    }
                } else {
                    Toast.makeText(NumberGuessingActivityBraille.this, "Error en inicialización de TTS", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Asigna los elementos de la interfaz de usuario
        hintImageView = findViewById(R.id.hint_image_view);
        guessLeftTextView = findViewById(R.id.guess_left_text_view);
        wrongNumbersTextView = findViewById(R.id.wrong_numbers_text_view);
        typingInput = findViewById(R.id.typing_input);
        resetButton = findViewById(R.id.reset_button);

        // Inicializa los sonidos
        correctSound = MediaPlayer.create(this, R.raw.correct_sound);
        wrongSound = MediaPlayer.create(this, R.raw.wrong_sound);

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                randomNumber();
            }
        });

        setUpKeyboard();
        randomNumber();
    }

    // Método para reproducir la explicación con TTS
    private void playExplanation() {
        String explanationText = "Bienvenido al juego Adivina el número en sistema Braille. Tu objetivo es adivinar el número correcto a partir de la imagen proporcionada. Tienes tres intentos para acertar. ¡Buena suerte!";
        textToSpeech.speak(explanationText, TextToSpeech.QUEUE_FLUSH, null, null);
    }

    private void setUpKeyboard() {
        Button button0 = findViewById(R.id.button_0);
        Button button1 = findViewById(R.id.button_1);
        Button button2 = findViewById(R.id.button_2);
        Button button3 = findViewById(R.id.button_3);
        Button button4 = findViewById(R.id.button_4);
        Button button5 = findViewById(R.id.button_5);
        Button button6 = findViewById(R.id.button_6);
        Button button7 = findViewById(R.id.button_7);
        Button button8 = findViewById(R.id.button_8);
        Button button9 = findViewById(R.id.button_9);


        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                String number = button.getText().toString();  // Obtener el número como texto
                initGame(number);  // Pasar el número al método initGame
            }
        };

        button0.setOnClickListener(listener);
        button1.setOnClickListener(listener);
        button2.setOnClickListener(listener);
        button3.setOnClickListener(listener);
        button4.setOnClickListener(listener);
        button5.setOnClickListener(listener);
        button6.setOnClickListener(listener);
        button7.setOnClickListener(listener);
        button8.setOnClickListener(listener);
        button9.setOnClickListener(listener);

    }

    private void randomNumber() {
        WordHint ranItem = numberList.get(new Random().nextInt(numberList.size()));
        number = ranItem.word;
        maxGuesses = 3;
        correctNumbers.clear();
        incorrectNumbers.clear();

        // Cargar el GIF para el número 10
        if ("10".equals(number)) {
            Glide.with(this)
                    .load(R.drawable.numero_diez_senas)  // Esto ahora puede ser un archivo GIF
                    .into(hintImageView);  // Usamos Glide para cargar el GIF
        } else {
            hintImageView.setImageResource(ranItem.hintResId);  // Para otros números, usar la imagen estática
        }

        guessLeftTextView.setText("Intentos que te quedan: " + maxGuesses);
        wrongNumbersTextView.setText("Números Incorrectos: " + String.join(", ", incorrectNumbers));
    }

    private void initGame(String key) {
        // Verifica si el número ingresado es un número válido (de 0 a 10)
        if (key.matches("[0-9]") || key.equals("10")) { // Acepta números del 0 al 9 y el 10
            // Verifica si el número no ha sido ya adivinado o incorrecto
            if (!incorrectNumbers.contains(" " + key) && !correctNumbers.contains(key)) {
                boolean isValidNumber = false;

                // Verifica si el número ingresado es válido (de 0 a 10)
                int numberInput = Integer.parseInt(key);
                if (numberInput >= 0 && numberInput <= 10) {
                    isValidNumber = true;
                }

                // Si el número es válido, continúa con la lógica del juego
                if (isValidNumber) {
                    if (number.equals(key)) {
                        correctNumbers.add(key);
                        Toast.makeText(this, "¡Felicidades! Encontraste el número " + number.toUpperCase(), Toast.LENGTH_SHORT).show();
                        playCorrectSound(); // Reproduce el sonido correcto
                        randomNumber(); // Carga un nuevo número aleatorio
                    } else {
                        maxGuesses--;
                        incorrectNumbers.add(" " + key);
                        guessLeftTextView.setText("Intentos que te quedan: " + maxGuesses);
                        wrongNumbersTextView.setText("Números Incorrectos: " + String.join(", ", incorrectNumbers));
                        playWrongSound(); // Reproduce el sonido incorrecto
                    }
                } else {
                    // Si el número no está en el rango de 0 a 10, muestra un mensaje de error
                    Toast.makeText(this, "Número no válido. Ingresa un número entre 0 y 10.", Toast.LENGTH_SHORT).show();
                }
            }
        } else {
            // Si el número no es válido (no es un número de un solo dígito o el número 10), muestra un mensaje de error
            Toast.makeText(this, "Número no válido. Ingresa un número entre 0 y 10.", Toast.LENGTH_SHORT).show();
        }

        // Si se han agotado los intentos, muestra el diálogo de pérdida
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
                .setTitle("¡Perdiste!")
                .setMessage("El número era: " + number.toUpperCase())
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        goToSubnumberActivityBraille();
                    }
                })
                .setCancelable(false)
                .show();
    }

    private void goToSubnumberActivityBraille() {
        Intent intent = new Intent(NumberGuessingActivityBraille.this, SubNumbersActivityBraille.class);
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
            textToSpeech.shutdown(); // Libera recursos del TTS
        }
    }

    private static class WordHint {
        String word;
        int hintResId;

        WordHint(String word, int hintResId) {
            this.word = word;
            this.hintResId = hintResId;
        }
    }
}
