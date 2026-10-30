package com.example.te;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.appbar.MaterialToolbar;

public class MenuActivity extends AppCompatActivity {
    public static final String EXTRA_CORREO = "correo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String correo = getIntent().getStringExtra(EXTRA_CORREO);

        TextView txtSaludo = findViewById(R.id.txtSaludo);
        txtSaludo.setText("Hola, " + correo);
    }
}
