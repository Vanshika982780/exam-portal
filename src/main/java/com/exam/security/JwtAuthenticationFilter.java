package com.exam.security;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.exam.jwt.JwtUtil;
import com.exam.model.User;
import com.exam.service.UserService;
import com.exam.service.impl.UserDetailsServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private ObjectMapper mapper;

	@Autowired
	private UserService userService;

	@Autowired
	private UserDetailsServiceImpl userServiceImpl;

	@Autowired
	private JwtUtil jwtUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		System.out.println("filter");
		final String requestTokenHeader = request.getHeader("Authorization");
		final String loginData = request.getHeader("test");
		String username = null;
		String jwtToken = null;
		System.out.println("loginData  " + loginData);

		System.out.println("requestb " + request);

		if (loginData != null && loginData.equals("login")) {
			filterChain.doFilter(request, response);
		}

		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			jwtToken = requestTokenHeader.substring(7);

			try {
				username = this.jwtUtil.getUsernameFromToken(jwtToken);
				System.out.println("username  " + username);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

			UserDetails userDetails = this.userServiceImpl.loadUserByUsername(username);

			String path = request.getRequestURI();

			System.out.print("path" + path);

			System.out.print("/quiz/".equals(path));

			if (this.jwtUtil.validateToken(jwtToken, userDetails)) {
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				
//				for (String name : userDetails.getAuthorities().toString()) {
//				    System.out.println(name);
//				}
				
				   String authorities = userDetails.getAuthorities().stream()
				            .map(GrantedAuthority::getAuthority)
				            .collect(Collectors.joining(","));
				   
				   System.out.print("authorities "+authorities);
				   
				   System.out.print("authorities "+(authorities == "ROLE_NORMAL"));

				if (path.contains("/adminQuiz") &&  authorities.equals("ROLE_NORMAL")) {
					System.out.println(path);
					// filterChain.doFilter(request, response);
					((HttpServletResponse) response).setStatus(HttpServletResponse.SC_UNAUTHORIZED);
					((HttpServletResponse) response).setContentType("application/json");
					return;
				}
				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			//	filterChain.doFilter(request, response);

			} else {
				System.out.println("Token is not validated ");
				Map<String, Object> errorDetails = new HashMap<>();

				errorDetails.put("message", "Invalid token");

				((HttpServletResponse) response).setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				((HttpServletResponse) response).setContentType("application/json");
			}

			filterChain.doFilter(request, response);
		}
	}
}
