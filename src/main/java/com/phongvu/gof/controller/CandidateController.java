package com.phongvu.gof.controller;

import com.phongvu.gof.creator.CandidateCreator;
import com.phongvu.gof.dto.CandidateDTO;
import com.phongvu.gof.factory.CandidateFactory;
import com.phongvu.gof.model.Candidate;
import com.phongvu.gof.utils.exception.AppException;
import com.phongvu.gof.view.CandidateView;

import java.util.ArrayList;
import java.util.List;

public class CandidateController {
    private CandidateDTO dto = new CandidateDTO();
    private List<Candidate> list = new ArrayList<>();
    private CandidateView view = new CandidateView();
    private final CandidateFactory factory;

    public CandidateController(CandidateFactory factory) {
        this.factory = factory;
    }

    //ToDo
    public void setDto(CandidateDTO dto) {
        this.dto = dto;
    }

    //Khởi tạo factory
    public Candidate create(CandidateDTO dto) {

        try {
            CandidateCreator creator
                    = factory.getCreator(dto.getTypeCandidate());
            return creator.createCandidate(dto);
        } catch (AppException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    //Thêm người mới
    public void addCandidate(Candidate candidate) {
        list.add(candidate);
    }

    //Hiển thị danh sách đối tượng
    public void printListNameCandidate() {
        StringBuilder str = new StringBuilder();
        for (Candidate candidate : list) {
            str.append(candidate).append("\n");
        }
        view.setBody(str.toString());
        view.display();
    }

    //Tìm đối tượng theo tên
    public void searchCandidate() {
        printListNameCandidate();
        StringBuilder str = new StringBuilder();
        for (Candidate candidate : list) {
            if (candidate.getTypeCandidate() == dto.getTypeCandidate()
                    && (candidate.getFirstName().contains(dto.getSearchName())
                    || candidate.getLastName().contains(dto.getSearchName()))) {
                str.append(candidate.toString()).append("\n");
            }
        }

        if (!str.isEmpty()) {
            view.setBody(str.toString());
        } else {
            view.setBody("No candidate found.");
        }
        view.display();
    }
}
