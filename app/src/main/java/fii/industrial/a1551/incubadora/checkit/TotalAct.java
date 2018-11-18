package fii.industrial.a1551.incubadora.checkit;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class TotalAct extends AppCompatActivity {

    private TextView Monto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.total_act);

        Monto = (TextView) findViewById(R.id.tv_montoTotal);
        SharedPreferences preferences = this.getSharedPreferences("NombreSuperOculto", Context.MODE_PRIVATE);
        String monto = "S/ "+ preferences.getFloat("MONTO", (float) 0.0);
        Monto.setText(monto);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(this, RegistrosAct.class);
        startActivity(i);
    }
}
