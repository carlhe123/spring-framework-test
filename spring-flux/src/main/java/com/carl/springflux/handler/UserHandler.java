package com.carl.springflux.handler;

import com.carl.springflux.model.User;
import com.carl.springflux.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @Description TODO Carl写点注释吧！
 * @Author carl.he
 * @Date 2019/5/27 9:54
 * @Version 1.0
 **/
@Component
public class UserHandler {

    /** 将用户保存在map中 key为用户ID */
    private final ConcurrentMap<Integer, User> map = new ConcurrentHashMap<>();

    public Mono<ServerResponse> findAll(ServerRequest request){
        Collection users = map.values();
        return ServerResponse.ok().body(Mono.just(users),Collection.class);

    }

    public Mono<ServerResponse> saveUser(ServerRequest request){
        Mono<User> userMono = request.bodyToMono(User.class);
        System.out.println(userMono.block());
        return ServerResponse.ok().body(userMono,User.class);
    }
}
