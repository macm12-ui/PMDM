package com.example.loginmiguelangel;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.materialswitch.MaterialSwitch;

public class MainActivity extends AppCompatActivity {

    private int intentosFallidos = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText correo = findViewById(R.id.edtCorreo);
        EditText contrasena = findViewById(R.id.edtContrasena);
        MaterialSwitch recordar = findViewById(R.id.switchRecordar);
        Button continuar = findViewById(R.id.btnContinuar);
        TextView mensaje = findViewById(R.id.txtMensaje);

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

            mensaje.setVisibility(View.VISIBLE);

            if (email.equals("miguelangel@correo.com")
                    && password.equals("1234")) {

                if (recordar.isChecked()) {
                    mensaje.setText("Usuario y contraseña correctos. Almacenados para siguientes accesos");
                } else {
                    mensaje.setText("Usuario y contraseña correctos");
                }

                mensaje.setTextColor(getColor(R.color.verde));

            } else {
                intentosFallidos++;

                mensaje.setText("Usuario y/o contraseña incorrectos. Intento "
                        + intentosFallidos + " de 3");
                mensaje.setTextColor(getColor(R.color.rojo));

                if (intentosFallidos >= 3) {
                    continuar.setEnabled(false);
                    mensaje.setText("Has superado los 3 intentos. Botón desactivado");
                }
            }
        });
    }
}
