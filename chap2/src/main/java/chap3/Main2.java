package chap3;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import chap2.Executor;
import chap2.HomeController;
import chap2.InfraredRaySenSor;
import chap2.WorkUnit;

public class Main2 {

	public static void main(String[] args) {
		//ApplicationContext : 객체를 저장하고 있는 컨테이너
		//AnnotationConfigApplicationContext : 자바 설정 파일로 부터 객체를 저장하기 위한 클래스
		ApplicationContext ctx= new AnnotationConfigApplicationContext(AppCtx.class);
	Executor exec=ctx.getBean("executor",Executor.class);
	exec.addUnit(new WorkUnit());
	exec.addUnit(new WorkUnit());

	
	HomeController home =ctx.getBean("homeController",HomeController.class);
	home.checkSensorAndAlarm();
	
	//창문에 침입
	System.out.println("================창문에 침입자 발견=====================");
	InfraredRaySenSor sensor=ctx.getBean("windowSensor",InfraredRaySenSor.class);
	sensor.foundObject();
	home.checkSensorAndAlarm();
	//창문,현관에 침입
	System.out.println("================현관에 침입자 발견=====================");
	sensor =ctx.getBean("doorSensor",InfraredRaySenSor.class);
	sensor.foundObject();
	home.checkSensorAndAlarm();
	System.out.println("================전등에 침입자 발견=====================");
	sensor = new InfraredRaySenSor("전등센서");  //   실행되지 않음 컨테이너 안에 있는 객체인지 아닌지 확인!
	sensor.foundObject();
	home.checkSensorAndAlarm();
	}
}
