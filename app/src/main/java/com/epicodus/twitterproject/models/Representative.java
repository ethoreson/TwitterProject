package com.epicodus.twitterproject.models;

import org.parceler.Parcel;

@Parcel
public class Representative {
    String name;
    String party;
    String phone;
    private String pushId;

    public Representative() {}

    public Representative(String name, String party, String phone) {
        this.name = name;
        this.party = party;
        this.phone = phone;
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

    public String getPushId(){ return pushId; }

    public void setPushId(String pushId){ this.pushId = pushId; }

}

