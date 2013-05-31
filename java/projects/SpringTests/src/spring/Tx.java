package spring;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

public class Tx {
	private PlatformTransactionManager txManager;

	public void doIt() {
		TransactionDefinition def = new DefaultTransactionDefinition(
				TransactionDefinition.PROPAGATION_NESTED);

		TransactionStatus tx = txManager.getTransaction(def);

		try {
			// do something
			txManager.commit(tx);
		} catch (Exception e) {
			txManager.rollback(tx);
		}

	}
}
