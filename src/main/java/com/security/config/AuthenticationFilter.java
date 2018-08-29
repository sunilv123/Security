package com.security.config;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.logging.Logger;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.Assert;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.security.dto.AppConstants;
import com.security.dto.GenericResponse;
import com.security.model.AppUser;
import com.security.model.LoginData;
import com.security.service.UserService;

public class AuthenticationFilter extends OncePerRequestFilter {

	private UserService loginService;
	
	AuthenticationFilter(UserService loginService){
		this.loginService = loginService;
	}
	
	private static Logger logger = Logger.getLogger(AuthenticationFilter.class);
	
	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filter)
			throws ServletException, IOException {
	try {	
        String authToken = req.getHeader("X-Authorization");
        if (authToken != null) {
        	
                LoginData loginData = loginService.loadByUserName(authToken);
            	Assert.notNull(loginData, "Invlaid Token!");
            	AppUser userDetails = loginData.getAppUser();
            	Assert.notNull(loginData.getAppUser(), "You are already logged out...");
        		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, Arrays.asList(
        				new SimpleGrantedAuthority(userDetails.getRole().name())));
        		authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
        		SecurityContextHolder.getContext().setAuthentication(authentication);
        		filter.doFilter(req, res);
   
        } else {
            logger.warn("couldn't find the header...");
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            GenericResponse resp = new GenericResponse(AppConstants.GENERIC_RESPONSE_FAILURE, "couldn't find the header","403");
            String jsonRespString = ow.writeValueAsString(resp);
            res.setContentType("application/json");
            PrintWriter writer = res.getWriter();
            writer.write(jsonRespString);

        }
       
	}catch(Exception e){
		e.printStackTrace();

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        GenericResponse resp = new GenericResponse(AppConstants.GENERIC_RESPONSE_FAILURE, e.getMessage(),"403");
        String jsonRespString = ow.writeValueAsString(resp);
        res.setContentType("application/json");
        PrintWriter writer = res.getWriter();
        writer.write(jsonRespString);
      
	}
    }
	
		
	}



