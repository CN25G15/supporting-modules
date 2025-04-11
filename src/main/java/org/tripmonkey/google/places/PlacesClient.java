package org.tripmonkey.google.places;

import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.Optional;


public class PlacesClient {

    @ConfigProperty(name = "api.key.google")
    Optional<String> key;

    @Inject
    @RestClient
    PlacesClientInterface placesClient;



}
