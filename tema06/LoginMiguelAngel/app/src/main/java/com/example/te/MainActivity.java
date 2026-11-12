package com.example.te;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.materialswitch.MaterialSwitch;

public class MainActivity extends AppCompatActivity {
    private int intentosFallidos = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText correo = findViewById(R.id.edtCorreo);
        EditText contrasena = findViewById(R.id.edtContrasena);
        MaterialSwitch recordar = findViewById(R.id.switchRecordar);
        Button continuar = findViewById(R.id.btnContinuar);
        TextView mensaje = findViewById(R.id.txtMensaje);

        SharedPreferences preferencias =
                getSharedPreferences("sesion", MODE_PRIVATE);

        String correoGuardado = preferencias.getString("correo", "");

        if (!correoGuardado.isEmpty()) {
            correo.setText(correoGuardado);
            recordar.setChecked(true);
            contrasena.requestFocus();
        }
        mensaje.setVisibility(View.GONE);

        continuar.setOnClickListener(v -> {
            String email = correo.getText().toString().trim();
            String password = contrasena.getText().toString();

            if (email.isEmpty()) {
                correo.setError("Introduce el correo electrónico");
                correo.requestFocus();
                return;
            }
            if (password.isEmpty()) {
                contrasena.setError("Introduce la contraseña");
                contrasena.requestFocus();
                return;
            }
            if (email.equals("miguelangel@correo.com")
                    && password.equals("1234")) {

                SharedPreferences.Editor editor = preferencias.edit();

                if (recordar.isChecked()) {
                    editor.putString("correo", email);
                } else {
                    editor.remove("correo");
                }
                editor.apply();

                Intent intent = new Intent(MainActivity.this, MenuActivity.class);
                intent.putExtra(MenuActivity.EXTRA_CORREO, email);
                startActivity(intent);
                finish();
            } else {
                intentosFallidos++;

                mensaje.setVisibility(View.VISIBLE);
                mensaje.setText("Usuario y/o contraseña incorrectos. Intento "
                        + intentosFallidos + " de 3");
                mensaje.setTextColor(Color.RED);

                if (intentosFallidos >= 3) {
                    continuar.setEnabled(false);
                    mensaje.setText("Has superado los 3 intentos. Botón desactivado");
                }
            }
        });
    }
}
