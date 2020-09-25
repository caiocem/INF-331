package com.example.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Versoes (View v) {
        String tag = v.getTag().toString();
        Log.v("tag do botao", tag);
        if (tag.equals("ver1")) {
            Intent calc = new Intent(this, versao1.class);
            //calc.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(calc);
        } else if (tag.equals("ver2")) {
            Intent calc = new Intent(this, versao2.class);
            //calc.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(calc);
        }
    }
}