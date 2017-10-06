package com.epicodus.twitterproject.models;

import org.parceler.Parcel;

@Parcel
public class Representative {
    String name;
    String party;
    String phone;
    String index;

    public Representative() {}

    public Representative(String name, String party, String phone) {
        this.name = name;
        this.party = party;
        this.phone = phone;
        this.index = "not_specified";
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

    public String getIndex() { return index; }

    public void setIndex(String index) { this.index = index; }

}

