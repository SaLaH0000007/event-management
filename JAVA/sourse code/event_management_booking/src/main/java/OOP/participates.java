package OOP;

public class participates {
    private String name;
    private int participant_ID;
    private int ticket_id;
    private char[] email;
    private String role_Name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getParticipant_ID() {
        return participant_ID;
    }

    public void setParticipant_ID(int participant_ID) {
        this.participant_ID = participant_ID;
    }

    public int getTicket_id() {
        return ticket_id;
    }

    public void setTicket_id(int ticket_id) {
        this.ticket_id = ticket_id;
    }

    public char[] getEmail() {
        return email;
    }

    public void setEmail(char[] email) {
        this.email = email;
    }

    public String getRole_Name() {
        return role_Name;
    }

    public void setRole_Name(String role_Name) {
        this.role_Name = role_Name;
    }

    public participates(String name, int participant_ID, int ticket_id, char[] email, String role_Name) {
        this.name = name;
        this.participant_ID = participant_ID;
        this.ticket_id = ticket_id;
        this.email = email;
        this.role_Name = role_Name;
    }
}
