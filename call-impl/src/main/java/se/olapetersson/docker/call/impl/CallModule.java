/*
 * Copyright (C) 2016 Lightbend Inc. <http://www.lightbend.com>
 */
package se.olapetersson.docker.call.impl;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.lightbend.lagom.javadsl.server.ServiceGuiceSupport;
import play.Configuration;
import play.Environment;
import se.olapetersson.docker.call.api.CallService;
import se.olapetersson.docker.hello.api.HelloService;
import se.olapetersson.lagom.consul.ConsulConfiguration;
import se.olapetersson.lagom.consul.ConsulService;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * The module that binds the StreamService so that it can be served.
 */
public class CallModule extends AbstractModule implements ServiceGuiceSupport {

    private Environment environment;
    private Configuration configuration;
    private ConsulConfiguration consulConfiguration;

    @Inject
    public CallModule(Environment environment, Configuration configuration) throws UnknownHostException {
        this.environment = environment;
        this.configuration = configuration;
        this.consulConfiguration = new ConsulConfiguration(configuration);
        registerService();
    }

    private void registerService() throws UnknownHostException {
        int servicePort = Integer.parseInt(configuration.getString("http.port"));
        String hostname = InetAddress.getLocalHost().getHostAddress();
        ConsulService consulService = new ConsulService("call", hostname, servicePort, "health/");
        consulService.registerService(consulConfiguration.getAgentHostname(), consulConfiguration.getAgentPort());
    }

    @Override
    protected void configure() {
        bindServices(serviceBinding(CallService.class, CallServiceImpl.class));
        bindClient(HelloService.class);
    }
}
