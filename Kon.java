import java.util.concurrent.atomic.AtomicBoolean;

// Klasa reprezentująca koń
class Kon extends Thread {

    // Pola reprezentujące imię konia, pojemność baterii oraz poziom energii
    private String imie;
    private int pojemnoscBaterii;
    private int poziomEnergii;

    // Zmienna atomiczna służąca do śledzenia stanu konia
    private final AtomicBoolean czyDziala = new AtomicBoolean();

    // Konstruktor tworzący nowego konia z podanymi parametrami
    public Kon(String imie, int pojemnoscBaterii, int poziomEnergii) {
        this.imie = imie;
        this.pojemnoscBaterii = pojemnoscBaterii;
        this.poziomEnergii = poziomEnergii;
    }

    // Nadpisanie metody start z klasy Thread
    public void start() {
        super.start();
    }

    // Metoda służąca do ładowania baterii konia
    public void ladowanieBaterii(int poziomEnergii) {
        System.out.println("Koń " + imie + " ładuje baterię");
        poziomEnergii = pojemnoscBaterii;
    }

    // Metoda służąca do zatrzymania konia
    public final void STOP() {
        System.out.println("Koń " + imie + " się zatrzymuje");
        czyDziala.set(false);
        ladowanieBaterii(poziomEnergii);
    }

    // Metoda służąca do uruchomienia konia
    public void START() throws InterruptedException {
        czyDziala.set(true);
        int iter = 0;

        // Pętla w której koń jedzie, dopóki jest uruchomiony (zmienna czyDziala jest ustawiona na true)
        while (czyDziala.get()) {

            Thread.sleep(1000); // Uśpienie wątku na 1000 milisekund (1 sekunda)
            System.out.println("Jedziemy koniem " + imie + " o ilości: " + poziomEnergii + "J energii");

            poziomEnergii -= 1; // Zmniejszenie ilości energii o 1 joule
            iter++;
            if (poziomEnergii <= 5) { // Jeśli ilość paliwa spadła poniżej 5 litrów, wyświetlenie komunikatu o potrzebie tankowania
 System.out.println("Koń " + imie + " musi naładować baterie");
            }
            if (poziomEnergii == 0) {
                STOP();
            }
        }
    }
// Nadpisanie metody run z klasy Thread
@Override
    public void run() {
        System.out.println("Koń o imieniu: " + imie);

        try {
            START();
        } catch (InterruptedException e) {
            STOP();
            e.printStackTrace();
        }
    }
}

class Symulator{
    public static void main(String[] args)  {
// Tworzenie nowych obiektów klasy Koń
        var rafal = new Koń("Rafał",10,10);
        var andrzej = new Koń("Andrzej",40,40);
        var tate = new Koń("Tate",60,60);
	// Tworzenie nowych obiektów klasy Koń
        rafal.start();
        andrzej.start();
        tate.start();
    }
}