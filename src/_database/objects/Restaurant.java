package _database.objects;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Restaurant is a custom serializable object that stores the restaurant informations
 */
public class Restaurant implements Serializable {

    private static final long serialVersionUID = -6075801231078042820L;

    /**
     * Custom enum class to handle different kinds of restaurant types
     */
    public enum types {
        ITALIAN,
        ETHNIC,
        FUSION
    }

    public String name;
    public String qualifier; // example: via or piazza
    public String streetName;
    public Integer civicNumber;
    public String city;
    public String province;
    public Integer CAP;
    public String fullAddress; // this will be auto generated
    public Long phoneNumber;
    public String url;
    public types type;
    public ArrayList<Judgement> judgement;

    public Restaurant(){}

    public Restaurant(String name, String qualifier, String streetName, Integer civicNumber, String city, String province, Integer CAP, Long phoneNumber, String url, types type, ArrayList<Judgement> judgement) {
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