/*
 * Copyright © 2022. This code's author is Viacheslav Mikhailov (mikhailowvw@gmail.com)
 */
package algos.graph.objects;

public class CityNode {
    private City city;
    public CityNode(City c) {
        this.city = c;
    }
    public City getCity() {
        return city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        CityNode cityNode = (CityNode) o;

        return city == cityNode.city;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + city.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return city.toString();
    }
}