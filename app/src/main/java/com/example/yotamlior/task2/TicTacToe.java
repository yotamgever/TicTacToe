package com.example.yotamlior.task2;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.ArrayList;


public class TicTacToe extends Activity {

    private int nCurrentTurn = 1;
    private char Board[] = new char[9];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tic_tac_toe);

        ArrayList<ImageButton> ImageButtonList = new ArrayList<ImageButton>();
        ImageButtonList.add((ImageButton) findViewById(R.id.cell_11));
        ImageButtonList.add((ImageButton) findViewById(R.id.cell_12));
        ImageButtonList.add((ImageButton) findViewById(R.id.cell_13));
        ImageButtonList.add((ImageButton) findViewById(R.id.cell_21));
        ImageButtonList.add((ImageButton) findViewById(R.id.cell_22));
        ImageButtonList.add((ImageButton) findViewById(R.id.cell_23));
        ImageButtonList.add((ImageButton) findViewById(R.id.cell_31));
        ImageButtonList.add((ImageButton) findViewById(R.id.cell_32));
        ImageButtonList.add((ImageButton) findViewById(R.id.cell_33));

        for (final ImageButton button : ImageButtonList){
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if (nCurrentTurn == 1){
                        button.setImageResource(R.drawable.x);
                        nCurrentTurn = 2;
                    }
                    else {
                        button.setImageResource(R.drawable.o);
                        nCurrentTurn = 1;
                    }
                }
            });
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tic_tac_toe, menu);
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
