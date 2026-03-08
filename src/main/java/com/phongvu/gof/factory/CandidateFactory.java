package com.phongvu.gof.factory;

import com.phongvu.gof.creator.CandidateCreator;
import com.phongvu.gof.utils.exception.AppException;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class CandidateFactory {
    private final Map<Integer, CandidateCreator> creatorMap = new HashMap<>();

    public CandidateFactory() {
        try {
            // Tự động quét toàn bộ package "creator"
            scanAndRegisterCreators("creator");
        } catch (Exception e) {
            System.err.println("Lỗi khi load tự động các Creator: " + e.getMessage());
        }
    }
    private void scanAndRegisterCreators(String packageName) throws Exception {
        String path = packageName.replace('.', '/');
        URL resource = Thread.currentThread().getContextClassLoader().getResource(path);

        if (resource == null) return;
        File directory = new File(resource.getFile());
        if (directory.exists() && directory.isDirectory()) {
            for (String fileName : Objects.requireNonNull(directory.list())) {
                if (fileName.endsWith(".class")) {
                    String className = packageName + '.' + fileName.substring(0, fileName.length() - 6);
                    try {
                        Class<?> clazz = Class.forName(className);
                        // Chỉ lấy những class implement từ CandidateCreator (bỏ qua bản thân interface)
                        if (CandidateCreator.class.isAssignableFrom(clazz) && !clazz.isInterface()) {
                            CandidateCreator creator = (CandidateCreator) clazz.getDeclaredConstructor().newInstance();
                            creatorMap.put(creator.supportsType(), creator);
                        }
                    } catch (Exception e) {
                        // Ignore classes that cannot be instantiated
                    }
                }
            }
        }
    }
    public CandidateCreator getCreator(int type) throws AppException {
        if (!creatorMap.containsKey(type)) {
            throw new AppException("Invalid candidate type");
        }
        return creatorMap.get(type);
    }
}
