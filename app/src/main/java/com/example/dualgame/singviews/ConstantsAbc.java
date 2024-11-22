package com.example.dualgame.singviews;

import com.example.dualgame.R;
import com.example.dualgame.games.Question;
import java.util.ArrayList;
import java.util.Collections;

public class ConstantsAbc {

    public static final String TOTAL_QUESTIONS = "total_question";
    public static final String CORRECT_ANSWERS = "correct_answers";

    // Método para obtener 5 preguntas aleatorias
    public static ArrayList<Question> getQuestions() {
        ArrayList<Question> questionList = new ArrayList<>();
        // Agregar todas las letras del abecedario
        questionList.add(new Question(1, "¿A qué letra pertenece esta imagen?", R.drawable.letra_a_senas, "a", "e", "i", "o", 1));
        questionList.add(new Question(2, "¿A qué letra pertenece esta imagen?", R.drawable.letra_b_senas, "d", "c", "b", "f", 3));
        questionList.add(new Question(3, "¿A qué letra pertenece esta imagen?", R.drawable.letra_c_senas, "a", "c", "e", "f", 2));
        questionList.add(new Question(4, "¿A qué letra pertenece esta imagen?", R.drawable.letra_d_senas, "a", "d", "i", "f", 2));
        questionList.add(new Question(5, "¿A qué letra pertenece esta imagen?", R.drawable.letra_e_senas, "o", "a", "i", "e", 4));
        questionList.add(new Question(6, "¿A qué letra pertenece esta imagen?", R.drawable.letra_f_senas, "e", "c", "d", "f", 4));
        questionList.add(new Question(7, "¿A qué letra pertenece esta imagen?", R.drawable.letra_g_senas, "f", "a", "g", "h", 3));
        questionList.add(new Question(8, "¿A qué letra pertenece esta imagen?", R.drawable.letra_h_senas, "g", "i", "e", "h", 4));
        questionList.add(new Question(9, "¿A qué letra pertenece esta imagen?", R.drawable.letra_i_senas, "a", "h", "e", "i", 4));
        questionList.add(new Question(10, "¿A qué letra pertenece esta imagen?", R.drawable.letra_j_senas, "j", "k", "l", "m", 1));
        questionList.add(new Question(11, "¿A qué letra pertenece esta imagen?", R.drawable.letra_k_senas, "k", "a", "b", "j", 1));
        questionList.add(new Question(12, "¿A qué letra pertenece esta imagen?", R.drawable.letra_l_senas, "n", "m", "l", "o", 3));
        questionList.add(new Question(13, "¿A qué letra pertenece esta imagen?", R.drawable.letra_m_senas, "n", "k", "m", "l", 3));
        questionList.add(new Question(14, "¿A qué letra pertenece esta imagen?", R.drawable.letra_n_senas, "n", "o", "m", "l", 1));
        questionList.add(new Question(15, "¿A qué letra pertenece esta imagen?", R.drawable.letra_enie_senas, "m", "ñ", "o", "l", 2));
        questionList.add(new Question(16, "¿A qué letra pertenece esta imagen?", R.drawable.letra_o_senas, "o", "i", "u", "a", 1));
        questionList.add(new Question(17, "¿A qué letra pertenece esta imagen?", R.drawable.letra_p_senas, "b", "p", "t", "r", 2));
        questionList.add(new Question(18, "¿A qué letra pertenece esta imagen?", R.drawable.letra_q_senas, "b", "a", "q", "g", 3));
        questionList.add(new Question(19, "¿A qué letra pertenece esta imagen?", R.drawable.letra_r_senas, "s", "r", "d", "f", 2));
        questionList.add(new Question(20, "¿A qué letra pertenece esta imagen?", R.drawable.letra_s_senas, "t", "r", "s", "f", 3));
        questionList.add(new Question(21, "¿A qué letra pertenece esta imagen?", R.drawable.letra_t_senas, "t", "e", "u", "v", 1));
        questionList.add(new Question(22, "¿A qué letra pertenece esta imagen?", R.drawable.letra_u_senas, "u", "v", "w", "x", 1));
        questionList.add(new Question(23, "¿A qué letra pertenece esta imagen?", R.drawable.letra_v_senas, "u", "a", "b", "v", 4));
        questionList.add(new Question(24, "¿A qué letra pertenece esta imagen?", R.drawable.letra_w_senas, "x", "w", "y", "z", 2));
        questionList.add(new Question(25, "¿A qué letra pertenece esta imagen?", R.drawable.letra_x_senas, "y", "w", "x", "z", 3));
        questionList.add(new Question(26, "¿A qué letra pertenece esta imagen?", R.drawable.letra_y_senas, "y", "z", "x", "w", 1));
        questionList.add(new Question(27, "¿A qué letra pertenece esta imagen?", R.drawable.letra_z_senas, "w", "y", "x", "z", 4));

        // Mezclar las preguntas para asegurar aleatoriedad
        Collections.shuffle(questionList);

        // Seleccionar solo 8 preguntas para el cuestionario
        return new ArrayList<>(questionList.subList(0, 8));
    }
}

