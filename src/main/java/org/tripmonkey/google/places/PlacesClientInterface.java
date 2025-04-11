package org.tripmonkey.google.places;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.reactive.Separator;

import java.util.List;

@RegisterRestClient
public interface PlacesClientInterface {

    @Path("/maps/api/place/details/json")
    @GET
    Object getLocationDetails(@QueryParam("key") String key,
                              @QueryParam("fields") @Separator(",") List<String> fields,
                              @QueryParam("place_id") String place_id);


}
