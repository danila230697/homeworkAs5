import java.util.concurrent.CountDownLatch;

public class MainClass {

    public static final int CARS_COUNT = 4 ;
    public static void main(String[] args) {
        final CountDownLatch cd = new CountDownLatch(4);
        final CountDownLatch cd1 = new CountDownLatch(4);
        final CountDownLatch cd2 = new CountDownLatch(4);


        System.out.println( "ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!" );
        Road race = new Road( new Road( 60 ,cd2), new Tunnel(), new Road( 40,cd2 ));
        Car[] cars = new Car[CARS_COUNT];
        for ( int i = 0 ; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + ( int ) (Math.random() * 10 ),cd,cd1);
        }

        for ( int i = 0 ; i < cars.length; i++) {
            new Thread(cars[i]).start();

        }

        try {
            cd.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println( "ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!" );


        try {
            cd1.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println( "ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка дорога 60 метров закончилась!!!" );


        try {
            cd2.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println( "ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка дорога 40 метров ЗАКОНЧИЛАСЬ!!!" );
        //не придумал с винером как сделать
    }
}
