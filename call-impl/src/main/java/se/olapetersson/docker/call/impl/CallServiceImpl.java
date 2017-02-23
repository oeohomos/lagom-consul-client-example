package se.olapetersson.docker.call.impl;

import akka.NotUsed;
import com.google.inject.Inject;
import com.lightbend.lagom.javadsl.api.ServiceCall;
import se.olapetersson.docker.call.api.CallService;
import se.olapetersson.docker.hello.api.HelloService;

import java.util.concurrent.CompletableFuture;

public class CallServiceImpl implements CallService {

    private HelloService helloService;

    @Inject
    public CallServiceImpl(HelloService helloService) {
        this.helloService = helloService;
    }

    @Override
    public ServiceCall<NotUsed, String> call() {
        return request -> helloService.hello().invoke().thenApply(r -> r + " called from call");
    }

    @Override
    public ServiceCall<NotUsed, String> health() {
        return request -> CompletableFuture.completedFuture("I am healthy!");
    }

}
