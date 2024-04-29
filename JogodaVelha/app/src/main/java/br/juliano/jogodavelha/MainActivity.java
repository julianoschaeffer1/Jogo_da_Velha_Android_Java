package br.juliano.jogodavelha;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    boolean jogoAtivo = true;
    boolean player = true;
    int njogadas;
    //quando o jogador ou a maquina jogar, acrescenta 1 ao contador njogadas
    int posicaoAtual[] = {2, 2, 2, 2, 2, 2, 2, 2, 2};
            //2 eh null
            //1 eh X(maquina)
            //0 eh O
    int winPositions[][] = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}};
    public void tap(View view){
    ImageView img = (ImageView) view;
        int imagetoque = Integer.parseInt(img.getTag().toString());

        if (posicaoAtual[imagetoque] == 2){
            njogadas++;
        }
        if (njogadas == 9){
            jogoAtivo = false;
        }
        posicaoAtual[imagetoque] = 0;
        if (player){
            TextView status = findViewById(R.id.mensagemID);
            //jogador é o circulo
            img.setImageResource(R.drawable.circulo);
            player = false;
            status.setText("Turno da Máquina!");
        }
        else {
            turnoComp();
        }

    }
//public void turnoComp(){
//    int random = new Random().nextInt(9);
//    //int imagecomp = Integer.parseInt(img.getTag(random).toString());
//   // String id = "image" + random;
//    ImageView img = findViewById(getResources().getIdentifier("image"+ random,"id",getPackageName()));
//    //ImageView img2 = (ImageView) findViewById(random);
//    posicaoAtual[random] = 1;
//    img.setImageResource(R.drawable.x);
//}
public void turnoComp(){
        int random = new Random().nextInt(9);
    ImageView img = (ImageView) findViewById(random);
    int imagecomp = Integer.parseInt(img.getTag(random).toString());
    // String id = "image" + random;
    ImageView img2 = (ImageView) findViewById(imagecomp);
    posicaoAtual[random] = 1;
    img2.setImageResource(R.drawable.x);
}


    public void verifvenc(){

}
}