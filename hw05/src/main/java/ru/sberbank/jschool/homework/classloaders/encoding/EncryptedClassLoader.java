package ru.sberbank.jschool.homework.classloaders.encoding;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.*;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by Ekaterina Kiseleva on 25.02.2018.
 */
public class EncryptedClassLoader extends ClassLoader {
    private static Map<String, Class> hashedClasses = new HashMap<String, Class>();
    private final String classPath;
    private int offset;

    public EncryptedClassLoader(String classPath, int offset) {
        this.classPath = classPath;
        this.offset = offset;
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
        Class result = (Class) hashedClasses.get(name);
        if (result != null) {
            System.out.println("cache");
            return result;
        }

        File f = findFile(name, ".class");

        if (f == null) {
            return findSystemClass(name);
        }

        try {
            byte[] classBytes = getBytes(f, offset);
            result = defineClass(name, classBytes, 0, classBytes.length);
        } catch (ClassFormatError e) {
            throw new ClassNotFoundException("Format of class file incorrect for class " + name + ": " + e);
        }

        hashedClasses.put(name, result);
        return result;
    }

    private byte[] getBytes(File file, int offset) {
        byte[] result = new byte[(int) file.length()];
        try (FileInputStream f = new FileInputStream(file)) {
            f.read(result);
            for (int i = 0; i < result.length; i++) {
                result[i] -= offset;
            }
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
