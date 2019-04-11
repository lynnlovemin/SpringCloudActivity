package com.lynn.blog.gateway.filter;



import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lynn.blog.common.redis.Redis;
import com.lynn.blog.common.result.Code;
import com.lynn.blog.common.result.SingleResult;
import com.lynn.blog.common.utils.Algorithm;
import com.lynn.blog.common.utils.MessageDigestUtils;
import io.netty.buffer.ByteBufAllocator;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.core.io.buffer.NettyDataBufferFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.nio.CharBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Component
public class ApiGlobalFilter implements GlobalFilter {

    @Value("${api.encrypt.key}")
    private String salt;

    private Redis redis;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest serverHttpRequest = exchange.getRequest();
        String body = requestBody(serverHttpRequest);
        String uriBuilder = getUrlAuthenticationApi(body);
        String sign = MessageDigestUtils.encrypt(uriBuilder + salt, Algorithm.MD5);
        String signature = serverHttpRequest.getHeaders().getFirst("signature");
        if (sign != null && sign.equals(signature)) {
            //以下代码再次包装request，否则会报：Only one connection receive subscriber allowed.错误
            URI uri = serverHttpRequest.getURI();
            ServerHttpRequest request = serverHttpRequest.mutate().uri(uri).build();
            DataBuffer bodyDataBuffer = stringBuffer(body);
            Flux<DataBuffer> bodyFlux = Flux.just(bodyDataBuffer);
            request = new ServerHttpRequestDecorator(request){
                @Override
                public Flux<DataBuffer> getBody() {
                    return bodyFlux;
                }
            };
            if(uri.getPath().contains("close")){
                String token = request.getHeaders().getFirst("token");
                if(StringUtils.isNotBlank(token)){
                    String userId = (String) redis.get(token);
                    if(StringUtils.isNotBlank(userId)){
                        JSONObject jsonObject = JSON.parseObject(body);
                        if(userId.equals(jsonObject.getLong("userId"))){
                            return chain.filter(exchange.mutate().request(request).build());
                        }else{
                            ServerHttpResponse response = exchange.getResponse();
                            byte[] bits = JSON.toJSONString(SingleResult.buildSuccess(Code.NO_PERMISSION,"invalid token")).getBytes(StandardCharsets.UTF_8);
                            DataBuffer buffer = response.bufferFactory().wrap(bits);
                            return response.writeWith(Mono.just(buffer));
                        }
                    }else {
                        ServerHttpResponse response = exchange.getResponse();
                        byte[] bits = JSON.toJSONString(SingleResult.buildSuccess(Code.NO_PERMISSION,"invalid token")).getBytes(StandardCharsets.UTF_8);
                        DataBuffer buffer = response.bufferFactory().wrap(bits);
                        return response.writeWith(Mono.just(buffer));
                    }
                }else{
                    ServerHttpResponse response = exchange.getResponse();
                    byte[] bits = JSON.toJSONString(SingleResult.buildSuccess(Code.NO_PERMISSION,"invalid token")).getBytes(StandardCharsets.UTF_8);
                    DataBuffer buffer = response.bufferFactory().wrap(bits);
                    return response.writeWith(Mono.just(buffer));
                }
            }
            return chain.filter(exchange.mutate().request(request).build());
        } else {
            //签名错误
            ServerHttpResponse response = exchange.getResponse();
            byte[] bits = JSON.toJSONString(SingleResult.buildSuccess(Code.NO_PERMISSION,"签名错误")).getBytes(StandardCharsets.UTF_8);
            DataBuffer buffer = response.bufferFactory().wrap(bits);
            return response.writeWith(Mono.just(buffer));
        }
    }

    private String getUrlAuthenticationApi(String body){
        if (StringUtils.isEmpty(body)) {
            return null;
        }
        List<String> nameList = new ArrayList<>();
        StringBuilder urlBuilder = new StringBuilder();
        JSONObject requestBodyJson = null;
        requestBodyJson = JSON.parseObject(body);
        nameList.addAll(requestBodyJson.keySet());
        final JSONObject requestBodyJsonFinal = requestBodyJson;
        nameList.stream().sorted().forEach(name -> {
            if(null != requestBodyJsonFinal){
                urlBuilder.append('&');
                urlBuilder.append(name).append('=').append(requestBodyJsonFinal.getString(name));
            }
        });
        urlBuilder.deleteCharAt(0);
        return urlBuilder.toString();
    }

    /**
     * 获得body数据
     * @return 请求体
     */
    private String requestBody(ServerHttpRequest serverHttpRequest) {
        //获取请求体
        Flux<DataBuffer> body = serverHttpRequest.getBody();
        AtomicReference<String> bodyRef = new AtomicReference<>();
        body.subscribe(buffer -> {
            CharBuffer charBuffer = StandardCharsets.UTF_8.decode(buffer.asByteBuffer());
            DataBufferUtils.release(buffer);
            bodyRef.set(charBuffer.toString());
        });
        return bodyRef.get();
    }

    private DataBuffer stringBuffer(String value) {
        byte[] bytes = value.getBytes(StandardCharsets.UTF_8);
        NettyDataBufferFactory nettyDataBufferFactory = new NettyDataBufferFactory(ByteBufAllocator.DEFAULT);
        DataBuffer buffer = nettyDataBufferFactory.allocateBuffer(bytes.length);
        buffer.write(bytes);
        return buffer;
    }
}
