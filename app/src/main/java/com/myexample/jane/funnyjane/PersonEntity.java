package com.myexample.jane.funnyjane;

import java.io.Serializable;

public class PersonEntity implements Serializable {
    private String name;
    private int sex;
    private int age;
    private boolean married;
    private boolean fullTime;
    private int score;

    public PersonEntity(String name, int sex, int age, boolean married, boolean fulltime) {
        this.name = (name == null || name.length() < 1) ? "Unnamed" : name;
        this.sex = sex;
        this.married = married;
        this.fullTime = fulltime;
        this.age = age;
        this.score = 0;
    }

    @Override
    public String toString() {
        return name + " " + sexString(sex) + " " + age + " " + (married ? "Married" : "Unmarried") + " " + (fullTime ? "FullTime" : "PartTime");
    }

    public int getSex() {
        return sex;
    }

    public String getName() {
        return name;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    private static String sexString(int sex) {
        switch (sex) {
            case Sex.FEMALE:
                return "Female";
            case Sex.MALE:
                return "Male";
            case Sex.UNKNOWN:
                return "Unknown";
            case Sex.UNDIFFERENTIATED:
            default:
                return "Undifferentiated";
        }
    }
}
