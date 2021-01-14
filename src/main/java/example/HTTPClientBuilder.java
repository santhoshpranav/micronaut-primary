package example;

import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.uri.UriBuilder;
import io.micronaut.http.uri.UriTemplate;
import io.reactivex.Flowable;
import io.reactivex.Maybe;

import javax.inject.Singleton;
import java.net.URI;
import java.util.List;

@Singleton 
public class HTTPClientBuilder {

    private final RxHttpClient httpClient;
    private final URI uri;

    public HTTPClientBuilder(@Client("${url.function-host}") RxHttpClient httpClient) {  
        this.httpClient = httpClient;
        this.uri = UriBuilder.of("/myfunction")
                .path("pets")
                .build();
    }

    Maybe<List<Pet>> getPets() {
    	System.out.println(uri);
        HttpRequest<?> req = HttpRequest.GET(uri);  
        Flowable flowable = httpClient.retrieve(req, Argument.listOf(Pet.class)); 
        System.out.println(flowable.firstElement());
        return (Maybe<List<Pet>>) flowable.firstElement();
    }
}