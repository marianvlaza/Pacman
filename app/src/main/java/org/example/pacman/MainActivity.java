package org.example.pacman;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import static android.R.attr.button;

public class MainActivity extends Activity {
    GameView gameView;
    Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //saying we want the game to run in one mode only
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);

        gameView =  findViewById(R.id.gameView);
        TextView textView = findViewById(R.id.points);


        game = new Game(this,textView);
        game.setGameView(gameView);
        gameView.setGame(game);

        game.newGame();

        ImageButton buttonUp = findViewById(R.id.moveUp);
        //listener of our pacman, when somebody clicks it
        buttonUp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                game.movePacmanUp(-50);
            }
        });

        ImageButton buttonLeft = findViewById(R.id.moveLeft);
        //listener of our pacman, when somebody clicks it
        buttonLeft.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                game.movePacmanLeft(-50);
            }
        });

        ImageButton buttonRight = findViewById(R.id.moveRight);
        //listener of our pacman, when somebody clicks it
        buttonRight.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                game.movePacmanRight(50);
            }
        });

        ImageButton buttonDown = findViewById(R.id.moveDown);
        //listener of our pacman, when somebody clicks it
        buttonDown.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                game.movePacmanDown(50);
            }
        });


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Toast.makeText(this,"settings clicked",Toast.LENGTH_LONG).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
