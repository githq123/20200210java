import java.io.*;
import java.net.*;
class MyServer{

	 public static void main(String args[]) {
		  String [] answer ={"�����������","����������","���ӡ������ɳ��"};
		 ServerSocket serverForClient=null;
		 Socket socketOnServer=null;
		 DataOutputStream out=null;
         DataInputStream  in=null;

		 try { 
		    serverForClient = new ServerSocket(2200);
         }catch(IOException e1) {
            System.out.println(e1);
         } 

         try{ 
            while(true) {
            System.out.println("�ȴ��ͻ�����");
            socketOnServer =serverForClient.accept();

             out=new DataOutputStream(socketOnServer.getOutputStream());
             in=new DataInputStream(socketOnServer.getInputStream());
             
             for(int i=0;i<answer.length;i++) {
                String s=in.readUTF(); // in��ȡ��Ϣ������״̬
                System.out.println("�������յ��ͻ�������:"+s);
                out.writeUTF(answer[i]);
                Thread.sleep(1000);
             }
             }
         }catch(Exception e) {
          System.out.println("�ͻ��ѶϿ�"+e);
         }
            

	 }
	
}