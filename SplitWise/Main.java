import java.util.*;
class User{
    String name;
    String password;
    int amount;
    ArrayList<Frnd>group_list = new ArrayList<>();
}
//......................................................................................................
class Frnd{
    String name;
    String password;
    int pending_amount;
    int wallet;
    ArrayList<Frnd> Frnd_list = new ArrayList<>();

}
//.......................................................................................................
class Main{
    static Scanner sc = new Scanner(System.in);
    //.......................................................................................................
    public static User createuser(){
        User x = new User();
        System.out.println("Enter your name : ");
        x.name = sc.next();
        System.out.println("Enter password : ");
        x.password = sc.next();
        System.out.println("Enter  wallet amount : ");
        x.amount = sc.nextInt();
        return x;
    }
    //........................................................................................................
    public static int valid_user(ArrayList<User> x){
        int val = -1;
        System.out.println("Enter your name : ");
        String name = sc.next();
        System.out.println("Enter your password : ");
        String password = sc.next();
        for(int i=0;i<x.size();i++){
            if(x.get(i).name.equals(name) && x.get(i).password.equals(password)){
                val = i;
            }
        }
        return val;

    }
    //.........................................................................................................
    public static int valid_frnd(ArrayList<Frnd> x){
        int val=-1;
        System.out.println("Enter your name: ");
        String name = sc.next();
        System.out.println("Enter your password: ");
        String password = sc.next();
        for(int i=0;i<x.size();i++){
            if(x.get(i).name.equals(name) && x.get(i).password.equals(password)){
                val = i;
            }
        }
        return val;

    }
    //.........................................................................................................
    //public static int pay_due(ArrayList<Frnd> x, ArrayList<User> y){

