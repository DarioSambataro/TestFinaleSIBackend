package it.project;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import jakarta.ws.rs.ApplicationPath;

@Component
@ApplicationPath("api")
public class JerseyConfig extends ResourceConfig{
	public JerseyConfig() {
		packages("it.project");
	}
}

//Configurazione di Jersey, implementazione di jax-rs per la creazione di api restful
