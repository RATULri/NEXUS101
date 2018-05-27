package nexus101.network.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StudentInfo {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("blood_group")
    @Expose
    private String bloodGroup;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("date_of_birth")
    @Expose
    private String dateOfBirth;
    @SerializedName("registration_number")
    @Expose
    private String registrationNumber;
    @SerializedName("roll_number")
    @Expose
    private String rollNumber;
    @SerializedName("session")
    @Expose
    private String session;
    @SerializedName("attached_hall")
    @Expose
    private String attachedHall;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    /**
     * No args constructor for use in serialization
     *
     */
    public StudentInfo() {
    }

    /**
     *
     * @param updatedAt
     * @param dateOfBirth
     * @param id
     * @param registrationNumber
     * @param attachedHall
     * @param bloodGroup
     * @param session
     * @param address
     * @param createdAt
     * @param userId
     * @param rollNumber
     */
    public StudentInfo(Integer id, String userId, String bloodGroup, String address, String dateOfBirth, String registrationNumber, String rollNumber, String session, String attachedHall, String createdAt, String updatedAt) {
        super();
        this.id = id;
        this.userId = userId;
        this.bloodGroup = bloodGroup;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.registrationNumber = registrationNumber;
        this.rollNumber = rollNumber;
        this.session = session;
        this.attachedHall = attachedHall;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String getAttachedHall() {
        return attachedHall;
    }

    public void setAttachedHall(String attachedHall) {
        this.attachedHall = attachedHall;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

}