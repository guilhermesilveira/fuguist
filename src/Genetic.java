import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.jfugue.Player;

public class Genetic {

	public Map<Transform, Notes> apply(LinkedList<Notes> allSequences, List<Transform> trans,
			Scanner sc, Player player) {
		System.out.println("Current are " + allSequences.size());
		Notes notes = allSequences.removeFirst();
		Map<Transform, Notes> applied = new HashMap<Transform, Notes>();
		for (Transform transform : trans) {
			Notes result = transform.apply(notes);
			applied.put(transform, result);
			player.play(result.toPattern());
			if(humm(sc, "Gostou? foi um " + result + " apos aplicar "+transform)) {
				allSequences.add(result);
			}
		}
		if(humm(sc, "deseja tocar novamente?")) {
			apply(allSequences, trans, sc, player);
		}
		return applied;
	}

	private boolean humm(Scanner sc, String msg) {
		System.out.println(msg);
		String naaa = sc.nextLine();
		return naaa.startsWith("s");
	}

}
