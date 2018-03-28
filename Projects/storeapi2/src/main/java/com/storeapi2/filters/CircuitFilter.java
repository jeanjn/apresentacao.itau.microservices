package com.storeapi2.filters;

import java.io.IOException;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.storeapi2.helpers.RouteHelper;

@Component
@Order(1)
public class CircuitFilter implements Filter {
	private static int countRequest;
	private Date dateLimitRequest;
	private Random random = new Random();

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String url = ((HttpServletRequest) request).getRequestURL().toString();
		if (url.contains(RouteHelper.API_URL + "checkout")) {

			countRequest++;

			if (countRequest == 1 || dateLimitRequest == null) {
				dateLimitRequest = new Date();
			}

			if (countRequest >= 5 && mustBlockRequest()) {

				long diff = (new Date().getTime() - dateLimitRequest.getTime());
				long minutes = TimeUnit.MILLISECONDS.toMinutes(diff);

				if (minutes < 5) {
					((HttpServletResponse) response).sendError(HttpServletResponse.SC_SERVICE_UNAVAILABLE,
							"Serviço indisponível.");

					return;
				}

				countRequest = 0;
				dateLimitRequest = null;

			}
		}

		chain.doFilter(request, response);
		return;
	}

	public void init(FilterConfig arg0) throws ServletException {

	}

	// Random para sortear se a requisicao sera atendida ou nao
	private boolean mustBlockRequest() {
		return random.nextBoolean();
	}

}
