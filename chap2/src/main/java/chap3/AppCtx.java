package chap3;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import chap2.Camera;
import chap2.DisplayMode;
import chap2.HomeController;
import chap2.InfraredRaySenSor;

@Configuration	//설정을 위한 자바 프로그램
@ComponentScan(basePackages= {"chap2"})
public class AppCtx {
	@Bean
	public HomeController homeController() {
		HomeController c = new HomeController() ;
		return c;
			

	}
	@Bean	//객체화. <bean id="camera1".../>
	public Camera camera1() {
		Camera c= new Camera();
		c.setNumber(1);
		return c;
	}
	
	@Bean
	public Camera camera2() {
		Camera c= new Camera();
		c.setNumber(2);
		return c;
	}
	
	@Bean
	public Camera camera3() {
		Camera c= new Camera();
		c.setNumber(3);
		return c;
	}
	
	@Bean
	public Camera camera4() {
		Camera c= new Camera();
		c.setNumber(4);
		return c;
	}
	@Bean
	@Qualifier("intrusionDetection")
	public InfraredRaySenSor windowSensor() {
		return new InfraredRaySenSor("창센서");
	}
	
	@Bean
	@Qualifier("intrusionDetection")	//@Qualifier : 별명 설정
	public InfraredRaySenSor doorSensor() {
		return new InfraredRaySenSor("현관센서");
	}
	
	@Bean
	@Qualifier("intrusionDetection")
	public InfraredRaySenSor lampSensor() {
		return new InfraredRaySenSor("전등센서");
	}
	
	@Bean
	public DisplayMode displayMode() {
		DisplayMode d = new DisplayMode();
		d.setType("GRID");
		return d;
	}

}
