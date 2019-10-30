package com.xhp.testutils;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class TestB implements Parcelable , Serializable {
    String name;
    String grade;
    int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public TestB(String name, String grade, int age) {
        this.name = name;
        this.grade = grade;
        this.age = age;
    }

    @Override
    public String toString() {
        return "TestB{" +
                "name='" + name + '\'' +
                ", grade='" + grade + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.grade);
        dest.writeInt(this.age);
    }

    protected TestB(Parcel in) {
        this.name = in.readString();
        this.grade = in.readString();
        this.age = in.readInt();
    }

    public static final Parcelable.Creator<TestB> CREATOR = new Parcelable.Creator<TestB>() {
        @Override
        public TestB createFromParcel(Parcel source) {
            return new TestB(source);
        }

        @Override
        public TestB[] newArray(int size) {
            return new TestB[size];
        }
    };
}
