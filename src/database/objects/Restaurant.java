package database.objects;

import java.io.Serializable;

public class Restaurant implements Serializable {
    public enum types {
        ITALIANO,
        ETNICO,
        FUSION
    }

    private String name;
    /* Address data */
    private String qualifier; // example: via or piazza
    private String streetName;
    private Integer civicNumber;
    private String city;
    private String province;
    private Integer CAP;
    private String fullAddress; // this will be auto generated

    private Integer phoneNumber;
    private String url;
    private types type;

    Restaurant(){}

    public Restaurant(String name, String qualifier, String streetName, Integer civicNumber, String city, String province, Integer CAP, Integer phoneNumber, String url, types type){
        this.name = name;
        this.qualifier = qualifier;
        this.streetName = streetName;
        this.civicNumber = civicNumber;
        this.city = city;
        this.province = province;
        this.CAP = CAP;
        this.fullAddress = qualifier+" "+streetName+" "+civicNumber.toString()+" "+city+" "+province+" "+CAP.toString();
        this.phoneNumber = phoneNumber;
        this.url = url;
        this.type = type;
    }

    public String toString(){
        return "Name:" +name+ "\nqualifier:"+qualifier+"\nstreetName:"+streetName+"\ncivicNumber:"+civicNumber+"\ncity:"+
                city+"\nprovince:"+province+"\nCAP:"+CAP+"\nfullAddress:"+fullAddress+"\nphoneNumber:"+phoneNumber.toString()+
                "\nurl:"+url+"\ntype:"+type.toString();

    }

}
