package com.trivago.insight.model;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class UserStats {

    private String userId;
    private Map<Integer, Integer> hotelClickCountMap = new ConcurrentHashMap<>();
    private Map<Integer, Integer> amenitySelectCountMap = new ConcurrentHashMap<>();

    public UserStats(String userId) {
        this.userId = userId;
    }

    public void recordHotelClick(int hotelId) {
        hotelClickCountMap.compute(hotelId, (key, value) -> value == null ? 1 : value + 1);
    }

    public void recordAmenitySelect(int amenityId) {
        amenitySelectCountMap.compute(amenityId, (key, value) -> value == null ? 1 : value + 1);
    }

    public List<Integer> getTopSelectedAmenities(int topN) {

        return amenitySelectCountMap.entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .limit(topN)
                .map(e -> e.getKey())
                .collect(Collectors.toList());
    }

    public List<Integer> getTopClickedHotels(int topN) {
        // simply scan through the hashmap
        // assume that user only clicks less than a thousand hotel
        // so the map is relatively small and scanning through it is quick
        // and the api are used by 3 teams so this method is not frequently called

        return hotelClickCountMap.entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .limit(topN)
                .map(e -> e.getKey())
                .collect(Collectors.toList());
    }
}