import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        String filename1 = args[0];
        String filename2 = args[1];

        File newFile1 = new File(filename1);
        File newFile2 = new File(filename2);

        Circle circle = new Circle(newFile1);
        Dots dots = new Dots(newFile2);

        for (List dot : dots.dots) {
            float s = (((Float) dot.get(0) - circle.x) * ((Float) dot.get(0) - circle.x))
                    + (((Float) dot.get(1) - circle.y) * ((Float) dot.get(1) - circle.y));

            if (s == (circle.r * circle.r)) System.out.println(0);
            else if (s < (circle.r * circle.r)) System.out.println(1);
            else System.out.println(2);
        }
    }
}

class Circle {

    public float x;
    public float y;
    public float r;

    Circle(File textFile) {
        try (BufferedReader input = new BufferedReader(new FileReader(textFile))) {

            String s = input.readLine();
            String parts[] = s.split(" ");
            this.x = Float.parseFloat(parts[0]);
            this.y = Float.parseFloat(parts[1]);
            this.r = Float.parseFloat(input.readLine());

        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
    }
}

class Dots {

    public List<List<Float>> dots = new ArrayList<>();

    Dots(File textFile) {

        try (BufferedReader input = new BufferedReader(new FileReader(textFile))) {

            String s = input.readLine();

            while (s != null) {
                String parts[] = s.split(" ");
                List<Float> dot = new ArrayList<>();

                dot.add(Float.parseFloat(parts[0]));
                dot.add(Float.parseFloat(parts[1]));
                dots.add(dot);
                s = input.readLine();
            }
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
    }
}