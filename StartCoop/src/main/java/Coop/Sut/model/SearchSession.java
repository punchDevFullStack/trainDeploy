package Coop.Sut.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by mhiew on 7/3/2560.
 */
public class SearchSession {
    private String key;
    private String hashkey;

    public String getHashkey() {
        return hashkey;
    }

    public void setHashkey(String hashkey) {
        this.hashkey = hashkey;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
