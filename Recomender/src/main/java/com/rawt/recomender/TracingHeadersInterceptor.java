package com.rawt.recomender;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Configuration
class TracingHeadersInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate template) {
        HttpServletRequest servletRequest =
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        System.out.println("X-B3-TraceId: " + servletRequest.getHeader("X-B3-TraceId"));

        template.header("x-request-id", servletRequest.getHeader("x-request-id"));

        template.header("X-B3-TraceId", servletRequest.getHeader("X-B3-TraceId"));
        template.header("X-B3-SpanId", servletRequest.getHeader("X-B3-SpanId"));
        template.header("X-B3-ParentSpanId", servletRequest.getHeader("X-B3-ParentSpanId"));
        template.header("X-B3-Sampled", servletRequest.getHeader("X-B3-Sampled"));
        template.header("X-B3-Flags", servletRequest.getHeader("X-B3-Flags"));
    }
}

