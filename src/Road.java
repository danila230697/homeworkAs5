import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;


public class Road extends Stage{
    final CyclicBarrier cb = new CyclicBarrier( 4 );
    CountDownLatch cd2 = new CountDownLatch(4);

    public Road ( int length,CountDownLatch cd2) {
        this.length = length;
        this.description = "Дорога " + length + " метров" ;
        this.cd2=cd2;

    }
    @Override
    public void go (Car c) {

        try {
           cb.await();
            System.out.println(c.getName() + " начал этап: " + description);
            Thread.sleep(length / c.getSpeed() * 1000 );
            System.out.println(c.getName() + " закончил этап: " + description);
            if(length==40) {
                cd2.countDown();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }

    }
    private ArrayList<Stage> stages;
    public ArrayList<Stage> getStages () { return stages; }
    public Road(Stage... stages) {
        this .stages = new ArrayList<>(Arrays.asList(stages));
    }
}
