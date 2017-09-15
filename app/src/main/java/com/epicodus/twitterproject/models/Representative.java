package com.epicodus.twitterproject.models;

public class Representative {
    private String mName;
    private String mParty;

    public Representative(String name, String party) {
        this.mName = name;
        this.mParty = party;
    }

    public String getName() {
        return mName;
    }

    public String getParty() {
        return mParty;
    }

}
