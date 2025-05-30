package org.tripmonkey.google.places;

import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.tripmonkey.google.places.data.APIResponse;

import java.util.List;

@RegisterRestClient(configKey = "google-rest-client")
public interface PlacesClientInterface {


    @Path("/maps/api/place/details/json")
    @GET
    APIResponse getLocationDetails(@QueryParam("key") String key,
                                        // @QueryParam("fields") @Separator(",") List<String> fields, FOR POSTERITY THIS IS A LIE THIS DOESNT WORK
                                        @QueryParam("fields")  String fields,
                                        @QueryParam("place_id") String place_id);

    //remove key from app props
}
