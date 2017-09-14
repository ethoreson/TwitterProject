package com.epicodus.twitterproject;

import java.util.ArrayList;

public class Representative {
    private String mName;
    private String mParty;
    private String mTwitter;
    private ArrayList<String> mChannels = new ArrayList<>();

    public Representative(String name, String party, ArrayList<String> channels) {
        this.mName = name;
        this.mParty = party;
        this.mChannels = channels;
    }

    public String getName() {
        return mName;
    }

    public String getParty() {
        return mName;
    }

    public ArrayList<String> getmChannels() {
        return mChannels;
    }
}
