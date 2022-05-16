
public class HTMLCharacterEscapes extends CharacterEscapes {
	
	private static final long serialVersionUID = 1L;
	private final int[] asciiEscapes;
	
	public HTMLCharacterEscapes() {
		asciiEscapes = CharacterEscapes.standardAsciiEscapesForJSON();
		asciiEscapes['<'] = CharacterEscapes.ESCAPE_CUSTOM;
		asciiEscapes['\"'] = CharacterEscapes.ESCAPE_CUSTOM;
		asciiEscapes['\''] = CharacterEscapes.ESCAPE_CUSTOM;		
	}
	
	@Override
	public int[] getEscapeCodesForAscii() {
		return asciiEscapes;
	}
	
	@Override
	public SerializableString getEscapesSequence(int ch) {
		return nuew SerializedString(StringEscapeUtils.escapeHtml4(Character.toString(char) ch)));
	}
}