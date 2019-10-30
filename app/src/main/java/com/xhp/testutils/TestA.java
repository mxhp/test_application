package com.xhp.testutils;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class TestA implements Parcelable , Serializable {
    String name;
    String grade;
    int age;

    public TestA(String name, String grade, int age) {
        this.name = name;
        this.grade = grade;
        this.age = age;
    }

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

    @Override
    public String toString() {
        return "TestA{" +
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

    protected TestA(Parcel in) {
        this.name = in.readString();
        this.grade = in.readString();
        this.age = in.readInt();
    }

    public static final Parcelable.Creator<TestA> CREATOR = new Parcelable.Creator<TestA>() {
        @Override
        public TestA createFromParcel(Parcel source) {
            return new TestA(source);
        }

        @Override
        public TestA[] newArray(int size) {
            return new TestA[size];
        }
    };
}
