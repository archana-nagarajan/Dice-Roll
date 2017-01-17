package com.example.diceroll;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.diceout.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView rollResult;
  //dice_100px.png  Button rollButton;
    int score;
    Random rand;
    int die1;
    int die2;
    int die3;
    ArrayList<Integer> dice;
    ArrayList<ImageView> diceImageViews;

    TextView scoreText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rollDice(view);
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });

        score=0;
        rollResult= (TextView)findViewById(R.id.rollResult);
   //     rollButton= (Button) findViewById(R.id.rollButton);
        scoreText=(TextView) findViewById(R.id.scoreText);
        ImageView die1Image =(ImageView) findViewById(R.id.die1Image);
        ImageView die2Image =(ImageView) findViewById(R.id.die2Image);
        ImageView die3Image =(ImageView) findViewById(R.id.die3Image);
        rand=new Random();
        dice=new ArrayList<Integer>();
        diceImageViews=new ArrayList<ImageView>();
        diceImageViews.add(die1Image);
        diceImageViews.add(die2Image);
        diceImageViews.add(die3Image);
        Toast.makeText(getApplicationContext(),"Welcome to DiceOut", Toast.LENGTH_SHORT).show();
    }

    public void rollDice(View v){
        rollResult.setText("Clicked!");
//        int num=rand.nextInt(6)+1;
//        String randomValue= "Number Generated: "+ num;
//        Toast.makeText(getApplicationContext(),randomValue,Toast.LENGTH_SHORT).show();
        die1=rand.nextInt(6)+1;
        die2=rand.nextInt(6)+1;
        die3=rand.nextInt(6)+1;
        dice.clear();
        dice.add(die1);
        dice.add(die2);
        dice.add(die3);

        for(int dieOffset=0;dieOffset<3;dieOffset++){
            String imageName="die_"+dice.get(dieOffset)+ ".png";
            try{
                InputStream stream= getAssets().open(imageName);
                Drawable d=Drawable.createFromStream(stream,null);
                diceImageViews.get(dieOffset).setImageDrawable(d);
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
    //    String msg="You rolled a " + die1+ " , a "+ die2+ " and a " +die3;
        String msg;
        if(die1==die2 && die1==die3){
            int finalScore= die1*100;
            msg="You rolled a triple. " + die1 + " You score " + finalScore + " points";
        }
        else if(die1==die2 || die1==die3 || die2==die3){
            int finalScore= die1*100;
            msg="You rolled a double. You get 50 points";
            score+=50;
        }
        else{
            msg="Shoot! You didn't score this roll. Give it another try!";
        }
        rollResult.setText(msg);
        scoreText.setText("Your Score: "+score);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
