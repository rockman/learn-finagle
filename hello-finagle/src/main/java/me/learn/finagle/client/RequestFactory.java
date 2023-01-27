package me.learn.finagle.client;

import com.twitter.finagle.http.Method;
import com.twitter.finagle.http.Request;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

interface RequestFactory {

    static Request withValue(final String value) {
        String encodedValue = URLEncoder.encode(value, StandardCharsets.UTF_8);
        Request request = Request.apply(Method.Get(), "/?value=" + encodedValue);
        request.host("localhost:8080");
        return request;
    }

}