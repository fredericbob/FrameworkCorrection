package etu1283.framework.util;

import etu1283.framework.Mapping;
import etu1283.framework.annotation.MethodeUrl;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Util {
    public void loadMapping(String path, String tomPath, HashMap<String, Mapping> mappingUrls) throws ClassNotFoundException {
        List<Class<?>> allClass = this.getAllClass(path, tomPath);
        Mapping mapping;
        Method[] allMethods;

        for (Class<?> c : allClass) {
            allMethods = c.getMethods();


            for (Method m : allMethods) {
                if (m.isAnnotationPresent(MethodeUrl.class)) {
                    mapping = new Mapping();
                    mapping.setClassName(c.getName());
                    mapping.setMethod(m.getName());
                    mappingUrls.put(m.getAnnotation(MethodeUrl.class).Url(), mapping);
                }
            }
        }
    }

    public List<Class<?>> getAllClass(String path, String tomPath) throws ClassNotFoundException {
        List<Class<?>> classes = new ArrayList<>();
        File directory = new File(path);

        if (!directory.exists()) {
            return classes;
        }

        File[] files = directory.listFiles();
        assert files != null;
        for (File file : files) {
            if (file.isDirectory()) {
                List<Class<?>> innerClasses = this.getAllClass(file.getAbsolutePath(), tomPath);
                classes.addAll(innerClasses);
            } else if (file.getName().endsWith(".class")) {
                String absolute_path_class = file.getPath().replace("\\", "/");
                int tom_int_path = absolute_path_class.indexOf(tomPath);

                String className = absolute_path_class
                        .substring(tom_int_path + tomPath.length())
                        .replace(".class", "")
                        .replace("/", ".");
                Class<?> clazz = Class.forName(className);

                classes.add(clazz);
            }
        }
        return classes;
    }
    public String processUrl(String url_input, String ctx) {
        ctx+="/";
        int ctx_ind = url_input.indexOf(ctx);

        return url_input.substring(ctx_ind + ctx.length());
    }
}
