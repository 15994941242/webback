package com.lin.config;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class HeaderRequestInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest httpRequest, byte[] body, ClientHttpRequestExecution clientHttpRequestExecution) throws IOException {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        String accessToken = request.getHeader("codeToken");
        if (accessToken == null) {
            System.out.println("codeToken为空");
        }
        HttpHeaders headers = httpRequest.getHeaders();
        headers.add("codeToken", accessToken);
        return clientHttpRequestExecution.execute(httpRequest, body);
    }

//    @Override
//    public ClientHttpResponse intercept(HttpRequest httpRequest, byte[] body, ClientHttpRequestExecution clientHttpRequestExecution) throws IOException {
//        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
//        String auth = request.getHeader("Authorization");
//        String accessToken = request.getParameter("access_token");
//        List<String> authorizations = httpRequest.getHeaders().get("Authorization");
//        String authorization = "";
//        if (accessToken != null){
//            authorization = "bearer " + accessToken;
//        }else if (auth != null){
//            authorization = auth;
//        }else if (authorizations != null){
//            authorization = "bearer " + authorizations.get(0);
//        }
//        HttpHeaders headers = httpRequest.getHeaders();
//        headers.add("Authorization", authorization);
//        return clientHttpRequestExecution.execute(httpRequest, body);
//    }

}
