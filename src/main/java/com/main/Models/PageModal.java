package com.main.Models;

import com.main.Entities.ClassEntity;
import org.springframework.data.domain.Page;

import java.util.List;

public class PageModal<E> {
    private List<E> data;
    private Integer totalPage;
    private long totalElement;
    private Integer page;
    private Integer size;

    public PageModal(Page<E> page) {
        this.data = page.getContent();
        this.totalPage = page.getTotalPages();
        this.totalElement = page.getTotalElements();
        this.size = page.getSize();
        this.page = page.getNumber() + 1;

    }

    public PageModal(Page page, List<E> data) {
        this.data = data;
        this.totalPage = page.getTotalPages();
        this.totalElement = page.getTotalElements();
        this.size = page.getSize();
        this.page = page.getNumber() + 1;
    }

    public List<E> getData() {
        return data;
    }

    public void setData(List<E> data) {
        this.data = data;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public long getTotalElement() {
        return totalElement;
    }

    public void setTotalElement(long totalElement) {
        this.totalElement = totalElement;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
