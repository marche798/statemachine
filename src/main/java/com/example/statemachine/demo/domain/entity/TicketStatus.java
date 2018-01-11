package com.example.statemachine.demo.domain.entity;

public enum TicketStatus {

    CREATED("Created", "Created"),
    CREATED_CHOICE("Created Choice", "Created Choice"),
    NEW("New", "New"),
    OPEN("Open", "Open"),
    PENDING("Pending", "Awaiting your reply"),
    ON_HOLD("On Hold", "On Hold"),
    SOLVED("Solved", "Solved"),
    CLOSED("Closed", "Solved");

    private String title;

    private String customerTitle;

    TicketStatus(final String title, final String customerTitle) {
        this.title = title;
        this.customerTitle = customerTitle;
    }

    public String getTitle() {
        return this.title;
    }

    public String getCustomerTitle() {
        return this.customerTitle;
    }

}
