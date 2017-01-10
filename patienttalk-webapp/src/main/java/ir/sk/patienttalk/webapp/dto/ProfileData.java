package ir.sk.patienttalk.webapp.dto;

import ir.sk.patienttalk.common.persistence.PersistenceException;
import ir.sk.patienttalk.common.persistence.file.web.FileItem;
import ir.sk.patienttalk.model.domain.GenderEnum;
import ir.sk.patienttalk.model.domain.Profile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 1/10/2017.
 */
public class ProfileData {

    private long id;
    @NotNull
    @Size(min = 2, max = 50)
    private String name;
    @NotNull
    @Size(min = 2, max = 80)
    private String family;
    @Size(min = 2, max = 100)
    private String nickname;
    private GenderEnum genderEnum = GenderEnum.UNKNOWN;
    private String location = "LOCATION"; // TODO: 1/10/2017  
    private FileItem oldPicture;
    private String picture; // TODO: 1/10/2017

    public ProfileData() {
    }

    public ProfileData sync(Profile profile) throws PersistenceException {
        if(profile != null) {
            this.id = profile.getUser().getId();
            this.name = profile.getName();
            this.family = profile.getFamily();
            this.nickname = profile.getNickName();
            this.genderEnum = profile.getGenderEnum();
            this.oldPicture = profile.getPicture();
            // TODO: 1/10/2017 Other fields needed
        }
        return this;
    }

    public Profile fill(Profile profile) {
        if(profile == null)
            profile = new Profile();
        profile.setName(name);
        profile.setFamily(family);
        profile.setNickName(nickname);
        profile.setGenderEnum(genderEnum);
        profile.setLocation(location);
        // TODO: 1/10/2017 Other fields needed
        return profile;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public GenderEnum getGenderEnum() {
        return genderEnum;
    }

    public void setGenderEnum(GenderEnum genderEnum) {
        this.genderEnum = genderEnum;
    }

    public FileItem getOldPicture() {
        return oldPicture;
    }

    public void setOldPicture(FileItem oldPicture) {
        this.oldPicture = oldPicture;
    }

}
