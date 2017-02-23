/*
 * Copyright (C) 2009-2017 Lightbend Inc. <https://www.lightbend.com>
 */
package se.olapetersson.docker.hello.impl;

import akka.NotUsed;
import com.lightbend.lagom.javadsl.api.ServiceCall;
import play.Logger;
import se.olapetersson.docker.hello.api.HelloService;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.CompletableFuture;

public class HelloServiceImpl implements HelloService {

  @Override
  public ServiceCall<NotUsed, String> hello() {
    return request -> {
      Logger.info("Got a request to helloService and hello");
      try {
        return CompletableFuture.completedFuture("Hello, World! from " + InetAddress.getLocalHost().getHostName());
      } catch (UnknownHostException e) {
        e.printStackTrace();
        throw new RuntimeException(e);
      }
    };
  }

  @Override
  public ServiceCall<NotUsed, String> health() {
    return request -> CompletableFuture.completedFuture("I am healthy!");
  }
}
