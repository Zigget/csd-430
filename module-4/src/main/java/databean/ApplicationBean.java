package databean;

import java.io.Serializable;

/**
 * ApplicationBean - holds job application data.
 * Implements Serializable as required for JavaBeans.
 */
public class ApplicationBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private String fullName;
    private String email;
    private int experience;
    private String position;
    private String reason;

    public ApplicationBean() {}

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public int getExperience() { return experience; }
    public void setExperience(int experience) { this.experience = experience; }

    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }

    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }
}