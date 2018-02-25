package ru.sberbank.jschool.homework.classloaders.encoding;

import ru.sberbank.jschool.homework.classloaders.ClassFinder;
import ru.sberbank.jschool.homework.classloaders.Plugin;
import ru.sberbank.jschool.homework.classloaders.PluginNotFoundException;

/**
 * Created by Ekaterina Kiseleva on 25.02.2018.
 */
@SuppressWarnings("Duplicates")
public class EncryptedPluginManager {

    // directory that contains plugin folders
    private final String rootDirectory;

    public EncryptedPluginManager(String rootDirectory) {
        this.rootDirectory = rootDirectory;
    }

    /**
     * method takes as a parameter a folder name in the root plugin directory,
     * loads the plugin .class file from the folder if present,
     * and returns a Plugin object
     *
     * @param pluginName - name of the plugin folder
     * @return Plugin
     * @throws PluginNotFoundException - when folder named 'pluginName' is missing,
     *                                 or it contains no .class files
     */
    public Plugin loadPlugin(String pluginName, int offset) throws PluginNotFoundException {
        String classPath = rootDirectory + "/" + pluginName + "/";
        ClassFinder finder = new ClassFinder(classPath);
        EncryptedClassLoader encryptedClassLoader = new EncryptedClassLoader(classPath, offset);

        try {
            String className = finder.findClassName();
            Class clazz = Class.forName(className.replace(".class", ""),
                    true, encryptedClassLoader);
            return (Plugin) clazz.newInstance();
        } catch (Exception e) {
            throw new PluginNotFoundException("couldn't locate plugin " + pluginName);
        }
    }
}
