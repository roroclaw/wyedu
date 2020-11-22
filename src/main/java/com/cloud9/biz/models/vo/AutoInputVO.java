package com.cloud9.biz.models.vo;

import java.util.List;

/**
 * Created by roroclaw on 2017/8/14.
 */
public class AutoInputVO {
    private String query;
    private List<AutoInputSuggestion> suggestions;

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public List<AutoInputSuggestion> getSuggestions() {
        return suggestions;
    }

    public void setSuggestions(List<AutoInputSuggestion> suggestions) {
        this.suggestions = suggestions;
    }
}
