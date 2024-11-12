package com.example.dualgame.games;

import com.example.dualgame.R;
import java.util.ArrayList;
import java.util.Collections;

public class ConstantsNumbersSenas{

    public static final String TOTAL_QUESTIONS = "total_question";
    public static final String CORRECT_ANSWERS = "correct_answers";

    // Método para obtener 5 preguntas aleatorias
    public static ArrayList<QuestionNumbersSenas> getQuestions() {
        ArrayList<QuestionNumbersSenas> questionList = new ArrayList<>();

        // Agregar todas las preguntas con números en las opciones
        questionList.add(new QuestionNumbersSenas(0, "¿A qué número pertenece esta imagen?", R.drawable.numero_cero_senas, "0", "1", "2", "3", 1));
        questionList.add(new QuestionNumbersSenas(1, "¿A qué número pertenece esta imagen?", R.drawable.numero_uno_senas, "1", "2", "3", "4", 1));
        questionList.add(new QuestionNumbersSenas(2, "¿A qué número pertenece esta imagen?", R.drawable.numero_dos_senas, "6", "2", "4", "5", 2));
        questionList.add(new QuestionNumbersSenas(3, "¿A qué número pertenece esta imagen?", R.drawable.numero_tres_senas, "7", "3", "5", "6", 2));
        questionList.add(new QuestionNumbersSenas(4, "¿A qué número pertenece esta imagen?", R.drawable.numero_cuatro_senas, "4", "2", "5", "6", 1));
        questionList.add(new QuestionNumbersSenas(5, "¿A qué número pertenece esta imagen?", R.drawable.numero_cinco_senas, "3", "2", "5", "4", 3));
        questionList.add(new QuestionNumbersSenas(6, "¿A qué número pertenece esta imagen?", R.drawable.numero_seis_senas, "9", "3", "5", "6", 4));
        questionList.add(new QuestionNumbersSenas(7, "¿A qué número pertenece esta imagen?", R.drawable.numero_siete_senas, "7", "8", "3", "6", 1));
        questionList.add(new QuestionNumbersSenas(8, "¿A qué número pertenece esta imagen?", R.drawable.numero_ocho_senas, "9", "8", "4", "7", 2));
        questionList.add(new QuestionNumbersSenas(9, "¿A qué número pertenece esta imagen?", R.drawable.numero_nueve_senas, "5", "6", "9", "4", 3));
        questionList.add(new QuestionNumbersSenas(10, "¿A qué número pertenece esta imagen?", R.drawable.numero_diez_senas, "10", "4", "8", "9", 1));

        // Mezclar las preguntas para asegurar aleatoriedad
        Collections.shuffle(questionList);

        // Seleccionar solo 5 preguntas para el cuestionario
        ArrayList<QuestionNumbersSenas> selectedQuestions = new ArrayList<>(questionList.subList(0, 8));

        return selectedQuestions;
    }
}
