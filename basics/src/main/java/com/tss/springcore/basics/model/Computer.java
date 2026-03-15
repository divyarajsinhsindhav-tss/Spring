package com.tss.springcore.basics.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Computer {

    private String computerName;
    private Harddisk hardDisk;

    @Autowired
    public Computer(Harddisk hardDisk) {
        this.computerName = "Mac";
        this.hardDisk = hardDisk;
    }

    public String getComputerName() {
        return computerName;
    }

    public void setComputerName(String computerName) {
        this.computerName = computerName;
    }

    public Harddisk getHardDisk() {
        return hardDisk;
    }

    public void setHardDisk(Harddisk hardDisk) {
        this.hardDisk = hardDisk;
    }

    @Override
    public String toString() {
        return "Computer{" +
                "computerName='" + computerName + '\'' +
                ", hardDisk=" + hardDisk +
                '}';
    }
}
