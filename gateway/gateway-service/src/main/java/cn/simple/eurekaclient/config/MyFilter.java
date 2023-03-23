package cn.simple.eurekaclient.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.HashMap;

/**
 * @Author Simple
 * @Date 2023/3/22
 */
@Component
public class MyFilter implements GlobalFilter, Ordered {

    /**
     * 这个就是过滤器的方法
     * @param exchange
     * @param chain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        ServerHttpRequest request = exchange.getRequest();
        String path = request.getURI().getPath();
        System.out.println(path);

        HttpHeaders headers = request.getHeaders();
        System.out.println(headers);

//        ServerHttpResponse response = exchange.getResponse();
//        response.getHeaders().set("content-type","application/json;charset=utf-8");
//        HashMap<String, Object> map = new HashMap<>(4);
//        map.put("code", HttpStatus.UNAUTHORIZED.value());
//        map.put("msg","Unauthorized");
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        byte[] bytes = new byte[0];
//        try {
//            bytes = objectMapper.writeValueAsBytes(map);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//
//        DataBuffer wrap = response.bufferFactory().wrap(bytes);
//        //拦截
//        return  response.writeWith(Mono.just(wrap));
        //放行
        return chain.filter(exchange);
    }

    /**
     * 指定执行顺序
     * @return
     */
    @Override
    public int getOrder() {
        return 0;
    }
}
