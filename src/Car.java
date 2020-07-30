import java.util.concurrent.CountDownLatch;

public class Car implements Runnable {
    private static int CARS_COUNT;
    private Road race;
    private int speed;
    private String name;
    CountDownLatch cd = new CountDownLatch(4);
    CountDownLatch cd1 = new CountDownLatch(4);
    public String getName () {
        return name;
    }
    public int getSpeed () {
        return speed;
    }

    public Car (Road race, int speed,CountDownLatch cd,CountDownLatch cd1) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
        this.cd=cd;
        this.cd1=cd1;
    }

    @Override
    public void run () {
        try {
            System.out.println( this .name + " готовится" );
            Thread.sleep( 500 + ( int )(Math.random() * 800 ));

            System.out.println( this .name + " готов" );
            cd.countDown();
            Thread.sleep( 500 + ( int )(Math.random() * 800 ));
        } catch (Exception e) {
            e.printStackTrace();
        }

        for ( int i = 0 ; i < race.getStages().size(); i++) {
            race.getStages().get(i).go( this );
            cd1.countDown();
        }

    }
}
