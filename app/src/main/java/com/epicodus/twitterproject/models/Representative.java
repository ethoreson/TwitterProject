package com.epicodus.twitterproject.models;

import org.parceler.Parcel;

@Parcel
public class Representative {
    String name;
    String party;
    String phone;
    String channels;

    public Representative() {}

    public Representative(String name, String party, String phone, String channels) {
        this.name = name;
        this.party = party;
        this.phone = phone;
        this.channels = channels;
    }

    public String getName() {
        return name;
    }

    public String getParty() {
        return party;
    }

    public String getPhone() {
        return phone;
    }

    public String getChannels() { return channels; }


}

