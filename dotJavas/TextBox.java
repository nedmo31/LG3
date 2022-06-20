package golf3;

public class TextBox {

	private String[] text = new String [4]; // 4 possible lines
	
	public TextBox(String s) {
		text[0] = s;
	}
	
	public TextBox(String line1, String line2) {
		text[0]=line1;
		text[1]=line2;
	}
	
	public TextBox(String line1, String line2, String line3) {
		text[0]=line1;
		text[1]=line2;
		text[2]=line3;
	}

	public TextBox(String line1, String line2, String line3, String line4) {
		text[0]=line1;
		text[1]=line2;
		text[2]=line3;
		text[3]=line4;
	}

	public String[] getText() {
		return text;
	}

	public void setText(String[] text) {
		this.text = text;
	}
	
	
	
	
}
