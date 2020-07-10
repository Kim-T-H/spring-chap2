package chap2;

import org.springframework.context.support.GenericXmlApplicationContext;

public class Main1 {

	public static void main(String[] args) {
		//이미 객체를 생성해서 container 에 저장을 함.
		//GenericXmlApplicationContext : xml을 이용하여 객체 저장
		GenericXmlApplicationContext ctx=
				new GenericXmlApplicationContext("classpath:annotation.xml");
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
