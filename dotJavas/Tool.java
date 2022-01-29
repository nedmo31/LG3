package golf3;

public class Tool {

	private String name, message;
	private int charges;
	
	public Tool() {
		name = "default";
		message = "default tool";
		charges = 0;
	}
	
	public void use() {
		
	}
		
	public void unuse() {
		
	}
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getCharges() {
		return charges;
	}


	public void setCharges(int charges) {
		this.charges = charges;
	}

	public GravityHammer getHammer() {
		return new GravityHammer();
	}
	
	public WindWaker getWaker() {
		return new WindWaker();
	}
	
	public GreenFlattener getFlat() {
		return new GreenFlattener();
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public class GravityHammer extends Tool {

		public GravityHammer() {
			setName("Gravity Hammer");
			setCharges(0);
			setMessage("Gravity Hammer used! Gravity has been lowered.");
		}
		
		public void use() {
			if (Play.currentPlayer.getGravHamCharges() <= 0)
				return;
			Ball.gravity = 5.0;
			Play.currentPlayer.setGravHamCharges(Play.currentPlayer.getGravHamCharges() - 1);
			setCharges(Play.currentPlayer.getGravHamCharges());
			MyPanel.currentTextBox = new TextBox(getMessage());
		}
		
		public void unuse() {
			Ball.gravity = 8.0;
		}
		
	}
	
	public class WindWaker extends Tool {
		
		//private double oldWind;
		
		public WindWaker() {
			setName("Wind Waker");
			setCharges(0);
			setMessage("Wind Waker used! The winds have calmed.");
		}
		
		public void use() {
			if (Play.currentPlayer.getWindWakCharges() <= 0)
				return;
			//oldWind = Play.current.getWind();
			Play.current.setWind(0);
			Play.currentPlayer.setWindWakCharges(Play.currentPlayer.getWindWakCharges() - 1);
			setCharges(Play.currentPlayer.getWindWakCharges());
			MyPanel.currentTextBox = new TextBox(getMessage());
		}
		
		public void unuse() {
			//Play.current.setWind(oldWind);
		}
		
	}
	
	public class GreenFlattener extends Tool {
		
		public GreenFlattener() {
			setName("Green Flattener");
			setCharges(0);
			setMessage("Green Flattener used! The greens are now flat.");
		}
		
		public void use() {
			if (Play.currentPlayer.getFlattenerCharges() <= 0)
				return;
			Play.current.getGreen().flattenSlope();
			Play.currentPlayer.setFlattenerCharges(Play.currentPlayer.getFlattenerCharges() - 1);
			setCharges(Play.currentPlayer.getFlattenerCharges());
			MyPanel.currentTextBox = new TextBox(getMessage());
		}
		
		public void unuse() {
			//No unuse
		}
		
	}
}
