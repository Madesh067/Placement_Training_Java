

import java.util.*;

class Admin{
    static Scanner sc = new Scanner(System.in);
    String adminname;
    String adminpin;
    public static int approveMerchent(ArrayList<Merchant> x,String ans){
        int i=-1;
        int c=0;
        for(int v=0;v<x.size();v++){
            if(!x.get(v).status){
                System.out.println(v+" "+x.get(v).merchantname);
                c+=1;
            }
        }
        if(c>0){
            System.out.println("Enter the merchant You Want To "+ans);
            i=sc.nextInt();
            if((i<x.size())&&(i>=0)){
                return i;
            }
            else{
                return -1;
            }
        }else{
            return -1;
        }
    }
    public static int findmerchant(ArrayList<Merchant> x,String ans){
        Scanner sc = new Scanner(System.in);
        int i=-1;
        for(int v=0;v<x.size();v++){
            System.out.println(v+"-"+x.get(v).status);
        }
        System.out.println("Enter the merchant You Want To "+ans);
        i=sc.nextInt();
        if((i<x.size())&&(i>=0)){
            return i;
        }
        else{
            return -1;
        }
    }


}

class User{
    String username;
    String userpin;
    ArrayList<HashMap<String,String>> MyOrders = new ArrayList<>();
    ArrayList<HashMap<String,String>> Cart = new ArrayList<>();
    public static ArrayList<String> add2cart(ArrayList<Merchant> x){
        Scanner sc = new Scanner(System.in);
        ArrayList<String> ans = new ArrayList<>();
        for(int i=0;i<x.size();i++){
            if(x.get(i).status){
                for(int j=0;j<x.get(i).prdct.size();j++){
                    System.out.println(i+""+j+"-"+x.get(i).prdct.get(j));
                }
            }
        }
        System.out.println("Enter Your choice : ");
        String c = sc.next();
        int f=Character.getNumericValue(c.charAt(0));
        int s=Character.getNumericValue(c.charAt(1));
        if((f<x.size())&&(f>=0)&&(s<x.get(f).prdct.size())&&(s>=0)){
            ans.add(Integer.toString(f));
            ans.add(Integer.toString(s));
            return ans;
        }
        else{
            ans.add(null);
            return ans;
        }

    }
    public static ArrayList<String> buyfromcart(ArrayList<HashMap<String,String>> x){
        Scanner sc = new Scanner(System.in);
        ArrayList<String> ans = new ArrayList<>();
        for(int i=0;i<x.size();i++){
            System.out.println(i+"-"+x.get(0));
        }
        System.out.println("Enter Your choice : ");
        int c = sc.nextInt();
        if((c<x.size())&&(c>=0)){
            ans.add(Integer.toString(c));
            ans.add(x.get(c).toString());
            return ans;
        }
        else{
            ans.add(null);
            return ans;
        }

    }

}

class Merchant{
    String merchantname;
    String merchantpin;
    ArrayList<HashMap<String,String>> prdct= new ArrayList<>();
    ArrayList<HashMap<HashMap<String,String>,String>> SalesReport = new ArrayList<>();
    Boolean status =false;
    public static HashMap<String,String> addproduct(String merchant,ArrayList<String> c){
        Scanner sc = new Scanner(System.in);
        HashMap<String,String> x = new HashMap<>();
        x.put("Merchant",merchant);
        System.out.println("Enter Product Name");
        x.put("name",sc.next());
        System.out.println("Enter Price");
        x.put("price",sc.next());
        for(int i=0;i<c.size();i++){
            System.out.println(i+" "+c.get(i));
        }
        System.out.println("Enter Category");
        int v = sc.nextInt();
        if((v>=0)&&(v<c.size())){
            x.put("category",c.get(v));
            return x;
        }
        else{
            x.put("category", null);
            return x;
        }
    }
    public static ArrayList<String> updateproduct(ArrayList<HashMap<String,String>> x){
        Scanner sc = new Scanner(System.in);
        ArrayList<String> ans = new ArrayList<>();
        for(int i=0;i<x.size();i++){
            System.out.println(i+"-"+x.get(i));
        }
        System.out.println("Enter your Choice");
        int pr = sc.nextInt();
        ans.add(Integer.toString(pr));
        System.out.println("0-name");
        System.out.println("1-price");
        System.out.println("2-Exit");
        int ch = sc.nextInt();
        switch(ch){
            case 0:ans.add("name");
                break;
            case 1:ans.add("price");
                break;
            default:ans.add(null);
        }
        System.out.println("Enter what it needs to be changed : ");
        String val = sc.next();
        ans.add(val);
        return ans;
    }
}



class Main {
    static Scanner sc = new Scanner(System.in);

