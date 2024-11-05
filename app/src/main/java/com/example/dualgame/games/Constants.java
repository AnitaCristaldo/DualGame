package com.example.dualgame.games;

import com.example.dualgame.R;
import java.util.ArrayList;
import java.util.Collections;

public class Constants{

        public static final String TOTAL_QUESTIONS = "total_question";
        public static final String CORRECT_ANSWERS = "correct_answers";

        public static ArrayList<Question> getQuestions() {
                ArrayList<Question> questionList = new ArrayList<>();

                Question que1 = new Question(1, "¿A que vocal pertenece esta imagen?",
                        R.drawable.letra_e_senas,
                        "e",
                        "a",
                        "u",
                        "i",
                        1);
                questionList.add(que1);

                Question que2 = new Question(1, "¿A que vocal pertenece esta imagen?",
                        R.drawable.letra_a_senas,
                        "e",
                        "a",
                        "o",
                        "i",
                        2);
                questionList.add(que2);

                Question que3 = new Question(1, "¿A que vocal pertenece esta imagen?",
                        R.drawable.letra_i_senas,
                        "u",
                        "e",
                        "a",
                        "i",
                        4);
                questionList.add(que3);

                Question que4 = new Question(1, "¿A que vocal pertenece esta imagen?",
                        R.drawable.letra_u_senas,
                        "o",
                        "u",
                        "i",
                        "a",
                        2);
                questionList.add(que4);

                Question que5 = new Question(1, "¿A que vocal pertenece esta imagen?",
                        R.drawable.letra_o_senas,
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
