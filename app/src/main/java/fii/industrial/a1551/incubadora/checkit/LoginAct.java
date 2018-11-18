package fii.industrial.a1551.incubadora.checkit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import fii.industrial.a1551.incubadora.checkit.R;

public class LoginAct extends AppCompatActivity {

    private Button Ingresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_act);

        Ingresar = (Button) findViewById(R.id.btn_ingresar);
        Ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pasarARegistros();
            }
        });

    }

    private void pasarARegistros(){
        if(validar()){
            Intent i = new Intent(LoginAct.this, RegistrosAct.class);
            startActivity(i);
            finish();
        }
        else{
            mostrarError();
        }

    }

    private boolean validar(){
        return true;
    }

    private void mostrarError(){
        Toast.makeText(LoginAct.this, "Código Inválido", Toast.LENGTH_LONG).show();
    }

}
