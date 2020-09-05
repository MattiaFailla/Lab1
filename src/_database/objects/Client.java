package _database.objects;

import java.io.Serializable;

public class Client implements Serializable {
    public String name;
    public String surname;
    public String city;
    public String province;
    public String email;
    public String nickname;
    public String password;

    Client() {
    }

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