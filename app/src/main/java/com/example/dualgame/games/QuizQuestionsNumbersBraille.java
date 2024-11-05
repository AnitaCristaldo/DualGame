package com.example.dualgame.games;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import com.example.dualgame.R;
import com.example.dualgame.brailleviews.AbecedarioActivityBraille;
import com.example.dualgame.brailleviews.ResultAbcBraille;
import com.example.dualgame.brailleviews.ResultActivityBraille;
import com.example.dualgame.brailleviews.SubNumbersActivityBraille;
import com.example.dualgame.singviews.SubVocalsActivity;
import java.util.ArrayList;

public class QuizQuestionsNumbersBraille extends AppCompatActivity implements View.OnClickListener{
    private int mCurrentPosition = 1; // Posición actual en el cuestionario.
    private ArrayList<QuestionNumbersBraille> mQuestionList; // Lista de preguntas en el quiz.
    private int mSelectedOptionPosition = 0; // Posición de la opción seleccionada por el usuario.
    private int mCorrectAnswers = 0; // Conteo de respuestas correctas.

    // Definir variables para las vistas (UI)
    private TextView tv_question;
    private ImageView iv_image;
    private TextView tv_option_one, tv_option_two, tv_option_three, tv_option_four;
    private Button btn_submit, btn_back;
    private ProgressBar progressBar;
    private TextView tv_progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_question_numbers_braille);

        // Inicializar las vistas asociándolas con los elementos en el layout XML.
        tv_question = findViewById(R.id.tv_question);
        iv_image = findViewById(R.id.iv_image);
        tv_option_one = findViewById(R.id.tv_option_one);
        tv_option_two = findViewById(R.id.tv_option_two);
        tv_option_three = findViewById(R.id.tv_option_three);
        tv_option_four = findViewById(R.id.tv_option_four);
        btn_submit = findViewById(R.id.btn_submit);
        btn_back = findViewById(R.id.btn_back_braille);
        progressBar = findViewById(R.id.progressBar);
        tv_progress = findViewById(R.id.tv_progress);

        // Obtener la lista de preguntas desde una clase de constantes.
        mQuestionList = ConstantsNumbersBraille.getQuestions();
        // Obtener 5 preguntas aleatorias
        progressBar.setMax(mQuestionList.size()); // Ajustar el progreso

        setQuestion(); // Mostrar la primera pregunta.

        // Establecer listeners para manejar los clics en las opciones y botones.
        tv_option_one.setOnClickListener(this);
        tv_option_two.setOnClickListener(this);
        tv_option_three.setOnClickListener(this);
        tv_option_four.setOnClickListener(this);
        btn_submit.setOnClickListener(this);

        // Listener específico para el botón de retroceso.
        btn_back.setOnClickListener(v -> {
            Intent intent = new Intent(QuizQuestionsNumbersBraille.this, SubNumbersActivityBraille.class);
            startActivity(intent);
            finish(); // Finaliza la actividad actual para no regresar a ella.
        });
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
                    Intent intent = new Intent(QuizQuestionsNumbersBraille.this, ResultAbcBraille.class);
                    intent.putExtra(Constants.CORRECT_ANSWERS, mCorrectAnswers);
                    intent.putExtra(Constants.TOTAL_QUESTIONS, mQuestionList.size());
                    startActivity(intent);
                    finish();
                }
            } else {
                // Desactivar las opciones después de "Entregar".
                disableOptions();

                // Verificar si la respuesta seleccionada es correcta.
                QuestionNumbersBraille question = mQuestionList.get(mCurrentPosition - 1);
                if (question.getCorrectAnswer() != mSelectedOptionPosition) {
                    answerView(mSelectedOptionPosition, R.drawable.selected_option_inco);
                } else {
                    mCorrectAnswers++;
                }
                answerView(question.getCorrectAnswer(), R.drawable.selected_option_correct);

                if (mCurrentPosition == mQuestionList.size()) {
                    btn_submit.setText("FINALIZADO");
                } else {
                    btn_submit.setText("IR A LA SIGUIENTE PREGUNTA");
                }
                mSelectedOptionPosition = 0;
            }
        }
    }

    // Método para desactivar el OnClickListener de las opciones
    private void disableOptions() {
        tv_option_one.setClickable(false);
        tv_option_two.setClickable(false);
        tv_option_three.setClickable(false);
        tv_option_four.setClickable(false);
    }

    // Método para activar el OnClickListener de las opciones
    private void enableOptions() {
        tv_option_one.setClickable(true);
        tv_option_two.setClickable(true);
        tv_option_three.setClickable(true);
        tv_option_four.setClickable(true);
    }

    // Método para establecer la pregunta actual en la interfaz.
    private void setQuestion() {
        QuestionNumbersBraille question = mQuestionList.get(mCurrentPosition - 1);
        defaultOptionsView();
        enableOptions(); // Reactivar las opciones para la nueva pregunta.

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

    // Método para restablecer las vistas de las opciones a su estilo predeterminado.
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

    // Método para mostrar la vista de la respuesta seleccionada o correcta.
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

    // Método para configurar la vista de la opción seleccionada.
    private void selectedOptionView(TextView tv, int selectedOptionNum) {
        defaultOptionsView(); // Restablecer todas las opciones a su estado predeterminado.
        mSelectedOptionPosition = selectedOptionNum; // Establecer la opción seleccionada.
        tv.setTextColor(Color.parseColor("#363A43"));
        tv.setTypeface(tv.getTypeface(), Typeface.BOLD);
        tv.setBackground(ContextCompat.getDrawable(this, R.drawable.selected_option_border_bg));
    }
}

