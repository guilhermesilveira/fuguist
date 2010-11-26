
public class TranslateTransform implements Transform{

	private final int trans;

	public TranslateTransform(int trans) {
		this.trans = trans;
	}

	public Notes apply(Notes original) {
		Notes transformed = new Notes();
		for (Note n : original) {
			transformed = transformed.add(n.move(trans));
		}
		return transformed;
	}

}
