package AssignmentProject;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
public class BookingSystem {
    private static int reservation_number=100;
    /**
     * An enum for indexes of errors
     */
    public enum ErrorMgs{
        /**
         * Not registered error message
         */
        NOT_REG(0),
        NOT_FOUND(1),
        SEAT_UNAVAILABLE(2);



        private final int _value;
        ErrorMgs(final int value){
            _value = value;
        }
        public int getValue() { return _value; }
    }
    /**
     * An array of error messages that can be used to show to a user
     *  */    
    public static final String[] ERROR_MESSAGES = {
        "Passenger with Passport number %s is not Registered%n",
        "Flight %s Not Found%n",
        "Seat %d%s is already Reserved Or Not found%n"
    };

    public static void main(String[] args) throws FileNotFoundException{
        //making files
        File flightinput = new File("flight_passenger.txt");
        File inputCommands = new File("inputCommands.txt");
        File output = new File("output.txt");

        //making scanners
        Scanner in_flight = new Scanner(flightinput);
        Scanner in = new Scanner(inputCommands);

        //making printwriter
        PrintWriter output_print = new PrintWriter(output);

        //making sure files exist
        if (!flightinput.exists() || !inputCommands.exists()) {
            System.out.println(flightinput.getName()+" or "+inputCommands.getName()+" Does not exist!");
            System.exit(0);
        }
        //Arrays for Flights and Passengers
        Flight[] flights = new Flight[in_flight.nextInt()];
        Passenger [] passengers = new Passenger[in_flight.nextInt()];
        //Inputting Flights & Passengers
        //-------------------------------REGISTRATION-------------------------------
        final String CMD_ADD_FLIGHT = "addflight";
        final String CMD_ADD_PASSENGER = "addpassenger";
        int counter_flight=0, counter_passenger=0;
        do {
            String command = in_flight.next();
            
            //Array for flights
            if (command.equalsIgnoreCase(CMD_ADD_FLIGHT)){
                flights[counter_flight]=AddFlight(in_flight);
                counter_flight++;
            }
            
            //Array for passengers
            else if(command.equalsIgnoreCase(CMD_ADD_PASSENGER)){
                passengers[counter_passenger]=AddPassenger(in_flight);
                counter_passenger++;
            }
            
            else{
                System.out.println("Incorrect Command");
            }
        } while (in_flight.hasNext());
        //-------------------------------BOOKING-------------------------------
        do {
            String command = in.next();
            if (command.equalsIgnoreCase("bookticket")) {
                BookTicket(passengers, flights, in.next(), in.next(), in.nextInt(), in.next(), in.next());
            }
            else{
                System.out.println("Incorrect Command");
            }
        } while (in.hasNextLine());


        

        //closing and flushing
        output_print.flush();
        output_print.close();
        in_flight.close();
        in.close();

    }



    /**
     * <ul>
     * <li>
     * <h4>Enables addition of flight. Each flight has the following:</h4>
     *  <ul>
     *      <li>flight number</li>
     *      <li>a departure and arrival city</li>
     *      <li>a gate number</li>
     *      <li>an arrangement</li>
     *      <li>and rows/columns for an array "plan"</li>
     *  </ul>
     * </li>
     * </ul>
     * @param in as a {@link Scanner}
     * 
     * @return an instance of {@link Flight}
     */
    public static Flight AddFlight(Scanner in){
        Flight flight = new Flight(in.next(),in.next(),in.next(),in.nextInt(),in.nextInt(),in.nextInt());
        String[][] seatMap = new String[flight.getRow()][flight.getColumn()];
        flight.setSeatMap(seatMap);
        System.out.println("Flight "+flight.getFlight_number()+" added successfully");
        return flight;
    }

    //enables the addition of passenger details
    //each passenger has a passport and a name
    public static Passenger AddPassenger(Scanner in){
        Passenger passenger = new Passenger();
        passenger.setPassport_number(in.next());
        passenger.setName(in.next());
        System.out.println("Passenger "+ passenger.getName() +" added successfully");
        return passenger;
        
    }



    //method to increment reservation confirmation number
    public static void reservationIncrement(){
        reservation_number++;
    }
    /**
     * Books a {@link Ticket} for a given {@link Passenger} and their {@link Flight} information
     * @param passengers as an array of {@link Passenger}
     * @param flights as an array of {@link Flight}
     * @param passport as an instance of {@link String}
     * @param flight_number as an instance of {@link String}
     * @param seat_row as an instance of {@link Integer}
     * @param seat_column as an instance of {@link String}
     * @param class_type as an instance of {@link String}
     * @return An instance of {@link Ticket} if there is an available seat in the specified {@link Flight}.
     *  Otherwise it will return {@code null}, and an appropriate error message will be displayed
     */
    public static Ticket BookTicket(Passenger[] passengers, Flight[] flights, String passport, String flight_number, int seat_row, String seat_column, String class_type){
        System.out.println("\n*********************BookTicket**************************\n");
        Passenger this_passenger=null;
        Flight this_flight=null;
        //checking which passenger matches passport
        for (int i = 0; i < passengers.length; i++) {
            if (passport.equalsIgnoreCase(passengers[i].getPassport())) {
                this_passenger = passengers[i];
            }
        }
        //checking which flight matches flight number
        for (int i = 0; i < flights.length; i++) {
            if (flight_number.equals(flights[i].getFlight_number())) {
                this_flight=flights[i];
            }
        }
        Ticket ticket = null;
        //if invalid
        if (this_passenger==null) {
            System.out.printf(ERROR_MESSAGES[ErrorMgs.NOT_REG.getValue()], passport);
            return ticket;
        }
        if(this_flight==null){
            System.out.printf(ERROR_MESSAGES[ErrorMgs.NOT_FOUND.getValue()],flight_number);
            return ticket;
        }
        if (!this_flight.isSeatAvailable(seat_row,seat_column.toCharArray()[0])) {
            System.out.printf(ERROR_MESSAGES[ErrorMgs.SEAT_UNAVAILABLE.getValue()],seat_row,seat_column);
            return ticket;
        }
        //making a Ticket object
        ticket = new Ticket(this_passenger, this_flight, seat_row, seat_column, class_type,reservation_number);
        reservationIncrement();
        System.out.println("Seat booked successfully.");
        System.out.println("Ticket information:");
        System.out.println(ticket.toString());
        return ticket;
    }

}
