package GUI;

import java.awt.*;
import javax.swing.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;

// 이미지를 타원 모양으로 자르고 테두리를 덧입히는 JComponent를 구현한 클래스
public class ImageAvatar extends JComponent {
	private Icon image; // 이미지 아이콘
	private int borderSize = 4; // 테두리 크기
	private Color borderColor = new Color(255, 255, 255); // 테두리 색상

	public ImageAvatar() {
	}

	// 이미지 아이콘을 반환하는 메서드
	public Icon getImage() {
		return image;
	}

	// 이미지 아이콘을 설정하는 메서드
	public void setImage(Icon image) {
		this.image = image;
	}

	// 테두리 크기를 반환하는 메서드
	public int getBorderSize() {
		return borderSize;
	}

	// 테두리 크기를 설정하는 메서드
	public void setBorderSize(int borderSize) {
		this.borderSize = borderSize;
	}

	// 테두리 색상을 반환하는 메서드
	public Color getBorderColor() {
		return borderColor;
	}

	// 테두리 색상을 설정하는 메서드
	public void setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
	}
	
	@Override
	public void paint(Graphics g) {
		if(image!= null) {
			// 이미지 처리 로직이 들어가는 부분입니다.
			int width = image.getIconWidth();
			int height= image.getIconHeight();
			// 이미지를 타원 모양으로 자르고 테두리를 씌우는 코드입니다.
			int diameter=Math.min(width, height);
			BufferedImage mask=new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
			Graphics2D g2d=mask.createGraphics();
			//이미지 부드럽게
			g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			g2d.fillOval(0,0,diameter-1,diameter-1);
			g2d.dispose();
			BufferedImage masked=new BufferedImage(diameter, diameter, BufferedImage.TYPE_INT_ARGB);
			g2d=masked.createGraphics();
			g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);;
			
			int x=(diameter-width)/2;
			int y=(diameter-height)/2;
			g2d.drawImage(toImage(image), x, y, null);
			g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.DST_IN));
			g2d.drawImage(mask, 0,0,null);
			g2d.dispose();
			Icon icon=new ImageIcon(masked);
			Rectangle size = getAutoSize(icon);
			Graphics2D g2 =(Graphics2D)g;
			g2.drawImage(toImage(icon), size.getLocation().x, size.getLocation().y, size.getSize().width, size.getSize().height, null);
			
			
			//border넣는 코드 - 필요없을 때 어떻게 처리해야할 지 모르겠음..
			 g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	            g2.drawImage(toImage(icon), size.getLocation().x, size.getLocation().y, size.getSize().width, size.getSize().height, null);
	            if (borderSize > 0) {
	                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	                g2.setColor(borderColor);
	                g2.setStroke(new BasicStroke(borderSize));
	                g2.drawOval(size.x = (borderSize / 2), size.y + (borderSize / 2), size.width - borderSize, size.height - borderSize);
	            }
	            
	            
	            
		}
		
		super.paint(g); // 기본 컴포넌트의 paint 메서드 호출
	}

	// 이미지를 화면에 맞게 조정하는 메서드
	private Rectangle getAutoSize(Icon image) {
        int w = getWidth();
        int h = getHeight();
        int iw = image.getIconWidth();
        int ih = image.getIconHeight();
        double xScale = (double) w / iw;
        double yScale = (double) h / ih;
        double scale = Math.max(xScale, yScale);
        int width = (int) (scale * iw);
        int height = (int) (scale * ih);
        int x = (w - width) / 2;
        int y = (h - height) / 2;
        return new Rectangle(new Point(x, y), new Dimension(width, height));
    }

	// Icon을 Image로 변환하는 메서드
	private Image toImage(Icon icon) {
		return ((ImageIcon)icon).getImage();
	}
}
