package _database.objects;

import java.io.Serializable;

public class Client implements Serializable {
    private String name;
    private String surname;
    private String city;
    private String province;
    private String email;
    private String nickname;
    private String password;

    Client(){}

    public Client(String name, String surname, String city, String province, String email, String nickname, String password) {
        this.name = name;
        this.surname = surname;
        this.city = city;
        this.province = province;
        this.email = email;
        this.nickname = nickname;
        this.password = password;
    }

    public String toString(){
        return  "Name: " + name +
                "\nSurname: " + surname +
                "\nCity: " + city +
                "\nProvince: " + province +
                "\nEmail: " + email +
                "\nNickname: " + nickname;
    }

    public String getPassword() {
        return this.password;
    }
}