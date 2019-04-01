package beans.form;

public class BookingForm {
    private String email;
    private Integer seat;
    private String eventName;
    private String dateTime;
    private String auditoriumName;

    public BookingForm() {
    }

    public BookingForm(String email, Integer seat, String eventName, String dateTime, String auditoriumName) {
        this.email = email;
        this.seat = seat;
        this.eventName = eventName;
        this.dateTime = dateTime;
        this.auditoriumName = auditoriumName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getSeat() {
        return seat;
    }

    public void setSeat(Integer seat) {
        this.seat = seat;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getAuditoriumName() {
        return auditoriumName;
    }

    public void setAuditoriumName(String auditoriumName) {
        this.auditoriumName = auditoriumName;
    }
}
