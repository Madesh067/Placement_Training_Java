package com.internshala.javaapp;

import java.text.SimpleDateFormat;
import java.util.*;

class Admin{
    String name;
    String Password;

}

class Car{
    int ID;
    int NumberOfcar_list;
    ArrayList<Booked> timeline = new ArrayList<>();
    int CostPerHour;
}
class Booked{
    Date pickup;
    Date drop;
    String user;
}


class Bill{
    Date pickup;
    Date drop;
    int ID;
    int amt;
}

class User{
    String name;
    String Password;
    ArrayList<Bill> History = new ArrayList<>();
}

public class Main{
    static Scanner sc = new Scanner(System.in);
    public static long avail_date(Car x,String spickup,String sdrop) throws Exception{
        SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        Date pickup = f.parse(spickup);
        Date drop = f.parse(sdrop);
        long res = -1;
        Boolean y = false;
        if(x.timeline.size()!=0){
            for(int i=0;i<x.timeline.size();i++){
                if(pickup.before(x.timeline.get(i).pickup)&&drop.before(x.timeline.get(i).pickup)||pickup.after(x.timeline.get(i).drop)&&drop.after(x.timeline.get(i).drop)){
                    y=true;
                }
            }
        }
        else{
            res=x.CostPerHour*(drop.getTime()-pickup.getTime())/3600000;
        }
        if(y){
            res=x.CostPerHour*(drop.getTime()-pickup.getTime())/3600000;
        }
        return res;
    }

