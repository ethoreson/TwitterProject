package com.epicodus.twitterproject.models;

import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel
public class Representative {
    String name;
    String party;
    List<String> phones = new ArrayList<>();
    List<String> channels = new ArrayList<>();
    String photoUrl;

    public Representative() {}

    public Representative(String name, String party, ArrayList<String> phones, ArrayList<String> channels, String photoUrl) {
        this.name = name;
        this.party = party;
        this.photoUrl = getLargePhotoUrl(photoUrl);
        this.phones = phones;
        this.channels = channels;
    }

    public String getName() {
        return name;
    }

    public String getParty() {
        return party;
    }

    public List<String> getPhones() {
        return phones;
    }

    public List<String> getChannels() {
        return channels;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public String getLargePhotoUrl(String photoUrl) {
        String largePhotoUrl = photoUrl.substring(0, photoUrl.length() - 6).concat("o.jpg");
        return largePhotoUrl;
    }



}
