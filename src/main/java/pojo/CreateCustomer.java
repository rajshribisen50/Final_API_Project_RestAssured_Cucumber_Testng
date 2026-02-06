package pojo;

import java.util.Map;

public class CreateCustomer {

    private String name;
    private String email;
    private String contact;
    private String fail_existing;
    private String gstin;
    private Map<String, String> notes;

    public CreateCustomer() {
    }

    // Getters & Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getFail_existing() {
        return fail_existing;
    }

    public void setFail_existing(String fail_existing) {
        this.fail_existing = fail_existing;
    }

    public String getGstin() {
        return gstin;
    }

    public void setGstin(String gstin) {
        this.gstin = gstin;
    }

    public Map<String, String> getNotes() {
        return notes;
    }

    public void setNotes(Map<String, String> notes) {
        this.notes = notes;
    }
}
