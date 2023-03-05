package com.main.Models;

import com.main.Entities.AnswerEntity;

import java.util.List;
import java.util.UUID;

public class DegreeGraph {
    private double degree;
    private Integer total;

    public DegreeGraph(List<String> list) {
        this.degree = Double.parseDouble(list.get(0));
        this.total = Integer.parseInt(list.get(1));
    }

    public DegreeGraph() {
    }

    public double getDegree() {
        return degree;
    }

    public void setDegree(double degree) {
        this.degree = degree;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
