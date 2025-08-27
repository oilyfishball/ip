package mypackage.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadlines extends Task{
    private LocalDate by;

    public Deadlines(String name, LocalDate by) {
        super(name);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by " + this.getBy()+ ")";
    }

    public String getBy() {
        return by.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
//        return this.by;
    }

    public String getSaveBy() {
        return by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
