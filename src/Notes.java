import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jfugue.Pattern;

public class Notes implements Iterable<Note> {

	private final List<Note> notes;

	public Notes(Note note) {
		this();
		notes.add(note);
	}

	public Notes() {
		this.notes = new ArrayList<Note>();
	}

	public static Notes build(String what) {
		return new Notes(new Note(what));
	}

	public Notes add(String what) {
		this.notes.add(new Note(what));
		return this;
	}

	public Pattern toPattern() {
		Pattern p = new Pattern();
		for (Note n : notes) {
			p.add(n.describe());
		}
		return p;
	}

	public Notes orn() {
		return new OrnTransform().apply(this);
	}

	Notes copy(int from, int upTo) {
		Notes song = new Notes();
		for (int i = from; i < this.notes.size() + upTo; i++) {
			Note n = this.notes.get(i);
			song.add(n);
		}
		return song;
	}

	public Notes add(Note n) {
		this.notes.add(n);
		return this;
	}

	public Notes border() {
		Notes song = copy(0, -2);
		Note n_1 = this.notes.get(this.notes.size() - 2);
		song.add(n_1.half_time());
		Note last = last();
		song.add(last.half_time().half_time());
		song.add(last.next_note().half_time().half_time());
		song.add(last);
		return song;
	}

	/**
	 * Dies with the first note
	 */
	public Notes die(String length) {
		Notes song = copy(0, -0);
		song.add(song.first().length(length));
		return song;
	}

	/**
	 * Dies with the last note
	 */
	public Notes repeat_and_die(String length) {
		Notes song = copy(0, -0);
		song.add(song.last().length(length));
		return song;
	}

	Note last() {
		return get(this.notes.size() - 1);
	}

	private Note first() {
		return get(0);
	}

	Note get(int i) {
		return notes.get(i);
	}

	public Notes add(String what, int len) {
		return add(new Note(what, len, "q"));
	}

	public Notes invert() {
		Note n = get(0);
		Note n2 = get(1);
		Note n3 = get(2);
		n = n3.move(-n.dist(n3));
		n2 = n3.move(-n2.dist(n3));
		return new Notes().add(n).add(n2).add(n3).add(copy(3, -0));
	}

	Notes add(Notes copy) {
		this.notes.addAll(copy.notes);
		return this;
	}

	public Notes invert_radix(int i) {
		return new InvertRadix().apply(this);
	}

	public Notes translate(int i) {
		return new TranslateTransform(i).apply(this);
	}

	public Notes translate_except_tail(int i) {
		Notes notes = head().translate(i);
		return notes.add(tail());
	}

	private Notes tail() {
		int last = find_tail_position();
		return copy(last, -0);
	}

	private Notes head() {
		int last = find_tail_position();
		return copy(0, -last);
	}

	private int find_tail_position() {
		int last = notes.size() - 1;
		Note current = last();
		int diff = norm(get(notes.size() - 2).dist(current));
		for (; last >= 0; last--) {
			Note previous = get(last);
			if (norm(previous.dist(current)) != diff) {
				break;
			}
			current = previous;
		}
		return last;
	}

	private int norm(int k) {
		if (k == 0) {
			return k;
		}
		return k / Math.abs(k);
	}

	public static Notes buildFrom(String content) {
		String[] parts = content.split("\\s");
		Notes notes = new Notes();
		for (String p : parts) {
			notes.add(new Note(p));
		}
		return notes;
	}

	public Iterator<Note> iterator() {
		return notes.iterator();
	}

	public int size() {
		return notes.size();
	}
	
	@Override
	public String toString() {
		String s = "[";
		for(Note n : notes) {
			s += "," + n;
		}
		return s + "]";
	}
}
