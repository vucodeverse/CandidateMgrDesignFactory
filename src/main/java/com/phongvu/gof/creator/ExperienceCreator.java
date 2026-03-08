package com.phongvu.gof.creator;

import com.phongvu.gof.constant.CandidateType;
import com.phongvu.gof.dto.CandidateDTO;
import com.phongvu.gof.model.Candidate;
import com.phongvu.gof.model.Experience;

public class ExperienceCreator implements CandidateCreator{
    @Override
    public int supportsType() {
        return CandidateType.EXPERIENCE.ordinal();
    }

    @Override
    public Candidate createCandidate(CandidateDTO dto) {
        return new Experience(
                dto.getId(),
                dto.getFirstName(),
                dto.getLastName(),
                dto.getBirthDate(),
                dto.getAddress(),
                dto.getPhone(),
                dto.getEmail(),
                dto.getTypeCandidate(),
                dto.getYearExperience(),
                dto.getProfessionalSkill()
        );
    }
}
