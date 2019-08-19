package com.trivago.insight.controller;

import com.trivago.insight.model.UserRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("/api/user-profile")
public class UserProfileController {

    @Autowired
    private UserRegistry userRegistry;

    @RequestMapping(value = "/top-amenities/{userid}", method=GET)
    public List<Integer> getTopAmenitiesByUserId(@PathVariable("userId") String userId, @RequestParam(defaultValue = "10") int topN){
        return userRegistry.getUserStats(userId).getTopSelectedAmenities(topN);
    }

    @RequestMapping(value = "/top-hotels/{userId}", method=GET)
    public List<Integer> getTopHotelsByUserId(@PathVariable("userId") String userId, @RequestParam(defaultValue = "10") int topN){
        return userRegistry.getUserStats(userId).getTopClickedHotels(topN);
    }
}
