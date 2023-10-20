package com.ziko.webfluxdemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 20 Oct, 2023
 */

@RestController
@RequestMapping(path = "jobs")
public class ParamsController {

    @GetMapping("search")
    public Flux<Integer> searchJobs(@RequestParam("count") int count,
                                    @RequestParam("page") int page){

        return Flux.just(count, page);
    }
}
