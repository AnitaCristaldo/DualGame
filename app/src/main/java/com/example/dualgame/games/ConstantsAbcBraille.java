package com.example.dualgame.games;

import com.example.dualgame.R;
import java.util.ArrayList;
import java.util.Collections;

public class ConstantsAbcBraille{

    public static final String TOTAL_QUESTIONS = "total_question";
    public static final String CORRECT_ANSWERS = "correct_answers";

    // Método para obtener 5 preguntas aleatorias
    public static ArrayList<QuestionAbcBraille> getQuestions() {
        ArrayList<QuestionAbcBraille> questionList = new ArrayList<>();

        // Agregar todas las letras del abecedario
        questionList.add(new QuestionAbcBraille(1, "¿A qué letra pertenece esta imagen?", R.drawable.letra_a_braille, "a", "e", "i", "o", 1));
        questionList.add(new QuestionAbcBraille(2, "¿A qué letra pertenece esta imagen?", R.drawable.letra_b_braille, "b", "c", "d", "f", 1));
        questionList.add(new QuestionAbcBraille(3, "¿A qué letra pertenece esta imagen?", R.drawable.letra_c_braille, "a", "c", "e", "f", 2));
        questionList.add(new QuestionAbcBraille(4, "¿A qué letra pertenece esta imagen?", R.drawable.letra_d_braille, "a", "d", "i", "f", 2));
        questionList.add(new QuestionAbcBraille(5, "¿A qué letra pertenece esta imagen?", R.drawable.letra_e_braille, "e", "a", "i", "o", 1));
        questionList.add(new QuestionAbcBraille(6, "¿A qué letra pertenece esta imagen?", R.drawable.letra_f_braille, "f", "c", "d", "e", 1));
        questionList.add(new QuestionAbcBraille(7, "¿A qué letra pertenece esta imagen?", R.drawable.letra_g_braille, "g", "a", "f", "h", 1));
        questionList.add(new QuestionAbcBraille(8, "¿A qué letra pertenece esta imagen?", R.drawable.letra_h_braille, "h", "i", "e", "g", 1));
        questionList.add(new QuestionAbcBraille(9, "¿A qué letra pertenece esta imagen?", R.drawable.letra_i_braille, "a", "i", "e", "h", 2));
        questionList.add(new QuestionAbcBraille(10, "¿A qué letra pertenece esta imagen?", R.drawable.letra_j_braille, "j", "k", "l", "m", 1));
        questionList.add(new QuestionAbcBraille(11, "¿A qué letra pertenece esta imagen?", R.drawable.letra_k_braille, "k", "a", "b", "j", 1));
        questionList.add(new QuestionAbcBraille(12, "¿A qué letra pertenece esta imagen?", R.drawable.letra_l_braille, "l", "m", "n", "o", 1));
        questionList.add(new QuestionAbcBraille(13, "¿A qué letra pertenece esta imagen?", R.drawable.letra_m_braille, "m", "k", "n", "l", 1));
        questionList.add(new QuestionAbcBraille(14, "¿A qué letra pertenece esta imagen?", R.drawable.letra_n_braille, "n", "o", "m", "l", 1));
        questionList.add(new QuestionAbcBraille(15, "¿A qué letra pertenece esta imagen?", R.drawable.letra_enie_braille, "ñ", "m", "o", "l", 1));
        questionList.add(new QuestionAbcBraille(16, "¿A qué letra pertenece esta imagen?", R.drawable.letra_o_braille, "o", "i", "u", "a", 1));
        questionList.add(new QuestionAbcBraille(17, "¿A qué letra pertenece esta imagen?", R.drawable.letra_p_braille, "p", "b", "t", "r", 1));
        questionList.add(new QuestionAbcBraille(18, "¿A qué letra pertenece esta imagen?", R.drawable.letra_q_braille, "q", "a", "b", "g", 1));
        questionList.add(new QuestionAbcBraille(19, "¿A qué letra pertenece esta imagen?", R.drawable.letra_r_braille, "r", "s", "d", "f", 1));
        questionList.add(new QuestionAbcBraille(20, "¿A qué letra pertenece esta imagen?", R.drawable.letra_s_braille, "s", "r", "t", "f", 1));
        questionList.add(new QuestionAbcBraille(21, "¿A qué letra pertenece esta imagen?", R.drawable.letra_t_braille, "t", "e", "u", "v", 1));
        questionList.add(new QuestionAbcBraille(22, "¿A qué letra pertenece esta imagen?", R.drawable.letra_u_braille, "u", "v", "w", "x", 1));
        questionList.add(new QuestionAbcBraille(23, "¿A qué letra pertenece esta imagen?", R.drawable.letra_v_braille, "v", "a", "b", "u", 1));
        questionList.add(new QuestionAbcBraille(24, "¿A qué letra pertenece esta imagen?", R.drawable.letra_w_braille, "w", "x", "y", "z", 1));
        questionList.add(new QuestionAbcBraille(25, "¿A qué letra pertenece esta imagen?", R.drawable.letra_x_braille, "x", "w", "y", "z", 1));
        questionList.add(new QuestionAbcBraille(26, "¿A qué letra pertenece esta imagen?", R.drawable.letra_y_braille, "y", "z", "x", "w", 1));
        questionList.add(new QuestionAbcBraille(27, "¿A qué letra pertenece esta imagen?", R.drawable.letra_z_braille, "z", "y", "x", "w", 1));


        // Mezclar las preguntas para asegurar aleatoriedad
        Collections.shuffle(questionList);

        // Seleccionar solo 5 preguntas para el cuestionario
        ArrayList<QuestionAbcBraille> selectedQuestions = new ArrayList<>(questionList.subList(0, 8));

        return selectedQuestions;
    }
}
