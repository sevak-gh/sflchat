package com.sfl.chat.dto;

/**
 * profie dto.
 *
 * @author Sevak Gharibian
 */
public class ProfileDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String pictureFileName;

    public String getFirstName() {  
        return firstName;    
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {  
        return lastName;    
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPictureFileName() {  
        return pictureFileName;    
    }

    public void setPictureFileName(String pictureFileName) {
        this.pictureFileName = pictureFileName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

