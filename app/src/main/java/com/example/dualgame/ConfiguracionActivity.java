package com.example.dualgame;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.Date;

public class ConfiguracionActivity extends AppCompatActivity {

    private FirebaseFirestore db;
    private FirebaseAuth auth;

    // Componentes de la interfaz
    private EditText etNombre, etEdad, etEmail;
    private TextView tvFechaRegistro, tvProgresoModulos, tvDetallesProgreso, tvProgresoCompleto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion);

        // Inicialización de Firebase Auth y Firestore
        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        // Asignar las vistas
        etNombre = findViewById(R.id.etNombre);
        etEdad = findViewById(R.id.etEdad);
        etEmail = findViewById(R.id.etEmail);
        tvFechaRegistro = findViewById(R.id.tvFechaRegistro);
        tvProgresoModulos = findViewById(R.id.tvProgresoModulos);
        tvDetallesProgreso = findViewById(R.id.tvDetallesProgreso);
        tvProgresoCompleto = findViewById(R.id.tvProgresoCompleto);

        // Obtener el usuario actual
        FirebaseUser currentUser = auth.getCurrentUser();
        if (currentUser != null) {
            String userId = currentUser.getUid();  // Obtener el UID del usuario autenticado
            cargarDatosUsuario(userId); // Cargar datos de Firestore

            // Configurar el botón para guardar cambios
            Button btnGuardar = findViewById(R.id.btnGuardar);
            btnGuardar.setOnClickListener(v -> guardarCambiosUsuario(userId));  // Llama a guardar cambios
        } else {
            // Manejo si el usuario no ha iniciado sesión, puedes redirigir al login
            Toast.makeText(this, "Usuario no autenticado, por favor inicia sesión.", Toast.LENGTH_SHORT).show();
            // Redirigir al login si es necesario
        }
    }

    // Método para cargar los datos del usuario desde Firestore
    private void cargarDatosUsuario(String userId) {
        DocumentReference docRef = db.collection("usuarios").document(userId);
        docRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                if (task.getResult() != null && task.getResult().exists()) {
                    Usuario usuario = task.getResult().toObject(Usuario.class);
                    if (usuario != null) {
                        // Mostrar los datos del usuario en los campos de texto
                        etNombre.setText(usuario.getNombre());
                        etEdad.setText(String.valueOf(usuario.getEdad()));
                        etEmail.setText(usuario.getEmail()); // Cargar el email


                        // Mostrar el progreso del usuario
                        mostrarProgreso(usuario.getProgreso());
                    }
                }
            } else {
                Toast.makeText(ConfiguracionActivity.this, "Error al cargar los datos.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Método para mostrar el progreso del usuario
    private void mostrarProgreso(Progreso progreso) {
        if (progreso != null) {
            StringBuilder progresoTexto = new StringBuilder();

            // Mostrar el progreso de lengua de señas
            progresoTexto.append("Lengua de Señas:\n");
            progresoTexto.append("Vocales: ").append(progreso.getLenguaSenas().getNivelVocales().isCompletado() ? "Completado" : "No completado")
                    .append(", Intentos: ").append(progreso.getLenguaSenas().getNivelVocales().getIntentos()).append("\n");
            progresoTexto.append("Abecedario: ").append(progreso.getLenguaSenas().getNivelAbecedario().isCompletado() ? "Completado" : "No completado")
                    .append(", Intentos: ").append(progreso.getLenguaSenas().getNivelAbecedario().getIntentos()).append("\n");
            progresoTexto.append("Números: ").append(progreso.getLenguaSenas().getNivelNumeros().isCompletado() ? "Completado" : "No completado")
                    .append(", Intentos: ").append(progreso.getLenguaSenas().getNivelNumeros().getIntentos()).append("\n");

            // Mostrar el progreso de Braille
            progresoTexto.append("Braille:\n");
            progresoTexto.append("Vocales: ").append(progreso.getBraille().getNivelVocales().isCompletado() ? "Completado" : "No completado")
                    .append(", Intentos: ").append(progreso.getBraille().getNivelVocales().getIntentos()).append("\n");
            progresoTexto.append("Abecedario: ").append(progreso.getBraille().getNivelAbecedario().isCompletado() ? "Completado" : "No completado")
                    .append(", Intentos: ").append(progreso.getBraille().getNivelAbecedario().getIntentos()).append("\n");
            progresoTexto.append("Números: ").append(progreso.getBraille().getNivelNumeros().isCompletado() ? "Completado" : "No completado")
                    .append(", Intentos: ").append(progreso.getBraille().getNivelNumeros().getIntentos()).append("\n");

            tvProgresoModulos.setText(progresoTexto.toString());

            // Actualizar el TextView para mostrar detalles del progreso
            tvDetallesProgreso.setText(progresoTexto.toString());

            // Calcular el progreso completo y actualizar el TextView tvProgresoCompleto
            String progresoCompletoTexto = calcularProgresoCompleto(progreso);
            tvProgresoCompleto.setText(progresoCompletoTexto);
        }
    }

    // Método para calcular el progreso completo
    private String calcularProgresoCompleto(Progreso progreso) {
        int totalCompletado = 0;
        int totalIntentos = 0;

        // Sumar completados y intentos de Lengua de Señas
        totalCompletado += (progreso.getLenguaSenas().getNivelVocales().isCompletado() ? 1 : 0);
        totalIntentos += progreso.getLenguaSenas().getNivelVocales().getIntentos();

        totalCompletado += (progreso.getLenguaSenas().getNivelAbecedario().isCompletado() ? 1 : 0);
        totalIntentos += progreso.getLenguaSenas().getNivelAbecedario().getIntentos();

        totalCompletado += (progreso.getLenguaSenas().getNivelNumeros().isCompletado() ? 1 : 0);
        totalIntentos += progreso.getLenguaSenas().getNivelNumeros().getIntentos();

        // Sumar completados y intentos de Braille
        totalCompletado += (progreso.getBraille().getNivelVocales().isCompletado() ? 1 : 0);
        totalIntentos += progreso.getBraille().getNivelVocales().getIntentos();

        totalCompletado += (progreso.getBraille().getNivelAbecedario().isCompletado() ? 1 : 0);
        totalIntentos += progreso.getBraille().getNivelAbecedario().getIntentos();

        totalCompletado += (progreso.getBraille().getNivelNumeros().isCompletado() ? 1 : 0);
        totalIntentos += progreso.getBraille().getNivelNumeros().getIntentos();

        // Calcular el porcentaje de progreso
        String progresoCompleto = totalIntentos > 0 ?
                "Progreso Completo: " + (totalCompletado * 100 / totalIntentos) + "%" :
                "Progreso Completo: 0%"; // Si no hay intentos, se considera 0%

        return progresoCompleto;
    }

    // Método para guardar los cambios de nombre, edad y email en Firestore
    private void guardarCambiosUsuario(String userId) {
        String nombre = etNombre.getText().toString();
        int edad = Integer.parseInt(etEdad.getText().toString());
        String email = etEmail.getText().toString();  // Obtener email

        DocumentReference docRef = db.collection("usuarios").document(userId);
        // Obtener la fecha de registro desde Firestore (o puedes definirla de otra manera)
        String fechaRegistro = tvFechaRegistro.getText().toString();

        // Crear el objeto Usuario con el nuevo email y fecha de registro
        Usuario usuarioActualizado = new Usuario(nombre, edad, email);
        docRef.set(usuarioActualizado, SetOptions.merge()).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(ConfiguracionActivity.this, "Cambios guardados correctamente.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(ConfiguracionActivity.this, "Error al guardar cambios.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
