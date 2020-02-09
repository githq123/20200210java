package com.qfedu.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ImageCodeServlet
 */
//这个类要解决图片验证码的问题
//注册功能有让用户输入图片中显示的内容的模块。
@WebServlet("/imagecode")
public class ImageCodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImageCodeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    //得把生成图片的成语都读到Java中。
    private List<String> words=new ArrayList<>(); 
    @Override
    public void init() throws ServletException {
    	// TODO Auto-generated method stub
    	//super.init();
    	//文件new_words.txt的路径
    	String path=getServletContext().getRealPath("WEB-INF/new_words.txt");
    	BufferedReader reader=null;
    	
    	
    	try {
			reader=new BufferedReader(new InputStreamReader(new FileInputStream(path),"utf-8"));
			String line;
			for(;(line=reader.readLine())!=null;) {
				words.add(line);
			}
    	
    	
    	} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(reader!=null) {
				try {
					reader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//Java要创建一个图片
		//Java AWT组件  用于窗体绘制等等的工具类。
		//也就是Java可以描述一个图片
		//世界上的图片我们理解为一个一个的像素点。
		//每一个像素点都有自己的颜色，组成了一个图片。
		//每一个像素点都有（透明度、红色、绿色、蓝色）四个部分。
		//#ff00ff
		//为什么用rgb三个颜色呢？
		//因为三原色。
		
		//Java int类型是四个字节。
		//很巧，颜色有四个部分。
		//我们用int类型的四个字节来描述颜色的四个部分
		//高八位：透明度
		//次高八位：红色
		//次低八位：绿色
		//低八位：蓝色
		
		//1111 1111
		//1111 1111
		//0000 0000
		//0000 0000
		
		//生成一个验证码
		//先确定要生成的验证码的内容的字符串，也就是一个成语
		//随机
		Random random=new Random();
		int index=random.nextInt(words.size());
		String word=words.get(index);
		
		//把word放入session中
		request.getSession().setAttribute("word", word);
		
		//定义验证码的宽高
		int width=120;
		int height=30;
		//创建一个图片
		//说明每一个像素点，都是透明的，只有RGB三个部分
		BufferedImage bufferedImage=new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		//绘制对象，也就是在图片上画画的对象
		Graphics graphics=bufferedImage.getGraphics();
		//验证码有三个部分：
		//第一部分，背景
		//第二部分，内容
		//第三部分，噪点
		//三个部分颜色要彼此不接近。
		//背景比较浅，内容比较深，噪点不深不浅。
		//rgb各个部分，接近255，就是浅颜色
		//接近0，就是深颜色。
		//接近128，就是不深不浅
		Color color=getRandomColor(200,240);
		//把绘制对象使用的颜色设置为color
		graphics.setColor(color);
		//画满整个图片，把颜色置为color
		graphics.fillRect(0, 0, width, height);
		//想描一个边框
		//填充式绘制、描边式绘制两种
		//fill指的是填充
		//draw指的是描边
		graphics.setColor(Color.WHITE);
		//描边
		graphics.drawRect(0, 0, width-1, height-1);
		//第二部分
		//验证码：
		Graphics2D graphics2d=(Graphics2D) graphics;
		//设置字体
		graphics2d.setFont(new Font("宋体",Font.BOLD,18));
		
		int x=10;
		int y=20;
		//绘制每一个字，其实一共是4个字
		for (int i = 0; i < word.length(); i++) {
			char c=word.charAt(i);
			//设置一个比较深的颜色
			graphics2d.setColor(getRandomColor(20, 110));
			//绘制的文字往往“歪歪扭扭”。
			int angle=random.nextInt(60)-30;
			//Java认的是弧度制
			double theta=Math.PI/180*angle;
			//写文字，歪歪扭扭的写
			
			//转
			graphics2d.rotate(theta, x, y);
			//写,在x，y这个坐标上写c
			graphics2d.drawString(""+c, x, y);
			//转回来
			graphics2d.rotate(-theta, x, y);
			
			x+=30;
		}
		
		//第三部分：
		//噪点
		int x1;
		int x2;
		int y1;
		int y2;
		for (int i = 0; i < 30; i++) {
			//线的长度不会超过sqrt(288)。
			//而且线的方向全都是左上到右下
			graphics.setColor(getRandomColor(160, 200));
			x1=random.nextInt(width);
			y1=random.nextInt(height);
			x2=x1+random.nextInt(12);
			y2=y1+random.nextInt(12);
			graphics.drawLine(x1, y1, x2, y2);
		}
		
		//至此绘制三个部分全部完成
		//释放资源
		graphics.dispose();
		
		//通过response把bufferedImage用jpg的图片格式发送到浏览器上
		ImageIO.write(bufferedImage, "jpg", response.getOutputStream());
		
	}
	
	//调用的时候要求ec>fc
	//如果我传入200,240，将会得到一个浅颜色
	private Color getRandomColor(int fc,int ec) {
		Random random=new Random();
		int r=random.nextInt(ec-fc)+fc;
		int g=random.nextInt(ec-fc)+fc;
		int b=random.nextInt(ec-fc)+fc;
		return new Color(r,g,b);
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
