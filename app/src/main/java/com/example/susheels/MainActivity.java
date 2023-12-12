package com.example.susheels;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    boolean gameActive=true;

    //player reprentation
    //0-x
    //1-0
    int activeplayer = 0;
    int[] gamestate = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    //state meanings
    //0-x
    //1-0
    //2-null
    int[][] winposition = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}};

    public void playerTap(View view) {
        ImageView img = (ImageView) view;
        int tappedImage = Integer.parseInt(img.getTag().toString());
        if(!gameActive){
            GameReset(view);
        }
        if (gamestate[tappedImage] == 2) {
            gamestate[tappedImage] = activeplayer;
            img.setTranslationY(-1000f);
            if (activeplayer == 0) {
                img.setImageResource(R.drawable.x);
                activeplayer = 1;
                TextView status=findViewById(R.id.status);
                status.setText("o's Turn -Tap to play");
            } else {
                img.setImageResource(R.drawable.o);
                activeplayer = 0;
                TextView status=findViewById(R.id.status);
                status.setText("x's Turn -Tap to play");
            }

            img.animate().translationYBy(1000f).setDuration(3000);

        }
        //check won
        for(int []winposition:winposition){
            if(gamestate[winposition[0]]==gamestate[winposition[1]]
                && gamestate[winposition[1]]==gamestate[winposition[2]]
                && gamestate[winposition[0]]!=2){
                //won find who
                String winnerStr;
                gameActive=false;
                if(gamestate[winposition[0]]==0){
                    winnerStr="X has won";
                }
                else{
                    winnerStr="O has won";
                }
                //updae the status bar for winner announcement

                TextView status=findViewById(R.id.status);
                status.setText(winnerStr);
            }
        }

    }
    //check if any player has won


public void GameReset(View view){
        gameActive=true;
        activeplayer=0;
        for(int i=0;i<gamestate.length;i++){
            gamestate[i]=2;
        }
    ((ImageView)findViewById(R.id.imageView0)).setImageResource(0);
    ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
    ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
    ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
    ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
    ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
    ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
    ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
    ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);

    TextView status=findViewById(R.id.status);
    status.setText("x's Turn -Tap to play");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

}