package com.labIDNP;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class BarChart extends View {
    Paint barPainter, axisPainter, guidePainter, xLabelPainter, yLabelPainter;
    float padding, yLabelWidth, xLabelWidth;
    List<BarSeries> series;

    public BarChart(Context context) {
        super(context);
        padding = 20f;

        barPainter = new Paint();
        barPainter.setStyle(Paint.Style.FILL);
        barPainter.setColor(Color.BLUE);

        axisPainter = new Paint();
        axisPainter.setStyle(Paint.Style.STROKE);
        axisPainter.setColor(Color.BLACK);
        axisPainter.setStrokeWidth(3f);

        guidePainter = new Paint();
        guidePainter.setStyle(Paint.Style.STROKE);
        guidePainter.setColor(Color.GRAY);
        guidePainter.setStrokeWidth(3f);

        xLabelPainter = new Paint(Paint.ANTI_ALIAS_FLAG);
        xLabelPainter.setColor(Color.BLACK);
        xLabelPainter.setTextSize(30f);
        xLabelPainter.setTextAlign(Paint.Align.RIGHT);

        yLabelPainter = new Paint(Paint.ANTI_ALIAS_FLAG);
        yLabelPainter.setColor(Color.BLACK);
        yLabelPainter.setTextSize(30f);
        yLabelPainter.setTextAlign(Paint.Align.RIGHT);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int height = getHeight();
        int width = getWidth();

        float gridBottom = height - padding - xLabelWidth;
        float gridRight = width - padding;
        drawGuides(canvas, gridBottom, gridRight);

        float gridTopLeft = padding + yLabelWidth;
        drawAxis(canvas, gridTopLeft, gridBottom, gridRight);
        drawBars(canvas, height, gridTopLeft, gridBottom, gridRight);
    }

    private void drawBars(Canvas canvas, float canvasHeight, float gridTopLeft, float gridBottom, float gridRight) {
        float space = 10f;
        float totalSpace = space * series.size();
        float width = (gridRight - gridTopLeft - totalSpace) / series.size();
        float left = gridTopLeft + 10f;
        float right = left + width;
        float height = canvasHeight - 2 * padding - xLabelWidth;

        for (BarSeries s : series){
            float top = padding + height * (1f - s.value);
            canvas.drawRect(left, top, right, gridBottom, barPainter);

            canvas.rotate(-90f);
            canvas.drawText(s.getName(), -height - padding - 10f, left + (width + 2 * space) / 2, xLabelPainter);
            canvas.rotate(90f);

            left = right + space;
            right = left + width;
        }
    }

    private void drawAxis(Canvas canvas, float gridTopLeft, float gridBottom, float gridRight) {
        canvas.drawLine(gridTopLeft, gridBottom, gridTopLeft, padding, axisPainter);
        canvas.drawLine(gridTopLeft, gridBottom, gridRight, gridBottom, axisPainter);
    }

    private void drawGuides(Canvas canvas, float gridBottom, float gridRight) {
        float spacing = (gridBottom - padding) / 10f;
        float y;
        for (int i = 0; i < 10; i++) {
            String label = Integer.toString(100 - i * 10);
            float width = yLabelPainter.measureText(label);

            if(yLabelWidth < width)
                yLabelWidth = width;

            Rect bound = new Rect();
            yLabelPainter.getTextBounds(label, 0, label.length(), bound);

            y = padding + i * spacing;
            canvas.drawLine(padding + yLabelWidth, y, gridRight, y, guidePainter);
            canvas.drawText(label, padding + yLabelWidth, y + bound.height() / 2, yLabelPainter);
        }
    }

    private void SetSeries(List<BarSeries> series){
        this.series = series;
        int aux = 0;
        String label = "";

        for(int i = 0; i < series.size(); i++){
            int nameLength = series.get(i).getName().length();

            if(nameLength > aux) {
                aux = nameLength;
                label = series.get(i).getName();
            }
        }

        xLabelWidth = xLabelPainter.measureText(label);

        /*int aux = 0;
        String label = "";

        for(int i = 0; i < series.size(); i++){
            int nameLength = series.get(i).getName().length();

            if(nameLength > aux) {
                aux = nameLength;
                label = series.get(i).getName();
            }
        }

        xLabelWidth = xLabelPainter.measureText(label);*/
    }

    /*private List<BarSeries> createSeries(){
        List<BarSeries> barras = new ArrayList<>();

        BarSeries b1 = new BarSeries("Arequipa", 0.21f);
        BarSeries b2 = new BarSeries("Moquegua", 0.08f);
        BarSeries b3 = new BarSeries("Tacna", 0.31f);
        BarSeries b4 = new BarSeries("Cuzco", 0.62f);

        barras.add(b1);
        barras.add(b2);
        barras.add(b3);
        barras.add(b4);

        return barras;
    }*/
}
