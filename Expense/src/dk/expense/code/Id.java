package dk.expense.code;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class Id {
	
	private String id;
	private int intId;
	
	
	public Id() {
		
		this.id = createId();
		this.intId = createIntId();
	}
	
	
	private String createId() {
		
		String id;
		
		id = UUID.randomUUID().toString();
		
		return id;
	}
	
	private int createIntId() {
		
		int intId;
		AtomicInteger a = new AtomicInteger();
		
		a.incrementAndGet();
		intId = a.get();
		
		return intId;
	}

	public String getId() {
		
		return id;
	}
	
	public int getIntId() {
		
		return intId;
	}
}