    public static User createuser() {
        Scanner sc = new Scanner(System.in);
        User x = new User();
        System.out.print("Enter Your name : ");
        x.username = sc.next();
        System.out.println("Enter Your Password : ");
        x.userpin = sc.next();
        return x;

    }




    public static int valid_admin(ArrayList<Admin> x) {
        int val = -1;
        System.out.println("Enter admin name: ");
        String name = sc.next();
        System.out.println("Enter admin pin: ");
        String pin = sc.next();
        for (int i = 0; i < x.size(); i++) {
            if ((x.get(i).adminname.equals(name)) && (x.get(i).adminpin.equals(pin))) {
                return val = i;
            }
        }
        return val;
    }

    public static int valid_merchant(ArrayList<Merchant> x) {
        int val = -1;
        System.out.println("Enter Name : ");
        String name = sc.next();
        System.out.println("Enter password : ");
        String pin = sc.next();
        for (int i = 0; i < x.size(); i++) {
            if ((x.get(i).merchantname.equals(name)) && (x.get(i).merchantpin.equals(pin))) {
                val = i;
            }
        }
        return val;
    }

    public static Merchant createmerchant() {
        Scanner sc = new Scanner(System.in);
        Merchant x = new Merchant();
        System.out.print("Enter Your name : ");
        x.merchantname = sc.next();
        System.out.println("Enter Your Password : ");
        x.merchantpin = sc.next();
        x.status = false;
        return x;
    }
    public static int valid_user(ArrayList<User> x) {
        int val = -1;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Name : ");
        String name = sc.next();
        System.out.println("Enter password : ");
        String password = sc.next();
        for (int i = 0; i < x.size(); i++) {
            if ((x.get(i).username.equals(name)) && (x.get(i).userpin.equals(password))) {
                val = i;
            }
        }
        return val;
    }


