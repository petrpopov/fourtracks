package org.springframework.social.foursquare.api;

import java.util.List;

public interface UserOperations {

    /**
     * Retrieve the currently authenticated user profile
     * @return A FoursquareUser
     */
	FoursquareUser getUser();
	
	/**
     * Retrieve the specified FoursquareUser
     * @param userId a Foursquare user ID
     * @return A FoursquareUser
     */
    FoursquareUser getUser(String userId);
	
	/**
	 * Retrieve the authenticated user's leaderboard
	 * @return A Leaderboard
	 */
	Leaderboard getLeaderboard();
	
	/**
     * Retrieve the authenticated user's leaderboard, only returning the user and neighbors in rank.
     * Number of friends' scores to return that are adjacent to the authenticated user's score, in 
     * ranked order. The current user's score is returned as well. An odd number of neighbors returns 
     * one more user ahead of the current user than behind. If the current user is at the top of the 
     * leaderboard, only the neighbors behind the current user are returned. If the current user is 
     * at the bottom of the leaderboard, only the neighbors ahead of the current user are returned.
     * @param neighbors The number of friends' scores to return that are adjacent in ranked order.
     * @return A Leaderboard
     */
	Leaderboard getLeaderboard(int neighbors);
	
	/**
	 * Get a list of users with whom the authenticated user has a pending friend request
	 * @return A list of FoursquareUser objects
	 */
	List<FoursquareUser> getRequests();
	
	/**
	 * Search for users by a combination of parameters
	 * @param phoneNumbers A list of phone numbers
	 * @param emailAddresses A list of email addresses
	 * @param twitterHandles A list of Twitter handles
	 * @param facebookUserIds A list of Facebook user IDs
	 * @return A UserSearchResults object containg matching users and a graph of unmatched criteria
	 */
	UserSearchResponse search(List<String> phoneNumbers, List<String> emailAddresses,
                              List<String> twitterHandles, List<String> facebookUserIds);
	
	/**
	 * Search for users by name.
	 * @param name A users name
	 * @return A UserSearchResults object containg matching users and a graph of unmatched criteria
	 */
	UserSearchResponse searchByName(String name);
	
	/**
	 * Searches the specified Twitter handle's followers for users who have Foursquare accounts.
	 * @param name A Twitter handle
     * @return A UserSearchResults object containg matching users and a graph of unmatched criteria
	 */
	UserSearchResponse searchTwitterFriends(String twitterHandle);
	
	/**
	 * Retrieve badges for the authenticated user
	 * @return A BadgesResponse object
	 */
	BadgesResponse getBadges();
	
	/**
	 * Retrieve badges for the specified user
	 * @param userId A Foursquare user ID
	 * @return	A BadgesReponse object
	 */
	BadgesResponse getBadges(String userId);
	
	/**
	 * Retrieve the authenticated user's checkin history
	 * @return CheckinInfo
	 */
	CheckinInfo getCheckins();
	
	/**
	 * Retrieve the specified user's checkin history
	 * @param userId A Foursquare user ID
	 * @return CheckinInfo
	 */
	CheckinInfo getCheckins(String userId);

    CheckinInfo getCheckinsByApi(String userId);
	
	/**
	 * Retrieve the specified user's checkin history
	 * @param userId A Foursquare user ID
	 * @param limit Number of results to return, up to 250.
	 * @param offset The number of results to skip. Used to page through results.
	 * @return CheckinInfo
	 */
	CheckinInfo getCheckins(String userId, int limit, int offset);
	
	/**
	 * Retrieve the specified user's checkin history and filter by timestamp
	 * @param userId A Foursquare user ID
	 * @param afterTimeStamp Seconds since epoch.
	 * @param beforeTimeStamp Seconds since epoch. 
	 * @param limit Number of results to return, up to 250.
	 * @param offset The number of results to skip. Used to page through results.
	 * @return CheckinInfo
	 */
	CheckinInfo getCheckins(String userId, int afterTimestamp, int beforeTimestamp, int limit, int offset);
	
	/**
	 * Retrieve the authenticated user's checkin history
	 * @param limit Number of results to return, up to 250.
	 * @param offset The number of results to skip. Used to page through results.
	 * @return CheckinInfo
	 */
	CheckinInfo getCheckins(int limit, int offset);
	
	/**
	 * Retrieve the authenticated user's checkin history and filter by timestamp
	 * @param afterTimeStamp Seconds since epoch.
	 * @param beforeTimeStamp Seconds since epoch. 
	 * @param limit Number of results to return, up to 250.
	 * @param offset The number of results to skip. Used to page through results.
	 * @return CheckinInfo
	 */
	CheckinInfo getCheckins(int afterTimestamp, int beforeTimestamp, int limit, int offset);
	
