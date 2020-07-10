package chap2;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component		//객체화됨.
public class HomeController {
	private AlarmDevice alarmDevice;	//SmsAlarmDevice 객체 주입.
	private Viewer viewer;				//SmartPhoneView 객체 주입.  객체가 꼭 있어야됨!  xml에서는 bean으로 만들고 어노테이션 형태에서는 @Component 형태로 만듦.
	
	//객체의 이름으로 주입
	@Resource(name = "camera1")
	private Camera camera1;
	@Resource(name = "camera2")
	private Camera camera2;
	@Resource(name = "camera3")
	private Camera camera3;
	@Resource(name = "camera4")
	private Camera camera4;

	private List<InfraredRaySenSor> sensors;

	@Autowired(required = false)  //주입되는 객체가 없는 경우도 허용 => true 는 객체가 꼭 필요!
	private Recorder rocorder;

	@Autowired		//반드시 주입되는 객체가 존재해야함.  alarmDevice:AlarmDevice 구현클래스의 객체가 주입.
					//viewer 객체가 있어야함.
	public void prepare(AlarmDevice alarmDevice, Viewer viewer) {
		this.alarmDevice = alarmDevice;
		this.viewer = viewer;
	}

	@PostConstruct		//객체의 주입이 완료된 이후에 호출되는 메서드=> Autowired(오토와이어드) 가 전부 실행된 후에 된다는 말임.
	public void init() {
		System.out.println("init() 메서드 호출");
		viewer.add(camera1);
		viewer.add(camera2);
		viewer.add(camera3);
		viewer.add(camera4);
		viewer.draw();
	}

	@Autowired
	@Qualifier("intrusionDetection") //별명 설정
	public void setSensors(List<InfraredRaySenSor> sensors) {
		System.out.println("setSensors() 메서드 호출");
		this.sensors = sensors;
		for (InfraredRaySenSor s : sensors) {
			System.out.println(s.getName());
			//alarmDevice.alarm(s.getName());
		}
	}

	public void checkSensorAndAlarm() {
		for (InfraredRaySenSor s : sensors) {
			if (s.isObjectFounded()) {
				alarmDevice.alarm(s.getName());
			}
		}
	}

}
/*
  기본어노테이션
  1.객체 생성 : @Component
  			xml 방식: <beam id="소문자 클래스 이름" class="클래스 이름">    =>    <context:component-scan base-package="chap2" />와 함께 사용
  
  2.객체 주입 : @Autowired : 변수선언문위, 메서드 위쪽에 표현가능   , 객체 선택 기준이 자료형임., 같은 자료형을 가진 객체가 여러인 경우 주의요함.
  						 boolean  => (required=false) : 객체가 없으면 null 로 주입함.
  			@Resource(이름): 객체 중 이름으로 객체를 선택하여 주입함.
  			@Required: 객체 선택의 기준의 자료형임. 반드시 주입되어야 함. => 거의 사용 안함.
  3.그 외 
  			@PostConstruct : 객체 생성이 완료된 후 호출되는 메서드 지정. 객체생성 완료란, 필요한 객체 주입이 완료됨 시점을 말함.
  			@Qualifier(별명) :객체에 설정된 별명을 사용함.
  							@Autowired와 함께 사용됨.
  			@Scope(...) : 생성된 객체의 지속가능한 영역 설정. 일회성 객체 생성 가능
  						
 */
