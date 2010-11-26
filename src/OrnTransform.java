
public class OrnTransform implements Transform{

	public Notes apply(Notes notes) {
		Notes song = notes.copy(0, -2);
		Note n_1 = notes.get(notes.size() - 2);
		song.add(n_1.half_time());
		Note last = notes.last();
		song.add(last.half_time().half_time());
		song.add(last.previous_note().half_time().half_time());
		song.add(last);
		return song;
	}

}
