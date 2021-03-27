package com.iamshekhargh.averybasicapp.models.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Created by <<-- iamShekharGH -->>
 * on 25 March 2021
 * at 6:31 PM.
 */
@Entity(tableName = "user_table")
public class User {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "first_name")
    public String firstname;

    @ColumnInfo(name = "last_name")
    public String lastname;

    @ColumnInfo(name = "age")
    public int age;

    @ColumnInfo(name = "gen")
    public boolean gen;

    @ColumnInfo(name = "mobile_number")
    public String mobNo;

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGen(boolean gen) {
        this.gen = gen;
    }

    public void setMobNo(String mobNo) {
        this.mobNo = mobNo;
    }

    public int getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public int getAge() {
        return age;
    }

    public boolean isGen() {
        return gen;
    }

    public String getMobNo() {
        return mobNo;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", age=" + age +
                ", gen=" + gen +
                ", mobNo='" + mobNo + '\'' +
                '}';
    }
}
