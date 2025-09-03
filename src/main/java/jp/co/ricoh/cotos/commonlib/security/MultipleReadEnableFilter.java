package jp.co.ricoh.cotos.commonlib.security;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class MultipleReadEnableFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

		String contentType = request.getContentType();
		if (contentType != null && contentType.contains("multipart/form-data")) {
			// multipartの場合はラップしない
			filterChain.doFilter(request, response);
		} else {
			// 通常のリクエストはラップする
			HttpServletRequest wrappedRequest = new BufferedServletRequestWrapper((HttpServletRequest) request);
			filterChain.doFilter(wrappedRequest, response);
		}
	}
}
