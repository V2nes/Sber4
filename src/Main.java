import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args){
        List<City> cityList = new ArrayList<>();
        try {
            File file = new File("C:\\Users\\Admin\\Desktop\\cities.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] tokens = line.split(",");

                StringBuffer foundation = new StringBuffer();
                for (int i = 5; i < tokens.length; i++) {
                    foundation.append(tokens[i]);
                    if (i < tokens.length - 1) {
                        foundation.append(",");
                    }
                }

                City city = new City(tokens[1], tokens[2], tokens[3], Integer.parseInt(tokens[4]), foundation.toString());
                cityList.add(city);
            }
            scanner.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // Сортировка по наименованию города
        Collections.sort(cityList, City.NameComparator);

        for (City city : cityList) {
            System.out.println(city);
        }

        // Сортировка по федеральному округу и наименованию города
        Collections.sort(cityList, City.DistrictNameComparator);

        for (City city : cityList) {
            System.out.println(city);
        }

        // Преобразование списка в массив
        City[] citiesArray = cityList.toArray(new City[0]);

        // Поиск города с наибольшим количеством жителей
        int maxPopulation = 0;
        int index = -1;
        for (int i = 0; i < citiesArray.length; i++) {
            if (citiesArray[i].getPopulation() > maxPopulation) {
                maxPopulation = citiesArray[i].getPopulation();
                index = i;
            }
        }

        System.out.println("[" + index + "] = " + maxPopulation);

        Map<String, Integer> regionCityCount = new HashMap<>();
        for (City city : cityList) {
            String region = city.getRegion();
            regionCityCount.put(region, regionCityCount.getOrDefault(region, 0) + 1);
        }

        for (Map.Entry<String, Integer> entry : regionCityCount.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }
    }
}
