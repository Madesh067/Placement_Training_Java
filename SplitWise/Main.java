import java.util.*;
class User{
    String name;
    String password;
    int amount;
    ArrayList<Frnds>group_list = new ArrayList<>();
    
}
//......................................................................................................
class Frnds{
    String name;
    int pending_amount;
    ArrayList<String> Frnds_list = new ArrayList<>();

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
        ArrayList<Frnds> Frnds_list = new ArrayList<>();
        Frnds c1 = new Frnds();
        Frnds c2 = new Frnds();
        Frnds c3 = new Frnds();
        Frnds c4 = new Frnds();
        Frnds c5 = new Frnds();
        
        c1.name = "Frnds1";
        c1.pending_amount = 0;
        c2.name = "Frnds2";
        c2.pending_amount = 0;
        c3.name = "Frnds3";
        c3.pending_amount = 0;
        c4.name = "Frnds4";
        c4.pending_amount = 0;
        c5.name = "Frnds5";
        c5.pending_amount = 0;
        
        Frnds_list.add(c1);
        Frnds_list.add(c2);
        Frnds_list.add(c3);
        Frnds_list.add(c4);
        Frnds_list.add(c5);
    
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
                            System.out.println("2.Update their Wallet Amount");
                            System.out.println("3.Add Friends into the Group");
                            System.out.println("4.remove Friends into the Group");
                            System.out.println("5.View and pay Pending Dues");
                            System.out.println("6.View their Transaction History");
                            System.out.println("7.LogOut");
                            System.out.println("Enter your choice : ");
                            int userchoice = sc.nextInt();
                            switch (userchoice){
                                case 1:{
                                    System.out.println("Enter the name of the expense");
                                    String expname = sc.next();
                                    System.out.println("Enter the total expense amount");
                                    int expamount = sc.nextInt();
                                    user_list.get(u).amount=user_list.get(u).amount-expamount;
                                    System.out.println("Enter the type\n"+"1.Non Group Expense\n"+"2.Group Expense\n"+"3.Exit");
                                    int echoice = sc.nextInt();
                                    if(echoice == 1) {

                                        HashMap<String, String> y = new HashMap<>();
                                        for (int i = 0; i < Frnds_list.size(); i++) {
                                            System.out.println((i+1) + "." + Frnds_list.get(i).name);
                                        }
                                        System.out.println("Select the Frnds you need to add expense : ");
                                        int select_Frnds = sc.nextInt();
                                        if (select_Frnds <= Frnds_list.size()) {
                                            Frnds_list.get(select_Frnds).pending_amount += expamount / 2;
                                            y.put("name", expname);
                                            y.put("Frnds", Frnds_list.get(select_Frnds).name);
                                            y.put("amount", Integer.toString(expamount / 2));
                                            transaction_list.add(y);
                                            System.out.println(transaction_list);
                                        }
                                    }
                                        else{
                                            System.out.println("Enter 0 for existing group");
                                            System.out.println("Enter 1 for creating a new group");
                                            int c=sc.nextInt();
                                            if(c==0){
                                           
                                            for(int i=0;i<user_list.get(u).group_list.size();i++){
                                                HashMap<String,String> y = new HashMap<>();
                                                user_list.get(u).group_list.get(i).pending_amount+=expamount/user_list.get(u).group_list.size();
                                                y.put("name",expname);
                                                y.put("Frnds",user_list.get(u).group_list.get(i).name);
                                                y.put("amount",Integer.toString(expamount/user_list.get(u).group_list.size()));
                                                transaction_list.add(y);
                                            }
                                        System.out.println(transaction_list);
                                        }
                                        else{
                                            if(user_list.get(u).group_list.size()!=0){
                                                for(int i=0;i<Frnds_list.size();i++){
                                                    Boolean h =true;
                                                for(int k=0;k<user_list.get(u).group_list.size();k++){
                                                    if(Frnds_list.get(i).name.equals(user_list.get(u).group_list.get(k).name)){
                                                        h=false;
                                                   }
                                                     }
                                                if(h){System.out.println((i+1)+ " . "+Frnds_list.get(i).name);}
                                                  }
                                            }
                                            else{
                                                for(int i=0;i<Frnds_list.size();i++){
                                                System.out.println((i+1)+ " . "+Frnds_list.get(i).name);
                                                       }
                                              }
                                            System.out.println("Number of Frndss to be grouped");
                                            int n=sc.nextInt();
                                            System.out.println("Select Frndss to be grouped");
                                            int[] arr= new int[n];
                                            for(int k=0;k<n;k++){
                                                arr[k]=sc.nextInt();
                                                
                                            }
                                            for(int k=0;k<n;k++){
                                    
                                                user_list.get(u).group_list.add(Frnds_list.get(arr[k]));
                                                
                                            }
                                            for(int i=0;i<user_list.get(u).group_list.size();i++){
                                                System.out.println((i+1)+ " . "+user_list.get(u).group_list.get(i).name);
                                            }
                                            System.out.println(user_list.get(u).group_list);
                                             
                                            for(int i=0;i< user_list.get(u).group_list.size();i++){
                                                HashMap<String,String> y = new HashMap<>();
                                                user_list.get(u).group_list.get(i).pending_amount+=expamount/user_list.get(u).group_list.size();

                                                y.put("name",expname);
                                                y.put("Frnds", user_list.get(u).group_list.get(i).name);
                                                y.put("amount",Integer.toString(expamount/user_list.get(u).group_list.size()));
                                                transaction_list.add(y);
                                            }
                                            System.out.println(transaction_list); }
                                        }
                                    }
                                    break;
                                    case 2: System.out.println(" Enter 1 for add money to wallet");
                                            System.out.println("Enter 2 for deduce money from wallet");
                                            System.out.println("Enter 3 to view wallet balance");
                                            System.out.prinln("Enter 4 for exit");
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
                                        for(int i=0;i<Frnds_list.size();i++){
                                            Boolean h =true;
                                        for(int k=0;k<user_list.get(u).group_list.size();k++){
                                            if(Frnds_list.get(i).name.equals(user_list.get(u).group_list.get(k).name)){
                                                h=false;
                                           }
                                             }
                                        if(h){System.out.println((i)+ " . "+Frnds_list.get(i).name);}
                                          }
                                    }
                                    else{
                                        for(int i=0;i<Frnds_list.size();i++){
                                        System.out.println((i)+ " . "+Frnds_list.get(i).name);
                                               }
                                      }
                                    System.out.println("Number of Frndss to be grouped");
                                    int n=sc.nextInt();
                                    System.out.println("Select Frndss to be grouped");
                                    int[] arr= new int[n];
                                    for(int k=0;k<n;k++){
                                        arr[k]=sc.nextInt();
                                        
                                    }
                                    for(int k=0;k<n;k++){
                            
                                        user_list.get(u).group_list.add(Frnds_list.get(arr[k]));
                                        
                                    }
                                    for(int i=0;i<user_list.get(u).group_list.size();i++){
                                        System.out.println((i)+ " . "+user_list.get(u).group_list.get(i).name);
                                    }
                                 break; 
                                 case 4 :  for(int i=0;i<user_list.get(u).group_list.size();i++){
                                    System.out.println("Refer code :"+(i)+ " for "+user_list.get(u).group_list.get(i).name);
                                 }
                                    System.out.println("Number of Frndss to be deleted from group");
                                    int rn=sc.nextInt();
                                    System.out.println("select Frnds to be deleted from group");
                                    int[] ar= new int[rn];
                                    for(int k=0;k<rn;k++){
                                        ar[k]=sc.nextInt();
                                        
                                    }
                                    for(int k=0;k<rn;k++){
                                        user_list.get(u).group_list.remove(Frnds_list.get(ar[k]));
                                        
                                    }
                                    for(int c=0;c<user_list.get(u).group_list.size();c++){
                                        System.out.println((c)+ " . "+user_list.get(u).group_list.get(c).name);
                                    }
                         break; 
                         case 5:for(int i=0;i<Frnds_list.size();i++){
                            System.out.println(Frnds_list.get(i).name+" pay due "+Frnds_list.get(i).pending_amount);
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
                case 3:whole_exit=true;
                break;
            }
        }

    }
}