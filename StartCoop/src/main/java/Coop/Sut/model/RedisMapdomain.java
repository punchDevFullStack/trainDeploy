package Coop.Sut.model;

import java.io.Serializable;

/**
 * Created by mhiew on 6/3/2560.
 */
public interface RedisMapdomain extends Serializable{
    public abstract String getKey();
    public abstract String getHashKey();
}
