package OOP;

import OOP.Staff;

import java.util.ArrayList;
import java.util.List;


class Organizer extends Employee {
    private List<Staff> managedStaff;

    public Organizer(int id, String name, String phoneNumber, char[] email) {
        super(id, name, phoneNumber, email);
        this.managedStaff = new ArrayList<>();
    }

    public void addStaff(Staff staff) {
        managedStaff.add(staff);
    }

    public void removeStaff(Staff staff) {
        managedStaff.remove(staff);
    }

    public List<Staff> getManagedStaff() {
        return managedStaff;
    }

    @Override
    public String toString() {
        return super.toString() + " [Manages " + managedStaff.size() + " Staff Members]";
    }
}