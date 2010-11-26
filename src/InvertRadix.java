
public class InvertRadix implements Transform{

	public Notes apply(Notes notes) {
		Note n = notes.get(0);
		Note n2 = notes.get(1);
		Note n3 = notes.get(2);
		n2 = n.move(-n2.dist(n));
		n3 = n.move(-n3.dist(n));
		return new Notes().add(n).add(n2).add(n3).add(notes.copy(3, -0));
	}

}
