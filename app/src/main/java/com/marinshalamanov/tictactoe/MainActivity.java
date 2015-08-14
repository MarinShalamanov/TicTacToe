package com.marinshalamanov.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    int player = 0; // 0 - X, 1 - O

    int taleIds[][] = {
            {R.id.tale11, R.id.tale12, R.id.tale13},
            {R.id.tale21, R.id.tale22, R.id.tale23},
            {R.id.tale31, R.id.tale32, R.id.tale33},
    };

    char table[][] = {
            {' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '}
    };

    boolean gameover;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    void init() {
        gameover = false;

        TextView title = (TextView) findViewById(R.id.title);
        title.setText(getResources().getString(R.string.title));

        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                ImageView tale = (ImageView)findViewById(taleIds[i][j]);
                tale.setImageDrawable(getResources().getDrawable(R.drawable.empty));
                tale.setOnClickListener(this);
            }
        }

        // empty table
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                table[i][j] = ' ';
            }
        }
    }

    @Override
    public void onClick(View v) {
        if (gameover) {
            return;
        }


        ImageView tale = (ImageView)v;

        // which tale is pressed?
        int taleI = -1, taleJ = -1;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (v.getId() == taleIds[i][j]) {
                    taleI = i;
                    taleJ = j;
                    break;
                }
            }
        }

        Log.e(getClass().toString(), "Pressed: " + taleI + " " + taleJ);

        boolean empty = (table[taleI][taleJ] == ' ');

        if (empty) {
            int id;
            char symbol;
            if (player == 0) {
                id = R.drawable.x;
                symbol = 'x';
            } else {
                id = R.drawable.o;
                symbol = 'o';
            }
            tale.setImageDrawable(getResources().getDrawable(id));
            table[taleI][taleJ] = symbol;

            if (player == 0) {
                player = 1;
            } else {
                player = 0;
            }

            if (isPlayerWon('o') || isPlayerWon('x')) {
                gameover = true;
                TextView title = (TextView) findViewById(R.id.title);
                title.setText(getResources().getString(R.string.gameover));
            }
        } else {
            Toast.makeText(this, "Tap on empty cell", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isPlayerWon(char symbol) {
        // check lines
        for (int i = 0; i < 3; i++) {
            boolean line = true;
            for (int j = 0; j < 3; j++) {
                if (table[i][j] != symbol) {
                    line = false;
                    break;
                }
            }

            if (line) {
                return true;
            }
        }

        // check columns
        for (int j = 0; j < 3; j++) {
            boolean column = true;
            for (int i = 0; i < 3; i++) {
                if (table[i][j] != symbol) {
                    column = false;
                    break;
                }
            }

            if (column) {
                return true;
            }
        }

        // check main diagonal
        boolean diagonal = true;
        for(int i = 0; i < 3; i++) {
            if(table[i][i] != symbol) {
                diagonal = false;
                break;
            }
        }
        if(diagonal) {
            return true;
        }

        // check second diagonal
        diagonal = true;
        for(int i = 0; i < 3; i++) {
            if(table[i][2-i] != symbol) {
                diagonal = false;
                break;
            }
        }
        if(diagonal) {
            return true;
        }

        return false;
    }

    public void restart(View v) {
        init();
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
