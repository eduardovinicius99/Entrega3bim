package com.example.consultorio.data.model;

import java.util.Calendar;

public class Agenda {
    private String name;
    private Calendar time;
    private String doctor;
    private String phone;
    private String email;

    public Agenda(String name, Calendar time, String doctor, String phone, String email) {
        this.name = name;
        this.time = time;
        this.doctor = doctor;
        this.phone = phone;
        this.email = email;
    }

    public String getName() { return name; }
    public Calendar getTime() { return time; }
    public String getDoctor() { return doctor; }
    public String getPhone() { return phone; }
    public String getEmail() { return email; }

    public void setName(String name) { this.name = name; }
    public void setTime(Calendar time) { this.time = time; }
    public void setDoctor(String doctor) { this.doctor = doctor; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setEmail(String email) { this.email = email; }
}
