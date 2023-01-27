package me.learn.finagle.server;

import com.twitter.finagle.Service;
import com.twitter.finagle.http.Request;
import com.twitter.finagle.http.Response;
import com.twitter.finagle.http.Status;
import com.twitter.io.Buf;
import com.twitter.io.Buf.ByteBuffer;
import com.twitter.io.Reader;
import com.twitter.util.Future;

import java.nio.charset.StandardCharsets;
import java.util.Optional;

class ReverseService extends Service<Request, Response> {

    @Override
    public Future<Response> apply(final Request request) {
        String value = getValueOrDefault(request);
        String reversedValue = reverseString(value);
        Reader<Buf> reader = newReader(reversedValue);
        return Future.value(Response.apply(request.version(), Status.Ok(), reader));
    }

    private static String getValueOrDefault(final Request request) {
        return Optional.ofNullable(request.getParam("value")).orElse("no value provided");
    }

    private static String reverseString(final String input) {
        return new StringBuffer(input).reverse().toString();
    }

    private static Reader<Buf> newReader(final String value) {
        return Reader.fromBuf(new ByteBuffer(java.nio.ByteBuffer.wrap(value.getBytes(StandardCharsets.UTF_8))));
    }

}