
import java.util.*;
class Passenger{
    static int id = 1;
    String name;
    int age;
    String berthPerference;
    int passengerId;//id created automatically
    String alloted;//alloted type
    int number;//seat number
    public Passenger(String name,int age, String berthPerference) {
        this.name = name;
        this.age = age;
        this.berthPerference = berthPerference;
        this.passengerId = id++;
        alloted = "";
        number = -1;
    }
}

class TicketBooker{
    static int availLowerBerths = 21;
    static int availMiddleBerths = 21;
    static int availUpperBerths = 21;
    static int availRacTickets = 18;
    static int availWaitingList =10;


    static Queue<Integer> waitingList = new LinkedList<>();//queue of wl
    static Queue<Integer> racList = new LinkedList<>();//queue of rac
    static List<Integer> bookedTicketList = new ArrayList<>();//bookedticket list

    static List<Integer> lowerBerthPositions = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21));//lower berth ticket numbers 1.2.3....
    static List<Integer> middleBerthPositions = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21));//middle berth ticket numbers 1.2.3....
    static List<Integer> upperBerthPositions = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21));//upper berth ticket numbers 1.2.3....
    static List<Integer> racPositions = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18));//rac ticket numbers 1.2.3....
    static List<Integer> waitingListPositions = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,9,10));//waiting list ticket numbers 1.2.3....

    static Map<Integer, Passenger> passengers = new HashMap<>();//map passenger ids to passengers

    //book tickets
    public static void bookTicket(Passenger p,int berthInfo,String allotedBerth){
        p.number = berthInfo;
        p.alloted = allotedBerth;
        //add passenger to map
        passengers.put(p.passengerId,p);
        //add passenger to bookedticket list
        bookedTicketList.add(p.passengerId);
        System.out.println(".......Ticket Booked Successfully.......");
        System.out.println("PASSENGER ID " + p.passengerId);
        System.out.println("PASSENGER NAME " + p.name);
        System.out.println("PASSENGER AGE " + p.age);
        System.out.println("PASSENGER STATUS " + p.number + p.alloted);
        System.out.println("...........................................");
    }
    public static void addToRAC(Passenger p,int racInfo,String allotedRAC){
        p.number = racInfo;
        p.alloted = allotedRAC;
        // add passenger to map
        passengers.put(p.passengerId,p);
        // add passenger to queue
        racList.add(p.passengerId);
        availRacTickets--;
        racPositions.remove(0);
        System.out.println("......your are now added to RAC.......");
    }
    public static void addToWaitingList(Passenger p,int waitlistInfo,String allotedWaitlist){
        p.number = waitlistInfo;
        p.alloted = allotedWaitlist;
        //add passenger to map
        passengers.put(p.passengerId,p);
        //add passenger to queue
        waitingList.add(p.passengerId);
        availWaitingList--;
        waitingListPositions.remove(0);
        System.out.println(".....you are now added to waiting list......");
    }
    //cancel Ticket
    public static void cancelTicket(int passengerId){
        //remove from passenger map
        Passenger p = passengers.get(passengerId);
        passengers.remove(passengerId);
        //remove from booked ticket list
        bookedTicketList.remove(passengerId);

        //take booked position
        int PositionBooked = p.number;
        System.out.println(".....Ticket Canceled Successfully......");

        //add the freed position to corresponding berth positions
        if(p.alloted.equals("L")){
            availLowerBerths++;
            lowerBerthPositions.add(PositionBooked);
        }
        else if(p.alloted.equals("M")){
            availMiddleBerths++;
            middleBerthPositions.add(PositionBooked);
        }
        else if(p.alloted.equals("U")){
            availMiddleBerths++;
            middleBerthPositions.add(PositionBooked);
        }
        //check if any RAC is there
        if(racList.size()>0){
            //take this passeneger form RAC and increase the free space in RAC List andincrases rac availability
            Passenger passengerFromRAC = passengers.get(racList.poll());
            int positionRac = passengerFromRAC.number;
            racPositions.add(positionRac);
            racList.remove(passengerFromRAC.passengerId);
            availRacTickets++;

            //check if any waiting list
            if(waitingList.size()>0){
                //take passenger form wl to rac increase and free space in wl
                //increase availability wl and decrease availability rac by 1;

                Passenger passengerFromWaitingList = passengers.get(waitingList.poll());
                int positionWL = passengerFromWaitingList.number;
                waitingListPositions.add(positionWL);
                waitingList.remove(passengerFromWaitingList.passengerId);

                passengerFromWaitingList.number = racPositions.get(0);
                passengerFromWaitingList.alloted = "RAC";
                racPositions.remove(0);
                racList.add(passengerFromWaitingList.passengerId);

                availRacTickets--;
                availWaitingList++;

            }
            //now we have a passenger from RAC to whom we need to book berth tickets
            //so book the canceled tickets to RAC passenger
            Main.bookTicket(passengerFromRAC);
        }
    }
    //print all the avail seats
    public static void printAvailable(){
        System.out.println("Available Lower Berths " + availLowerBerths);
        System.out.println("Available Middle Berths " + availMiddleBerths);
        System.out.println("Available Upper Berths " + availUpperBerths);
        System.out.println("Available RAC Tickets " + availRacTickets);
        System.out.println("Available Waiting list Tickets " + availWaitingList);
        System.out.println("........................................");

    }
    public static void printPassengers(){
        if(passengers.size()==0){
            System.out.println("No Passengers booked");
        }
        for(Passenger p : passengers.values()){
            System.out.println("PASSENGER ID " + p.passengerId);
            System.out.println("PASSENGER NAME " + p.name);
            System.out.println("PASSENGER AGE " + p.age);
            System.out.println("PASSENGER STATUS " + p.number + p.alloted);
            System.out.println("...........................................");
        }
    }

}