    public static ArrayList<Integer> edit_car(ArrayList<Car> x){
        ArrayList<Integer> n = new ArrayList<>();
        for(int i=0;i<x.size();i++){
            System.out.println(i+"-"+x.get(i).ID);
        }
        System.out.println("Choose the car to edit : ");
        int ch = sc.nextInt();
        if(ch>=0 && ch<x.size()){
            System.out.println("0-Number Of Seats");
            System.out.println("1-Cost Per Hour");
            System.out.println("Choose The option You Should Edit : ");
            n.add(ch);
            int opt = sc.nextInt();
            if(opt<2&&opt>-1){
                System.out.println("Enter what it needs to be : ");
                int val = sc.nextInt();
                n.add(opt);
                n.add(val);
            }
            else{
                n.add(-1);
            }
        }
        else{
            n.add(-1);
        }
        return n;
    }
    public static int valid_user(ArrayList<User> x){
        int res=-1;
        System.out.println("Enter your name : ");
        String name = sc.next();
        System.out.println("Enter your Password : ");
        String Password = sc.next();
        for(int i=0;i<x.size();i++){
            if(x.get(i).name.equals(name)&&x.get(i).Password.equals(Password)){
                res =i;
            }
        }
        return res;
    }
    public static int valid_admin(ArrayList<Admin> x){
        int res=-1;
        System.out.println("Enter your name : ");
        String name = sc.next();
        System.out.println("Enter your Password : ");
        String Password = sc.next();
        for(int i=0;i<x.size();i++){
            if(x.get(i).name.equals(name)&&x.get(i).Password.equals(Password)){
                res =i;
            }
        }
        return res;
    }
    public static User new_User(){

        User n = new User();
        System.out.println("Enter Your Name : ");
        String uname = sc.next();

        System.out.println("Enter Your Password : ");
        String Password = sc.next();
        n.name =uname;
        n.Password = Password;
        return n;
    }
    public static Car add_car(ArrayList<Car> x){
        Car c = new Car();
        System.out.println("Enter Car ID : ");
        int ID = sc.nextInt();
        Boolean ch = true;
        for(int i=0;i<x.size();i++){
            if(x.get(i).ID==ID){
                ch=false;
            }
        }
        System.out.println("Enter No. of Seats : ");
        int NumberOfcar_list = sc.nextInt();
        System.out.println("Enter Cost Per Hour : ");
        int CostPerHour = sc.nextInt();
        c.NumberOfcar_list = NumberOfcar_list;
        c.CostPerHour = CostPerHour;
        if(ch){
            c.ID = ID;
        }
        else{
            c.ID=-1;
        }
        return c;
    }
    public static int delete_car(ArrayList<Car> x){
        int res=-1;
        for(int i=0;i<x.size();i++){
            System.out.println(i+"- Car ID : "+x.get(i).ID);
        }
        int val = sc.nextInt();
        if(val<x.size()&&val>0){
            res=val;
        }
        return res;
    }
    public static void main(String[] args) throws Exception {
        SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        ArrayList<User> user_list = new ArrayList<>();
        ArrayList<Car> car_list = new ArrayList<>();
        ArrayList<Admin> admins = new ArrayList<>();
        User u1 = new User();
        User u2 = new User();
        u1.name = "user1";
        u1.Password = "1111";
        u2.name = "user2";
        u2.Password = "2222";
        user_list.add(u1);
        user_list.add(u2);
        Car c1 = new Car();
        Car c2 = new Car();
        c1.ID=1;
        c2.ID=2;
        c1.NumberOfcar_list = 5;
        c2.NumberOfcar_list = 8;
        c1.CostPerHour = 200;
        c2.CostPerHour = 500;
        car_list.add(c1);
        car_list.add(c2);
        Admin a1 = new Admin();
        a1.name = "admin1";
        a1.Password = "1111";
        admins.add(a1);
        Boolean whole_exit = false;
        while(!whole_exit){
            System.out.println("1-Admin");
            System.out.println("2-User");
            System.out.println("3-Exit");
            System.out.println("Enter Your Choice : ");
            int ch = sc.nextInt();
            switch(ch){
                case 1:int al = valid_admin(admins);
                    if(al!=-1){
                        Boolean admin_exit = false;
                        while(!admin_exit){
                            System.out.println("1-Add car_list");
                            System.out.println("2-Delete car_list");
                            System.out.println("3-Edit Car Details");
                            System.out.println("4-View Booked car_list");
                            System.out.println("5-View All car_list");
                            System.out.println("6-LogOut");
                            System.out.println("Enter Your Choice : ");
                            int ach = sc.nextInt();
                            switch(ach){
                                case 1: Car k = add_car(car_list);
                                    System.out.println("Car Added Successfully");
                                    if(k.ID!=-1){
                                        car_list.add(k);
                                    }
                                    else{
                                        System.out.println("ID is not available");
                                    }
                                    break;
                                case 2:int dc = delete_car(car_list);
                                    if(dc!=-1){
                                        car_list.remove(dc);
                                    }
                                    else{
                                        System.out.println("Select a corre number");
                                    }
                                    break;
                                case 3:ArrayList<Integer> ecar = edit_car(car_list);
                                    if(ecar.get(0)!=-1){
                                        if(ecar.get(1)!=-1){
                                            if(ecar.get(1)==0){
                                                car_list.get(ecar.get(0)).NumberOfcar_list=ecar.get(2);
                                            }
                                            else{
                                                car_list.get(ecar.get(0)).CostPerHour=ecar.get(2);
                                            }
                                        }
                                        else{
                                            System.out.println("Enter a corre Option");
                                        }
                                    }
                                    else{
                                        System.out.println("Enter a corre option");
                                    }
                                    break;
                                case 4:for(int i =0;i<car_list.size();i++){
                                    System.out.println("Car ID : "+car_list.get(i).ID);
                                    for(int j=0;j<car_list.get(i).timeline.size();j++){
                                        System.out.println("Booked By : "+car_list.get(i).timeline.get(j).user);
                                        System.out.println("Pickup time : "+car_list.get(i).timeline.get(j).pickup);
                                        System.out.println("Drop Time : "+car_list.get(i).timeline.get(j).drop);
                                        System.out.println("----------------------------------------");
                                    }
                                    System.out.println("----------------------------------------");
                                }
                                    break;
                                case 5:for(int i=0;i<car_list.size();i++){
                                    System.out.println("car ID : "+car_list.get(i).ID);
                                    System.out.println("Avail. Seats : "+car_list.get(i).NumberOfcar_list);
                                    System.out.println("Cost Per Hour : "+car_list.get(i).CostPerHour);
                                    System.out.println("----------------------");
                                }
                                    break;
                                case 6:admin_exit = true;
                                    break;
                                default:System.out.println("Enter a corre Option");
                            }
                        }
                    }
                    else{
                        System.out.println("Enter  details do no match");
                    }
                    break;
                case 2:System.out.println("1-New User");
                    System.out.println("2-Existing User");
                    System.out.println("3-Exit");
                    System.out.println("Enter Your Choice : ");
                    int url = sc.nextInt();
                    switch(url){
                        case 1:user_list.add(new_User());
                            break;
                        case 2:int ul = valid_user(user_list);
                            if(ul!=-1){
                                Boolean user_exit=false;
                                while(!user_exit){
                                    System.out.println("1-Book a car");
                                    System.out.println("2-Cancel Booking");
                                    System.out.println("3-View Booked car_list");
                                    System.out.println("4-logout");
                                    System.out.println("Enter Your Choice : ");
                                    int uch = sc.nextInt();
                                    switch(uch){
                                        case 1:for(int i=0;i<car_list.size();i++){
                                            System.out.println("car ID : "+car_list.get(i).ID);
                                            System.out.println("Avail. Seats : "+car_list.get(i).NumberOfcar_list);
                                            System.out.println("Cost Per Hour : "+car_list.get(i).CostPerHour);
                                            System.out.println("----------------------");
                                        }
                                            System.out.println("Enter The car ID You want to book : ");
                                            int bcID = sc.nextInt();
                                            int indexce=-1;
                                            for(int i=0;i<car_list.size();i++){
                                                if(car_list.get(i).ID==bcID){
                                                    indexce=i;
                                                }
                                            }
                                            if(indexce!=-1){
                                                System.out.println("Enter the Day You Want to Pickup the car : in (dd-mm-yyyy)");
                                                String pdate  = sc.next();
                                                System.out.println("Enter the time You Want to Pickup the car : in (HH:MM)");
                                                String ptime  = sc.next();
                                                String ppickup =pdate+" "+ptime;
                                                System.out.println("Enter the Day You Want to drop the car : in (dd-mm-yyyy)");
                                                String ddate  = sc.next();
                                                System.out.println("Enter the time You Want to drop the car : in (HH:MM)");
                                                String dtime  = sc.next();
                                                String ddrop = ddate+" "+dtime;
                                                long ad = avail_date(car_list.get(indexce),ppickup,ddrop);
                                                if(ad!=-1){
                                                    System.out.println("Total Amount is : "+ad);
                                                    System.out.println("Do You Wish to book ?");
                                                    System.out.println("Press 1 to confirm");
                                                    int b = sc.nextInt();
                                                    if(b==1){
                                                        Date pickup = f.parse(ppickup);
                                                        Date drop = f.parse(ddrop);
                                                        Booked bk = new Booked();
                                                        bk.pickup = pickup;
                                                        bk.drop  = drop;
                                                        bk.user = user_list.get(ul).name;
                                                        car_list.get(indexce).timeline.add(bk);
                                                        Bill bill = new Bill();
                                                        bill.amt = (int)ad;
                                                        bill.pickup = pickup;
                                                        bill.drop = drop;
                                                        bill.ID = car_list.get(indexce).ID;
                                                        user_list.get(ul).History.add(bill);
                                                        System.out.println("Booked Successfully");
                                                    }
                                                    else{
                                                        System.out.println("Booking Cancelled By User");
                                                    }
                                                }else{
                                                    System.out.println("Car Not Available");
                                                }
                                            }
                                            else{
                                                System.out.println("Incorrect ID Entered");
                                            }
                                            break;
                                        case 2:for(int i=0;i<user_list.get(ul).History.size();i++){
                                            System.out.println("Car ID : "+user_list.get(ul).History.get(i).ID);
                                            System.out.println("Pickup Time : "+user_list.get(ul).History.get(i).pickup);
                                            System.out.println("Drop Time : "+user_list.get(ul).History.get(i).drop);
                                            System.out.println("Total Amount : "+user_list.get(ul).History.get(i).amt);
                                        }
                                            System.out.println("Enter The car ID You want to book : ");
                                            int caID = sc.nextInt();
                                            int index=-1;
                                            for(int i=0;i<car_list.size();i++){
                                                if(car_list.get(i).ID==caID){
                                                    index=i;
                                                }
                                            }
                                            int tailindex=-1;
                                            for(int i=0;i<car_list.get(index).timeline.size();i++){
                                                if(car_list.get(index).timeline.get(i).user==user_list.get(ul).name){
                                                    tailindex=i;
                                                }
                                            }
                                            int highindex =-1;
                                            for(int i=0;i<user_list.get(ul).History.size();i++){
                                                if(user_list.get(ul).History.get(i).ID==caID){
                                                    highindex = i;
                                                }
                                            }
                                            user_list.get(ul).History.remove(highindex);
                                            car_list.get(index).timeline.remove(tailindex);


                                            break;
                                        case 3:for(int i=0;i<user_list.get(ul).History.size();i++){
                                            System.out.println("Car ID : "+user_list.get(ul).History.get(i).ID);
                                            System.out.println("Pickup Time : "+user_list.get(ul).History.get(i).pickup);
                                            System.out.println("Drop Time : "+user_list.get(ul).History.get(i).drop);
                                            System.out.println("Total Amount : "+user_list.get(ul).History.get(i).amt);
                                        }
                                            break;
                                        case 4:user_exit=true;
                                            break;
                                        default:System.out.println("Choose a corre Option");
                                    }
                                }
                            }System.out.println("Entered details do no Match");
                    }
                    break;
                case 3:whole_exit=true;
                    break;
                default:System.out.println("Enter a corre Option");
            }
        }
        System.out.println("Thank you");
    }
}