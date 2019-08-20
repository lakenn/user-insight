### Run Instruction

- docker build -t trivago-insight .
- docker run -p 8080:8080 trivago-insight

### Questions and Ans
+ What are the assumptions that you made during the implementation?
    1. Each item count is equal weighted across time
	2. Top N is a varaible provided by the api caller as request time
	3. Number of Hotel clicks / Amenity selections per User are less than a thousand
	4. The api are used by 3 teams so this method is not frequently called

+ What are the performance characteristics of your implementation?
    1.  Think one of the bottleneck is file reading which is IO bound
	2.  Counting -- O(n)
	3.  Sorting -- O(1) map lookup and O(nlogn) fpr sorting where n is the number of items per user

+ If you could load test it, what do you expect to see in the result?
   1.  the response time of the api should be very quick.  Think it can handle hundreds of requests in a second

+ If you had more time, how would you improve your solution?
    1.  Use distributed computing (map-reduce) for counting.  (eg. spark/storm) if there are multiple big files

	2. Use distributed memory eg. apache Ignite or redis to store the count of items

+ What comments would you expect when this goes to a code review?
  1.  I think the program is in good quality given the current requirements

### Bonus
----
+ What other user insights could we possibly generate from this data?
   1. we can find out which hotels/regions are popular across time period (same for amenities)
   eg. the # of user clicks for a hotel within a time interval

   2. we can compute coocurrence between amenities and hotel across time period.
   Thus, we know the correlation between them

+ If you had to update the data source in real time, how would your solution change?
  1. Add a real time data stream subscriber and workers to process the data
  2. Worker updates UserStats concurrently
  3. Add two caches for the sorted result of ConcurrentHashMap in UserStats.java
  4. For each user, when the ConcurrentHashMap in UserStats gets updated, clear the cache.
  5. GetTopN will read from the cache if the cache is not emptpy ; otherwise rebuild cache (sort)
