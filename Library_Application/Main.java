import java.time.temporal.ChronoUnit;
import java.time.LocalTime;
import java.util.*;

class Admin{
    static Scanner sc = new Scanner(System.in);
    String adminname;
    String adminpin;
}

class User{
    String username;
    String userpin;
    int money =1500;
    ArrayList<Books> cart = new ArrayList<>();
    ArrayList<LocalTime> borrowtime = new ArrayList<>();
}

class Books{
    String name;
    int ISBN;
    int count;
    int amount;
}



public class Main {
    static Scanner sc = new Scanner(System.in);

    //............................................................................................
    public static int removefromcart(ArrayList<Books> x) {
        System.out.println("Select the Bok You want to return : ");
        Scanner sc = new Scanner(System.in);
        int res = -1;
        for (int i = 0; i < x.size(); i++) {
            System.out.println(i + "-" + x.get(i).name);
        }
        int v = sc.nextInt();
        if ((v >= 0) && (v < x.size())) {
            res = v;
        }
        return res;
    }

    //.......................................................................................
    public static int add2cart(ArrayList<Books> x) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Select the book you want to borrow : ");
        int res = -1;
        for (int i = 0; i < x.size(); i++) {
            System.out.println(i + "-" + x.get(i).name);
        }
        int v = sc.nextInt();
        if ((v >= 0) && (v < x.size())) {
            res = v;
        }
        return res;
    }

    //.......................................................................
    public static int removebook(ArrayList<Books> x) {
        for (int i = 0; i < x.size(); i++) {
            System.out.println(i + "-" + x.get(i).name);
        }
        System.out.println("Enter the book you want to remove : ");
        int rb = sc.nextInt();
        if ((rb < x.size()) && (rb >= 0)) {
            return rb;
        } else {
            return -1;
        }
    }

    //....................................................................................................
    public static Books addbook(ArrayList<Books> x) {
        Books b = new Books();
        System.out.println("Enter book name : ");
        b.name = sc.nextLine();
        System.out.println("Enter book ISBN number : ");
        int ISBN = sc.nextInt();
        Boolean is = true;
        for (int i = 0; i < x.size(); i++) {
            if (ISBN == x.get(i).ISBN) {
                is = false;
            }
        }
        if (is) {
            b.ISBN = ISBN;
        } else {
            b.ISBN = -1;
        }
        System.out.println("Enter book count : ");
        b.count = sc.nextInt();
        return b;
    }
//...............................................................................................

    public static int valid_admin(ArrayList<Admin> x) {
        int val = -1;
        System.out.println("Enter admin login email: ");
        String name = sc.next();
        System.out.println("Enter admin login password");
        String pin = sc.next();
        for (int i = 0; i < x.size(); i++) {
            if (x.get(i).adminname.equals(name) && x.get(i).adminpin.equals(pin)) {
                val = i;
            }
        }
        return val;
    }

    //..................................................................................................
    public static int valid_user(ArrayList<User> x) {
        int val = -1;
        System.out.println("Enter your emailId: ");
        String name = sc.next();
        System.out.println("Enter your password: ");
        String pin = sc.next();
        for (int i = 0; i < x.size(); i++) {
            if (x.get(i).username.equals(name) && x.get(i).userpin.equals(pin)) {
                val = i;
            }
        }
        return val;
    }
