import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.jfugue.Player;

public class Base {

	public static void main(String[] args) {
		
		List<Transform> trans = new ArrayList<Transform>();
//		trans.add(new NothingTransform());
		trans.add(new TranslateTransform(4));
		trans.add(new InvertRadix());
		trans.add(new OrnTransform());
		Notes notes = Notes.buildFrom("G A B A G F Gh Eh Eh");

		Player player = new Player();
//		player.play(notes.toPattern());

//		Scanner sc = new Scanner(System.in);
//		LinkedList<Notes> n = new LinkedList<Notes>();
//		n.add(notes);
//		Map<Transform, Notes> applied = new Genetic().apply(n, trans, sc, player);

//		Notes notes = Notes.build("F").add("E").add("D").add("C").add("D").add("E").add("F").add("E").add(note("D", "h")).add(note("D", "h"));

//		Pattern pattern1 = new Pattern(
//		 "F5q E5q D5q C5q D5q E5q F5q E5q C#5h. C#5h.");
//		player.play(notes.toPattern());
//		player.play(notes.orn().toPattern());
//		player.play(notes.add(notes.invert().border()).toPattern());
//		player.play(notes.translate(4).invert_radix(3).toPattern());
		player.play(notes.translate_except_tail(4).toPattern());
//		player.play(notes.invert(3).toPattern());
//		player.play(notes.border().toPattern());
//		player.play(notes.die("w").toPattern());
//		player.play(notes.border().die("w").toPattern());

	}

}
