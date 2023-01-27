package me.learn.finagle.client;

import com.twitter.finagle.Http;
import com.twitter.finagle.Service;
import com.twitter.finagle.http.Request;
import com.twitter.finagle.http.Response;
import com.twitter.util.Await;
import com.twitter.util.Future;
import scala.runtime.BoxedUnit;

class ClientSampleRunner {

    void run() throws Exception {
        Service<Request, Response> clientService = Http.newService(":8080");
        Future<Response> responseFuture = clientService.apply(RequestFactory.withValue("Hello, Finagle!"));
        defaultAwait(responseFuture);
    }

    void defaultAwait(final Future<Response> responseFuture) throws Exception {
        Await.result(responseFuture
                .onSuccess(r -> {
                    System.out.println("Received: " + r.getContentString());
                    System.out.flush();
                    return BoxedUnit.UNIT;
                })
                .onFailure(r -> {
                    throw new RuntimeException(r);
                })
        );
    }

}