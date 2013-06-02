package spring.component_scan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("stuffService")
public class StuffServiceImpl implements StuffService {
	private StuffDao stuffDao;

	@Autowired
	public void setStuffDao(StuffDao istuffDao) {
		stuffDao = istuffDao;
	}

	public void doSomethingWithStuff(int id) {
		Object o = stuffDao.getStuff(id);

		// do something...
		System.out.println("doing something with " + o);
	}
}
