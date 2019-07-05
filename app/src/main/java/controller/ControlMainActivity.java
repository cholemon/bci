package controller;

import android.content.Intent;
import android.view.View;

import com.pruebabci.MainActivity;
import com.pruebabci.R;
import com.pruebabci.VistaResultados;
import helpers.Global;

public class ControlMainActivity implements View.OnClickListener {

    private MainActivity vista;

    public ControlMainActivity(MainActivity vista){
        this.vista = vista;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.btn_buscar:

                final String busqueda = vista.txt_busqueda.getText().toString().trim();

                if (busqueda.isEmpty()) {
                    vista.layout_busqueda.setError(vista.getString(R.string.lbl_error_text));
                    Global.hideKeyboard(vista, vista.txt_busqueda);
                } else {
                    vista.layout_busqueda.setErrorEnabled(false);
                    Global.SEARCH = busqueda;
                    Global.clearTextView(vista.txt_busqueda);
                    Intent resultados = new Intent(vista, VistaResultados.class);
                    vista.startActivity(resultados);
                    vista.overridePendingTransition(R.anim.two, R.anim.one);
                }

                break;

                default: break;
        }
    }
}