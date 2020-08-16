package database.objects;

import java.io.Serializable;

public class Client implements Serializable {
    private String name;
    private String surname;
    private String cityName;
    private String province;
    private String emailAddress;
    private String nickName;
    private String password;

    Client(){}

    public Client(String name, String surname, String cityName, String province, String emailAddress, String nickName, String password){
        this.name = name;
        this.surname = surname;
        this.cityName = cityName;
        this.province = province;
        this.emailAddress = emailAddress;
        this.nickName = nickName;
        this.password = password;
    }

    public String toString(){
        return "Name:"+name+"\nsurname:"+surname+"\ncityName:"+cityName+"\nprovince:"+province+
                "\nemailAddress:"+emailAddress+"\nnickName:"+nickName;
    }


    public String getPassword() {
        return this.password;
    }
}
