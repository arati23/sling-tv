package com.sling.tv.core.services;

import com.sling.tv.core.beans.RedirectsResultBean;

import java.util.List;

public interface RedirectsToolService {

    List<RedirectsResultBean> getResults(String offset, String limit);

}

