package com.main.Models;

import java.util.List;

public class Search {
    private Integer page;
    private Integer perPage;
    private List<Filter> filters;
    private Filter sort;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPerPage() {
        return perPage;
    }

    public void setPerPage(Integer perPage) {
        this.perPage = perPage;
    }

    public List<Filter> getFilters() {
        return filters;
    }

    public void setFilters(List<Filter> filters) {
        this.filters = filters;
    }

    public Filter getSort() {
        return sort;
    }

    public void setSort(Filter sort) {
        this.sort = sort;
    }
}
