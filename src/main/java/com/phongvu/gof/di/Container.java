package com.phongvu.gof.di;


import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Container {
    private static final Map<Class<?>, Object> instances = new HashMap<>();
    private static final Set<Class<?>> components = new HashSet<>();

    private Container() {
    }

    // Đăng ký những class cần quản lý
    public static void register(Class<?> c) {
        components.add(c);
    }

    // Lấy instance ra
    public static Object get(Class<?> clazz) {
        return instances.get(clazz);
    }

    // Khởi tạo các dependence và Inject tự động
    public static void init() throws Exception {
        // 1. Khởi tạo các instance
        for (Class<?> clazz : components) {
            if (!instances.containsKey(clazz)) {
                Object ins = clazz.getDeclaredConstructor().newInstance();
                instances.put(clazz, ins);
            }
        }
        // Inject tự động các dependent
        for (Class<?> clazz : components) {
            Object instance = instances.get(clazz);
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                // kiểm tra field có chứa annotation inject không
                if (field.isAnnotationPresent(Inject.class)) {
                    Class<?> fieldType = field.getType();
                    Object dep = instances.get(fieldType);
                    // nếu không lấy được dependent tức là chưa đang ký dependent đó
                    if (dep == null) {
                        throw new RuntimeException("Unregistered dependency: " + fieldType);
                    }
                    //set dependent vào object
                    field.setAccessible(true);
                    field.set(instance, dep);
                }
            }
        }
    }
}
