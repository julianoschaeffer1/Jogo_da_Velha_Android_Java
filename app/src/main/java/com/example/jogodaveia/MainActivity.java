package com.example.jogodaveia;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button[][] botoes = new Button[3][3];
    private boolean TurnoJogador = true;

    private int JogadaCount = 0;

    private int PlayerPontuacao = 0;
    private int MaquinaPontuacao = 0;

    private TextView TextViewPJogador;
    private TextView TextViewPMaquina;

    private TextView TextViewResultado;

    private Button TextViewBotaoReiniciar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextViewResultado = findViewById(R.id.resultado);
        TextViewPJogador = findViewById(R.id.TextViewPontuacaoJogador);
        TextViewPMaquina = findViewById(R.id.TextViewPontuacaoMaquina);
        TextViewBotaoReiniciar = findViewById(R.id.BotaoReiniciar);

        botoes[0][0] = findViewById(R.id.botao00);
        botoes[0][1] = findViewById(R.id.botao01);
        botoes[0][2] = findViewById(R.id.botao02);
        botoes[1][0] = findViewById(R.id.botao03);
        botoes[1][1] = findViewById(R.id.botao04);
        botoes[1][2] = findViewById(R.id.botao05);
        botoes[2][0] = findViewById(R.id.botao06);
        botoes[2][1] = findViewById(R.id.botao07);
        botoes[2][2] = findViewById(R.id.botao08);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                botoes[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!v.isEnabled()) {
                            return;
                        }
                        if (TurnoJogador) {
                            ((Button) v).setText("O");
                        } else {
                            ((Button) v).setText("X");
                        }
                        v.setEnabled(false);
                        JogadaCount++;
                        if (verificarVitoria()) {
                            if (TurnoJogador) {
                                PlayerPontuacao++;
                                TextViewResultado.setText("Jogador Venceu \uD83C\uDFAE");
                                TextViewPJogador.setText("Jogador: " + PlayerPontuacao);
                            } else {
                                MaquinaPontuacao++;
                                //TextViewResultado.setText("Máquina Venceu(Mundo Destruído)");
                                TextViewPMaquina.setText("Maquina: " + MaquinaPontuacao);
                            }
                            TextViewBotaoReiniciar.setEnabled(true);
                        } else if (JogadaCount == 9) {
                            TextViewResultado.setText("Deu veia \uD83D\uDC75");
                            TextViewBotaoReiniciar.setEnabled(true);
                        } else {
                            TurnoJogador = !TurnoJogador;
                            if (!TurnoJogador) {
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        jogadaMaquina();
                                    }
                                }, 500);
                            }
                        }
                    }
                });
            }

        }


    }

    private void jogadaMaquina() {
        Random random = new Random();
        int i, j;
        do {
            i = random.nextInt(3);
            j = random.nextInt(3);
        } while (!botoes[i][j].isEnabled());
        botoes[i][j].setText("X");
        botoes[i][j].setEnabled(false);
        JogadaCount++;
        if (verificarVitoria()) {

            MaquinaPontuacao++;
            TextViewResultado.setText("Máquina Venceu(Mundo Destruído \uD83D\uDCA3)");
            TextViewPMaquina.setText("Máquina: " + MaquinaPontuacao);
            TextViewBotaoReiniciar.setEnabled(true);

        } else if (JogadaCount == 9) {
            TextViewResultado.setText("Deu veia \uD83D\uDC75");
            TextViewBotaoReiniciar.setEnabled(true);
        }
        //mesma coisa que TurnoJogador = true
        TurnoJogador = !TurnoJogador;

    }

    private boolean verificarVitoria() {

        String campo[][] = new String[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                campo[i][j] = botoes[i][j].getText().toString();
            }
        }
        for (int i = 0; i < 3; i++) {
            if (campo[i][0].equals(campo[i][1]) && campo[i][0].equals(campo[i][2])
                    && !campo[i][0].equals("")) {
                return true;
            }

        }
        for (int i = 0; i < 3; i++) {
            if (campo[0][i].equals(campo[1][i]) && campo[0][i].equals(campo[2][i])
                    && !campo[0][i].equals("")) {
                return true;
            }

        }
        if (campo[0][0].equals(campo[1][1]) && campo[0][0].equals(campo[2][0])
                && !campo[0][0].equals("")) {
            return true;
        }


        if (campo[0][2].equals(campo[1][1]) && campo[0][2].equals(campo[2][0])
                && !campo[0][2].equals("")) {
            return true;
        }

        return false;
    }

    public void ReiniciarJogo(View view) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                botoes[i][j].setText("");
                botoes[i][j].setEnabled(true);
            }
        }
        JogadaCount = 0;
        TurnoJogador = true;
        TextViewResultado.setText("");
        TextViewBotaoReiniciar.setEnabled(false);
    }
}