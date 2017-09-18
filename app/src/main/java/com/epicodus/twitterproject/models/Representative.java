package com.epicodus.twitterproject.models;

import org.parceler.Parcel;

@Parcel
public class Representative {
    String mName;
    String mParty;

    public Representative() {}

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
