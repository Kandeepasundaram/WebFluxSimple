package com.seracorp.WebFluxSimple;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.BodyInserters.fromPublisher;
import static org.springframework.web.reactive.function.server.ServerResponse.created;
import static org.springframework.web.reactive.function.server.ServerResponse.notFound;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.util.UriComponentsBuilder;

import reactor.core.publisher.Mono;

@Component
public class TestsHandler {
	public Mono<ServerResponse> get(ServerRequest request) {
		Person q = new Person();
	    final Mono<Person> person = Mono.just(q);
	    return person
	        .flatMap(p -> ok().contentType(APPLICATION_JSON).body(fromPublisher(person, Person.class)))
	        .switchIfEmpty(notFound().build());
	}
	
	public Mono<ServerResponse> post(ServerRequest request) {
	    final Mono<Person> person = request.bodyToMono(Person.class);
	    final UUID id = UUID.randomUUID();
	    return created(UriComponentsBuilder.fromPath("people/" + id).build().toUri())
	        .contentType(APPLICATION_JSON)
	        .body(
	            fromPublisher(
	                person.map(p -> new Person(p, id)).flatMap(this::save), Person.class));
	  }
	
	 public Mono<Person> save(Person person) {
		    return Mono.fromSupplier(
		        () -> {
		        	person.setAge(25);
		        	person.setCountry("TamizhNadu");
		        	person.setFirstName("Tamizh");
		          return person;
		        });
		  }

}
