package com.trivago.insight.util;

import com.trivago.insight.model.UserRegistry;
import com.trivago.insight.model.UserStats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

/**
 * Count the number of hotel or amenity per User
 */

@Component
public class FrequencyCountUtil {

    @Autowired
    private UserRegistry userRegistry;

    public void updateHotelClick(String userId, int hotelId){
        UserStats stats = userRegistry.getUserStats(userId);
        stats.recordHotelClick(hotelId);
    }

    public void updateUserSelection(String userId, int amenityId){
        UserStats stats = userRegistry.getUserStats(userId);
        stats.recordAmenitySelect(amenityId);
    }
}
