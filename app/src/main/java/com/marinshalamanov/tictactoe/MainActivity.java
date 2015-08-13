package com.marinshalamanov.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    int player = 0; // 0 - X, 1 - O

    int taleIds[][] = {
            {R.id.tale11, R.id.tale12, R.id.tale13},
            {R.id.tale21, R.id.tale22, R.id.tale23},
            {R.id.tale31, R.id.tale32, R.id.tale33},
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                ImageView tale = (ImageView)findViewById(taleIds[i][j]);
                tale.setImageDrawable(getResources().getDrawable(R.drawable.empty));
                tale.setOnClickListener(this);
            }
        }
    }

    @Override
    public void onClick(View v) {
        ImageView tale = (ImageView)v;
        int id;
        if(player == 0) {
            id = R.drawable.x;
        } else {
            id = R.drawable.o;
        }
        tale.setImageDrawable(getResources().getDrawable(id));

        if(player == 0) {
            player = 1;
        } else {
            player = 0;
        }
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
