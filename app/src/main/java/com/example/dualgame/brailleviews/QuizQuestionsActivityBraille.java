package com.example.dualgame.brailleviews;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.dualgame.R;
import com.example.dualgame.games.ConstantsBraille;
import com.example.dualgame.games.QuestionBraille;
import com.example.dualgame.games.QuizQuestionsNumbersSenas;

import java.util.ArrayList;
import java.util.Locale;

public class QuizQuestionsActivityBraille extends AppCompatActivity implements View.OnClickListener {

    private int mCurrentPosition = 1;
    private ArrayList<QuestionBraille> mQuestionList;
    private int mSelectedOptionPosition = 0;
    private int mCorrectAnswers = 0;

    // Define variables for views
    private TextView tv_question;
    private ImageView iv_image;
    private TextView tv_option_one;
    private TextView tv_option_two;
    private TextView tv_option_three;
    private TextView tv_option_four;
    private Button btn_submit, btn_back_braille;
    private ProgressBar progressBar;
    private TextView tv_progress;

    // Para sonidos y TextToSpeech
    private MediaPlayer correctSound;
    private MediaPlayer wrongSound;
    private TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_questions_braille);   //CAMBIAR

        // Initialize views
        tv_question = findViewById(R.id.tv_question);
        iv_image = findViewById(R.id.iv_image);
        tv_option_one = findViewById(R.id.tv_option_one);
        tv_option_two = findViewById(R.id.tv_option_two);
        tv_option_three = findViewById(R.id.tv_option_three);
        tv_option_four = findViewById(R.id.tv_option_four);
        btn_submit = findViewById(R.id.btn_submit);
        progressBar = findViewById(R.id.progressBar);
        tv_progress = findViewById(R.id.tv_progress);
        btn_back_braille = findViewById(R.id.btn_back_braille); // Inicializar el botón de retroceso.

        mQuestionList = ConstantsBraille.getQuestions();
        setQuestion();

        // Set onClickListeners
        tv_option_one.setOnClickListener(this);
        tv_option_two.setOnClickListener(this);
        tv_option_three.setOnClickListener(this);
        tv_option_four.setOnClickListener(this);
        btn_submit.setOnClickListener(this);
        // Establecer un listener específico para el botón de retroceso.
        btn_back_braille.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Inicia la actividad SubVocalsActivity cuando se presiona el botón de retroceso.
                Intent intent = new Intent(QuizQuestionsActivityBraille.this, SubVocalsActivityBraille.class);
                startActivity(intent);
                finish(); // Finaliza la actividad actual para no regresar a ella.
            }
        });


        // Inicializar TextToSpeech
        textToSpeech = new TextToSpeech(this, status -> {
            if (status == TextToSpeech.SUCCESS) {
                int langResult = textToSpeech.setLanguage(new Locale("es", "MX"));
                if (langResult == TextToSpeech.LANG_MISSING_DATA || langResult == TextToSpeech.LANG_NOT_SUPPORTED) {
                    Toast.makeText(QuizQuestionsActivityBraille.this, "Idioma no soportado para TTS", Toast.LENGTH_SHORT).show();
                } else {
                    textToSpeech.setPitch(1.5f);
                    textToSpeech.setSpeechRate(1.1f);
                    playExplanation();
                }
            } else {
                Toast.makeText(QuizQuestionsActivityBraille.this, "Error en inicialización de TTS", Toast.LENGTH_SHORT).show();
            }
        });

        // Inicializar sonidos
        correctSound = MediaPlayer.create(this, R.raw.correct_sound);
        wrongSound = MediaPlayer.create(this, R.raw.wrong_sound);


    }

    private void playExplanation() {
        String explanationText = "Bienvenido al juego Cuestionario de vocales en Sistema Braile. Tu objetivo es seleccionar la respuesta correcta a partir de la imagen proporcionada. Tienes 5 preguntas. ¡Buena suerte!";
        textToSpeech.speak(explanationText, TextToSpeech.QUEUE_FLUSH, null, null);
    }

    private void setQuestion() {
        QuestionBraille question = mQuestionList.get(mCurrentPosition - 1);
        defaultOptionsView();

        if (mCurrentPosition == mQuestionList.size()) {
            btn_submit.setText("Finalizado");
        } else {
            btn_submit.setText("Entregar");
        }

        progressBar.setProgress(mCurrentPosition);
        tv_progress.setText(mCurrentPosition + "/" + progressBar.getMax());
        tv_question.setText(question.getQuestion());
        iv_image.setImageResource(question.getImage());
        tv_option_one.setText(question.getOptionOne());
        tv_option_two.setText(question.getOptionTwo());
        tv_option_three.setText(question.getOptionThree());
        tv_option_four.setText(question.getOptionFour());
    }

    private void defaultOptionsView() {
        ArrayList<TextView> options = new ArrayList<>();
        options.add(tv_option_one);
        options.add(tv_option_two);
        options.add(tv_option_three);
        options.add(tv_option_four);

        for (TextView option : options) {
            option.setTextColor(Color.parseColor("#7A8089"));
            option.setTypeface(Typeface.DEFAULT);
            option.setBackground(ContextCompat.getDrawable(this, R.drawable.default_option_border_bg));
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.tv_option_one) {
            selectedOptionView(tv_option_one, 1);
        } else if (id == R.id.tv_option_two) {
            selectedOptionView(tv_option_two, 2);
        } else if (id == R.id.tv_option_three) {
            selectedOptionView(tv_option_three, 3);
        } else if (id == R.id.tv_option_four) {
            selectedOptionView(tv_option_four, 4);
        } else if (id == R.id.btn_submit) {
            if (mSelectedOptionPosition == 0) {
                mCurrentPosition++;
                if (mCurrentPosition <= mQuestionList.size()) {
                    setQuestion();
                } else {
                    Intent intent = new Intent(QuizQuestionsActivityBraille.this, ResultActivityBraille.class);
                    intent.putExtra(ConstantsBraille.CORRECT_ANSWERS, mCorrectAnswers);
                    intent.putExtra(ConstantsBraille.TOTAL_QUESTIONS, mQuestionList.size());
                    startActivity(intent);
                    finish(); // Finish QuizQuestionsActivity to prevent returning here
                }
            } else {
                QuestionBraille question = mQuestionList.get(mCurrentPosition - 1);
                if (question.getCorrectAnswer() == mSelectedOptionPosition) {
                    // Respuesta correcta
                    mCorrectAnswers++;
                    answerView(question.getCorrectAnswer(), R.drawable.selected_option_correct);
                } else {
                    // Respuesta incorrecta
                    answerView(mSelectedOptionPosition, R.drawable.selected_option_inco);
                    // Mostrar la respuesta correcta
                    answerView(question.getCorrectAnswer(), R.drawable.selected_option_correct);
                }

                if (mCurrentPosition == mQuestionList.size()) {
                    btn_submit.setText("FINALIZADO");
                } else {
                    btn_submit.setText("IR A LA SIGUIENTE PREGUNTA");
                }
                mSelectedOptionPosition = 0; // Reset selected option position
            }
        }
    }


    private void answerView(int answer, int drawableView) {
        switch (answer) {
            case 1:
                tv_option_one.setBackground(ContextCompat.getDrawable(this, drawableView));
                break;
            case 2:
                tv_option_two.setBackground(ContextCompat.getDrawable(this, drawableView));
                break;
            case 3:
                tv_option_three.setBackground(ContextCompat.getDrawable(this, drawableView));
                break;
            case 4:
                tv_option_four.setBackground(ContextCompat.getDrawable(this, drawableView));
                break;
        }
    }

    private void selectedOptionView(TextView tv, int selectedOptionNum) {
        defaultOptionsView();
        mSelectedOptionPosition = selectedOptionNum;

        tv.setTextColor(Color.parseColor("#363A43"));
        tv.setTypeface(tv.getTypeface(), Typeface.BOLD);
        tv.setBackground(ContextCompat.getDrawable(this, R.drawable.selected_option_border_bg));
    }

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




}
