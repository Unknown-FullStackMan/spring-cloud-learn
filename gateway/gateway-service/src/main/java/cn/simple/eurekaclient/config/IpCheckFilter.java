package cn.simple.eurekaclient.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.*;

/**
 * @Author Simple
 * @Date 2023/3/22
 */
@Component
public class IpCheckFilter implements GlobalFilter, Ordered {

    public static final List<String> BLACK_LIST = Arrays.asList("127.0.0.1","196.23.55.61");

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        String ip = exchange.getRequest().getHeaders().getHost().getHostString();

        //查询数据是否
        if (!BLACK_LIST.contains(ip)) {
            return chain.filter(exchange);
        }else {
            ServerHttpResponse response = exchange.getResponse();
            response.getHeaders().set("content-type","application/json;charset=utf-8");
            HashMap<String, Object> map = new HashMap<>(4);
            map.put("code", HttpStatus.UNAUTHORIZED.value());
            map.put("msg","Unauthorized");

            ObjectMapper objectMapper = new ObjectMapper();
            byte[] bytes = new byte[0];
            try {
                bytes = objectMapper.writeValueAsBytes(map);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

            DataBuffer wrap = response.bufferFactory().wrap(bytes);
            //拦截
            return  response.writeWith(Mono.just(wrap));
        }
    }

    @Override
    public int getOrder() {
        return -1;
    }
}
