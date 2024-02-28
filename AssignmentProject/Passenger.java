//maintains essential passenger details like name and passport number
package AssignmentProject;
public class Passenger {
    private String name;
    private String passport_number;

    public Passenger(){
        this.name = "N/A";
        this.passport_number = "N/A";
    }

    // public Passenger(String passport_number, String name){
    //     this.name = name;
    //     this.passport_number = passport_number;
    // }
    
    public void setName(String name) {
        this.name = name;
    }

    public void setPassport_number(String passport_number) {
        this.passport_number = passport_number;
    }

    public String getName(){
        return this.name;
    }
    public String getPassport(){
        return this.passport_number;
    }
    public String toString(){
        return "Passenger Name= "+ this.name;
    }
}
