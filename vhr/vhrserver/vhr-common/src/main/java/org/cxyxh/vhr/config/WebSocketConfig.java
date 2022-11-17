package org.cxyxh.vhr.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * @author ： cxyxh
 * @date : 2022/9/24 11:26
 * @describetion :
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		WebSocketMessageBrokerConfigurer.super.registerStompEndpoints(registry);
	}

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		WebSocketMessageBrokerConfigurer.super.configureMessageBroker(registry);
	}
}