    //}
    //.........................................................................................................
    public static void main(String[] args) {
        ArrayList<User> user_list = new ArrayList<>();
        User a = new User();
        User b = new User();
        a.name = "user1";
        a.password = "1111";
        a.amount = 50000;
        b.name = "user2";
        b.password = "2222";
        b.amount = 70000;
        user_list.add(a);
        user_list.add(b);
        ArrayList<Frnd> Frnd_list = new ArrayList<>();
        Frnd c1 = new Frnd();
        Frnd c2 = new Frnd();
        Frnd c3 = new Frnd();
        Frnd c4 = new Frnd();
        Frnd c5 = new Frnd();

        c1.name = "Frnd1";
        c1.pending_amount = 0;
        c1.wallet = 500000;
        c2.name = "Frnd2";
        c2.pending_amount = 0;
        c2.wallet = 600000;
        c3.name = "Frnd3";
        c3.pending_amount = 0;
        c3.wallet = 700000;
        c4.name = "Frnd4";
        c4.pending_amount = 0;
        c3.wallet = 4500000;
        c5.name = "Frnd5";
        c5.pending_amount = 0;
        c5.wallet = 5000000;

        Frnd_list.add(c1);
        Frnd_list.add(c2);
        Frnd_list.add(c3);
        Frnd_list.add(c4);
        Frnd_list.add(c5);

        ArrayList<HashMap<String,String>> transaction_list = new ArrayList<>();
//..................................................................................................................
        Boolean whole_exit = false;
        while (!whole_exit){
            System.out.println("1.New User");
            System.out.println("2.Existing User");
            System.out.println("3.Exit");
            System.out.println("Enter your choice : ");
            int loginchoice = sc.nextInt();
            switch (loginchoice){
                case 1: {
                    user_list.add(createuser());
                    System.out.println("New User Created Successfully");
                }
                break;
                case 2:{
                    int u = valid_user(user_list);
                    if(u != -1){
                        Boolean user_exit = false;
                        while (!user_exit){
                            System.out.println("1.Add an Expense");
                            System.out.println("2.Wallet settings");
                            System.out.println("3.Add Friends into the Group");
                            System.out.println("4.remove Friends into the Group");
                            System.out.println("5.View and get Pending Dues");
                            System.out.println("6.View their Transaction History");
                            System.out.println("7.LogOut");
                            System.out.println("Enter your choice : ");
                            int userchoice = sc.nextInt();
                            switch (userchoice){
                                case 1:{
                                    System.out.println("Enter the name of the expense");
                                    String expname = sc.next();
                                    System.out.println("Enter the total expense amount");
                                    int eamt = sc.nextInt();
                                    user_list.get(u).amount=user_list.get(u).amount-eamt;
                                    System.out.println("Enter the type\n"+"1.Non Group Expense\n"+"2.Group Expense\n"+"3.Exit");
                                    int echoice = sc.nextInt();
                                    if(echoice == 1) {

                                        HashMap<String, String> y = new HashMap<>();
                                        for (int i = 0; i < Frnd_list.size(); i++) {
                                            System.out.println((i) + "." + Frnd_list.get(i).name);
                                        }
                                        System.out.println("Select the Frnd you need to add expense : ");
                                        int select_Frnd = sc.nextInt();
                                        if (select_Frnd <= Frnd_list.size()) {
                                            Frnd_list.get(select_Frnd).pending_amount += eamt / 2;
                                            Frnd_list.get(select_Frnd).wallet -= eamt/2;
                                            user_list.get(u).amount += Frnd_list.get(select_Frnd).pending_amount;
                                            y.put("name", expname);
                                            y.put("Frnd", Frnd_list.get(select_Frnd).name);
                                            y.put("amount", Integer.toString(eamt / 2));
                                            transaction_list.add(y);
                                            System.out.println(transaction_list);
                                        }
                                    }
                                    else{
                                        System.out.println("Enter 0 for existing group");
                                        System.out.println("Enter 1 for creating a new group");
                                        int c=sc.nextInt();
                                        if(c==0){
                                            if(user_list.get(u).group_list.size()>0) {
                                                for (int i = 0; i < user_list.get(u).group_list.size(); i++) {
                                                    HashMap<String, String> y = new HashMap<>();
                                                    user_list.get(u).group_list.get(i).pending_amount += eamt / user_list.get(u).group_list.size();
                                                    user_list.get(u).group_list.get(i).wallet -= eamt / user_list.get(u).group_list.size();
                                                    y.put("name", expname);
                                                    y.put("Frnd", user_list.get(u).group_list.get(i).name);
                                                    y.put("amount", Integer.toString(eamt / user_list.get(u).group_list.size()));
                                                    transaction_list.add(y);
                                                }
                                            }
                                            else{
                                                System.out.println("There is no such existing group please create a group first");
                                            }
                                            System.out.println(transaction_list);
                                        }
                                        else{
                                            if(user_list.get(u).group_list.size()!=0){
                                                for(int i=0;i<Frnd_list.size();i++){
                                                    Boolean h =true;
                                                    for(int k=0;k<user_list.get(u).group_list.size();k++){
                                                        if(Frnd_list.get(i).name.equals(user_list.get(u).group_list.get(k).name)){
                                                            h=false;
                                                        }
                                                    }
                                                    if(h){System.out.println((i)+ " . "+Frnd_list.get(i).name);}
                                                }
                                            }
                                            else{
                                                for(int i=0;i<Frnd_list.size();i++){
                                                    System.out.println((i)+ " . "+Frnd_list.get(i).name);
                                                }
                                            }
                                            System.out.println("Number of Frnd to be grouped");
                                            int n=sc.nextInt();
                                            System.out.println("Select Frnd to be grouped");
                                            int[] arr= new int[n];
                                            for(int k=0;k<n;k++){
                                                arr[k]=sc.nextInt();

                                            }
                                            for(int k=0;k<n;k++){

                                                user_list.get(u).group_list.add(Frnd_list.get(arr[k]));

                                            }
                                            for(int i=0;i<user_list.get(u).group_list.size();i++){
                                                System.out.println((i)+ " . "+user_list.get(u).group_list.get(i).name);
                                            }
                                            System.out.println("Members added to group successfully");

                                            for(int i=0;i< user_list.get(u).group_list.size();i++){
                                                HashMap<String,String> y = new HashMap<>();
                                                user_list.get(u).group_list.get(i).pending_amount+=eamt/(user_list.get(u).group_list.size()+1);
                                                user_list.get(u).group_list.get(i).wallet -= eamt/(user_list.get(u).group_list.size()+1);
                                                y.put("name",expname);
                                                y.put("Frnd", user_list.get(u).group_list.get(i).name);
                                                y.put("amount",Integer.toString(eamt/(user_list.get(u).group_list.size()+1)));
                                                transaction_list.add(y);
                                            }
                                            System.out.println(transaction_list); }
                                    }
                                }
                                break;
                                case 2: System.out.println("Enter 1 for add money to wallet");
                                    System.out.println("Enter 2 for deduce money from wallet");
                                    System.out.println("Enter 3 to view wallet balance");
                                    System.out.println("Enter 4 for exit");
                                    int ch=sc.nextInt();
                                    if(ch==1){
                                        System.out.println("Enter amount to be added  ");
                                        int ad=sc.nextInt();
                                        user_list.get(u).amount=user_list.get(u).amount+ad;
                                    }
                                    else if(ch==2){
                                        System.out.println("Enter amount to be deduce  ");
                                        int ad=sc.nextInt();
                                        user_list.get(u).amount=user_list.get(u).amount-ad;
                                    }
                                    else if(ch==3){
                                        System.out.println(user_list.get(u).amount);

                                    }
                                    else if(ch==4){
                                        break;
                                    }
                                    else{
                                        System.out.println("Enter valid option");
                                    }



                                    break;

                                case 3:if(user_list.get(u).group_list.size()!=0){
                                    for(int i=0;i<Frnd_list.size();i++){
                                        Boolean h =true;
                                        for(int k=0;k<user_list.get(u).group_list.size();k++){
                                            if(Frnd_list.get(i).name.equals(user_list.get(u).group_list.get(k).name)){
                                                h=false;
                                            }
                                        }
                                        if(h){System.out.println((i)+ " . "+Frnd_list.get(i).name);}
                                    }
                                }
                                else{
                                    for(int i=0;i<Frnd_list.size();i++){
                                        System.out.println((i)+ " . "+Frnd_list.get(i).name);
                                    }
                                }
                                    System.out.println("Number of Frnds to be grouped");
                                    int n=sc.nextInt();
                                    System.out.println("Select Frnds to be grouped");
                                    int[] arr= new int[n];
                                    for(int k=0;k<n;k++){
                                        arr[k]=sc.nextInt();

                                    }
                                    for(int k=0;k<n;k++){

                                        user_list.get(u).group_list.add(Frnd_list.get(arr[k]));

                                    }
                                    for(int i=0;i<user_list.get(u).group_list.size();i++){
                                        System.out.println((i)+ " . "+user_list.get(u).group_list.get(i).name);
                                    }
                                    break;
                                case 4 :  for(int i=0;i<user_list.get(u).group_list.size();i++){
                                    System.out.println("Refer code :"+(i)+ " for "+user_list.get(u).group_list.get(i).name);
                                }
                                    System.out.println("Number of Frnd to be deleted from group");
                                    int rn=sc.nextInt();
                                    System.out.println("select Frnd to be deleted from group");
                                    int[] ar= new int[rn];
                                    for(int k=0;k<rn;k++){
                                        ar[k]=sc.nextInt();

                                    }
                                    for(int k=0;k<rn;k++){
                                        user_list.get(u).group_list.remove(Frnd_list.get(ar[k]));

                                    }
                                    for(int c=0;c<user_list.get(u).group_list.size();c++){
                                        System.out.println((c)+ " . "+user_list.get(u).group_list.get(c).name);
                                    }
                                    break;
                                case 5: {
                                    for (int i = 0; i < Frnd_list.size(); i++) {
                                        System.out.println(i+"."+Frnd_list.get(i).name + "need to get dues " + Frnd_list.get(i).pending_amount);
                                    }
                                    System.out.println("Enter the frnd no. to get the pending dues");
                                    int user_get_choice = sc.nextInt();
                                    if(user_get_choice <= Frnd_list.size()){
                                        user_list.get(u).amount = user_list.get(u).amount + user_list.get(u).group_list.get(user_get_choice).pending_amount;
                                        user_list.get(u).group_list.get(user_get_choice).pending_amount = 0;
                                    }
                                }
                                    break;

                                case 6:System.out.println(transaction_list);
                                    break;

                                case 7:user_exit=true;
                                    break;
                            }
                        }
                    }
                }
                break;
                case 3:whole_exit=true;
                    break;
            }
        }

    }
}
