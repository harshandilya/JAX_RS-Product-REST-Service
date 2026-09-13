package com.harsh.productapi.resource;
import com.harsh.productapi.model.*; import com.harsh.productapi.service.ProductService;
import jakarta.ws.rs.*; import jakarta.ws.rs.core.*; import java.net.URI; import java.util.List;
@Path("/products") @Produces(MediaType.APPLICATION_JSON)
public class ProductResource {
 private final ProductService service=ProductService.getInstance();
 private Response problem(int s,String t,String d,UriInfo u,List<String> e){return Response.status(s).type("application/problem+json").entity(ProblemResponse.of(t,s,d,u.getRequestUri().getPath(),e)).build();}
 @GET public Response list(@QueryParam("page") @DefaultValue("0") int page,@QueryParam("size") @DefaultValue("10") int size,@Context UriInfo uri){if(page<0||size<1||size>100)return problem(400,"Bad Request","page must be >= 0 and size must be between 1 and 100",uri,List.of());return Response.ok(service.list(page,size)).build();}
 @GET @Path("/{id}") public Response get(@PathParam("id") long id,@Context UriInfo uri){if(id<=0)return problem(400,"Bad Request","id must be a positive integer",uri,List.of("Invalid path parameter: id"));return service.find(id).map(p->Response.ok(p).build()).orElseGet(()->problem(404,"Not Found","Product "+id+" was not found",uri,List.of()));}
 @POST @Consumes(MediaType.APPLICATION_JSON) public Response create(Product p,@Context UriInfo uri){List<String> errors=service.validate(p);if(!errors.isEmpty())return problem(400,"Bad Request","Product validation failed",uri,errors);if(service.exists(p.getId()))return problem(409,"Conflict","A product with id "+p.getId()+" already exists",uri,List.of());Product saved=service.create(p);URI location=uri.getAbsolutePathBuilder().path(String.valueOf(saved.getId())).build();return Response.created(location).entity(saved).build();}
}