package com.example.imc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

    public void GerarRelatorio (View view) {
        EditText nome = (EditText) findViewById(R.id.editTextTextPersonName);
        EditText idade = (EditText) findViewById(R.id.editTextNumber);
        EditText peso = (EditText) findViewById(R.id.editTextNumberDecimal2);
        EditText altura = (EditText) findViewById(R.id.editTextNumberDecimal);
        Log.d("altura",altura.getText().toString());
        boolean valido = (!nome.getText().toString().equals("") && !idade.getText().toString().equals("") && !peso.getText().toString().equals("") && !altura.getText().toString().equals("") && Double.parseDouble(altura.getText().toString())>0);
        if (valido) {
            Intent relato = new Intent(getBaseContext(), relatorio.class);
            Bundle dados = new Bundle();

            double imc = Double.parseDouble(peso.getText().toString())/(Double.parseDouble(altura.getText().toString())*Double.parseDouble(altura.getText().toString()));
            dados.putString("nome",nome.getText().toString());
            dados.putString("idade",idade.getText().toString());
            dados.putString("peso",peso.getText().toString());
            dados.putString("altura",altura.getText().toString());
            dados.putDouble("imc",Double.parseDouble(String.format("%.2f",imc)));
            dados.putString("classificacao", calculaimc());

            relato.putExtras(dados);
            startActivity(relato);
        } else {
            Log.d("ASFASF", "asfasfasf");
            Toast.makeText(this, "Dados Incompletos", 3000).show();
        }
    }

    String calculaimc (){
        EditText peso = (EditText) findViewById(R.id.editTextNumberDecimal2);
        EditText altura = (EditText) findViewById(R.id.editTextNumberDecimal);
        double imc = Double.parseDouble(peso.getText().toString())/(Double.parseDouble(altura.getText().toString())*Double.parseDouble(altura.getText().toString()));
        String classificacao = new String();
        if (imc < 18.5) {
            classificacao = "Abaixo do Peso";
        }
        else if (18.5 < imc && imc < 24.9) {
            classificacao = "Saudável";
        }
        else if (25 < imc && imc < 29.9) {
            classificacao = "Sobrepeso";
        }
        else if (30 < imc && imc < 34.9) {
            classificacao = "Obesidade Grau I";
        }
        else if (35 < imc && imc < 39.9) {
            classificacao = "Obesidade Grau II(severa)";
        }
        else if (imc > 40) {
            classificacao = "Obesidade Grau III(morbida)";
        }
        return classificacao;
    }


}