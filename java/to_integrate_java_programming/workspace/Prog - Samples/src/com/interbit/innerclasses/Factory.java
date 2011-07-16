package com.interbit.innerclasses;

/* Write the following exercise:
 * 
 * Write a factory which has workers.
 * Workers should be inner classes of the factory.
 * The factory should hold an array of all it's workers.
 * When ever a worker is created it automatically
 * adds itself to the list of the factory workers.
 * 
 * Write a small test to show that all is working.
 */

public class Factory {
	private Worker[] workers;
	private int index;
	private String name;

	public class Worker {
		String name;
		int id;

		/**
		 * @param name
		 * @param id
		 */
		public Worker(String name, int id) {
			super();
			this.name = name;
			this.id = id;
			addWorker(this);
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		@Override
		public String toString() {
			return super.toString() + " " + name;
		}
	}

	public Factory(String iname) {
		name = iname;
		workers = new Worker[100];
		index = 0;
	}

	public void addWorker(Worker t) {
		workers[index++] = t;
	}

	public int getNumOfWorkers() {
		return index;
	}

	public static void main(String[] args) {
		Factory f = new Factory("The factory");
		f.new Worker("mark", 4564);
		f.new Worker("alex", 645);
		System.out.println(f.getNumOfWorkers());
		f.printYourself();
	}

	private void printYourself() {
		for (int i = 0; i < index; i++) {
			System.out.println(workers[i]);
		}

	}

	@Override
	public String toString() {
		return super.toString() + " " + name;
	}
}
