package com.phongvu.gof.creator;

import com.phongvu.gof.dto.CandidateDTO;
import com.phongvu.gof.model.Candidate;

public interface CandidateCreator {
    public int supportsType();
    public Candidate createCandidate(CandidateDTO dto);
}
