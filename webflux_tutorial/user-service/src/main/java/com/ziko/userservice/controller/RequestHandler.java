package com.ziko.userservice.controller;

import com.ziko.userservice.dto.UserDto;
import com.ziko.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Optional;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 08 Nov, 2023
 */

@Component
@RequiredArgsConstructor
public class RequestHandler {

    private final UserService service;

    public Mono<ServerResponse> getAllHandler(ServerRequest serverRequest){
        return ServerResponse.ok().body(this.service.getAllUsers(), UserDto.class);
    }

    public Mono<ServerResponse> getByIdHandler(ServerRequest serverRequest){
        String id = serverRequest.pathVariable("id");
        return this.service.getOne(Integer.parseInt(id))
                .flatMap(UserDto -> ServerResponse.ok().bodyValue(UserDto))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> insertUserHandler(ServerRequest serverRequest){
        Mono<UserDto> userDtoMono = serverRequest.bodyToMono(UserDto.class);
        return ServerResponse.ok().body(this.service.createUser(userDtoMono), UserDto.class);
    }

    public Mono<ServerResponse> updateUserHandler(ServerRequest serverRequest){
        String id = serverRequest.pathVariable("id");
        Mono<UserDto> userDtoMono = serverRequest.bodyToMono(UserDto.class);

        return this.service.updateUser(Integer.parseInt(id), userDtoMono)
                .flatMap(userDto -> ServerResponse.ok().bodyValue(userDto))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> deleteUserHandler(ServerRequest serverRequest){
        String id = serverRequest.pathVariable("id");
        return ServerResponse.ok().body(this.service.delete(Integer.parseInt(id)), new ParameterizedTypeReference<>() {});
    }
}
