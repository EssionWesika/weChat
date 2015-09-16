package com.wxsys.util;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

@Component("commonUtil")
public class CommonUtil {
	
	public JSONObject getToken(String appid,String secret){
		String url="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+appid+"&secret="+secret;
		JSONObject json = (JSONObject) JSON.parse(loadJson(url, "GET"));
		return json;
	}
	
	/**
	 * 解析URL返回JSON数据
	 * @param url
	 * @param method [get],[post]
	 * @return String
	 */
	public String loadJson (String url,String method) {
		if(isNull(url,method))return null;
        StringBuilder json = new StringBuilder();
        try {
            URL urlObject = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) urlObject.openConnection();
            if(method.toLowerCase().equals("get")){
            	conn.setRequestMethod("GET");
            }else{
            	conn.setRequestMethod("POST");
            }
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine = null;
            while ( (inputLine = in.readLine()) != null) {
                json.append(inputLine);
            }
            in.close();
        } catch (MalformedURLException e) {
        	e.printStackTrace();
        } catch (IOException e) {
        	e.printStackTrace();
        }
        return json.toString();
    }
	
	/**
	 * MD5生成工具
	 * @param a 需要转换MD5的原始字符串
	 * @param charset 设置编码类型 推荐不写默认UTF-8编码以保证数据相同
	 * @return
	 */
	public String MD5(String a,String ...charset){
		try {
			if(!isNull(charset)){
				return DigestUtils.md5Hex(a.getBytes(charset[0]));
			}else{
				return DigestUtils.md5Hex(a.getBytes("UTF-8"));
			}
		} catch (UnsupportedEncodingException e) {
			String b=(charset==null||charset[0]=="")?"UTF-8":charset[0];
			throw new RuntimeException("MD5转换过程中出现错误,指定的编码集不对,默认UTF-8，您目前指定的编码集是:" + b);
		}
	}
	/**
	 * 生成验证码 版本一
	 * @param res
	 * @return
	 */
	public String getVerificationV1 (HttpServletResponse res){
		BufferedImage bim = new BufferedImage(70, 20,BufferedImage.TYPE_INT_RGB);
        Graphics2D gc = bim.createGraphics();
        // 设置图片填充颜色
        gc.setColor(Color.yellow);
        gc.fillRect(0, 0, 70, 35);
        // 设置边框颜色
        gc.setColor(Color.pink);
        gc.drawRect(0, 0, 69, 34);
        // 产生4位随机数
        Random rand = new Random();
        StringBuffer sb = new StringBuffer();
        // 设置干扰线颜色
        gc.setColor(Color.cyan);
        for (int j = 0; j < 30; j++) {
            int x = rand.nextInt(70);
            int y = rand.nextInt(35);
            int x1 = rand.nextInt(6);
            int y1 = rand.nextInt(6);
            // 往图片里面画干扰线
            gc.drawLine(x, y, x + x1, y + y1);
        }
        for (int i = 0; i < 4; i++) {
            int m = rand.nextInt(9);
            // 将生成的数字写入到图片中去,int转成string
            String str = String.valueOf(m);
            // 设置字体颜色
            gc.setColor(Color.RED);
            gc.drawString(str, i * 10 + 20, 15);
            sb.append(m);
        }
        // 将stringbuffer转成string
        String sb1 = String.valueOf(sb);
        // 将生成的验证码的值放到session中去
        /*req.getSession().setAttribute("code", sb1);*/
        // 将图片以流的形式输出
        try {
			ServletOutputStream sos = res.getOutputStream();
			ImageIO.write(bim, "jpg", sos);
			sos.close();
		} catch (IOException e) {
			return "";
		}
		
		return sb1;
	}
	
	/**
	 * 生成验证码 版本二
	 * @param res
	 * @return
	 */
	public String getVerificationV2(int width, int height,HttpServletResponse res) {
		char charTable[] = { 'a', 'A', 'b', 'B', 'c', 'C', 'd', 'D', 'e','E', 'f', 'F', 'g', 'G', 'h', 'H', 'i', 'I', 'j', 'J', '0', '1','2', '3', '4', '5', '6', '7', '8', '9' };
		if (width <= 0)width = 100;
		if (height <= 0)height = 60;
		BufferedImage image = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0, 0, width, height);
		g.setColor(new Color(0x5265fd));
		g.drawRect(0, 0, width-1, height-1);
		String str = "";
		for (int x = 0; x < 4; x++) {
			str += charTable[(int) (Math.random() * charTable.length)];
		}
		g.drawString(str.substring(0, 1), 0, 15);
		g.drawString(str.substring(1, 2), 15, 17);
		g.drawString(str.substring(2, 3), 35, 19);
		g.drawString(str.substring(3, 4), 50, 16);
		Random rand = new Random();
		for (int i = 0; i < 10; i++) {
			int x = rand.nextInt(width);
			int y = rand.nextInt(height);
			g.drawOval(x, y, 1, 1);
		}
		g.dispose();
		try {
			ImageIO.write(image, "JPEG",res.getOutputStream());
		} catch (IOException e) {
			return "";
		}
		return str;
	}
	
	/**
	 * 生成指定长度 不包含 0 随机字符串类型数字 长度范围 2~8位
	 * @param length
	 * @return
	 */
	public  String getRandom(int length){
		StringBuffer sb = new StringBuffer();
		String m=MD5(UUID.randomUUID().toString());
		for (int i = 0; i < m.length(); i++) {
			char c = m.charAt(i);
			if(Character.isDigit(c)){
				if(String.valueOf(c).equals("0"))continue;
				sb.append(c);
				if(sb.length()==length)return sb.toString();
			}
		}
		return sb.toString();
	}
	/**
	 * 判断是否为11位手机号
	 * @param phone
	 * @return
	 */
	public Boolean isPhone(String phone){
		int len = phone.length();if(len!=11)return false;
		if(!String.valueOf(phone.charAt(0)).equals("1"))return false;
		for (int i = 0; i < len; i++) {
			char c = phone.charAt(i);
			if(!Character.isDigit(c))return false;
		}
		return true;
	}
	/**
	 * 判断是否为整数
	 * @param str
	 * @return
	 */
	public boolean isInteger(String str) {
		Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
	    return pattern.matcher(str).matches();
	}
	/**
	 * 传入任意数量字符串类型的参数，若有一个为null或者“” 则返回true
	 * @param ... a
	 * @return
	 */
	public Boolean isNull(String... a){
		if(a==null||a.length==0){
			return true;
		}else{
			for (int i = 0; i < a.length; i++) {
				if(((a[i]==null)||(a[i].trim().equals(""))==true))return true;
			}
			return false;
		}
	}
	
	public void getImagePixel(String image) throws Exception {  
        int[] rgb = new int[3];  
        File file = new File(image);  
        BufferedImage bi = null;  
        try {  
            bi = ImageIO.read(file);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        int width = bi.getWidth();  
        int height = bi.getHeight();  
        int minx = bi.getMinX();  
        int miny = bi.getMinY();  
        System.out.println("width=" + width + ",height=" + height + ".");  
        System.out.println("minx=" + minx + ",miniy=" + miny + ".");  
        for (int i = minx; i < width; i++) {  
            for (int j = miny; j < height; j++) {  
                int pixel = bi.getRGB(i, j); // 下面三行代码将一个数字转换为RGB数字  
                rgb[0] = (pixel & 0xff0000) >> 16;  
                rgb[1] = (pixel & 0xff00) >> 8;  
                rgb[2] = (pixel & 0xff);  
                System.out.println("i=" + i + ",j=" + j + ":(" + rgb[0] + ","  
                        + rgb[1] + "," + rgb[2] + ")");  
            }  
        }  
    }
	public static void main(String[] args) {
		System.out.println(new Date().getTime());
	}
}
