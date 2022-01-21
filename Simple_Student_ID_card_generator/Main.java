package com.internshala.javaapp;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
	Scanner sc = new Scanner(System.in);
    System.out.println("Enter your name : ");
    String stuName = sc.nextLine();
    System.out.println("Enter yor age : ");
    int stuAge  =sc.nextInt();
    System.out.println("Enter your Blood Group : ");
    String stuBlood = sc.next();
    String msg = null;

    if(stuAge <20){
        if(stuAge>=15){
            msg = "Your group is BLUE";
        }
        else if(stuAge>=10 && stuAge<15){
            msg  = "Your group is YELLOW";
        }
    }
    else{
        msg = "Your group is RED";
    }

    System.out.println("------------------------");
    System.out.println("Name : "+stuName);
    System.out.println("Age : "+stuAge);
    System.out.println("Blood Group : "+stuBlood);
    System.out.println("------------------------");
    System.out.println((String)msg);
    System.out.println("------------------------");
    sc.close();
    }
}
