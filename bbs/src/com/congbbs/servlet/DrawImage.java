package com.congbbs.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DrawImage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public static final int WIDTH = 120;//定义图片的宽和高
	public static final int HEIGHT= 30;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1.在内存中创建一张图片
		BufferedImage bi = new BufferedImage(WIDTH, HEIGHT,
				BufferedImage.TYPE_INT_RGB);
		// 2.得到图片
		Graphics g = bi.getGraphics();
		// 3.设置图片的背景颜色
		setBackGround(g);
		// 4.设置图片的边框
		setBorder(g);
		// 5.在图片上话干扰线
		drawRandomLine(g);
		String random = drawRandomNum((Graphics2D) g);//生成验证码图片
		// 7.将随机数存在session中
		request.getSession().setAttribute("chenkcode", random);
		// 8.设置响应头通知浏览器以图片的形式打开
		response.setContentType("image.jpeg");
		// 9.设置相应头通知浏览器不要缓存
		response.setDateHeader("expries", -1);
		response.setHeader("Cache-Control", "nc-cache");
		response.setHeader("Pragma", "no-Cache");
		// 10.将图片写给浏览器
		ImageIO.write(bi, "jpg", response.getOutputStream());
	}
	
	// 设置图片的背景颜色
		public void setBackGround(Graphics g) {
			g.setColor(Color.WHITE);
			g.fillRect(0, 0, WIDTH, HEIGHT);
		}

		// 设置图片的边框

		public void setBorder(Graphics g) {
			g.setColor(Color.BLUE);
			g.drawRect(1, 1, WIDTH - 2, HEIGHT - 2);
		}

		// 在图片上画随机线条

		private void drawRandomLine(Graphics g) {
			g.setColor(Color.GREEN);

			for (int i = 0; i < 5; i++) {
				int x1 = new Random().nextInt(WIDTH);
				int y1 = new Random().nextInt(HEIGHT);
				int x2 = new Random().nextInt(WIDTH);
				int y2 = new Random().nextInt(HEIGHT);
				g.drawLine(x1, y1, x2, y2);
			}
		}

		// 获得随机的字符
		private String drawRandomNum(Graphics2D g) {
			// 设置颜色
			g.setColor(Color.RED);
			// 设置字体
			g.setFont(new Font("宋体", Font.BOLD, 20));
			// 数字和字母组合
			String baseNumLetter = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
			//返回随机绘制的数字和字母
			return createRandomChar(g, baseNumLetter);

		}

		private String createRandomChar(Graphics2D g, String baseChar) {
			StringBuffer sb = new StringBuffer();
			int x = 5;
			String ch = "";
			// 控制数字
			for (int i = 0; i < 4; i++) {
				int degree = new Random().nextInt() % 30;
				ch = baseChar.charAt(new Random().nextInt(baseChar.length())) + "";
				sb.append(ch);
				// 正向角度
				g.rotate(degree * Math.PI / 180, x, 20);
				g.drawString(ch, x, 20);
				// 反向角度
				g.rotate(-degree * Math.PI / 180, x, 20);
				x += 30;
			}
			return sb.toString();
		}
}
