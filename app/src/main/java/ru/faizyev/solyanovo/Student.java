package ru.faizyev.solyanovo;

public class Student {

    private String name;
    private String surname;
    private Gender gender;



    private int avatar;

    public Student(String name, String surname, Gender gender, int avatar) {
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.avatar = avatar;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }
}