package com.phongvu.gof.view;

public class CandidateView {
    private String body;

    public void setBody(String body) {
        this.body = body;
    }

    public void display() {
        System.out.println(body);
    }
}
