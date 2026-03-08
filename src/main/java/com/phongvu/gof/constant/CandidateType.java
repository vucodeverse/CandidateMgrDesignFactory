package com.phongvu.gof.constant;

public enum CandidateType {

    EXPERIENCE("creator.ExperienceCreator"),
    FRESHER("creator.FresherCreator"),
    INTERN("creator.InternCreator");

    private final String creatorClass;

    CandidateType(String creatorClass) {
        this.creatorClass = creatorClass;
    }

    public String getCreatorClass() {
        return creatorClass;
    }

}
