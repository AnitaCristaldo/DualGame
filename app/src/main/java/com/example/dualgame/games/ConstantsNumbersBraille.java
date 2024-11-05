package com.example.dualgame.games;

import com.example.dualgame.R;
import java.util.ArrayList;
import java.util.Collections;

public class ConstantsNumbersBraille{

    public static final String TOTAL_QUESTIONS = "total_question";
    public static final String CORRECT_ANSWERS = "correct_answers";

    // Método para obtener 5 preguntas aleatorias
    public static ArrayList<QuestionNumbersBraille> getQuestions() {
        ArrayList<QuestionNumbersBraille> questionList = new ArrayList<>();

        // Agregar todas las preguntas con números en las opciones
        questionList.add(new QuestionNumbersBraille(1, "¿A qué número pertenece esta imagen?", R.drawable.numero_cero_braille, "0", "1", "2", "3", 1));
        questionList.add(new QuestionNumbersBraille(2, "¿A qué número pertenece esta imagen?", R.drawable.numero_uno_braille, "1", "2", "3", "4", 1));
        questionList.add(new QuestionNumbersBraille(3, "¿A qué número pertenece esta imagen?", R.drawable.numero_dos_braille, "0", "2", "4", "5", 2));
        questionList.add(new QuestionNumbersBraille(4, "¿A qué número pertenece esta imagen?", R.drawable.numero_tres_braille, "0", "3", "5", "6", 2));
        questionList.add(new QuestionNumbersBraille(5, "¿A qué número pertenece esta imagen?", R.drawable.numero_cuatro_braille, "4", "0", "5", "6", 1));
        questionList.add(new QuestionNumbersBraille(6, "¿A qué número pertenece esta imagen?", R.drawable.numero_cinco_braille, "5", "2", "3", "4", 1));
        questionList.add(new QuestionNumbersBraille(7, "¿A qué número pertenece esta imagen?", R.drawable.numero_seis_braille, "6", "0", "5", "7", 1));
        questionList.add(new QuestionNumbersBraille(8, "¿A qué número pertenece esta imagen?", R.drawable.numero_siete_braille, "7", "8", "4", "6", 1));
        questionList.add(new QuestionNumbersBraille(9, "¿A qué número pertenece esta imagen?", R.drawable.numero_ocho_braille, "0", "8", "4", "7", 2));
        questionList.add(new QuestionNumbersBraille(10, "¿A qué número pertenece esta imagen?", R.drawable.numero_nueve_braille, "9", "4", "7", "0", 1));


        // Mezclar las preguntas para asegurar aleatoriedad
        Collections.shuffle(questionList);

        // Seleccionar solo 5 preguntas para el cuestionario
        ArrayList<QuestionNumbersBraille> selectedQuestions = new ArrayList<>(questionList.subList(0, 8));

        return selectedQuestions;
    }
}
