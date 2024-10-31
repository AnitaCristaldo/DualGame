package com.example.dualgame.brailleviews;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.speech.tts.TextToSpeech;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dualgame.R;
import com.example.dualgame.games.VocalGuessingGameActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class VocalGuessingGameActivityBraille extends AppCompatActivity { // Define la clase WordGuessingGameActivityBraille que extiende AppCompatActivity

    // Declaración de variables miembro
    private String word; // Variable para almacenar la palabra actual que se va a adivinar
    private int maxGuesses; // Variable para almacenar el número máximo de intentos permitidos
    private List<String> incorrectLetters = new ArrayList<>(); // Lista para almacenar las letras incorrectas adivinadas
    private List<String> correctLetters = new ArrayList<>(); // Lista para almacenar las letras correctas adivinadas
    private List<VocalGuessingGameActivity.WordHint> wordList = Arrays.asList( // Lista de palabras con pistas que se van a utilizar en el juego
            new VocalGuessingGameActivity.WordHint("a", R.drawable.letra_a_braille),
            new VocalGuessingGameActivity.WordHint("e", R.drawable.letra_e_braille),
            new VocalGuessingGameActivity.WordHint("i", R.drawable.letra_i_braille),
            new VocalGuessingGameActivity.WordHint("o", R.drawable.letra_o_braille),
            new VocalGuessingGameActivity.WordHint("u", R.drawable.letra_u_braille)
    );

    // Declaración de variables para los elementos de la interfaz de usuario
    private ImageView hintImageView; // ImageView para mostrar la imagen de pista
    private TextView guessLeftTextView; // TextView para mostrar el número de intentos restantes
    private TextView wrongLettersTextView; // TextView para mostrar las letras incorrectas adivinadas
    private EditText typingInput; // EditText para la entrada de texto (aunque no parece estar en uso)
    private Button resetButton; // Botón para reiniciar el juego


    // Variables para los sonidos
    private MediaPlayer correctSound;
    private MediaPlayer wrongSound;

    // Variable para TextToSpeech
    private TextToSpeech textToSpeech;



    @Override
    protected void onCreate(Bundle savedInstanceState) { // Método que se llama cuando se crea la actividad
        super.onCreate(savedInstanceState); // Llama al método onCreate de la superclase
        setContentView(R.layout.activity_guessinggame); // Establece el diseño de la actividad solo activity_guessinggame pq tiene el diseño para las vocales


        // Inicializa TextToSpeech
        textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    // Configura el idioma español para el TTS
                    int langResult = textToSpeech.setLanguage(new Locale("es", "ES"));
                    if (langResult == TextToSpeech.LANG_MISSING_DATA || langResult == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Toast.makeText(VocalGuessingGameActivityBraille.this, "Idioma no soportado para TTS", Toast.LENGTH_SHORT).show();
                    } else {
                        // Reproduce el texto explicativo cuando TTS esté listo
                        playExplanation();
                    }
                } else {
                    Toast.makeText(VocalGuessingGameActivityBraille.this, "Error en inicialización de TTS", Toast.LENGTH_SHORT).show();
                }
            }
        });











        // Asigna los elementos de la interfaz de usuario a las variables
        hintImageView = findViewById(R.id.hint_image_view); // Encuentra el ImageView en el diseño
        guessLeftTextView = findViewById(R.id.guess_left_text_view); // Encuentra el TextView para los intentos restantes
        wrongLettersTextView = findViewById(R.id.wrong_letters_text_view); // Encuentra el TextView para las letras incorrectas
        typingInput = findViewById(R.id.typing_input); // Encuentra el EditText para la entrada de texto (aunque no se usa)
        resetButton = findViewById(R.id.reset_button); // Encuentra el botón de reinicio

        // Inicializa los sonidos
        correctSound = MediaPlayer.create(this, R.raw.correct_sound);
        wrongSound = MediaPlayer.create(this, R.raw.wrong_sound);

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                randomWord();
            }
        });






        // Establece un OnClickListener en el botón de reinicio
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // Método que se llama cuando se hace clic en el botón de reinicio
                randomWord(); // Llama al método para seleccionar una nueva palabra aleatoria
            }
        });

        setUpKeyboard(); // Configura el teclado para las letras
        randomWord(); // Selecciona una palabra aleatoria para empezar el juego
    }


    // Método para reproducir la explicación con TTS
    private void playExplanation() {
        String explanationText = "Bienvenido al juego Adivina la Vocal en sistema Braille. Tu objetivo es adivinar la vocal correcta a partir de la imagen proporcionada. Tienes tres intentos para acertar. ¡Buena suerte!";
        textToSpeech.speak(explanationText, TextToSpeech.QUEUE_FLUSH, null, null);
    }






    private void setUpKeyboard() { // Método para configurar el teclado de letras
        // Asigna cada botón de letra a una variable
        Button buttonA = findViewById(R.id.button_a); // Encuentra el botón para la letra 'A'
        Button buttonE = findViewById(R.id.button_e); // Encuentra el botón para la letra 'E'
        Button buttonI = findViewById(R.id.button_i); // Encuentra el botón para la letra 'I'
        Button buttonO = findViewById(R.id.button_o); // Encuentra el botón para la letra 'O'
        Button buttonU = findViewById(R.id.button_u); // Encuentra el botón para la letra 'U'

        // Crea un listener que maneja el clic de los botones
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) { // Método que se llama cuando se hace clic en un botón de letra
                Button button = (Button) v; // Obtiene el botón que fue clicado
                String letter = button.getText().toString().toLowerCase(); // Obtiene el texto del botón y lo convierte a minúsculas
                initGame(letter); // Llama al método initGame con la letra seleccionada
            }
        };

        // Establece el listener en cada botón
        buttonA.setOnClickListener(listener); // Configura el listener para el botón 'A'
        buttonE.setOnClickListener(listener); // Configura el listener para el botón 'E'
        buttonI.setOnClickListener(listener); // Configura el listener para el botón 'I'
        buttonO.setOnClickListener(listener); // Configura el listener para el botón 'O'
        buttonU.setOnClickListener(listener); // Configura el listener para el botón 'U'
    }

    private void randomWord() { // Método para seleccionar una palabra aleatoria y reiniciar el juego
        VocalGuessingGameActivity.WordHint ranItem = wordList.get(new Random().nextInt(wordList.size())); // Selecciona un elemento aleatorio de la lista de palabras
        word = ranItem.word; // Asigna la palabra seleccionada a la variable 'word'
        maxGuesses = 3; // Establece el número máximo de intentos a 3
        correctLetters.clear(); // Limpia la lista de letras correctas
        incorrectLetters.clear(); // Limpia la lista de letras incorrectas
        hintImageView.setImageResource(ranItem.hintResId); // Cambia la imagen de pista al recurso de imagen asociado con la palabra
        guessLeftTextView.setText("Intentos que te quedan: " + maxGuesses); // Actualiza el TextView con el número de intentos restantes
        wrongLettersTextView.setText("Vocales Incorrectas: " + String.join(", ", incorrectLetters)); // Actualiza el TextView con las letras incorrectas
    }

    private void initGame(String key) { // Método para manejar la lógica del juego cuando se selecciona una letra
        if (key.matches("[aeiou]") && !incorrectLetters.contains(" " + key) && !correctLetters.contains(key)) { // Verifica si la letra es válida y no ha sido adivinada antes
            if (word.equals(key)) { // Si la letra es la correcta
                correctLetters.add(key); // Añade la letra a la lista de letras correctas
                Toast.makeText(this, "Felicidades! Encontraste la vocal " + word.toUpperCase(), Toast.LENGTH_SHORT).show(); // Muestra un mensaje de felicitación
                randomWord(); // Selecciona una nueva palabra aleatoria
            } else { // Si la letra es incorrecta
                maxGuesses--; // Disminuye el número de intentos restantes
                incorrectLetters.add(" " + key); // Añade la letra a la lista de letras incorrectas
                guessLeftTextView.setText("Intentos que te quedan: " + maxGuesses); // Actualiza el TextView con el número de intentos restantes
                wrongLettersTextView.setText("Vocales Incorrectas: " + String.join(", ", incorrectLetters)); // Actualiza el TextView con las letras incorrectas
            }
        }

        if (maxGuesses < 1) { // Si no quedan intentos
            showLossDialog(); // Llama al método para mostrar el diálogo de pérdida
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



    private void showLossDialog() { // Método para mostrar un diálogo cuando se pierde el juego
        new AlertDialog.Builder(this) // Crea un nuevo AlertDialog
                .setTitle("Perdiste!") // Establece el título del diálogo
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

    // Método para cambiar a la actividad SubVocalsActivity
    private void goToSubVocalsActivity() {
        Intent intent = new Intent(VocalGuessingGameActivityBraille.this, SubVocalsActivityBraille.class);
        startActivity(intent);
        finish(); // Termina la actividad actual para que no esté en la pila de actividades
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
        public String word;        // Cambiado a public
        public int hintResId;      // Cambiado a public

        public WordHint(String word, int hintResId) {
            this.word = word;
            this.hintResId = hintResId;
        }
    }
}
