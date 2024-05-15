package org.servlet.p2p_lottery.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SecurityFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization code, if needed
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        if (request instanceof HttpServletRequest && response instanceof HttpServletResponse) {
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            HttpServletResponse httpResponse = (HttpServletResponse) response;

            System.out.println(httpRequest);

            // Check if the user has the "tomcat" role
            if (httpRequest.isUserInRole("tomcat")) {
                chain.doFilter(request, response); // Continue the filter chain
            } else {
                httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN, "Access denied. You do not have the required role.");
            }
        } else {
            throw new ServletException("HttpServletRequest or HttpServletResponse expected but not received");
        }
    }

    @Override
    public void destroy() {
        // Cleanup code, if needed
    }
}
