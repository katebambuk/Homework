Прочитать первую часть брошюры(вторую в рамках общего развития):
https://vcfvct.files.wordpress.com/2015/06/do-you-really-get-classloaders.pdf

Видео:
- Хорошее видео, если победить индийский акцент https://www.youtube.com/watch?v=M2spZP-a2xA&t
- CEO ZeroTurnaround собственной персоной https://www.youtube.com/watch?v=t8sQw3pGJzM&t




1)	Задача - разработать загрузчик плагинов для некоего web-приложения.
Пользователи пишут класс, имплементирующий следующий интерфейс (предоставляется приложением):

```java
public interface Plugin {
    void run(String[] urls);
}
```

Затем пользователи кладут скомпилированные классы в папку, называя её именем плагина (на свой вкус), и помещают её в директорию с плагинами в дистрибутиве предполагаемого приложения.

Необходимо реализовать класс, выполняющий работу по загрузке плагинов в приложение:


```java
public class PluginManager {
    private final String rootDirectory;

    public PluginManager(String rootDirectory) {
        this.pluginRootDirectory = pluginRootDirectory;
    }

    public Plugin loadPlugin(String pluginName) throws PluginNotFoundException {
        //TODO implement
        throw new PluginNotFoundException("couldn't locate plugin " + pluginName);
    }
}
```

`PluginManager` ищет .class-файлы плагина в *pluginRootDirectory\pluginName\*
Пользователи могут назвать свои классы одинаковыми именами, такие ситуации необходимо обрабатывать корректно.

Необходимо переопределить метод `loadClass(...)`, убрав делегацию, и добавить хранение загруженных классов, чтобы не грузить повторно то, что уже было загружено ранее.


2)	Задача - разработать `EncryptedClassLoader` (наследование от ClassLoader или URLClassLoader, на свой вкус)
EncryptedClassLoader умеет загружать зашифрованные .class файлы. Нужно переопределить метод `findClass(..)`. В нем лоадер считывает .class-файл в зашифрованный массив байт, дешифрует его и превращает в класс (с помощью метода `defineClass(…)`).
Файлы шифруются простым ‘Caesar cipher’, ‘отступ’ передаётся в параметре offset. Также класслодер принимает папку, в которой будет искать классы, и родительский класслодер.



Ограничим условия обоих заданий - мы ожидаем, что в случае и с плагином, и с зашифрованными классами, мы найдём один .class-файл от пользователя.
