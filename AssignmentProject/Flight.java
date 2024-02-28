//This class stores details like flight number, departure and arrival locations, and seats
package AssignmentProject;
public class Flight {
    private String flight_number;
    private String departure_city;
    private String arrival_city;
    private int gate_number;
    private String[][] seatMap;
    private int row;
    private int column;
    private String column_letter;

    //constructor
    public Flight(String flight_number,String departure_city,String arrival_city,int gate_number,int row,int column){
        this.flight_number=flight_number;
        this.departure_city=departure_city;
        this.arrival_city=arrival_city;
        this.gate_number=gate_number;
        this.row=row;
        this.column=column;
    }

    //Getters------------------------------------------------------------------------------
    public String getFlight_number() {
        return flight_number;
    }
    public String getDeparture_city() {
        return departure_city;
    }
    public String getArrival_city() {
        return arrival_city;
    }
    public int getGate_number() {
        return gate_number;
    }
    public String[][] getSeatMap() {
        return seatMap;
    }
    public int getRow() {
        return row;
    }
    public int getColumn() {
        return column;
    }
    //Setters------------------------------------------------------------------------------
    public void setFlight_number(String flight_number) {
        this.flight_number = flight_number;
    }
    public void setDeparture_city(String departure_city) {
        this.departure_city = departure_city;
    }
    public void setArrival_city(String arrival_city) {
        this.arrival_city = arrival_city;
    }
    public void setGate_number(int gate_number) {
        this.gate_number = gate_number;
    }
    public void setSeatMap(String[][] seatMap) {
        for (int i = 0; i < seatMap.length; i++) {
            for (int j = 0; j < seatMap[i].length; j++) {
                seatMap[i][j]=null;
            }
        }
        this.seatMap = seatMap;
    }
    public void setRow(int row) {
        this.row = row;
    }
    public void setColumn(int column) {
        this.column = column;
    }
    
    //others
    public boolean bookSeat(int row, char column, String passengerID){
        return false;
    }
    public boolean isSeatAvailable(int row, char column){
        if (row>this.row || (int)(column-'A')>this.column) {
            return false;
        }
        return this.seatMap[row][(int)(column-'A')] == null;
    }
    public String toString(){
        return "Flight Number=" + this.flight_number;
    }
    public String printSeatPlan(){
        return null;
    }

}