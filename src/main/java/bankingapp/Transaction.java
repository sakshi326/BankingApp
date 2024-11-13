package bankingapp;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class Transaction {
    private String id;
    private LocalDateTime date;
    private String type;
    private double amount;

    public Transaction(String type, double amount) {
        this.id = UUID.randomUUID().toString();
        this.date = LocalDateTime.now();
        this.type = type;
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return String.format("%s | %s | %s | Rs.%.2f", id, date.format(formatter), type, amount);
    }
}