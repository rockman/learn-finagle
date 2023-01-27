package me.learn.finagle.server;

import com.twitter.finagle.Http;
import com.twitter.finagle.ListeningServer;
import com.twitter.util.Await;

class ServerSampleRunner {

    void run() throws Exception {
        ListeningServer server = Http.serve(":8080", new ReverseService());
        Await.ready(server);
    }

}
