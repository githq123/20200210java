import java.io.*;
import java.net.*;
public class MyClient {
   public static void main(String args[]) {

	   String [] mess ={"JAVA: 1+1��ʲô����²�����2","JAVA: ��Ϊʲ��������","JAVA: ʲô�����ܿ����ܳԡ�����"};
	   
      Socket mysocket;
      DataInputStream in=null;
      DataOutputStream out=null;
      
      try{  
         mysocket=new Socket("127.0.0.1",2200);
         in=new DataInputStream(mysocket.getInputStream());
         out=new DataOutputStream(mysocket.getOutputStream()); 
         for(int i=0;i<mess.length;i++) {
              out.writeUTF(mess[i]);
              String  s=in.readUTF();   //in��ȡ��Ϣ������״̬
              System.out.println("�ͻ��յ��������Ļش�:"+s);
              Thread.sleep(500);
            } 
       }
       catch(Exception e) {
            System.out.println("�������ѶϿ�"+e);
       }
         

   }

}