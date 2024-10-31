package com.example.dualgame.games;

import com.example.dualgame.R;
import java.util.ArrayList;
import java.util.Collections;

public class ConstantsBraille {

    public static final String TOTAL_QUESTIONS = "total_question";
    public static final String CORRECT_ANSWERS = "correct_answers";

    public static ArrayList<QuestionBraille> getQuestions() {
        ArrayList<QuestionBraille> questionList = new ArrayList<>();

        QuestionBraille que1 = new QuestionBraille(1, "¿A que vocal pertenece esta imagen?",
                R.drawable.letra_e_braille,
                "e",
                "a",
                "u",
                "i",
                1);
        questionList.add(que1);

        QuestionBraille que2 = new QuestionBraille(1, "¿A que vocal pertenece esta imagen?",
                R.drawable.letra_a_braille,
                "e",
                "a",
                "o",
                "i",
                2);
        questionList.add(que2);

        QuestionBraille que3 = new QuestionBraille(1, "¿A que vocal pertenece esta imagen?",
                R.drawable.letra_i_braille,
                "u",
                "e",
                "a",
                "i",
                4);
        questionList.add(que3);

        QuestionBraille que4 = new QuestionBraille(1, "¿A que vocal pertenece esta imagen?",
                R.drawable.letra_u_braille,
                "o",
                "u",
                "i",
                "a",
                2);
        questionList.add(que4);

        QuestionBraille que5 = new QuestionBraille(1, "¿A que vocal pertenece esta imagen?",
                R.drawable.letra_o_braille,
                "e",
                "a",
                "o",
                "i",
                3);
        questionList.add(que5);

        // Mezclar las preguntas de manera aleatoria
        Collections.shuffle(questionList);

        return questionList;
    }
}
