package fii.industrial.a1551.incubadora.checkit;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.function.DoubleUnaryOperator;

import fii.industrial.a1551.incubadora.checkit.model.Registro;

public class RegistrosAct extends AppCompatActivity {

    private Toolbar toolbar;

    private RecyclerView Recycler;
    private RegistrosAdapter Adapter;
    private List<String> registros = new ArrayList<>();
    private List<Registro> RegistrosFijos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registros_act);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Registros");
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorWhite));
        setSupportActionBar(toolbar);
        toolbar.setSubtitle("0 Registro(s)");
        toolbar.setSubtitleTextColor(getResources().getColor(R.color.colorWhite));

        Recycler = (RecyclerView) findViewById(R.id.rv_registros);
        Recycler.setLayoutManager(new LinearLayoutManager(this));

        registros = crearRegistros();

        Adapter = new RegistrosAdapter(RegistrosFijos);

        Recycler.setAdapter(Adapter);

    }

    private List<String> crearRegistros(){

        List<String> equipos = new ArrayList<>();
        equipos.add("Equipo 1");
        equipos.add("Equipo 2");
        equipos.add("Equipo 3");
        equipos.add("Equipo 4");
        equipos.add("Equipo 1");
        equipos.add("Equipo 2");
        equipos.add("Equipo 3");
        equipos.add("Equipo 4");


        return equipos;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        switch (item.getItemId()){
            case R.id.Registrar:
                Intent i = new Intent(this, QRAct.class);
                startActivity(i);
                finish();
                break;
            case R.id.Balance:
                Intent f = new Intent(this, TotalAct.class);
                startActivity(f);
                finish();
                break;

            default:

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mensajeBienvenida();
        parsearInfo();
//        pasarInfoRecyclerView();
    }

    private void mensajeBienvenida(){
        Toast.makeText(RegistrosAct.this, "Bienvenido Eduardo Lévano", Toast.LENGTH_SHORT).show();
    }

    private void parsearInfo(){
        SharedPreferences preferences = this.getSharedPreferences("NombreSuperOculto",Context.MODE_PRIVATE);
//        preferences.edit().putString("KEY_STRING", "parte1de1|fasd2de1|parte1de1|fasd2de1|f1parte1de1|fasd2de1|f1,parte1de2|parte2de2|parte1de2|parte2de2|dfadf" +
//                "parte1de2|parte2de2|dfadfparte1de2|parte2de2|dfadf," +
//                "parte1de3|parte2de3|fadfparte1de3|parte2de3|fadfparte1de3|parte2de3|fadfparte1de3|parte2de3|fadf" +
//                ",parte1de4|parte2de4|dfderwparte1de4|parte2de4|dfderwparte1de4|parte2de4|dfderwparte1de4|parte2de4|dfderwparte1de4|parte2de4|dfderw").apply();
//        Toast.makeText(this, preferences.getString("KEY_STRING", "No hubo"), Toast.LENGTH_SHORT).show();
        Float montoTotal = (float) 0;
        String bruto = preferences.getString("KEY_STRING", "");

        if(!bruto.equals("")){
            String[] cadenasBrutas = bruto.split(",");
            for(int i = 0; i < cadenasBrutas.length; i++){
               String[] partes = cadenasBrutas[i].split("\\|");
               Registro registro = new Registro();
               registro.setFecha("Fecha: " + partes[6]);
               registro.setMonto("Monto: " + partes[5]);
               registro.setNumero(i+1);
               registro.setRUC("RUC: " + partes[0]);
               RegistrosFijos.add(registro);
               montoTotal = Float.parseFloat(partes[5]) + montoTotal;
            }
            preferences.edit().putFloat("MONTO", montoTotal).apply();
            toolbar.setSubtitle(bruto.split(",").length+" Registro(s)");
        }
        Adapter.setRegistros(RegistrosFijos);
        Adapter.notifyDataSetChanged();
        //Toast.makeText(this, "Monto: " +preferences.getFloat("MONTO", (float) 0.0), Toast.LENGTH_SHORT).show();

    }

    private class RegistroHolder extends RecyclerView.ViewHolder{

        private TextView Numero;
        private TextView RUC;
        private TextView Monto;
        private TextView Fecha;

        public RegistroHolder(View itemView) {
            super(itemView);

            Numero = (TextView) itemView.findViewById(R.id.tv_numero);
            RUC = (TextView) itemView.findViewById(R.id.tv_RUC);
            Monto = (TextView) itemView.findViewById(R.id.tv_Monto);
            Fecha = (TextView) itemView.findViewById(R.id.tv_fecha);

        }

        public void bindRegistro(Registro registro){
            Numero.setText(registro.getNumero()+"");
            RUC.setText(registro.getRUC());
            Monto.setText(registro.getMonto());
            Fecha.setText(registro.getFecha());
        }
    }

    private class RegistrosAdapter extends RecyclerView.Adapter<RegistroHolder>{

        private List<Registro> mRegistros;

        public RegistrosAdapter (List<Registro> registros){
            mRegistros = registros;
        }

        @Override
        public RegistroHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = getLayoutInflater();
            View view = layoutInflater.inflate(R.layout.registro_item, parent, false);
            return new RegistroHolder(view);
        }

        @Override
        public void onBindViewHolder(RegistroHolder holder, int position) {
            Registro registro = mRegistros.get(position);
            holder.bindRegistro(registro);
        }

        @Override
        public int getItemCount() {
            return mRegistros.size();
        }

        public void setRegistros(List<Registro> registros){
            mRegistros = registros;
        }

    }


}
