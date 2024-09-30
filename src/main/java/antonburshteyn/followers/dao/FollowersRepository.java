package antonburshteyn.followers.dao;

import antonburshteyn.accounting.model.UserAccount;
import antonburshteyn.followers.model.Follower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;

import java.util.Optional;
import java.util.Set;

public interface FollowersRepository extends JpaRepository<Follower, String> {
    Set<Follower> findByUser(UserAccount user);

    Set<Follower> findByFollower(UserAccount user);

    Optional<Follower> findByFollowerAndUser(UserAccount follower, UserAccount followee);
}
