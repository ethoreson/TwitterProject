package com.epicodus.twitterproject.models;

import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel
public class Representative {
    String name;
    String party;
    List<String> phone = new ArrayList<>();
    List<String> channels = new ArrayList<>();

    public Representative() {}

    public Representative(String name, String party, ArrayList<String> phone, ArrayList<String> channels) {
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

    public List<String> getPhone() {
        return phone;
    }

    public List<String> getChannels() {
        return channels;
    }





}

