package com.eazybytes.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectSecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

        /**
         * requestMatchers(url) -> url 의 갯수는 무제한이며 인자값으로 넘겨준 url 에대한 권한 설정
         * authenticated -> 인증이 필요함
         * permitAll -> 누구든지 접근 가능
         */
        http.authorizeRequests(authorize -> authorize
                        .requestMatchers("/myAccount", "/myBalance", "/myLoans", "/myCard").authenticated()
                        .requestMatchers("/notices", "/contact").permitAll())
                        .formLogin(Customizer.withDefaults())
                        .httpBasic(Customizer.withDefaults());

        return http.build();

        /**
         * anyRequest() -> 모든 request 에 대한 권한 설정
         * .denyAll() -> 어떠한 작업도 허용하지 않음 자격 증명을 해도 403에러가 듬
         * 요청을 거부하려면 먼저 요청을 인증해야 하기 때문에 자격 증명을 하게되고
         * 증명을 하게되면 인증이 성공이 되지만 어떠한 작업도 허용하지 않기 때문에 에러 반환
         */
//        http.authorizeRequests(authorize -> authorize
//                .anyRequest().denyAll())
//                .formLogin(Customizer.withDefaults())
//                .httpBasic(Customizer.withDefaults());
//        return http.build();
    }

}