    public static void main(String[] args) {
        Admin a1 = new Admin();
        Admin a2 = new Admin();
        ArrayList<Admin> admin = new ArrayList<>();
        a1.adminname = "admin1";
        a1.adminpin = "a111";
        a2.adminname = "admin2";
        a2.adminpin = "a222";
        admin.add(a1);
        admin.add(a2);

        Merchant m1 = new Merchant();
        ArrayList<Merchant> merchant_list = new ArrayList<>();
        m1.merchantname = "apple";
        m1.merchantpin = "m111";
        m1.status = true;
        HashMap<String, String> x = new HashMap<>();
        x.put("name", "iphone");
        x.put("price", "10000");
        x.put("category", "Electronics");
        x.put("Merchant", "apple");
        HashMap<String, String> y = new HashMap<>();
        y.put("name", "smartwatch");
        y.put("price", "15000");
        y.put("category", "Electronics");
        y.put("Merchant", "apple");
        m1.prdct.add(x);
        m1.prdct.add(y);
        merchant_list.add(m1);

        ArrayList<String> category = new ArrayList<>();
        category.add("Electronics");

        User u1 = new User();
        u1.username = "user1";
        u1.userpin = "u111";
        ArrayList<User> user_list = new ArrayList<>();
        user_list.add(u1);


        Boolean whole_exit = false;
        while (!whole_exit) {
            System.out.println("1-Admin");
            System.out.println("2-Merchant");
            System.out.println("3-User");
            System.out.println("4-Exit");
            System.out.println("Enter your choice : ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1: {
                    int i = valid_admin(admin);
                    if (i != -1) {
                        Boolean admin_exit = false;
                        while (!admin_exit) {
                            System.out.println("1-Approve merchants");
                            System.out.println("2-Remove merchants");
                            System.out.println("3-View Products");
                            System.out.println("4-Add category");
                            System.out.println("5-Exit");
                            System.out.println("Enter Your Choice : ");
                            int choice2 = sc.nextInt();
                            switch (choice2) {
                                case 1:
                                    int merapp = admin.get(i).approveMerchent(merchant_list, "Approve");
                                    if ((merapp != -1) && (!merchant_list.get(merapp).status)) {
                                        merchant_list.get(merapp).status = true;
                                    } else {
                                        System.out.println("Merchant Approved already");
                                    }
                                    break;
                                case 2:
                                    int removemerchant = admin.get(i).findmerchant(merchant_list, "remove");
                                    if (removemerchant != -1) {
                                        merchant_list.remove(removemerchant);
                                    } else {
                                        System.out.println("Invalid Option");
                                    }
                                    break;
                                case 3:
                                    for (int v = 0; v < merchant_list.size(); v++) {
                                        System.out.println(merchant_list.get(v).prdct);
                                    }
                                    break;
                                case 4:
                                    System.out.println("Enter the category You want to add : ");
                                    String cat = sc.next();
                                    category.add(cat);
                                    break;
                                case 5:
                                    admin_exit = true;
                                    break;
                                default:
                                    System.out.println("Enter a valid choice : ");
                            }

                        }
                    }
                }
                case 2:
                    System.out.println("1-New Merchant");
                    System.out.println("2-Existing Merchant");
                    System.out.println("3-Exit");
                    System.out.println("Enter Your Choice : ");
                    int merchantchoice = sc.nextInt();
                    switch (merchantchoice) {
                        case 1:
                            merchant_list.add(createmerchant());
                            System.out.println("Registered Successfully");
                            System.out.println("--Waiting For Approval--");
                            break;
                        case 2:
                            int m = valid_merchant(merchant_list);
                            if (m != -1) {
                                Boolean merchant_exit = false;
                                while (!merchant_exit) {
                                    System.out.println("1-Add products");
                                    System.out.println("2-Update products");
                                    System.out.println("3-View Products");
                                    System.out.println("4-View sales report");
                                    System.out.println("5-Exit");
                                    System.out.println("Enter Your Choice : ");
                                    int mec = sc.nextInt();
                                    switch (mec) {
                                        case 1:
                                            HashMap<String, String> np = merchant_list.get(m).addproduct(merchant_list.get(m).merchantname, category);
                                            merchant_list.get(m).prdct.add(np);
                                            break;
                                        case 2:
                                            ArrayList<String> updp = merchant_list.get(m).updateproduct(merchant_list.get(m).prdct);
                                            if (updp.get(0) != null) {
                                                merchant_list.get(m).prdct.get(Integer.parseInt(updp.get(0))).replace(updp.get(1), updp.get(2));
                                            }
                                            break;
                                        case 3:
                                            System.out.println(merchant_list.get(m).prdct);
                                        case 4:
                                            System.out.println(merchant_list.get(m).SalesReport);
                                            break;
                                        case 5:
                                            merchant_exit = true;

                                    }
                                }
                            }
                        case 3:
                            break;
                    }
                case 3:
                    System.out.println("1-New User");
                    System.out.println("2-Existing User");
                    System.out.println("3-Exit");
                    System.out.println("Enter Your Choice : ");
                    int fup = sc.nextInt();
                    switch (fup) {
                        case 1:
                            user_list.add(createuser());
                            System.out.println("Registered Successfully");
                            break;
                        case 2:
                        int u = valid_user(user_list);
                        if (u != -1) {
                            Boolean user_exit = false;
                            while (!user_exit) {
                                System.out.println("1-View products");
                                System.out.println("2-Add to cart");
                                System.out.println("3-Buy From Cart");
                                System.out.println("4-My Orders");
                                System.out.println("5-Exit");
                                System.out.println("Enter Your Choice : ");
                                int userchoice = sc.nextInt();
                                switch (userchoice) {
                                    case 1:
                                        for (int uvp = 0; uvp < merchant_list.size(); uvp++) {
                                            if (merchant_list.get(uvp).status) {
                                                System.out.println(merchant_list.get(uvp).prdct);
                                            }
                                        }
                                        break;
                                    case 2:
                                        ArrayList<String> a2c = user_list.get(u).add2cart(merchant_list);
                                        if (a2c.get(0) != null) {
                                            user_list.get(u).Cart.add(merchant_list.get(Integer.parseInt(a2c.get(0))).prdct.get(Integer.parseInt(a2c.get(1))));
                                        }
                                        break;
                                    case 3:ArrayList<String> bfc = user_list.get(u).buyfromcart(user_list.get(u).Cart);
                                        if(bfc!=null){
                                            user_list.get(u).MyOrders.add(user_list.get(u).Cart.get(Integer.parseInt(bfc.get(0))));
                                            int tm=-1;
                                            for(int fm =0;fm<merchant_list.size();fm++){
                                                if (user_list.get(u).MyOrders.get(user_list.get(u).MyOrders.size()-1).get("Merchant")==merchant_list.get(fm).merchantname){
                                                    tm=fm;
                                                }
                                            }
                                            HashMap<String,String> cv = user_list.get(u).MyOrders.get(user_list.get(u).MyOrders.size()-1);
                                            HashMap<HashMap<String,String>,String> h = new HashMap<>();
                                            h.put(cv,user_list.get(u).username);
                                            merchant_list.get(tm).SalesReport.add(h);
                                        }
                                        break;
                                    case 4:System.out.println(user_list.get(u).MyOrders);
                                        break;
                                    case 5:user_exit=true;
                                }
                            }
                        }
                    }
                    break;
                case 4:whole_exit=true;
                    break;
                default:System.out.println("Enter a valid choice");
            }
        }
    }
}
