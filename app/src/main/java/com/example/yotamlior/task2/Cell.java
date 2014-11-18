package com.example.yotamlior.task2;

import android.widget.ImageButton;

/**
 * Created by yotam&lior on 18/11/2014.
 */
public class Cell {
    public final static char X = 'x';
    public final static char O = 'o';
    public final static char Empty = ' ';

    private int cellId;
    private ImageButton cellShapeImg;
    private char cellShape;

    public Cell(int cellId, ImageButton cellImg) {
        this.cellId = cellId;
        this.cellShapeImg = cellImg;
        this.cellShape = Empty;
    }

    public char getCellShape() {

        return cellShape;
    }

    public void setCellShape(char cellShape) {
        this.cellShape = cellShape;
    }

    public int getCellId() {
        return cellId;
    }

    public void setCellId(int cellId) {
        this.cellId = cellId;
    }

    public ImageButton getCellShapeImg() {
        return cellShapeImg;
    }

    public void setCellShapeImg(ImageButton cellShapeImg) {
        this.cellShapeImg = cellShapeImg;
    }


}
