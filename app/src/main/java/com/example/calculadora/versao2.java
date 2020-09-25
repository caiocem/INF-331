package com.example.calculadora;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class versao2 extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.versao2);
        PrimeiroPonto = false;
    }
    static boolean PrimeiroPonto = false; //variaveis para tratamento da insercao do "." na formula, um "." por conjunto numero limitado entre sinais de operacao
    static boolean ColocarPonto = false;
    static boolean TeveOperacao = false;
    static boolean ColocouPonto = false;
    public void addtext (View v) {
        TextView entrada = (TextView) v;
        String input = entrada.getText().toString();
//        if (PrimeiroPonto) Log.d("Botao","true");
        TextView barra = (TextView) findViewById(R.id.editTextNumberDecimal);
        String textoAtual = barra.getText().toString();
        String lastChar = new String("");
        if (textoAtual.equals("Infinity") || textoAtual.equals("0")) { textoAtual = ""; barra.setText(textoAtual);} //caso resultado anterior seja 0 ou infinito (divisao por 0), limpar a tela
        if (!textoAtual.equals("")) { lastChar = textoAtual.substring(textoAtual.length() - 1);} //se a "entrada" nao estivar vazia, salva o ultimo caracter para checagens futuras
        if (input.equals("1")||input.equals("2")||input.equals("3")||input.equals("4")||input.equals("5")||input.equals("6")||input.equals("7")||input.equals("8")||input.equals("9")) { //entrada de digitos que nao o 0
            ColocarPonto = true;
            if (lastChar.equals(" ")) ColocouPonto = false;
            String text = textoAtual +  input;
            barra.setText(text);
        } else if(!textoAtual.equals("") && !lastChar.equals("+") && !lastChar.equals("-") && !lastChar.equals("x") && !lastChar.equals("/") && input.equals("0")){ //entrada do 0 separada para evitar 0 a esquerda, somente se nao vazio
            ColocarPonto = true;
            String text = textoAtual + input;
            barra.setText(text);
        } else if(!textoAtual.equals("") && input.equals("<<")){ //operacao de apagar uma letra/digito somente se nao estiver vazio
           if (lastChar.equals(" ")) { //caso o ultimo caracter seja " ", sigifica que houve uma operacao, ou seja um conjunto de " " "op" " ", precisando de 3 apagar
               textoAtual = textoAtual.substring(0, textoAtual.length() - 3); TeveOperacao = false; ColocarPonto = true; if(!ColocouPonto) PrimeiroPonto = false; }
           else if (lastChar.equals(".")) { ColocarPonto = true; TeveOperacao = true; ColocouPonto = false; textoAtual = textoAtual.substring(0, textoAtual.length() - 1);} //caso seja um "." apagado, resetar algumas das variaveis de .
           else textoAtual = textoAtual.substring(0, textoAtual.length() - 1); //para digitos basta apagar normal
           barra.setText(textoAtual);
        } else if(!textoAtual.equals("") && input.equals("C")) { //apagar tudo se nao vazio, caso apague tudo, resetar variaveis de "."
            PrimeiroPonto = false;
            ColocarPonto = false;
            TeveOperacao = false;
            ColocouPonto = false;
            barra.setText("");
        } else if(!textoAtual.equals("") && (input.equals("+") || input.equals("-") || input.equals("x") || input.equals("/"))) { //entrada com simbolos de operacao caso nao vazio
            TeveOperacao = true;
            ColocarPonto = false;
            String text = new String();
            if (lastChar.equals(" ") && !input.equals("-")) { //caso ultimo char seja " ", significa que houve uma operacao antes, basta apagar 2, o simbolo e o espaco " ", e substituir por um novo simbolo e " "
                textoAtual = textoAtual.substring(0, textoAtual.length() - 2);
                text = textoAtual + input + " ";
            } else if (lastChar.equals(" ") && input.equals("-")) { //caso ultimo char seja " ", significa que houve uma operacao antes, acrescentando somente o "-" premitimos a entrada de numeros negativos
                text = textoAtual + input;
            } else if (lastChar.equals("-")) { //caso parte de um estado prestes a digitar um numero negativo para outra operacao, nao realizar alteracao
                text = textoAtual;
            }
              else if (lastChar.equals(".")) { //caso o ultimo char seja ".", basta substituir o "." pelo conjunto de operacao (" "+"op"+" ")
                textoAtual = textoAtual.substring(0, textoAtual.length() - 1);
                text = textoAtual + " " + input + " ";
            } else { //caso seja um digito, basta acrescentar o conjunto operacao
                text = textoAtual + " " + input + " ";
            }
            barra.setText(text);
        } else if (input.equals("-") && textoAtual.equals("")) { //caso queira comecar a entrada com um numero negativo
            String text = new String();
            textoAtual = "-";
            text = textoAtual;
            barra.setText(text);
        } else if (!textoAtual.equals("") && ((ColocarPonto && TeveOperacao)||!PrimeiroPonto) && input.equals(".")) { //colocar ponto se nao vazio e organizar as variaveis de ponto
            String text = textoAtual + input;
            barra.setText(text);
            ColocouPonto = true;
            PrimeiroPonto = true;
            ColocarPonto = false;
            TeveOperacao = false;
        }
        if (input.equals("=") && !textoAtual.equals("")) { //igual somente se nao estiver vazio
            if (lastChar.equals(" ")) { //caso ultima entrada tenha sido um conjunto operacao, elimina-lo (apagar 3) e fazer o calculo com o restante
                textoAtual = textoAtual.substring(0, textoAtual.length() - 3); }
            else if (lastChar.equals(".")) textoAtual = textoAtual.substring(0, textoAtual.length() - 1); //caso ultima entrada tenha sido um ".", elimina-lo (apagar 1) e fazer o calculo com o restante
            textoAtual = textoAtual + " "; //adicionar um " " para o split
            String [] text = textoAtual.split(" "); //ja que cada operacao esta entre " ", e possivel splitar a string facilmente em (numero,operacao,numero)+ utilizando o " "
            double [] valores = new double [textoAtual.length()]; //array de double para armazenar os numeros
            for (int i=0; i < text.length; i++) {
                Log.d("texto ", text[i]);
                if (i%2==0) valores[i] = Double.parseDouble(text[i]); //passagem dos numero de dois em dois para pular os simbolos de operacao, indices pares
            }
            int tamanho = text.length;
            for (int i=1; i < tamanho; i=i+2) { //primeiro fazendo as operacoes para x e / , vamos iterar de 2 em 2 para pegar somente os simbolos
                if (text[i].equals("x") || text[i].equals("/")) {
                    if (text[i].equals("x")) valores[i-1] = valores[i-1] * valores[i+1]; //como estamos no indice do simbolo, os numeros que devem ser operados sao os indice -1 e +1
                    else valores[i-1] = valores[i-1] / valores[i+1]; //caso "-"
                    for (int j=i; j<tamanho-2;j++) { //apos os calculo eh preciso trazer os restante da formula dois espacos para tras, tapando o sinal e numero usados no calculo anterior
                        text[j] = text[j + 2];
                    }
                    for (int j=i; j<tamanho-2;j++) {   //apos os calculo eh preciso trazer os restante dos numeros na formula um espaco para tras para calculos futuros
                        valores[j+1] = valores[j + 3]; //(eles estao armazenado de 2 em 2 por conta do armazenamento pular os simbolos)
                    }
//                    for (int k=0; k<tamanho-2;k+=2) {
//                        Log.d("texto apos andar",String.valueOf(valores[k]));
//                    }
//                    Log.d("resultado", String.valueOf(valores[i-1]));
                    tamanho = tamanho - 2; //apos trazer a formula 2 espacos para tras, precisamos diminuir o tamanho em 2
                    i=i-2; //ao trazer dois espacos e preciso checar a operacao do msm local novamente, ja que essa foi substituida pela seguinte
                }
            }
            for (int i=1; i < tamanho; i=i+2) { //analogo as operacoes de "x" e "/", porem aplicadas apos o processamento das mesmas
                //Log.d("resultado", text[i]);
                if (text[i].equals("+") || text[i].equals("-")) {
                    //Log.d("resultado", String.valueOf(valores[i-1]));
                    if (text[i].equals("+")) {valores[i-1] = valores[i-1] + valores[i+1];}
                    else {valores[i-1] = valores[i-1] - valores[i+1];}
                    for (int j=i; j<tamanho-2;j++) {
                        text[j] = text[j + 2];
                    }
                    for (int j=i; j<tamanho-2;j++) {
                        valores[j+1] = valores[j + 3];
                    }
//                    for (int k=0; k<tamanho-2;k+=2) {
//                        Log.d("texto apos andar",String.valueOf(valores[k]));
//                    }
//                    Log.d("resultado", String.valueOf(valores[i-1]));
                    tamanho = tamanho - 2;
                    i=i-2;
                }
            }
            if (valores[0] % 1 == 0) {  //caso o resultado seja inteiro transformar o valor em inteiro para evitar impressao do .0 e resetar a variavel de colocar o primeiro "."
                int result = (int) valores[0];
                PrimeiroPonto = false;
                barra.setText(String.valueOf(result));
            } else {
                PrimeiroPonto = true;
                barra.setText(String.valueOf(valores[0]));
            }
            ColocarPonto = false; //resetar variaveis de "."
            TeveOperacao = false;
            ColocouPonto = false;
        }
    }
}
