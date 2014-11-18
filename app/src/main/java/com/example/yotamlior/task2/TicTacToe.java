package com.example.yotamlior.task2;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.ArrayList;


public class TicTacToe extends Activity {

    private final int ROWS_LENGTH = 3;
    private final int COLS_LENGTH = 3;

    private int nCurrentTurn = 1;
    private int nMovesCounter = 0;
    //private char Board[] = new char[9];
    private Cell[][] Board = new Cell[ROWS_LENGTH][COLS_LENGTH];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tic_tac_toe);
        ((ImageView) findViewById(R.id.imageView)).setImageResource(R.drawable.xplay);

        // Initialize the new game button
        final Button btnNewGame = (Button) findViewById(R.id.new_game);
        btnNewGame.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                recreate();
            }
        });

        Board[0][0] = new Cell(R.id.cell_00, (ImageButton) findViewById(R.id.cell_00));
        Board[0][1] = new Cell(R.id.cell_01, (ImageButton) findViewById(R.id.cell_01));
        Board[0][2] = new Cell(R.id.cell_02, (ImageButton) findViewById(R.id.cell_02));
        Board[1][0] = new Cell(R.id.cell_10, (ImageButton) findViewById(R.id.cell_10));
        Board[1][1] = new Cell(R.id.cell_11, (ImageButton) findViewById(R.id.cell_11));
        Board[1][2] = new Cell(R.id.cell_12, (ImageButton) findViewById(R.id.cell_12));
        Board[2][0] = new Cell(R.id.cell_20, (ImageButton) findViewById(R.id.cell_20));
        Board[2][1] = new Cell(R.id.cell_21, (ImageButton) findViewById(R.id.cell_21));
        Board[2][2] = new Cell(R.id.cell_22, (ImageButton) findViewById(R.id.cell_22));

        for (int rowIndex = 0; rowIndex < ROWS_LENGTH; rowIndex++) {
            for (int colIndex = 0; colIndex < COLS_LENGTH; colIndex++) {
                final ImageButton currImgBtn =  Board[rowIndex][colIndex].getCellShapeImg();

                currImgBtn.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        Cell clickedCell = getCellByID(v.getId());

                        if (clickedCell.getCellShape() == Cell.Empty) {
                            if (nCurrentTurn == 1) {
                                currImgBtn.setImageResource(R.drawable.x);
                                clickedCell.setCellShape(Cell.X);
                                nCurrentTurn = 2;
                                ((ImageView) findViewById(R.id.imageView)).setImageResource(R.drawable.oplay);
                            } else {
                                currImgBtn.setImageResource(R.drawable.o);
                                clickedCell.setCellShape(Cell.O);
                                nCurrentTurn = 1;
                                ((ImageView) findViewById(R.id.imageView)).setImageResource(R.drawable.xplay);
                            }

                            nMovesCounter++;

                            if (nMovesCounter == ROWS_LENGTH * COLS_LENGTH && !checkWin()) {
                                ((ImageView) findViewById(R.id.imageView)).setImageResource(R.drawable.nowin);
                            }
                            if (checkWin()) {
                                if (nCurrentTurn == 1){
                                    ((ImageView) findViewById(R.id.imageView)).setImageResource(R.drawable.owin);
                                }
                                else{
                                    ((ImageView) findViewById(R.id.imageView)).setImageResource(R.drawable.xwin);
                                }
                                for (int rowIndex = 0; rowIndex < ROWS_LENGTH; rowIndex++) {
                                    for (int colIndex = 0; colIndex < COLS_LENGTH; colIndex++) {
                                        Board[rowIndex][colIndex].getCellShapeImg().setEnabled(false);
                                    }
                                }
                            };
                        }
                    }
                });
            }
        }
    }

    private boolean checkWin(){

        if (checkULTLRDiagonal()){
            return true;
        }
        if (checkURTLLDiagonal()) {
            return true;
        }
        if (checkHorizontal()){
            return true;
        }
        if (checkVertical()){
            return true;
        }

        return false;
    }

    private boolean checkULTLRDiagonal() {
        if (Board[0][0].getCellShape() == Board[1][1].getCellShape() &&
                Board[1][1].getCellShape() == Board[2][2].getCellShape() &&
                Board[2][2].getCellShape() != Cell.Empty) {

            Board[0][0].getCellShapeImg().setImageResource(R.drawable.mark1);
            Board[1][1].getCellShapeImg().setImageResource(R.drawable.mark1);
            Board[2][2].getCellShapeImg().setImageResource(R.drawable.mark1);

            return true;
        }
        return false;
    }

    private boolean checkURTLLDiagonal() {
        if (Board[0][2].getCellShape() == Board[1][1].getCellShape() &&
                Board[1][1].getCellShape() == Board[2][0].getCellShape() &&
                Board[0][2].getCellShape() != Cell.Empty) {

            Board[0][2].getCellShapeImg().setImageResource(R.drawable.mark2);
            Board[1][1].getCellShapeImg().setImageResource(R.drawable.mark2);
            Board[2][0].getCellShapeImg().setImageResource(R.drawable.mark2);

            return true;
        }
        return false;
    }

    private boolean checkHorizontal(){
        for (int rowIndex = 0; rowIndex < ROWS_LENGTH; rowIndex++) {
            if (Board[rowIndex][0].getCellShape() == Board[rowIndex][1].getCellShape() &&
                    Board[rowIndex][1].getCellShape() == Board[rowIndex][2].getCellShape() &&
                    Board[rowIndex][0].getCellShape() != Cell.Empty) {
                for (int colIndex = 0; colIndex < COLS_LENGTH; colIndex++) {
                    Board[rowIndex][colIndex].getCellShapeImg().setImageResource(R.drawable.mark6);
                }
                return true;
            }
        }

        return false;
    }

    private boolean checkVertical(){
        for (int colIndex = 0; colIndex < COLS_LENGTH; colIndex++) {
            if (Board[0][colIndex].getCellShape() == Board[1][colIndex].getCellShape() &&
                    Board[1][colIndex].getCellShape() == Board[2][colIndex].getCellShape() &&
                    Board[0][colIndex].getCellShape() != Cell.Empty) {
                for (int rowIndex = 0; rowIndex < ROWS_LENGTH; rowIndex++) {
                    Board[rowIndex][colIndex].getCellShapeImg().setImageResource(R.drawable.mark4);
                }
                return true;
            }
        }

        return false;
    }

    private Cell getCellByID(int cellID){
        for (int rowIndex = 0; rowIndex < ROWS_LENGTH; rowIndex++) {
            for (int colIndex = 0; colIndex < COLS_LENGTH; colIndex++) {
                if (Board[rowIndex][colIndex].getCellId() == cellID) {
                    return Board[rowIndex][colIndex];
                }
            }
        }
        return null;
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
