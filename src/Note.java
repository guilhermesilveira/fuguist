class Note {
	String name;
	int octave = 5;
	String length = "q";
	static String LENGTH = "whqistxo";
	static String NOTES = "CDEFGAB";

	public Note(String name) {
		this.name = "";
		for(char c : name.toCharArray()) {
			if(LENGTH.contains(c+"")) {
				this.length = c + ""; 
			} else {
				this.name += c;
			}
		}
	}

	public Note(String name, String length) {
		this.name = name;
		this.length = length;
	}

	public String describe() {
		return name + octave + length;
	}

	public Note half_time() {
		return new Note(name, octave, half_length());
	}

	public Note(String name, int octave, String length) {
		this.name = name;
		this.octave = octave;
		this.length = length;
	}

	private String half_length() {
		return LENGTH.charAt(my_length() + 1) + "";
	}

	Note double_length() {
		return new Note(name, octave, LENGTH.charAt(my_length() - 1) + "");
	}

	private int my_length() {
		return LENGTH.indexOf(length);
	}

	public Note previous_note() {
		return new Note(previous_note_(), octave, length);
	}

	private String previous_note_() {
		return NOTES.charAt(my_note() - 1) + "";
	}

	private String next_note_() {
		return NOTES.charAt(my_note() + 1) + "";
	}

	private int my_note() {
		return NOTES.indexOf(name);
	}

	public Note next_note() {
		return new Note(next_note_(), octave, length);
	}

	public Note length(String len) {
		return new Note(name, octave, len);
	}

	public int dist(Note other) {
		return NOTES.indexOf(name) - NOTES.indexOf(other.name);
	}

	public Note move(int i) {
		return new Note(note_for(NOTES.indexOf(name) + i) + "");
	}

	private char note_for(int i) {
		return NOTES.charAt((i+NOTES.length()) % NOTES.length());
	}
	
	@Override
	public String toString() {
		return this.name + this.octave + this.length;
	}
}
