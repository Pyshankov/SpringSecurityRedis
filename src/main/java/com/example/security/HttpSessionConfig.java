package com.example.security;


import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.HeaderHttpSessionStrategy;
import org.springframework.session.web.http.HttpSessionStrategy;
import redis.clients.jedis.Protocol;
import redis.embedded.RedisServer;

import javax.annotation.PreDestroy;
import java.io.IOException;

/**
 * Created by pyshankov on 11.10.2016.;
 */
@EnableRedisHttpSession
public class HttpSessionConfig {

    private static RedisServer redisServer;

    @Bean
    public JedisConnectionFactory connectionFactory() throws IOException {
        redisServer = new RedisServer(Protocol.DEFAULT_PORT);
        redisServer.start();
        return new JedisConnectionFactory();
    }

    @PreDestroy
    public void destroy() {
        redisServer.stop();
    }

    @Bean
    public HttpSessionStrategy httpSessionStrategy() {
        return new HeaderHttpSessionStrategy();
    }

}