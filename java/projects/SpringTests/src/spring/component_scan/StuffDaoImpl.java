package spring.component_scan;

import org.springframework.stereotype.Repository;

@Repository
public class StuffDaoImpl implements StuffDao {
	public Object getStuff(int id) {
		return "from dao: " + id;
	}
}
