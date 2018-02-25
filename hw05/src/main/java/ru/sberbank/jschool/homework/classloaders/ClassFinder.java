package ru.sberbank.jschool.homework.classloaders;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Ekaterina Kiseleva on 25.02.2018.
 */
public class ClassFinder {
    private String classPath;

    public ClassFinder(String classPath) {
        this.classPath = classPath;
    }

    public String findClassName() {
        File file = new File(classPath);
        List<String> fileNames = new ArrayList<>(Arrays.asList(file.list(new MyFileNameFilter())));
        return fileNames.get(0);
    }

    public class MyFileNameFilter implements FilenameFilter {
        private final String ext = ".class";

        @Override
        public boolean accept(File dir, String name) {
            return name.toLowerCase().endsWith(ext);
        }
    }
}
