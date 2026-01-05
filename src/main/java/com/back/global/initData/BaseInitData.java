package com.back.global.initData;

import com.back.domain.post.post.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class BaseInitData {
    private final PostService postService;

    @Bean
    public ApplicationRunner baseInitDataRunner (){
        return args->{
            System.out.println("ApplicationRunner 빈은 스프링에 등록되면 자동으로 실행됩니다");
            work1();
        };
    }

    private void work1(){
        log.debug("Post entity 개수: {}",postService.count());
    }
}