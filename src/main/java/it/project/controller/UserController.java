package it.project.controller;

import java.security.Key;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;


import it.project.dto.UserLoginRequestDto;
import it.project.dto.UserLoginResponseDto;
import it.project.dto.UserSignupDto;
import it.project.model.User;
import it.project.service.UserService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	public UserLoginResponseDto issueToken(String email) {
		byte[] secret = "djcknjfnkcfdnhcnfuhcdjchsdnjncfjdsfcsndfcsadksxj84938321898129eij9".getBytes();
		
		Key key = Keys.hmacShaKeyFor(secret);
		
		User infoUser = userService.getUserByEmail(email);
		
		Map<String, Object> map = new HashMap<>();
		
		map.put("firstname", infoUser.getFirstname());
		map.put("lastname", infoUser.getLastname());
		map.put("email", email);
		
		Date creation = new Date();
		Date end = java.sql.Timestamp.valueOf(LocalDateTime.now().plusMinutes(15L));
		
		String tokenJwts = Jwts.builder()
				.setClaims(map)
				.setIssuer("http://localhost:8080")
				.setIssuedAt(creation)
				.setExpiration(end)
				.signWith(key)
				.compact();
		
		UserLoginResponseDto token = new UserLoginResponseDto();
		
		token.setToken(tokenJwts);
		token.setTokenCreationTime(creation);
		token.setTtl(end);
		
		return token;
	}
	
	@POST
	@Path("/reg")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response userSignup(@RequestBody UserSignupDto user) {
		try {
			if(!Pattern.matches("(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,20}", user.getPassword())) {
				return Response.status(Response.Status.BAD_REQUEST).build();
			}
			
			if (userService.existsUserByEmail(user.getEmail())) {
				return Response.status(Response.Status.BAD_REQUEST).build();
			}
			userService.userSignup(user);
			return Response.status(Response.Status.OK).build();
		}
		catch (Exception ex) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}
	
	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response userLogin(@RequestBody UserLoginRequestDto user) {
		try {
			if(userService.userLogin(user)) {
				return Response.ok(issueToken(user.getEmail())).build();			}
		}
		catch(Exception ex) {
			return Response.status (Response.Status.BAD_REQUEST).build();
		}
		return Response.status (Response.Status.BAD_REQUEST).build();
	}
	
	@DELETE
	@Path("/delete/email/{email}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteUserByEmail(@PathParam("email") String email) {
		try {
			userService.userDeleteByEmail(email);
			return Response.status(Response.Status.NO_CONTENT).build();
		}
		catch(Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}
	
	@DELETE
	@Path("/delete/id/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteUserById(@PathParam("id") int id) {
		try {
			userService.userDeleteById(id);
			return Response.status(Response.Status.NO_CONTENT).build();
		}
		catch(Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}
	
	@GET
	@Path("/get/email/{email}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUserByEmail(@PathParam("email") String email) {
		try {
			User user = userService.getUserByEmail(email);
			return Response.status(Response.Status.OK).entity(user).build();
		}
		catch (Exception ex) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}
	
	@GET
	@Path("/get/id/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUserByEmail(@PathParam("id") int id) {
		try {
			User user = userService.getUserById(id);
			return Response.status(Response.Status.OK).entity(user).build();
		}
		catch (Exception ex) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}
}
