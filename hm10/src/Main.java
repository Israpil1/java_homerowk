import java.util.HashMap;
import java.util.Map;

class Color {
    String name;

    Color(String name) {
        this.name = name;
    }
}

public class Main {
    public static void main(String[] args) {
        String[] colors = {"red", "orange", "aqua", "pink", "gray",
                "blue", "white", "black", "yellow", "brown"};

        HashMap<String, Color> colorMap = new HashMap<>();

        for (String colorName : colors) {
            Color color = new Color(colorName.toUpperCase());
            colorMap.put(colorName, color);
        }

        int number = 1;
        for (Map.Entry<String, Color> entry : colorMap.entrySet()) {
            System.out.println(number + ") " + entry.getKey() + ": " + entry.getValue().name);
            number++;
        }
    }
}