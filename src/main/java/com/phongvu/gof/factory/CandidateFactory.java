package com.phongvu.gof.factory;

import com.phongvu.gof.creator.CandidateCreator;
import com.phongvu.gof.utils.exception.AppException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CandidateFactory {
    private final Map<Integer, CandidateCreator> creatorMap = new HashMap<>();

    // DI thủ công
    public CandidateFactory(List<CandidateCreator> creators) {
        for (CandidateCreator creator : creators) {
            creatorMap.put(creator.supportsType(), creator);
        }
    }

    public CandidateCreator getCreator(int type) throws AppException {
        if (!creatorMap.containsKey(type)) {
            throw new AppException("Invalid candidate type");
        }
        return creatorMap.get(type);
    }
}
