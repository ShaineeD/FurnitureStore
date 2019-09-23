package com.example.furniturestore;

public class Member {
    private String Name;
    private String Email;
    private String Password;
    private String Type;
    private String pushId;

    public Member() {
    }

    public String getName() { return Name; }

    public String getPushId() { return pushId; }

    public void setPushId(String pushId) { this.pushId = pushId; }

    public  void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }


}
