package com.ziko.userservice.service;

import com.ziko.userservice.dto.UserDto;
import com.ziko.userservice.entity.User;
import com.ziko.userservice.repository.UserRepository;
import com.ziko.userservice.util.EntityDtoUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 13 Nov, 2023
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public Flux<UserDto> getAllUsers(){
        return userRepository.findAll()
                .map(EntityDtoUtil::toDto);
    }

    public Mono<UserDto> getOne(Integer id){
        return userRepository.findById(id)
                .map(EntityDtoUtil::toDto);
    }

    public Mono<UserDto> createUser(Mono<UserDto> userDtoMono){
        return userDtoMono
                .flatMap(userDto -> {
                    log.info("USERDTO: {}\n{}", userDto, userDto.toString());
                    User entity = EntityDtoUtil.toEntity(userDto);
                    return this.userRepository.save(entity);
                }).map(EntityDtoUtil::toDto);
    }

    public Mono<UserDto> updateUser(int id, Mono<UserDto> userDtoMono){
        return this.userRepository.findById(id)
                .flatMap(user -> userDtoMono
                        .map(EntityDtoUtil::toEntity)
                        .doOnNext(e -> e.setId(id))
                )
                .flatMap(this.userRepository::save)
                .map(EntityDtoUtil::toDto);

    }

    public Mono<Void> delete(Integer id){
        return userRepository.deleteById(id);
    }

}
