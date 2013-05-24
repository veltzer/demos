package programming.samples.interfaces;

public class Zoo {
	private Animal[] animals;
	private int index;

	public Zoo() {
		super();
		animals = new Animal[10];
		index = 0;
	}

	public void addAnimal(Animal newAnimal) {
		animals[index++] = newAnimal;
	}
}
