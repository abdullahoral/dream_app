package com.furkansalihege.android.dreamapp;

public class Dream {

    private String title;
    int rate;

    public Dream() {
    }

    public Dream(String title, int rate) {
        this.title = title;
        this.rate = rate;
    }

    public String getTitle() {
        return title;
    }

    public int getRate() {
        return rate;
    }
}
