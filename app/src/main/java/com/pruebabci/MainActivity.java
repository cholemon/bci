package com.pruebabci;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import com.google.android.material.textfield.TextInputLayout;
import androidx.appcompat.app.AppCompatActivity;
import controller.ControlMainActivity;
import android.os.StrictMode;

public class MainActivity extends AppCompatActivity {

    private ControlMainActivity control;

    public TextInputLayout layout_busqueda;
    public EditText txt_busqueda;
    public Button btn_buscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        init_components();
        init_events();

    }

    private void init_components(){
        layout_busqueda = findViewById(R.id.layout_busqueda);
        txt_busqueda    = findViewById(R.id.txt_busqueda);
        btn_buscar      = findViewById(R.id.btn_buscar);
    }

    private void init_events(){
        control = new ControlMainActivity(this);
        btn_buscar.setOnClickListener(control);
    }

}
