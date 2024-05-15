package org.servlet.p2p_lottery.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class BlacklistFilter implements Filter {
    private Set<String> blacklist;

    @Override
    public void init(FilterConfig filterConfig) {
        // Load the blacklist from a configuration file or database
        blacklist = new HashSet<>();
        blacklist.add("baduser1");
        blacklist.add("baduser2");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String userName = httpRequest.getParameter("name");
        if (userName != null && blacklist.contains(userName.toLowerCase())) {
            httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN, "Access denied. Your name is on the blacklist.");
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
        // Clean up resources, if needed
        blacklist.clear(); // Clear the blacklist to release memory
    }
}
