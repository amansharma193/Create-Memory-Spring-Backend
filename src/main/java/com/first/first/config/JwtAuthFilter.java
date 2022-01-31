package com.first.first.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.first.first.service.CustomUserDetailsService;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String requestTokenHeader = request.getHeader("Authorization");
		String username=null,jwtToken=null;
		
		if(requestTokenHeader!=null  && requestTokenHeader.startsWith("Bearer ")) {
			jwtToken = requestTokenHeader.substring(7);
			try {
				username = this.jwtTokenUtil.getUsernameFromToken(jwtToken);
				UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(username);
				System.out.println("username "+username);
				if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {

					UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				    usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				    
				    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				    
				}else {
					System.out.println("token not valid");
				}
				
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		filterChain.doFilter(request, response);
		
		
	}
	

}
