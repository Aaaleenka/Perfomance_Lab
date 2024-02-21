public class Main {
    public static void main(String[] args) {

        int n = Integer.parseInt(args[0]);
        int m = Integer.parseInt(args[1]);
        StringBuilder circle_array = new StringBuilder();
        StringBuilder answer = new StringBuilder();

        for (int i = 1; i <= n; i++) {
            circle_array.append(i);
        }

        String s = new String(circle_array);
        Character finish = ' ';

        while (finish != '1'){

            while (m > circle_array.length()){
                circle_array.append(s);
            }

            answer.append(circle_array.charAt(0));
            circle_array.delete(0,m-1);
            finish = circle_array.charAt(0);
        }

        System.out.println(answer);
    }
}