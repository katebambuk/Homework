package ru.sberbank.homework.kiseleva.serialization;

import ru.sberbank.homework.common.City;
import ru.sberbank.homework.common.Route;

import java.io.*;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Ekaterina Kiseleva on 01.03.2018.
 */
public class SerialRoute extends Route implements Serializable, Externalizable {
    private String routeName;

    @Override
    public List<City> getCities() {
        return cities;
    }

    private List<City> cities = new LinkedList<>();

    public SerialRoute() {

    }

    public SerialRoute(String routeName, List<City> cities) {
        this.routeName = routeName;
        this.cities = cities;
    }

    public String toString() {
        return "Route: { " +
                String.join(" -> ", cities.stream().map(City::getCityName).collect(Collectors.toList()))
                + " }";
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(routeName);
        out.writeObject(cities.size());
        for (City city : cities) {
            out.writeObject(city.getId());
            out.writeObject(city.getCityName());
            out.writeObject(city.getFoundDate());
            out.writeObject(city.getNumberOfInhabitants());
            out.writeObject(city.getNearCities());
        }
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        routeName = (String) in.readObject();
        int size = (int) in.readObject();
        for (int i = 0; i < size; i++) {
            City city = new City();
            city.setId((int) in.readObject());
            city.setCityName((String) in.readObject());
            city.setFoundDate((LocalDate) in.readObject());
            city.setNumberOfInhabitants((long) in.readObject());
            city.setNearCities((List<City>) in.readObject());
            cities.add(city);
        }
    }
}
