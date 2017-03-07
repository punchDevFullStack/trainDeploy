package Coop.Sut.service;

import Coop.Sut.model.SearchSession;
import Coop.Sut.model.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by mhiew on 6/3/2560.
 */
@Service
public class RedisService {

    @Autowired
    private RedisTemplate redisTemplate;

    public void insert(Session session){
        redisTemplate.opsForHash().put(session.getKey(), session.getHashKey(), session);
    }

    public void delete(SearchSession searchSession){
        redisTemplate.opsForHash().delete(searchSession.getKey(), searchSession.getHashkey());
    }

}
