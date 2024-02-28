// manages booking information including 
//flight, passenger, seat number, and class (economy, business, etc.).
package AssignmentProject;
public class Ticket {
    private Flight flight;
    private Passenger passenger;
    private int seat_row;
    private String seat_number;
    private String class_type;
    private int reservation_number;

    //constructor
    public Ticket(Passenger passenger, Flight flight, int seat_row, String seat_number,String class_type,int reservation_number){
        this.passenger=passenger;
        this.flight=flight;
        this.seat_row = seat_row;
        this.seat_number = seat_number;
        this.class_type = class_type;
        this.reservation_number = reservation_number;
    }

    public String toString(){
        return 
        "Reservation Confirmation Number= " + this.reservation_number + ", " +
        flight.toString() + ", " +
        passenger.toString() + ", " + 
        "Seat Number= " + this.seat_row + this.seat_number + ", " +
        "classtype= " + this.class_type;

    }
    

}
