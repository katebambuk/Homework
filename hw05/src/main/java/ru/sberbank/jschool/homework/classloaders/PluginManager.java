package ru.sberbank.jschool.homework.classloaders;

@SuppressWarnings("Duplicates")
public class PluginManager {
    // directory that contains plugin folders
    private final String rootDirectory;

    public PluginManager(String rootDirectory) {
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
    public Plugin loadPlugin(String pluginName) throws PluginNotFoundException {
        String classPath = rootDirectory + "/" + pluginName + "/";
        ClassFinder finder = new ClassFinder(classPath);
        ClassOverloader classOverloader = new ClassOverloader(classPath);

        try {
            String className = finder.findClassName();
            Class clazz = Class.forName(className.replace(".class", ""),
                    true, classOverloader);
            return (Plugin) clazz.newInstance();
        } catch (Exception e) {
            throw new PluginNotFoundException("couldn't locate plugin " + pluginName);
        }
    }
}