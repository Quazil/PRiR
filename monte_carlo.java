Ten kod używa metody Monte Carlo do oszacowania pola koła. Metoda Monte Carlo polega na wygenerowaniu dużej liczby losowych punktów, a następnie wykorzystaniu stosunku liczby punktów wewnątrz koła do całkowitej liczby punktów do oszacowania pola koła.



import java.util.Random;

class monte_carlo extends Thread {
    double xStart, xStop, yStart, yStop; // początek i koniec przedziałów x i y
    int tryCount; // liczba prób
    double score; // liczba punktów w kołu
    Random random; // generator liczb pseudolosowych

    public monte_carlo(double xStart, double xStop, double yStop, double yStart, int counter) {
        this.xStart = xStart;
        this.yStart = yStart;
        this.xStop = xStop;
        this.yStop = yStop;
        this.score = 0; // zerujemy licznik
        this.random = new Random(); // tworzymy generator liczb pseudolosowych
        this.tryCount = counter; // ustawiamy liczbę prób
    }

    public void run() {
        int iter = 0; // licznik punktów w kołu
        for (int i = 0; i < this.tryCount; i++) {
            double x = Math.random(); // losujemy x
            double y = Math.random(); // losujemy y
            if ((x * x + y * y) <= 1) // sprawdzamy, czy punkt leży w kołu o promieniu 1
                iter++; // jeśli tak, to zwiększamy licznik
        }
        this.score = iter; // ustawiamy liczbę punktów w kołu
    }
    public double Score() {
        return this.score; // zwracamy liczbę punktów w kołu
    }
}

public class LAB3_MonteCarlo {
    public static void main(String[] args) {
        monte_carlo alfa, beta, gamma, omega; // tworzymy wątki
        int attmpts = 1000; // ustawiamy liczbę prób
        double a = 10; // ustawiamy bok kwadratu
        alfa = new monte_carlo(0,0, a/2, a/2, attmpts); // wątek dla lewego górnego rogu
        beta = new monte_carlo(a/2,0, 1, a/2, attmpts); // wątek dla lewego dolnego rogu
        gamma = new monte_carlo(0, a/2, a/2, a, attmpts); // wątek dla prawego górnego rogu
        omega = new monte_carlo(a/2,a/2, a, a, attmpts); // wątek dla prawego dolnego rogu
        alfa.run(); // uruchamiamy wątki
        beta.run();
	gamma.run();
        omega.run();
 try {
            alfa.join();
            beta.join();
            gamma.join();
            omega.join();
        }
        catch (Exception e){
        }
        double field = alfa.Score() + beta.Score() + gamma.Score() + omega.Score();
        field = field / ((double)attmpts * 4) * (a * a);
        System.out.println("Pole kola wynosi = " + field);
    }
}