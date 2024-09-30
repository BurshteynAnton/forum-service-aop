package antonburshteyn.followers.service;

import antonburshteyn.followers.dto.FollowerDto;

public interface FollowService {

    boolean followUser(String followerLogin, String followeeLogin);

    boolean unfollowUser(String followerLogin, String followeeLogin);

    FollowerDto getFollowers(String login);

    FollowerDto getFollowings(String login);

}
