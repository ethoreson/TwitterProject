package com.epicodus.twitterproject.models;

import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel
public class Representative {
    String name;
    String party;
    List<String> phone = new ArrayList<>();

    public Representative() {}

    public Representative(String name, String party, ArrayList<String> phone) {
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

    public List<String> getPhone() {
        return phone;
    }


}

