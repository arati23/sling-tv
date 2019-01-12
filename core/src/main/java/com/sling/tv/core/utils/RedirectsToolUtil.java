package com.sling.tv.core.utils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sling.tv.core.beans.RedirectsResultBean;

import java.util.List;
public class RedirectsToolUtil {

    private List<RedirectsResultBean> Redirects;

    public void setRedirects(List<RedirectsResultBean> Redirects) {
        this.Redirects = Redirects;
    }
    public String getRedirectsJSONResults(List<RedirectsResultBean> results) {
        RedirectsToolUtil redirectResults = new RedirectsToolUtil();
        redirectResults.setRedirects(results);
        return getRedirectsJSONObject(redirectResults);

    }
    public String getRedirectsJSONObject( RedirectsToolUtil redirectsToolUtil) {
        Gson gson = new GsonBuilder().create();
        return gson.toJson(redirectsToolUtil);

    }

}
