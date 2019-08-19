package com.trivago.insight.model;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class UserStats {

    private String userId;
    private Map<Integer, AtomicInteger> hotelClickCountMap = new ConcurrentHashMap<>();
    private Map<Integer, AtomicInteger> amenitySelectCountMap = new ConcurrentHashMap<>();


    public UserStats(String userId) {
        this.userId = userId;
    }

    public void recordHotelClick(int hotelId) {
        hotelClickCountMap.computeIfAbsent(hotelId, key -> new AtomicInteger()).incrementAndGet();
    }

    public void recordAmenitySelect(int amenityId) {
        amenitySelectCountMap.computeIfAbsent(amenityId, key -> new AtomicInteger()).incrementAndGet();
    }

    public List<Integer> getTopSelectedAmenities(int topN) {

        List<Integer> result = amenitySelectCountMap.entrySet()
                .stream()
                .sorted((amenity, amenity2) -> Integer.compare(amenity2.getValue().get(), amenity.getValue().get()))
                .limit(topN)
                .map(e -> e.getKey())
                .collect(Collectors.toList());

        return result;
    }

    public List<Integer> getTopClickedHotels(int topN) {
        // simply scan through the hashmap
        // assume that user only clicks less than a thousand hotel
        // so the map is relatively small and scanning through it is quick
        // and the api are used by 3 teams so this method is not frequently called

        List<Integer> result = hotelClickCountMap.entrySet()
                .stream()
                .sorted((hotel, hotel2) -> Integer.compare(hotel2.getValue().get(), hotel.getValue().get()))
                .limit(topN)
                .map(e -> e.getKey())
                .collect(Collectors.toList());

        return result;
    }
}