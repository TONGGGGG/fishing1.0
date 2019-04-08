package listener;

import correspondence.ReceiveData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

@Service
public class StartServiceListener implements  ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    ReceiveData receiveData;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if(contextRefreshedEvent.getApplicationContext().getParent() == null){
            //需要执行的代码
            new Thread(new Runnable() {
                public void run() {
                    try {
                        receiveData.receiveService();
                    }
                    catch (Exception e){
                        System.out.println("------服务启动失败------");
                    }
                }
            },"--------startService thread").start();
            System.out.println("------服务启动成功------");
        }
    }

}