	/**
	 * Retrieve the authenticated user's friends
	 * @return	Friends object
	 */
	Friends getFriends();
	
	/**
	 * Retrieve the authenticated user's friends
	 * @param limit	Number of results to return, up to 500
	 * @param offset Page offset
	 * @return	Friends object
	 */
	Friends getFriends(int limit, int offset);
	
	/**
	 * Retrieve the specified user's friends
	 * @param userId A Foursquare user ID
	 * @return	Friends object
	 */
	Friends getFriends(String userId);
	
	/**
	 * Retrieve the specified user's friends
	 * @param userId A Foursquare user ID
	 * @param limit	Number of results to return, up to 500
	 * @param offset Page offset
	 * @return	Friends object
	 */
	Friends getFriends(String userId, int limit, int offset);
	
	/**
	 * Retrieve recent tips for the authenticated user
	 * @param limit	limit number of results, up to 500
	 * @param offset Page offset
	 * @return	Tips object
	 */
	Tips getRecentTips(int limit, int offset);
	
	/**
	 * Retrieve recent tips for the specified user
	 * @param userId A Foursquare user ID
	 * @param limit	limit number of results, up to 500
	 * @param offset Page offset
	 * @return	Tips object
	 */
	Tips getRecentTips(String userId, int limit, int offset);
	
	/**
	 * Retrieve popular tips for the authenticated user
	 * @param limit	limit number of results, up to 500
	 * @param offset Page offset
	 * @return	Tips object
	 */
	Tips getPopularTips(int limit, int offset);
	
	/**
	 * Retrieve popular tips for the specified user
	 * @param userId A Foursquare user ID
	 * @param limit	limit number of results, up to 500
	 * @param offset Page offset
	 * @return	Tips object
	 */
	Tips getPopularTips(String userId, int limit, int offset);
	
	/**
	 * Retrieve nearby tips for the authenticated user
	 * @param latitude Latitude
	 * @param longitude Longitude 
	 * @param limit	limit number of results, up to 500
	 * @param offset Page offset
	 * @return	Tips object
	 */
	Tips getNearbyTips(double latitude, double longitude, int limit, int offset);
	
	/**
	 * Retrieve nearby tips for the specified user
	 * @param userId A Foursquare user ID
	 * @param latitude Latitude
	 * @param longitude Longitude 
	 * @param limit	limit number of results, up to 500
	 * @param offset Page offset
	 * @return	Tips object
	 */
	Tips getNearbyTips(String userId, double latitude, double longitude, int limit, int offset);
	
	/**
	 * Get recent todos for the authenticated user
	 * @param userId A Foursquare user ID
	 * @return Todos object
	 */
	Todos getRecentTodos();
	
	/**
	 * Get recent todos for the specified user
	 * @param userId A Foursquare user ID
	 * @return Todos object
	 */
	Todos getRecentTodos(String userId);
	
	/**
	 * Get nearby todos for the authenticated user
	 * @param latitude	Latitude
	 * @param longitude	Longitude
	 * @return Todos object
	 */
	Todos getNearbyTodos(double latitude, double longitude);
	
	/**
	 * Get nearby todos for the specified user
	 * @param userId A Foursquare user ID
	 * @param latitude	Latitude
	 * @param longitude	Longitude
	 * @return Todos object
	 */
	Todos getNearbyTodos(String userId, double latitude, double longitude);
	
	/**
	 * Get popular todos for the authenticated user
	 * @return Todos object
	 */
	Todos getPopularTodos();
	
	/**
	 * Get popular todos for the specified user
	 * @param userId A Foursquare user ID
	 * @return Todos object
	 */
	Todos getPopularTodos(String userId);
	
	/**
	 * Retrieve venues for the authenticated user with how many visits
	 * @param beforeTimestamp	Seconds since epoch
	 * @param afterTimestamp	Seconds after epoch
	 * @param categoryId	Foursquare venue category ID
	 * @return VenueHistory
	 */
	VenueHistory getVenueHistory(long beforeTimestamp, long afterTimestamp, String categoryId);
	
	/**
	 * Retrieve venues for the specified user with how many visits
	 * @param userId	A Foursquare user ID
	 * @param beforeTimestamp	Seconds since epoch
	 * @param afterTimestamp	Seconds after epoch
	 * @param categoryId	Foursquare venue category ID
	 * @return	VenueHistory
	 */
	VenueHistory getVenueHistory(String userId, long beforeTimestamp, long afterTimestamp, String categoryId);
	
	void requestFriend(String userId);
	
	void removeFriend(String userId);
	
	void approveFriend(String userId);
	
	void denyFriend(String userId);
	
	void setPings(String userId, boolean value);
	
	public static final String USERS_ENDPOINT = "users/";
}
