package AC.Meituan;

// WaitTimeoutTest.java的源码
class ThreadB extends Thread{

    public ThreadB(String name) {
        super(name);
    }

    public void run() {
        long i=0;
        System.out.println(Thread.currentThread().getName() + " run ");
        // 死循环，不断运行
        while (!Thread.currentThread().isInterrupted()){
            i++;
        }
        System.out.println(i);

    }
}

public class WaitTimeoutTest {

    public static void main(String[] args) {

        ThreadB t1 = new ThreadB("t1");

        synchronized(t1) {
            try {
                // 启动“线程t1”
                System.out.println(Thread.currentThread().getName() + " start t1");
                t1.start();

                // 主线程等待t1通过notify()唤醒 或 notifyAll()唤醒，或超过3000ms延时；然后才被唤醒。
                System.out.println(Thread.currentThread().getName() + " call wait ");
                t1.wait(3000);

                System.out.println(Thread.currentThread().getName() + " continue");
                t1.interrupt();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}