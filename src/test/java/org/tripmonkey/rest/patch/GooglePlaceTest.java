package org.tripmonkey.rest.patch;

import io.quarkus.test.junit.QuarkusTest;
import io.smallrye.mutiny.infrastructure.Infrastructure;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpVersion;
import io.vertx.ext.web.client.WebClient;
import io.vertx.ext.web.client.WebClientOptions;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.tripmonkey.google.places.PlaceDetailsService;
import org.tripmonkey.google.places.PlacesClientInterface;
import org.tripmonkey.google.places.data.APIResponse;
import org.tripmonkey.google.places.data.Place;
import org.tripmonkey.google.places.data.ResponseStatus;

import java.time.Duration;
import java.util.List;

@QuarkusTest
public class GooglePlaceTest {


    @Inject
    @RestClient
    PlacesClientInterface pci;

    @ConfigProperty(name = "api.key.google")
    String key;

    @Inject
    Logger log;

    /*
    @Inject
    PlaceDetailsService pds;*/

/*
    @Test
    public void testGoogleEndpoint(){
        /*APIResponse s = pci.getLocationDetails(key,
                String.join(",",List.of("place_id", "name", "type", "geometry", "rating")),
                "ChIJ68aBlEKuEmsRHUA9oME5Zh0")
                .runSubscriptionOn(Infrastructure.getDefaultExecutor())
                .subscribe().with(apiResponse -> log.infof("This one was the reactive one %s",apiResponse.getResult().getName()));
        log.infof("This one is from indefinetely: %s",pci.getLocationDetails(key,
                String.join(",",List.of("place_id", "name", "type", "geometry", "rating")),
                "ChIJ68aBlEKuEmsRHUA9oME5Zh0").await().indefinitely().getResult().getName());*/
        //Assertions.assertEquals(ResponseStatus.REQUEST_DENIED,s.getStatus());
        //System.out.println(r.getErrorMessage());
        //Assertions.assertTrue(ResponseStatus.OK.equals(r.getStatus()));
                /*.onItem().invoke(apiResponse -> log.infof("ITEM %s", apiResponse.getErrorMessage()))
                .onFailure().invoke(throwable -> log.error(throwable))
                .subscribe().with(apiResponse -> {
                    log.info(apiResponse.getErrorMessage());
                    Assertions.assertNotNull(apiResponse);
                    Assertions.assertNotNull(apiResponse.getStatus());
                    Assertions.assertNull(apiResponse.getErrorMessage());
                });
    }*/

    /*
    @Test
    public void customBeanDebugTest() {
        Place p = pds.getPlaceDetails("ChIJ68aBlEKuEmsRHUA9oME5Zh0").await().indefinitely().getResult();
        System.out.println(p.getRating());
    }
                /*.onItem().invoke(apiResponse -> log.info(apiResponse.getStatus()))
                .onFailure().invoke(throwable -> log.error(throwable))
                .subscribe().with(apiResponse -> {
                    Assertions.assertNotNull(apiResponse);
                    Assertions.assertNotNull(apiResponse.getStatus());
                    Assertions.assertNull(apiResponse.getErrorMessage());
                });
    }

    /*
    @Test
    void testRawVertxClient() {
        Vertx vertx = Vertx.vertx();
        WebClientOptions options = new WebClientOptions()
                .setSsl(true)
                .setProtocolVersion(HttpVersion.HTTP_2)
                .setTrustAll(true)
                .setVerifyHost(false)
                .setUseAlpn(true);

        WebClient client = WebClient.create(vertx, options);

        client.getAbs("https://maps.googleapis.com/maps/api/place/details/json")
                .addQueryParam("place_id", "ChIJ68aBlEKuEmsRHUA9oME5Zh0")
                .addQueryParam("key", key)
                .addQueryParam("fields", "place_id,name,type,geometry,rating")
                .send()
                .onSuccess(res -> log.info(res.bodyAsString()))
                .onFailure(log::error);
    }*/

    /*
    @Test
    public void testGoogleEndpointScuff() {
        pci.getLocationDetails(key,
                        List.of("place_id", "name", "type", "geometry", "rating"),
                        "ChIJ68aBlEKuEmsRHUA9oME5Zh0")
                .onItem().invoke(apiResponse -> log.infof("ITEM %s", apiResponse.getErrorMessage()))
                .onFailure().invoke(throwable -> log.error("Failure: ", throwable))
                .subscribe().with(apiResponse -> {
                    log.info(apiResponse.getErrorMessage());
                    Assertions.assertNotNull(apiResponse);
                    Assertions.assertNotNull(apiResponse.getStatus());
                    Assertions.assertNull(apiResponse.getErrorMessage());
                });
        }*/
    @Test
    public void singleTest(){

        APIResponse r =  pci.getLocationDetails("key",String.join(",",List.of("place_id", "name", "type", "geometry", "rating")),
                "ChIJ68aBlEKuEmsRHUA9oME5Zh0");

        Assertions.assertNotNull(r.getErrorMessage());


    }
}
