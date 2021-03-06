package com.bangahu.main.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.bangahu.main.model.domain.User;
import com.bangahu.main.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TestController {
	private final UserService userService;
	private final WebClient webClient;
	
	@GetMapping("/react/user")
	public ResponseEntity<?> reactGetUser(String username) {
		Mono<?> monoUser = webClient.get().uri(builder -> builder.path("/user").queryParam("username", username).build())
//			.retrieve()
//			.onStatus(httpStatus -> httpStatus.value() != 200,
//            	error -> Mono.error(new Exception("error Body"))
//            )
//			.bodyToMono(User.class)
			.exchangeToMono(response -> {
				if (!response.statusCode().is2xxSuccessful()) {
					 return response.bodyToMono(Map.class);
		        }
				log.info("### response: {}-{}", response.statusCode(), response.bodyToMono(User.class));
				return response.bodyToMono(User.class);
			})
			.log()
			.onErrorResume(e -> {
				log.debug("e: {}", e.getMessage());
				return Mono.just(null);
			});
		
//		Mono<User> monoUser = webClient.get().uri(builder -> builder.path("/user").queryParam("username", username).build())
//				.retrieve().bodyToMono(User.class).log();
		
		//User user = userService.selectUser(username);
		return ResponseEntity.ok(monoUser.block());
	}
	
	@GetMapping("/user")
	public ResponseEntity<?> getUser(String username) {
		User user = userService.selectUser(username);
		return ResponseEntity.ok(user);
	}
}
