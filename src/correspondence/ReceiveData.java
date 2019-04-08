package correspondence;

import bean.Data;
import bean.Device;
import bean.Pond;
import dao.DataDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import service.DeviceService;
import util.EmailHelper;
import util.JsonUtil;
import util.ParamUtil;

import javax.annotation.PostConstruct;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class ReceiveData {

    @Autowired
    DeviceService deviceService;
    @Autowired
    DataDao dataDao;
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    static boolean flag = true;

    //监控设备数据接收
    public  void receiveService()throws Exception{
        DatagramSocket receiveSocket = new DatagramSocket(12306);
        byte[] buffer = new byte[1024];
        DatagramPacket dp = new DatagramPacket(buffer, 1024);
        while (flag) {
            receiveSocket.receive(dp);
            final DatagramPacket fdp = dp;
            new Thread(new Runnable() {
                public void run() {
                    byte[] data = fdp.getData();
                    int length = fdp.getLength();
                    String dataStr = new String(data, 0, length);

                    try {
                        String did = dataStr.split("\\|",2)[0];
                        String datas = dataStr.split("\\|",2)[1];
                        Device device = deviceService.getDevice(did);
                        Pond pond = device.getPond();
                        //此处存data到redis中
                        stringRedisTemplate.opsForValue().set("did:"+did,datas);
                        stringRedisTemplate.opsForValue().set("time_did:"+did,String.valueOf(System.currentTimeMillis()));
                        stringRedisTemplate.opsForValue().set("pid:"+pond.getPid(),"");
                        dataDao.saveData(new Data(datas,String.valueOf(System.currentTimeMillis()),device));
                        Map cre_paramMap =  ParamUtil.getCre_paramMap(pond.getCreature().getCre_params());
                        Map pidMap = JsonUtil.data2PidMap(datas);
                        Map checkParamMap = ParamUtil.getParamMap( cre_paramMap ,pidMap);
                        String msg = ParamUtil.checkParamMap(checkParamMap,pond);
                        if (msg.length()!=0){
                            String emailState = stringRedisTemplate.opsForValue().get("pid_email:"+pond.getPid());
                            if (emailState==null){
                                EmailHelper.sendEmail(pond.getUser().getEmail(),"！alarm！",msg);
                                stringRedisTemplate.opsForValue().set("pid_email:"+pond.getPid(),"",21600,TimeUnit.SECONDS);
                            }
                        }

                    }catch (Exception e){
                        throw e;
                    }
                }
            },"--------receive thread").start();

        }
        //5,释放流资源
        receiveSocket.close();
    }

    //监控设备数据接收
//    @PostConstruct
    public  void cul_devceService()throws Exception{
        DatagramSocket receiveSocket = new DatagramSocket(12307);
        byte[] buffer = new byte[1024];
        DatagramPacket dp = new DatagramPacket(buffer, 1024);
        while (flag) {
            receiveSocket.receive(dp);
            final DatagramPacket fdp = dp;
            new Thread(new Runnable() {
                public void run() {
                    InetAddress ipAddress = fdp.getAddress();
                    String ip = ipAddress.getHostAddress();
                    byte[] data = fdp.getData();
                    int length = fdp.getLength();
                    String dataStr = new String(data, 0, length);

                    try {
                        String did = dataStr.split("\\|",2)[0];
                        String datas = dataStr.split("\\|",2)[1];
                        if (datas.equals("on")||datas.equals("off")){
                            stringRedisTemplate.opsForValue().set("ip_did:"+did,ip);
                            stringRedisTemplate.opsForValue().set("state_did:"+did,datas);
                        }
                        sendService("start up successful!",ip);
                    }catch (Exception e){

                    }
                }
            }).start();

        }
        //5,释放流资源
        receiveSocket.close();
    }



    //养殖设备--数据发送和确认
    public  boolean sendService(String data,String ip)throws Exception {
        DatagramSocket receiveSocket = new DatagramSocket(12307);
        byte[] buffer1 = new byte[1024];

        DatagramSocket sendSocket = new DatagramSocket();
        byte[] buffer2 = data.getBytes();
        DatagramPacket dp = new DatagramPacket(buffer2, buffer2.length, InetAddress.getByName(ip), 12316);
        sendSocket.send(dp);


        dp = new DatagramPacket(buffer1, 1024);
        receiveSocket.receive(dp);
        String confirm = new String(dp.getData(),0,dp.getData().length);
        receiveSocket.close();
        sendSocket.close();
        if (confirm.equals("1")){
            return true;
        }
        return false;

    }
}
