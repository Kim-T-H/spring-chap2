package chap2;

public class InfraredRaySenSor {
	private String name;
	private boolean objectFounded;
	
	
	//생성자
	public InfraredRaySenSor(String name) {
		this.name=name;
	}
	
	public void foundObject() {
		this.objectFounded=true;
	}
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isObjectFounded() {
		return objectFounded;
	}
	public void setObjectFounded(boolean objectFounded) {
		this.objectFounded = objectFounded;
	}
	@Override
	public String toString() {
		return "InfraredRaySenSor [name=" + name + ", objectFounded=" + objectFounded + "]";
	}
	
	
	
}
