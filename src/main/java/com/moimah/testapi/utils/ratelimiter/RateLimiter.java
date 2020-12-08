package com.moimah.testapi.utils.ratelimiter;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.*;


/**
 * Implements a rate limiter that read from properties file
 * a interval, unit and capacity of rate limiter,
 * use rateLimiter.getBucket().tryConsume(1)
 */
@Component
public class RateLimiter {
    

    private final Bucket bucket;
    private  InputStream inputStream;
    private static final String PROPERTIES_FILE = "application.properties";

    public RateLimiter() {

        HashMap<String, Object> properties = properties = getPropertiesValues(PROPERTIES_FILE);

        long interval = (long) properties.get("interval");
        String unit = (String) properties.get("unit");
        long capacity = (long) properties.get("capacity");


        Duration duration = getDuration(unit, interval);
        Bandwidth limit = Bandwidth.simple(capacity, duration);
        bucket = Bucket4j.builder()
                .addLimit(limit)
                .build();
    }

    /**
     * Create duration by unit and interval
     * default value seconds
     * @param unit
     * @param interval
     * @return Duration.ofUnit(interval)
     */
    private Duration getDuration (String unit, long interval){
        switch(unit)
        {
            case "s":
                return Duration.ofSeconds(interval);
            case "m":
                return Duration.ofMinutes(interval);
            case "h":
                return Duration.ofHours(interval);
            case "d":
                return Duration.ofDays(interval);
            default:
                return Duration.ofSeconds(interval);
        }
    }

    /**
     *  Reaf from properties
     * @return map with interval, unit, capacity
     */
    private HashMap<String, Object> getPropertiesValues(String propertiesFile) {

        HashMap<String, Object> properties = new HashMap<String, Object>();

            try {
                Properties prop = new Properties();

               inputStream  = getClass().getClassLoader().getResourceAsStream(propertiesFile);

                if (inputStream != null) {
                    prop.load(inputStream);
                } else {
                    throw new FileNotFoundException("property file '" + propertiesFile + "' not found in the classpath");
                }

                long interval = prop.getProperty("ratelimiter.interval") != null ? Long.parseLong(prop.getProperty("ratelimiter.interval")) : 1;
                String unit = prop.getProperty("ratelimiter.unit") != null ? prop.getProperty("ratelimiter.unit") : "s";
                long capacity = prop.getProperty("ratelimiter.capacity") != null ? Long.parseLong(prop.getProperty("ratelimiter.capacity")) : 1;

                properties.put("interval", interval);
                properties.put("unit", unit);
                properties.put("capacity", capacity);

            } catch (Exception e) {
                System.out.println("Exception: " + e);
            } finally {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return properties;
        }



    public Bucket getBucket() {
        return bucket;
    }

}
