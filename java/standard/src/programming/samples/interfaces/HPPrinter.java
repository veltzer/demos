package programming.samples.interfaces;

public class HPPrinter implements Animal, Shape {
	// private int pagesPerMinute=6;
	// private boolean canPrintinColor=true;
	public void printDocument(boolean postscript) {
		System.out.print("working...");
	}

	@Override
	public int getNumberOfLegs() {
		return 0;
	}

	@Override
	public void hide() {

	}

	@Override
	public void run() {

	}

	@Override
	public double getArea() {
		return 0;
	}

	@Override
	public double getPerimeter() {
		return 0;
	}
}
