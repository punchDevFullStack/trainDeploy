package Coop.Sut.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.RandomStringUtils;

public class Session implements RedisMapdomain{

    private String sessionId= RandomStringUtils.randomAlphanumeric(40);
    //private String KEY = RandomStringUtils.random(5);
    public static final String KEY = "session";
    private String firstName;
    private String lastName;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

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


    @Override
    @JsonIgnore
    public String getKey() {
        return KEY;
    }

    @Override
    @JsonIgnore
    public String getHashKey() {
      return getSessionId();
    }
}
