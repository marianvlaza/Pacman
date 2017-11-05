package org.example.pacman;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Timer;
import java.util.TimerTask;

import static android.R.attr.button;

public class MainActivity extends Activity {
    GameView gameView;
    Game game;
    private Timer myTimer;
    private Timer enemyTimer;

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
        myTimer = new Timer();
        enemyTimer = new Timer();

        // create timers for pacman and enemies movement

        //We will call the timer 7 times each second
        myTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                TimerMethod();
            }

        }, 0, 7); //0 indicates we start now, 7
        //is the number of miliseconds between each call

        //We will call the timer 4 times each second
        enemyTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                EnemyTimerMethod();
            }

        }, 0, 4); //0 indicates we start now, 4
        //is the number of miliseconds between each call

        // create action for the buttons

        ImageButton buttonUp = findViewById(R.id.moveUp);
        //listener of our pacman, when somebody clicks it and set direction Up
        buttonUp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                game.setDirection(4);
            }
        });

        ImageButton buttonLeft = findViewById(R.id.moveLeft);
        //listener of our pacman, when somebody clicks it and set direction Left
        buttonLeft.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                game.setDirection(3);
            }
        });

        ImageButton buttonRight = findViewById(R.id.moveRight);
        //listener of our pacman, when somebody clicks it and set direction Right
        buttonRight.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                game.setDirection(1);
            }
        });

        ImageButton buttonDown = findViewById(R.id.moveDown);
        //listener of our pacman, when somebody clicks it and set direction Down
        buttonDown.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                game.setDirection(2);
            }
        });

        Button buttonNewGame = findViewById(R.id.newGame);
        //listener of new game button, when somebody clicks it starts a new game
        buttonNewGame.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                game.newGame();

                Context context = getApplicationContext();
                CharSequence text = "Game has restarted!";
                int duration = Toast.LENGTH_LONG;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });

        Button buttonPauseGame = findViewById(R.id.pauseGame);
        //listener of pause button, when somebody clicks it the game is paused
        buttonPauseGame.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                game.pauseGame();
            }
        });

        Button buttonResumeGame = findViewById(R.id.resumeGame);
        //listener of resume button, when somebody clicks it the game is resumed
        buttonResumeGame.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                game.resumeGame();
            }
        });

    }

    private void TimerMethod()
    {
        //This method is called directly by the timer
        //and runs in the same thread as the timer.

        //We call the method that will work with the UI
        //through the runOnUiThread method.
        this.runOnUiThread(Timer_Tick);
    }

    private void EnemyTimerMethod()
    {
        //This method is called directly by the timer
        //and runs in the same thread as the timer.

        //We call the method that will work with the UI
        //through the runOnUiThread method.
        this.runOnUiThread(Enemy_Timer_Tick);
    }

    private Runnable Timer_Tick = new Runnable() {
        public void run() {
            //This method runs in the same thread as the UI.
            // so we can draw
            if (game.isRunning())
            {
                if(game.getDirection() == 1) {
                    game.movePacmanRight(2);
                }
                else if(game.getDirection() == 2) {
                    game.movePacmanDown(2);
                }
                else if(game.getDirection() == 3) {
                    game.movePacmanLeft(-2);
                }
                else if(game.getDirection() == 4) {
                    game.movePacmanUp(-2);
                }
            }
        }
    };

    private Runnable Enemy_Timer_Tick = new Runnable() {
        public void run() {
            //This method runs in the same thread as the UI.
            // so we can draw
            if (game.isRunning())
            {
                if(game.getDirection() == 1) {
                    game.moveEnemyY(-2);
                }
                else if(game.getDirection() == 2) {
                    game.moveEnemyY(2);
                }
                else if(game.getDirection() == 3) {
                    game.moveEnemyX(2);
                }
                else if(game.getDirection() == 4) {
                    game.moveEnemyX(-2);
                }
            }
        }
    };

    // NOT USING THE DOT MENU SI I COMMENTED IT OUT

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//        if (id == R.id.action_settings) {
//            Toast.makeText(this,"settings clicked",Toast.LENGTH_LONG).show();
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }

}
