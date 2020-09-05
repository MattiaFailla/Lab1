package _database.objects;

import java.io.Serializable;
import java.util.ArrayList;

public class Restaurant implements Serializable {
    public enum types {
        ITALIANO,
        ETNICO,
        FUSION
    }

    public final String name;
    public final String qualifier; // example: via or piazza
    public final String streetName;
    public final Integer civicNumber;
    public final String city;
    public final String province;
    public final Integer CAP;
    public final String fullAddress; // this will be auto generated
    public final Integer phoneNumber;
    public final String url;
    public final types type;
    public final ArrayList<Judgement> judgement;

    public Restaurant(String name, String qualifier, String streetName, Integer civicNumber, String city, String province, Integer CAP, Integer phoneNumber, String url, types type, ArrayList<Judgement> judgement) {
        this.name = name;
        this.qualifier = qualifier;
        this.streetName = streetName;
        this.civicNumber = civicNumber;
        this.city = city;
        this.province = province;
        this.CAP = CAP;
        this.fullAddress = qualifier + " " + streetName + ", " + civicNumber.toString() + ", " + city + " (" + province + ") " + CAP.toString();
        this.phoneNumber = phoneNumber;
        this.url = url;
        this.type = type;
        this.judgement = judgement;
    }

    public String toString(){
        return "Name: " + name +
                "\nqualifier: " + qualifier +
                "\nstreetName: " + streetName +
                "\ncivicNumber: " + civicNumber +
                "\ncity: " + city +
                "\nprovince: " + province +
                "\nCAP :" + CAP +
                "\nfullAddress: " + fullAddress +
                "\nphoneNumber: " + phoneNumber.toString() +
                "\nurl: " + url +
                "\ntype: " + type.toString() +
                "\n Giudizi: " + judgement.toString();
    }
}