package ru.sberbank.jschool.homework.classloaders;

import java.io.*;
import java.util.*;

/**
 * Created by Ekaterina Kiseleva on 25.02.2018.
 */
public class ClassOverloader extends java.lang.ClassLoader {
    private static Map<String, Class> hashedClasses = new HashMap<String, Class>();
    private final String classPath;

    public ClassOverloader(String classPath) {
        this.classPath = classPath;
    }

    @Override
    public Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        Class<?> result = findClass(name);
        if (resolve)
            resolveClass(result);
        return result;
    }

    @Override
    public Class<?> findClass(String name) throws ClassNotFoundException {
        Class result = (Class) hashedClasses.get(name); //проверяем, быд ли класс уже загружен
        if (result != null) {
            System.out.println("cache");
            return result;
        }

        File f = findFile(name, ".class");

        if (f == null) {
            return findSystemClass(name);
        }

        try {
            byte[] classBytes = getBytes(f);
            result = defineClass(name, classBytes, 0, classBytes.length);
        } catch (ClassFormatError e) {
            throw new ClassNotFoundException("Format of class file incorrect for class " + name + ": " + e);
        }

        hashedClasses.put(name, result);
        return result;
    }

    private byte[] getBytes(File file) {
        byte[] result = new byte[(int) file.length()];
        try (FileInputStream f = new FileInputStream(file)) {
            f.read(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public File findFile(String name, String extension) {
        File f = new File((new File(classPath).getPath() +
                File.separatorChar + name.replace('/', File.separatorChar) + extension));
        if (f.exists()) {
            return f;
        }
        return null;
    }
}
