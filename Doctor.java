import java.util.ArrayList;
import java.util.HashMap;

public class Doctor { // a doctor is equivalent to a store in the project handout
    private String name; // Doctor's name
    private ArrayList<Appointment> appointments; // List of appointments for the doctor


    public Doctor(String name) {
        this.name = name;
        this.appointments = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Appointment> getAppointments() {
        return appointments;
    }

    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
    }

    public void removeAppointment(Appointment appointment) {
        appointments.remove(appointment);
    }

    public boolean isAvailable(Appointment appointmentTime) {
        // Check if the doctor has an available appointment slot at the specified time
        for (Appointment appointment : appointments) {
            if (appointment.getTime().equals(appointmentTime.getTime()) && !appointment.isBooked()) {
                return true;
            }
        }
        return false;
    }

    public Appointment findAppointment(String customerName) { // returns the appointment of a given customer
        // assumes there is only one appointment for each customer name
        // returns null if there is no such appoinment with given customer name
        for (Appointment appointment : appointments) {
            if (customerName.equalsIgnoreCase(appointment.getCustomerName())) {
                return appointment;
            }
        }
        return null;
    }

    public HashMap[] getStatistics() {
        // returns an array of two hashmaps.
        // Map 1: a list of customers and corresponding # of appointments per customer
        // Map 2: a list of String time and corresponding # of appointments

        HashMap<String, Integer> customerData = new HashMap<String, Integer>(); // maps customer names to integer # of appointments
        HashMap<String, Integer> timeData = new HashMap<String, Integer>(); // maps times to frequency of appointment slot

        for (Appointment apt : appointments) {
            String cusName = apt.getCustomerName();
            String thisTime = apt.getTime();
            if (apt.isBooked()) {
                if (customerData.containsKey(cusName)) { // if the customer is already in database, increase by 1
                    customerData.replace(cusName, customerData.get(cusName) + 1);
                } else { // add customer to database
                    customerData.put(cusName, 1);
                }
                if (timeData.containsKey(thisTime)) { // if the time is already in database, increase by 1
                    timeData.replace(thisTime, timeData.get(thisTime) + 1);
                } else { // add time to database
                    timeData.put(thisTime, 1);
                }

            }
        }
        HashMap[] output = {customerData, timeData};
        return output;

    }

    @Override
    public String toString() {
        return name;
    }
}
