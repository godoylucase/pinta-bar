package com.pintabar.webservices.apis;

import com.pintabar.persistence.dto.CommentDTO;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Component;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

/**
 * Created by lucasgodoy on 26/06/17.
 */
@Component
@Path("/review")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class ReviewAPI {

	private static final String REVIEW_MICROSERVICE_ID = "pintabar-review-center";
	private final LoadBalancerClient loadBalancerClient;

	public ReviewAPI(LoadBalancerClient loadBalancerClient) {
		this.loadBalancerClient = loadBalancerClient;
	}

	@GET
	@Path("/business/{businessUuid}")
	public List<CommentDTO> getReviewsFromBusiness(
			@PathParam("businessUuid") String businessUuid) {
		ServiceInstance serviceInstance = loadBalancerClient.choose(REVIEW_MICROSERVICE_ID);
		URI uri = serviceInstance.getUri();
		String url = uri.toString();

		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(url).path("review-api/dummy");
		Invocation.Builder invocationBuilder
				= webTarget.request()
				.accept(MediaType.APPLICATION_JSON)
				.header("Content-Type", MediaType.APPLICATION_JSON);
		List<CommentDTO> response = invocationBuilder.get(new GenericType<List<CommentDTO>>(){});
		return response;
	}

}
