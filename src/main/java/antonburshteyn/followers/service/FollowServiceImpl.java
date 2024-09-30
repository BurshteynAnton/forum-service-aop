package antonburshteyn.followers.service;

import antonburshteyn.accounting.dao.UserAccountRepository;
import antonburshteyn.accounting.model.UserAccount;
import antonburshteyn.followers.dao.FollowersRepository;
import antonburshteyn.followers.dto.FollowerDto;
import antonburshteyn.followers.model.Follower;
import antonburshteyn.followers.dto.exceptions.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FollowServiceImpl implements FollowService {

    private final FollowersRepository followersRepository;
    private final UserAccountRepository userAccountRepository;

    @Override
    public boolean followUser(String followerLogin, String followeeLogin) {
        UserAccount follower = userAccountRepository.findByLogin(followerLogin)
                .orElseThrow(() -> new UserNotFoundException("Follower not found: " + followerLogin));
        UserAccount followee = userAccountRepository.findByLogin(followeeLogin)
                .orElseThrow(() -> new UserNotFoundException("Followee not found: " + followeeLogin));

        Follower newFollow = new Follower(follower, followee);
        followersRepository.save(newFollow);
        return true;
    }

    @Override
    public boolean unfollowUser(String followerLogin, String followeeLogin) {
        UserAccount follower = userAccountRepository.findByLogin(followerLogin)
                .orElseThrow(() -> new UserNotFoundException("Follower not found: " + followerLogin));
        UserAccount followee = userAccountRepository.findByLogin(followeeLogin)
                .orElseThrow(() -> new UserNotFoundException("Followee not found: " + followeeLogin));
        Follower follow = followersRepository.findByFollowerAndUser(follower, followee)
                .orElseThrow(() -> new IllegalArgumentException("No such following relation exists"));
        followersRepository.delete(follow);
        return true;
    }

    @Override
    public FollowerDto getFollowers(String login) {
        if (login == null || login.trim().isEmpty()) {
            throw new IllegalArgumentException("Login cannot be null or empty");
        }
        UserAccount user = userAccountRepository.findByLogin(login)
                .orElseThrow(() -> new UserNotFoundException("User not found: " + login));
        Set<Follower> followers = followersRepository.findByUser(user);

        Set<String> followerNames = followers.stream()
                .map(follower -> follower.getFollower().getLogin())
                .collect(Collectors.toSet());

        return new FollowerDto(login, followerNames, null);
    }

    @Override
    public FollowerDto getFollowings(String login) {
        if (login == null || login.trim().isEmpty()) {
            throw new IllegalArgumentException("Login cannot be null or empty");
        }
        UserAccount user = userAccountRepository.findByLogin(login)
                .orElseThrow(() -> new UserNotFoundException("User not found: " + login));
        Set<Follower> followings = followersRepository.findByFollower(user);

        Set<String> followingNames = followings.stream()
                .map(following -> following.getUser().getLogin())
                .collect(Collectors.toSet());

        return new FollowerDto(login, null, followingNames);
    }
}
