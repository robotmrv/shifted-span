package com.example.tracedemo;

import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.sleuth.annotation.NewSpan;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.function.Function;

@SpringBootApplication
public class TraceDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(TraceDemoApplication.class, args);
    }

    public static <T> Function<Mono<T>, Publisher<T>> log(Logger log, String name, Object... params) {
        return it -> Mono.defer(() -> {
            log.info("invoke {}({})", name, (Object) params);
            return it;
        })
                .doOnNext(s -> log.info("next({})", s))
                .doOnError(err -> log.error("error: {}", err.getMessage()));
    }

    @RestController
    public static class MyController {
        private final MyService service;
        Logger log = LoggerFactory.getLogger(getClass());

        public MyController(MyService service) {
            this.service = service;
        }

        @NewSpan
        @GetMapping("/test")
        public Mono<String> endpointMethod(@RequestParam(defaultValue = "1") String name,
                                           @RequestParam(defaultValue = "false") boolean error) {
            return Mono.defer(() -> service.serviceMethod(name, error))
                    .transform(log(log, "endpointMethod", name, error));
        }

        @GetMapping("/some-error")
        public ResponseEntity<String> error() {
            return ResponseEntity.unprocessableEntity().build();
        }

        @GetMapping("/success")
        public ResponseEntity<String> success() {
            return ResponseEntity.ok("Success ");
        }
    }

    @Component
    public static class MyService {
        private final MyAdapter myAdapter;
        Logger log = LoggerFactory.getLogger(getClass());

        public MyService(MyAdapter myAdapter) {
            this.myAdapter = myAdapter;
        }

        @NewSpan
        public <T> Mono<String> serviceMethod(String name, boolean error) {
            return Mono.defer(() -> myAdapter.webclientMethod(name, error))
                    .map(it -> it + name)
                    .subscribeOn(Schedulers.parallel())
                    .transform(log(log, "serviceMethod", name, error));
        }
    }

    @Component
    public static class MyAdapter {
        Logger log = LoggerFactory.getLogger(getClass());

        WebClient client;

        public MyAdapter(WebClient.Builder wcBuilder, @Value("${server.port}") Integer port) {
            this.client = wcBuilder.baseUrl("http://localhost:" + port).build();
        }

        @NewSpan
        public <T> Mono<String> webclientMethod(String name, boolean error) {
            return Mono.defer(() -> client.get()
                    .uri(error ? "/some-error" : "/success")
                    .retrieve()
                    .bodyToMono(String.class))
                    .publishOn(Schedulers.elastic())
                    .transform(log(log, "webclientMethod", name, error));
        }
    }

}

