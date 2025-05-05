package org.tripmonkey.google.places;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.reactive.Separator;
import org.tripmonkey.google.places.data.APIResponse;
import org.tripmonkey.google.places.data.Place;

import java.util.List;

@RegisterRestClient
public interface PlacesClientInterface {

    @Path("/maps/api/place/details/json")
    @GET
    APIResponse getLocationDetails(@QueryParam("key") String key,
                                   @QueryParam("fields") @Separator(",") List<String> fields,
                                   @QueryParam("place_id") String place_id);


}
