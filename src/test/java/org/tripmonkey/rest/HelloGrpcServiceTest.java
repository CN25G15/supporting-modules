package org.tripmonkey.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
class HelloGrpcServiceTest {
    /*
    @GrpcClient
    HelloGrpc helloGrpc;

    @Test
    void testHello() {
        HelloReply reply = helloGrpc
                .sayHello(HelloRequest.newBuilder().setName("Neo").build()).await().atMost(Duration.ofSeconds(5));
        assertEquals("Hello Neo!", reply.getMessage());
    }*/

}
