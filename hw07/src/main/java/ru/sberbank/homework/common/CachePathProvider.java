package ru.sberbank.homework.common;

public interface CachePathProvider {
    /**
     * Отдает путь до директории для хранения временных файлов.
     * Пример:
     * C:\cache\
     * \\192.168.2.1\tmp\
     * /var/cache/
     *
     * @return строковое представление пути
     */
    String getCacheDirectoryPath();
}
