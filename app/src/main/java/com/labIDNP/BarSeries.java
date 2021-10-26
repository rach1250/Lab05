package com.labIDNP;

public class BarSeries {
    public String name;
    public float value;

    public BarSeries(String name, float value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
