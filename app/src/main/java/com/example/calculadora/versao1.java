package com.example.calculadora;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import java.lang.*;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class versao1 extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.versao1);
//        if (savedInstanceState != null){
//            CharSequence valor = savedInstanceState.getCharSequence("valor1");
//            TextView valores = findViewById(R.id.editTextNumberDecimal1);
//            valores.setText(valor.toString());
//            valor = savedInstanceState.getCharSequence("valor2");
//            valores = findViewById(R.id.editTextNumberDecimal2);
//            valores.setText(valor.toString());
//        }
    }

//    @Override
//    protected void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//        TextView valor = (TextView) findViewById(R.id.editTextNumberDecimal1);
//        if (valor != null) { outState.putCharSequence("valor1",valor.getText()); }
//        valor = (TextView) findViewById(R.id.editTextNumberDecimal2);
//        if (valor != null) {outState.putCharSequence("valor2",valor.getText()); }
//    }

    public void operacoes (View v) {
        String tag = v.getTag().toString();
        TextView valor1 = (TextView) findViewById(R.id.editTextNumberDecimal1);
        TextView valor2 = (TextView) findViewById(R.id.editTextNumberDecimal2);
        TextView resul = (TextView) findViewById(R.id.textView3);
        String entrada1 = valor1.getText().toString();
        String entrada2 = valor2.getText().toString();
        boolean Preenchidos = !entrada1.isEmpty() && !entrada2.isEmpty();
        double valor = 0;
        if (Preenchidos) { //realizar operacao somente se ambos os campos forem preenchidos
            if (tag.equals("botaoPlus")) {
                valor = Double.parseDouble(valor1.getText().toString()) + Double.parseDouble(valor2.getText().toString());
            } else if (tag.equals("botaoMinus")) {
                valor = Double.parseDouble(valor1.getText().toString()) - Double.parseDouble(valor2.getText().toString());
            } else if (tag.equals("botaoTimes")) {
                valor = Double.parseDouble(valor1.getText().toString()) * Double.parseDouble(valor2.getText().toString());
            } else if (tag.equals("botaoDiv")) {
                valor = Double.parseDouble(valor1.getText().toString()) / Double.parseDouble(valor2.getText().toString());
            }
            if (valor % 1 == 0) {
                int result = (int) valor;
                resul.setText("O resultado é de: " + result);
            } else {
                resul.setText("O resultado é de: " + valor);
            }
        } else {
            resul.setText("Preencha ambos os campos");
        }
    }
}