//.....................................................................................................

    public static void main(String[] args) {
        // write your code here
        ArrayList<User> user_list = new ArrayList<>();
        User u1 = new User();
        User u2 = new User();
        u1.username = "user1@gmail.com";
        u1.userpin = "u111";
        u2.username = "user2@gmail.com";
        u2.userpin = "u222";
        user_list.add(u1);
        user_list.add(u2);
//.............................................................................
        ArrayList<Admin> admin_list = new ArrayList<>();
        Admin a = new Admin();
        Admin a1 = new Admin();
        Admin a2 = new Admin();
        a1.adminname = "admin1@gmail.com";
        a1.adminpin = "a111";
        a2.adminname = "admin2@gmail.com";
        a2.adminpin = "a222";
        admin_list.add(a1);
        admin_list.add(a2);
//..............................................................................
        ArrayList<Books> book_list = new ArrayList<>();
        Books b1 = new Books();
        b1.name = "Harry Potter";
        b1.ISBN = 01;
        b1.count = 10;
        b1.amount = 250;
        book_list.add(b1);
//..............................................................................

        Boolean whole_exit = false;
        while (!whole_exit) {
            System.out.println("1-Admin");
            System.out.println("2-User");
            System.out.println("3-Exit");
            System.out.println("Enter Your Choice : ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1: {
                    int val = valid_admin(admin_list);
                    if (val != -1) {
                        Boolean admin_exit = false;
                        while (!admin_exit) {
                            System.out.println("1-Add book");
                            System.out.println("2-remove book");
                            System.out.println("3-Update book");
                            System.out.println("4-View books");
                            System.out.println("5-Show Borrowed Book List");
                            System.out.println("6-LogOut");
                            System.out.println("Enter Your Choice : ");
                            int adminchoice = sc.nextInt();
                            switch (adminchoice) {
                                case 1: {
                                    Books addb = addbook(book_list);
                                    if (addb.ISBN != -1) {
                                        book_list.add(addb);
                                        System.out.println("Book Added Successfully");
                                    } else {
                                        System.out.println("Something Went Wrong");
                                    }
                                }
                                break;
                                case 2: {
                                    book_list.remove(removebook(book_list));
                                }
                                break;
                                case 3: {
                                    for (int i = 0; i < book_list.size(); i++) {
                                        System.out.println(i + "." + book_list.get(i).name);
                                    }
                                    System.out.println("Enter the book you want to edit : ");
                                    int editbook = sc.nextInt();
                                    if ((editbook < book_list.size()) && (editbook >= 0)) {
                                        System.out.println("0-name");
                                        System.out.println("2-count");
                                        System.out.println("Enter Your Choice");
                                        int c = sc.nextInt();
                                        if ((c >= 0) && (c < 2)) {
                                            if (c == 0) {
                                                book_list.get(editbook).name = sc.next();
                                            } else {
                                                book_list.get(editbook).count = sc.nextInt();
                                            }
                                        } else {
                                            System.out.println("Enter a valid choice");
                                        }
                                    } else {
                                        System.out.println("Enter a valid book.");
                                    }
                                }
                                break;
                                case 4: {
                                    for (int i = 0; i < book_list.size(); i++) {
                                        System.out.println("Name : " + book_list.get(i).name);
                                        System.out.println("ISBN : " + book_list.get(i).ISBN);
                                        System.out.println("Count : " + book_list.get(i).count);
                                        System.out.println("Cost : " + book_list.get(i).amount);
                                    }
                                }
                                break;
                                case 5: {

                                }
                                break;
                                case 6:
                                    admin_exit = true;
                                    break;
                                default:
                                    System.out.println("Enter a valid option");
                            }
                        }
                    } else {
                        System.out.println("Credential Error");
                    }
                }
                break;
                case 2: {
                    int ul = valid_user(user_list);
                    if (ul != -1) {
                        Boolean user_exit = false;
                        while (!user_exit) {
                            System.out.println("1-Borrow a Book");
                            System.out.println("2-Return a Book");
                            System.out.println("3-Check Wallet Balance");
                            System.out.println("4-Add Amount to Wallet");
                            System.out.println("5-View My Cart");
                            System.out.println("6-Exit");
                            System.out.println("Enter Your choice : ");
                            int userchoice = sc.nextInt();
                            switch (userchoice) {
                                case 1:
                                    if (user_list.get(ul).money > 500) {
                                        int bb = add2cart(book_list);
                                        if (bb != -1) {
                                            Boolean alb = true;
                                            for (int i = 0; i < user_list.get(ul).cart.size(); i++) {
                                                if (user_list.get(ul).cart.get(i).name == book_list.get(i).name) {
                                                    alb = false;
                                                }
                                            }
                                            if (alb) {
                                                user_list.get(ul).cart.add(book_list.get(bb));
                                                user_list.get(ul).borrowtime.add(LocalTime.now());
                                                book_list.get(bb).count -= 1;
                                            } else {
                                                System.out.println("Yoy already have this book");
                                            }
                                        }
                                    } else {
                                        System.out.println("Insufficient Wallet amount");
                                    }
                                    break;
                                case 2:
                                    int rb = removefromcart(user_list.get(ul).cart);
                                    if (rb != -1) {
                                        int f = -1;
                                        for (int i = 0; i < book_list.size(); i++) {
                                            if (book_list.get(i).name == user_list
                                                    .get(ul).cart.get(rb).name) {
                                                f = i;
                                            }
                                        }
                                        LocalTime t1 = user_list.get(ul).borrowtime.get(rb);
                                        LocalTime t2 = LocalTime.now();
                                        long finetime = ChronoUnit.HOURS.between(t1, t2) * 60 + ChronoUnit.MINUTES.between(t1, t2);
                                        if (finetime < 1) {
                                            book_list.get(f).count += 1;
                                            user_list.get(ul).cart.remove(rb);
                                            user_list.get(ul).borrowtime.remove(rb);
                                            System.out.println("Book returned in-time");
                                        } else {
                                            System.out.println("Book returning date expired");
                                            long fineamount = 0;
                                            finetime -= 1;
                                            int it = 1;
                                            while (finetime > 0) {
                                                if (finetime >= 10) {
                                                    fineamount += 10 * ((int) Math.pow(2, it));
                                                    finetime -= 10;
                                                } else {
                                                    fineamount += finetime * ((int) Math.pow(2, it));
                                                    finetime = 0;
                                                }
                                                it += 1;
                                            }
                                            if (fineamount > user_list.get(ul).cart.get(rb).amount * 0.8) {
                                                fineamount = (long) user_list.get(ul).cart.get(rb).amount / 2;
                                            }
                                            System.out.println("Your fine amount is : " + fineamount);
                                            System.out.println("1-pay from wallet");
                                            System.out.println("2-pay by other means");
                                            System.out.println("Enter Your Choice : ");
                                            int fc = sc.nextInt();
                                            switch (fc) {
                                                case 1:
                                                    user_list.get(ul).money -= fineamount;
                                                    user_list.get(ul).cart.remove(rb);
                                                    user_list.get(ul).borrowtime.remove(rb);
                                                    break;
                                                case 2:
                                                    user_list.get(ul).cart.remove(rb);
                                                    user_list.get(ul).borrowtime.remove(rb);
                                                    break;
                                                default:System.out.println("enter valid choice");
                                            }
                                        }
                                    }
                                    break;
                                case 3:System.out.println("Your balance is "+user_list.get(ul).money);
                                    break;
                                case 4:System.out.println("Enter the amount to be added to wallet : ");
                                    int at = sc.nextInt();
                                    if(at>0){
                                        user_list.get(ul).money+=at;
                                        System.out.println("Amount Added to your wallet");
                                    }
                                    else{
                                        System.out.println("Enter a valid amount");
                                    }
                                    break;
                                case 5:for(int i=0;i<user_list.get(ul).cart.size();i++){
                                    System.out.println(user_list.get(ul).cart.get(i).name);
                                }
                                    break;
                                case 6:user_exit=true;
                            }
                        }
                    }
                    else{
                        System.out.println("Entered Credentials Doesnot match");
                    }

                }
                break;
                case 3:whole_exit=true;
                    break;
                default:System.out.println("Enter a valid number");


            }
        }
    }
}
