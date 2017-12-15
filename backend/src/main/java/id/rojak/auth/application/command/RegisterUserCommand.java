package id.rojak.auth.application.command;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by inagi on 8/3/17.
 */
public class RegisterUserCommand {

    @NotNull
    @JsonProperty("username")
    private String username;

    @NotNull
    @JsonProperty("password")
    private String password;

    @NotNull
    @JsonProperty("first_name")
    private String firstName;

    @NotNull
    @JsonProperty("last_name")
    private String lastName;

    @NotNull
    @JsonProperty("enabled")
    private boolean enabled;

    @JsonProperty("enabled_start_date")
    private Date startDate;

    @JsonProperty("enabled_end_date")
    private Date endDate;

    @NotNull
    @JsonProperty("email_address")
    private String emailAddress;

    @NotNull
    @JsonProperty("primary_telephone")
    private String primaryTelephone;

    @NotNull
    @JsonProperty("secondary_telephone")
    private String secondaryTelephone;

    @NotNull
    @JsonProperty("address_street_address")
    private String addressStreetAddress;

    @NotNull
    @JsonProperty("address_city")
    private String addressCity;

    @NotNull
    @JsonProperty("address_province")
    private String addressStateProvince;

    @NotNull
    @JsonProperty("address_postal_code")
    private String addressPostalCode;

    @NotNull
    @JsonProperty("address_country")
    private String addressCountry;

    protected RegisterUserCommand() {}

    public RegisterUserCommand( String username, String password, String firstName,
                               String lastName, boolean enabled, Date startDate, Date endDate, String emailAddress, String primaryTelephone,
                               String secondaryTelephone, String addressStreetAddress, String addressCity, String addressStateProvince,
                               String addressPostalCode, String addressCountry) {

        super();

        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.enabled = enabled;
        this.startDate = startDate;
        this.endDate = endDate;
        this.emailAddress = emailAddress;
        this.primaryTelephone = primaryTelephone;
        this.secondaryTelephone = secondaryTelephone;
        this.addressStreetAddress = addressStreetAddress;
        this.addressCity = addressCity;
        this.addressStateProvince = addressStateProvince;
        this.addressPostalCode = addressPostalCode;
        this.addressCountry = addressCountry;
    }


    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Date getStartDate() {
        return this.startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return this.endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getEmailAddress() {
        return this.emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPrimaryTelephone() {
        return this.primaryTelephone;
    }

    public void setPrimaryTelephone(String primaryTelephone) {
        this.primaryTelephone = primaryTelephone;
    }

    public String getSecondaryTelephone() {
        return this.secondaryTelephone;
    }

    public void setSecondaryTelephone(String secondaryTelephone) {
        this.secondaryTelephone = secondaryTelephone;
    }

    public String getAddressStreetAddress() {
        return this.addressStreetAddress;
    }

    public void setAddressStreetAddress(String addressStreetAddress) {
        this.addressStreetAddress = addressStreetAddress;
    }

    public String getAddressCity() {
        return this.addressCity;
    }

    public void setAddressCity(String addressCity) {
        this.addressCity = addressCity;
    }

    public String getAddressStateProvince() {
        return this.addressStateProvince;
    }

    public void setAddressStateProvince(String addressStateProvince) {
        this.addressStateProvince = addressStateProvince;
    }

    public String getAddressPostalCode() {
        return this.addressPostalCode;
    }

    public void setAddressPostalCode(String addressPostalCode) {
        this.addressPostalCode = addressPostalCode;
    }

    public String getAddressCountry() {
        return this.addressCountry;
    }

    public void setAddressCountry(String addressCountry) {
        this.addressCountry = addressCountry;
    }



}
