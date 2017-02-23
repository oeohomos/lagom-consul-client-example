/*
 * Copyright (C) 2009-2017 Lightbend Inc. <https://www.lightbend.com>
 */
package se.olapetersson.docker.call.api;

import static com.lightbend.lagom.javadsl.api.Service.named;
import static com.lightbend.lagom.javadsl.api.Service.pathCall;

import akka.Done;
import akka.NotUsed;
import com.lightbend.lagom.javadsl.api.Descriptor;
import com.lightbend.lagom.javadsl.api.Service;
import com.lightbend.lagom.javadsl.api.ServiceCall;


public interface CallService extends Service {

  ServiceCall<NotUsed, String> call();

  ServiceCall<NotUsed, String> health();

  @Override
  default Descriptor descriptor() {
    // @formatter:off
    return named("call").withCalls(
        pathCall("/",  this::call),
        pathCall("/health/", this::health)
      ).withAutoAcl(true);
    // @formatter:on
  }
}