public class Main {

    public static void bookTicket(Passenger p){
        TicketBooker booker = new TicketBooker();
        if(TicketBooker.availWaitingList == 0){
            System.out.println("No Tickets Available");
            System.out.println("..............................................");
        }
        //check preference

        if((p.berthPerference.equals("L") && TicketBooker.availLowerBerths>0)||
                (p.berthPerference.equals("M") && TicketBooker.availMiddleBerths>0)||
                (p.berthPerference.equals("U") && TicketBooker.availUpperBerths>0)) {
            System.out.println("Perfered Birth Available");
            if (p.berthPerference.equals("L")) {
                System.out.println("Lower Birth booked");
                //call booking method in ticketbooker class
                booker.bookTicket(p, (TicketBooker.lowerBerthPositions.get(0)), "l");
                TicketBooker.lowerBerthPositions.remove(0);
                TicketBooker.availLowerBerths--;
            } else if (p.berthPerference.equals("M")) {
                System.out.println("Middle Birth booked");
                //call booking method in ticketbooker class
                booker.bookTicket(p, (TicketBooker.middleBerthPositions.get(0)), "M");
                TicketBooker.middleBerthPositions.remove(0);
                TicketBooker.availMiddleBerths--;
            } else if (p.berthPerference.equals("U")) {
                System.out.println("Upper Birth booked");
                //call booking method in ticketbooker class
                booker.bookTicket(p, (TicketBooker.upperBerthPositions.get(0)), "U");
                TicketBooker.upperBerthPositions.remove(0);
                TicketBooker.availUpperBerths--;
            }
        }
            // if perference not avail then book avail berth
            else if (TicketBooker.availLowerBerths > 0) {
                System.out.println("Lower berth is only available");
                booker.bookTicket(p, (TicketBooker.lowerBerthPositions.get(0)), "l");
                TicketBooker.lowerBerthPositions.remove(0);
                TicketBooker.availLowerBerths--;
            } else if (TicketBooker.availMiddleBerths > 0) {
                System.out.println("Middle berth is only available");
                booker.bookTicket(p, (TicketBooker.middleBerthPositions.get(0)), "M");
                TicketBooker.middleBerthPositions.remove(0);
                TicketBooker.availMiddleBerths--;
            } else if (TicketBooker.availUpperBerths > 0) {
                System.out.println("Upper berth is only available");
                booker.bookTicket(p, (TicketBooker.upperBerthPositions.get(0)), "U");
                TicketBooker.upperBerthPositions.remove(0);
                TicketBooker.availUpperBerths--;
            }

        //if no berth available go to rac
        else if(TicketBooker.availRacTickets>0){
            System.out.println("Only RAC tickets are available");
            booker.addToRAC(p,(TicketBooker.racPositions.get(0)),"RAC");
        }
        //if no rac available then go to waiting list
        else if(TicketBooker.availWaitingList>0){
            System.out.println("Only WaitingList tickets are available");
            booker.addToWaitingList(p,(TicketBooker.waitingListPositions.get(0)),"Wl");
        }
    }

    public static void cancelTicket(int id){
        TicketBooker booker = new TicketBooker();
        //check if passenger is valid or not
        if(!booker.passengers.containsKey(id)){
            System.out.println("passenger Details unknown");
        }
        booker.cancelTicket(id);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean loop = true;

        while (true){
            System.out.println("1.Book Tickets \n"+"2.Cancel Tickets \n"+"3.Available Tickets \n"+ "4. Booked Tickets \n" +"5.Exit\n"+"Enter your operation: ");
            int choise = sc.nextInt();
            switch (choise){
                case 1:{
                    System.out.println("Enter passenger name:");
                    String name = sc.next();
                    System.out.println("Enter passenger age: ");
                    int age = sc.nextInt();
                    System.out.println("Enter Berth perfernce (lower as L , middle as M, upper as U) : ");
                    String berthpreference = sc.next();
                    Passenger p = new Passenger(name,age,berthpreference);
                    bookTicket(p);
                }
                break;
                case 2:{
                    System.out.println("Enter the id you need to cancel: ");
                    int id = sc.nextInt();
                    cancelTicket(id);
                }
                break;
                case 3:{
                    TicketBooker booker = new TicketBooker();
                    booker.printAvailable();
                }
                break;
                case 4:{
                    TicketBooker booker = new TicketBooker();
                    booker.printPassengers();
                }
                break;
                case 5:{
                    loop = false;
                    System.out.println("......Thank you for visiting Maddy Railways please do visit again.....");
                }
                break;
                default:
                    break;
            }
        }
    }
}
