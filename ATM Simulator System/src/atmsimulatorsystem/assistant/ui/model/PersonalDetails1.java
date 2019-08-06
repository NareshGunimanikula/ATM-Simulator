package atmsimulatorsystem.assistant.ui.model;

public class PersonalDetails1 {
    
    
    private long personalDetailsID;
    private String first_name;
    private String middle_name;
    private String last_name;
    private String father_name;
    private String gender;
    private String emailAddress;
    private String maritalStatus;
    private String address;
    private String city;
    private String pinCode;
    private String state;

    public PersonalDetails1(long personalDetailsID, String first_name, String last_name, String middle_name, String father_name, String gender, String emailAddress, String maritalStatus, String address, String city, String pinCode, String state) {
        this.personalDetailsID = personalDetailsID;
        this.first_name = first_name;
        this.last_name = last_name;
        this.middle_name = middle_name;
        this.father_name = father_name;
        this.gender = gender;
        this.emailAddress = emailAddress;
        this.maritalStatus = maritalStatus;
        this.address = address;
        this.city = city;
        this.pinCode = pinCode;
        this.state = state;
    }

    public long getPersonalDetailsID() {
        return personalDetailsID;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getMiddle_name() {
        return middle_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getFather_name() {
        return father_name;
    }

    public String getGender() {
        return gender;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getPinCode() {
        return pinCode;
    }

    public String getState() {
        return state;
    }

}
