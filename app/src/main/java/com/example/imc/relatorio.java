package com.example.imc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class relatorio extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.relatorio);
        Log.d("asfadfaa","asfdfa");
        Intent relato = getIntent();
        Bundle dados = relato.getExtras();

        TextView text = (TextView) findViewById((R.id.textView12));
        text.setText(dados.getString("nome"));
        text = (TextView) findViewById((R.id.textView13));
        text.setText(dados.getString("idade")+" anos");
        text = (TextView) findViewById((R.id.textView14));
        text.setText(dados.getString("peso")+" Kg");
        text = (TextView) findViewById((R.id.textView15));
        text.setText(dados.getString("altura")+" m");
        text = (TextView) findViewById((R.id.textView16));
        text.setText(String.valueOf(dados.getDouble("imc"))+ "Kg/m\u00B2");
        text = (TextView) findViewById((R.id.textView17));
        text.setText(dados.getString("classificacao"));
    }

    public void retorna(View v) {
        Intent it = new Intent(this, MainActivity.class);
        it.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(it);
    }

    protected void onStart(){
        super.onStart();
        Log.d("Estado atual de ", getClass().getName() + "= .onStart");
    }

    protected void onResume(){
        super.onResume();
        Log.d("Estado atual de ", getClass().getName() + "= .onResume");
    }

    protected void onRestart(){
        super.onRestart();
        Log.d("Estado atual de ", getClass().getName() + "= .onRestart");
    }

    protected void onPause(){
        super.onPause();
        Log.d("Estado atual de ", getClass().getName() + "= .onPause");
    }

    protected void onDestroy(){
        super.onDestroy();
        Log.d("Estado atual de ", getClass().getName() + "= .onDestroy");
    }
}
