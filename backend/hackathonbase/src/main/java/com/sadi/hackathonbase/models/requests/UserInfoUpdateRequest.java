package com.sadi.hackathonbase.models.requests;


public class UserInfoUpdateRequest {
    private String fullName;
    private String occupation;
    private String hobbies;
    private String profilePic;

    public UserInfoUpdateRequest() {
    }

    public UserInfoUpdateRequest(String fullName, String occupation, String hobbies, String profilePic) {
        this.fullName = fullName;
        this.occupation = occupation;
        this.hobbies = hobbies;
        this.profilePic = profilePic;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getHobbies() {
        return hobbies;
    }

    public void setHobbies(String hobbies) {
        this.hobbies = hobbies;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    @Override
    public String toString() {
        return "UserInfoUpdateRequest{" +
                "fullName='" + fullName + '\'' +
                ", occupation='" + occupation + '\'' +
                ", hobbies='" + hobbies + '\'' +
                ", profilePic='" + profilePic + '\'' +
                '}';
    }
}
