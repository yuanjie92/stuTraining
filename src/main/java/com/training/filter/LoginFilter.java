package com.training.filter;

import com.training.common.config.Config;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest servletRequest = (HttpServletRequest)request;
        HttpServletResponse servletResponse = (HttpServletResponse)response;
        String uri = servletRequest.getRequestURI();
        String exclude = Config.getStringProperty("filter.exclude");
        String[] excludeUri = exclude.split(",");

        boolean notFilter = false;
        if(excludeUri != null && excludeUri.length >0){
            for(String u : excludeUri){
                if(uri.contains(u)){
                    notFilter = true;
                    break;
                }
            }
        }

        if(!notFilter){
            HttpSession session = servletRequest.getSession();
            String name = (String) session.getAttribute("name");

            if (StringUtils.isBlank(name)) {
                servletResponse.sendRedirect("/login");
            }
        }
        chain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
