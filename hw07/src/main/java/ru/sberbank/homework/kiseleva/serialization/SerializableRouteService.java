package ru.sberbank.homework.kiseleva.serialization;

import ru.sberbank.homework.common.CachePathProvider;
import ru.sberbank.homework.common.City;
import ru.sberbank.homework.common.Route;
import ru.sberbank.homework.common.RouteService;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

/**
 * Created by Ekaterina Kiseleva on 01.03.2018.
 */
public class SerializableRouteService extends RouteService<City, Route<City>> implements Serializable {
    private HashSet<String> routeHashSet = new HashSet<>();
    private SerialRoute serialRoute;
    private String cachePath;

    public SerializableRouteService(CachePathProvider cachePathProvider) {
        super(cachePathProvider, false);
        this.cachePath = cachePathProvider.getCacheDirectoryPath();
    }

    @Override
    public Route<City> getRoute(String from, String to) {
        String key = from + "_" + to;

        Route<City> route = null;

        if (!routeHashSet.contains(key)) {
            route = super.getRoute(from, to);
            routeHashSet.add(key);

            try (FileOutputStream fos = new FileOutputStream(cachePath + "/" + key);
                 ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                serialRoute = new SerialRoute(route.getRouteName(), route.getCities());
                oos.writeObject(serialRoute);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            if (new File(cachePath + "/" + key).isFile()) {
                try (FileInputStream fis = new FileInputStream(cachePath + "/" + key);
                     ObjectInputStream ois = new ObjectInputStream(fis)) {
                    route = (SerialRoute) ois.readObject();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        return route;
    }


    @Override
    protected City createCity(int id, String cityName, LocalDate foundDate, long numberOfInhabitants) {
        return new City(id, cityName, foundDate, numberOfInhabitants);
    }

    @Override
    protected Route<City> createRoute(List<City> cities) {
        return new Route<>(UUID.randomUUID().toString(), cities);
    }
}
