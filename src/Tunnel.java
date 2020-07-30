import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class Tunnel extends Stage {
    public Tunnel () {
        this.length = 80;
        this.description = "Тоннель " + length + " метров";
    }
    final Semaphore sm = new Semaphore( 2);
    final CyclicBarrier cb = new CyclicBarrier( 2 );
    @Override
    public void go (Car c) {
        try {
            try {
                sm.acquire();
                System.out.println(c.getName() + " готовится к этапу(ждет): " +
                        description);

                cb.await();
                System.out.println(c.getName() + " начал этап: " + description);
                Thread.sleep(length / c.getSpeed() * 1000 );


            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                sm.release();
                System.out.println(c.getName() + " закончил этап: " +
                        description);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
