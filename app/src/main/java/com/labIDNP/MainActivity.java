package com.labIDNP;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;

import com.labIDNP.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public List<BarSeries> series = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BarSeries b1 = new BarSeries("Arequipa", 21);
        BarSeries b2 = new BarSeries("Moquegua", 8);
        BarSeries b3 = new BarSeries("Tacna", 31);
        BarSeries b4 = new BarSeries("Cuzco", 62);

        series.add(b1);
        series.add(b2);
        series.add(b3);
        series.add(b4);


        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setViewModel(this);
    }
}