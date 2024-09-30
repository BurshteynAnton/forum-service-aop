package antonburshteyn.followers.controller;

import antonburshteyn.followers.dto.FollowerDto;
import antonburshteyn.followers.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/followers")
public class FollowersController {

    final FollowService followService;

    // Метод для подписки
    @PostMapping("/{login}/follow")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void follow(Principal principal, @PathVariable String login) {
        followService.followUser(principal.getName(), login);
    }

    // Метод для отписки
    @DeleteMapping("/{login}/unfollow")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void unfollow(Principal principal, @PathVariable String login) {
        followService.unfollowUser(principal.getName(), login);
    }

    // Получение подписчиков пользователя
    @GetMapping("/{login}/followers")
    public FollowerDto getFollowers(@PathVariable String login) {
        return followService.getFollowers(login);
    }

    // Получение подписок пользователя
    @GetMapping("/{login}/following")
    public FollowerDto getFollowing(@PathVariable String login) {
        return followService.getFollowings(login);
    }
}
