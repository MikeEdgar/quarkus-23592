package org.acme.getting.started;

import javax.inject.Inject;
import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@Path("/hello")
public class GreetingResource {

    @Inject
    GreetingService service;

    @GET
    @Path("/world")
    @Produces(MediaType.APPLICATION_JSON)
    public CompletionStage<Response> helloWorld(@BeanParam OrderByInput<Key1> orderParams) {
        return CompletableFuture.completedStage(Response.ok("World").build());
    }

    @GET
    @Path("/there")
    @Produces(MediaType.APPLICATION_JSON)
    public CompletionStage<Response> helloThere(@BeanParam OrderByInput<Key2> orderParams) {
        return CompletableFuture.completedStage(Response.ok("There").build());
    }

    public static class OrderByInput<T> {
        @QueryParam("orderKey")
        T field;

        @QueryParam("order")
        String order;

        public T getField() {
            return field;
        }

        public void setField(T field) {
            this.field = field;
        }

        public String getOrder() {
            return order;
        }

        public void setOrder(String order) {
            this.order = order;
        }

    }

    public enum Key1 {
        NAME("name"),
        DATE("date");

        String value;

        private Key1(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public static Key1 fromString(String value) {
            return Arrays.stream(values())
                    .filter(v -> v.getValue().equals(value))
                    .findFirst()
                    .orElse(null);
        }
    }

    public enum Key2 {
        NAME("name"),
        DATE("date");

        String value;

        private Key2(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public static Key2 fromString(String value) {
            return Arrays.stream(values())
                    .filter(v -> v.getValue().equals(value))
                    .findFirst()
                    .orElse(null);
        }
    }
}