package chap2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component	//객체화 . <bean id="executor" class="chap2.Executor"/>
public class Executor {
	@Autowired	//컨테이너에서 Worker 객체를 주입   => DI
	private Worker worker;
	public void addUnit(WorkUnit unit) {
		worker.work(unit); 
	}

}
