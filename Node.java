import java.util.Date;

public class Node {
    private String flight_no;
    private Date arrival_time;

    public String getFlight_no() {
        return flight_no;
    }

    public void setFlight_no(String flight_no) {
        this.flight_no = flight_no;
    }

    public Date getArrival_time() {
        return arrival_time;
    }

    public void setArrival_time(Date arrival_time) {
        this.arrival_time = arrival_time;
    }

    public Node() {
        this.flight_no = "";
        this.arrival_time = new Date();
    }

    public Node(String flight_no, Date arrival_time) {
        this.flight_no = flight_no;
        this.arrival_time = arrival_time;
    }

    @Override
    public String toString() {
        return "Node{" +
                "flight_no='" + flight_no + '\'' +
                ", arrival_time=" + arrival_time +
                '}';
    }
}