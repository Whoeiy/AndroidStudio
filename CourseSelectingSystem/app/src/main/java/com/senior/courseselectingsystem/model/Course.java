package com.senior.courseselectingsystem.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Course implements Parcelable {

    private String num;
    private String name;
    private String descrip;
    private String teacher;
    private int uplimit;
    private int chosen;

    public Course(String num, String name, String descrip, String teacher, int uplimit, int chosen) {
        this.num = num;
        this.name = name;
        this.descrip = descrip;
        this.teacher = teacher;
        this.uplimit = uplimit;
        this.chosen = chosen;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public int getUplimit() {
        return uplimit;
    }

    public void setUplimit(int uplimit) {
        this.uplimit = uplimit;
    }

    public int getChosen() {
        return chosen;
    }

    public void setChosen(int chosen) {
        this.chosen = chosen;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        // 1.必须按成员变量声明的顺序封装数据，不然会出现获取数据出错
        // 2.序列化对象
        parcel.writeString(name);
        parcel.writeString(descrip);
        parcel.writeString(teacher);
        parcel.writeInt(uplimit);
        parcel.writeInt(chosen);
    }

    // 1.必须实现Parcelable.Creator接口,否则在获取Person数据的时候，会报错，如下：
    // android.os.BadParcelableException:
    // Parcelable protocol requires a Parcelable.Creator object called  CREATOR on class com.um.demo.Person
    // 2.这个接口实现了从Percel容器读取Person数据，并返回Person对象给逻辑层使用
    // 3.实现Parcelable.Creator接口对象名必须为CREATOR，不如同样会报错上面所提到的错；
    // 4.在读取Parcel容器里的数据事，必须按成员变量声明的顺序读取数据，不然会出现获取数据出错
    // 5.反序列化对象
    public static final Parcelable.Creator<Course> CREATOR = new Creator(){
        @Override
        public Course createFromParcel(Parcel parcel) {
            // 必须按成员变量声明的顺序读取数据，不然会出现获取数据出错
            Course course = new Course(parcel.readString(), parcel.readString(),parcel.readString(),parcel.readString(),parcel.readInt(),parcel.readInt());
            course.setNum(parcel.readString());
            course.setName(parcel.readString());
            course.setDescrip(parcel.readString());
            course.setTeacher(parcel.readString());
            course.setUplimit(parcel.readInt());
            course.setChosen(parcel.readInt());
            return course;
        }

        @Override
        public Course[] newArray(int i) {
            return new Course[i];
        }
    };
}
