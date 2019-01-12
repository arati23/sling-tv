package com.sling.tv.core.servlet;

import com.sling.tv.core.services.RedirectsToolService;
import com.sling.tv.core.utils.RedirectsToolUtil;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 *
 * Redirects Servlet: This servlet takes the filtered text String
 * and the offset from the request and gets the results in json format
 *
 */
@SlingServlet(description = "JSON response to fetch redirects", methods = { "GET" }, paths = "/bin/sling/redirects", extensions = { "json" }, metatype = true)
public class RedirectsToolServlet extends SlingAllMethodsServlet {

    private static final long serialVersionUID = 1L;

    private static final Logger LOGGER = LoggerFactory
            .getLogger(RedirectsToolServlet.class);


    @Reference
    private transient RedirectsToolService redirectsToolService;

    @Override
    protected void doGet(final SlingHttpServletRequest request,
                         final SlingHttpServletResponse response) throws IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        StringBuilder jsonResponse = new StringBuilder();
        String[] selectors = request.getRequestPathInfo().getSelectors();
        LOGGER.info("in Redirects tools Servlet");
        if (selectors.length >= 1) {
            String offset = selectors[1];
            String limit = selectors[2];

            RedirectsToolUtil redirectsResultUtil = new RedirectsToolUtil();
            String redirectsResultResponse;
            redirectsResultResponse = redirectsResultUtil.getRedirectsJSONResults(redirectsToolService.getResults(offset,limit));
            jsonResponse.append(redirectsResultResponse);
        } else {
            LOGGER.error("Invalid Request");
        }
        response.getWriter().write(jsonResponse.toString());
    }
}