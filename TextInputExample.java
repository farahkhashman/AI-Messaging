

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

// this class demonstrates how to create input/output text fields
public class TextInputExample extends JPanel  {
	
	// constant width and height, and our file's name
	private final int WIDTH = 600, HEIGHT = 600, TEXTHEIGHT = 475;
	
	// our input/output text fields
	private JTextArea displayarea, typearea;
	
	// keeps track of whether input has been given
	private boolean entered = false;
	
	// possible answers for chatbot
	private final String[] answers = {"Wow! So cool, tell me more.", "How do you feel about that?",
			"You are so great. Keep on talking", "Hahaha omg no way. What then?", 
			"Could you have done something different?", "...... cannot compute. Please repeat.",
			"Yeah that's neat, but do you like computers?"};
	
	
	// the constructor now does several things rather than just set up basic graphics, so I've 
	// separated it into helper methods.
	public TextInputExample() {
		
		// gives our panel a certain layout to my liking - not necessary, just looks nice
		BoxLayout boxlayout = new BoxLayout(this, BoxLayout.Y_AXIS);
		setLayout(boxlayout);
		
		// gives our panel a nice looking border and title
		setBorder(BorderFactory.createTitledBorder("AI ChatBot"));
		
		// initializes a display area, which cannot be typed into
		displayarea = new JTextArea();
		displayarea.setEditable(false);
		
		// initializes an input area, where the user will type into
		typearea = new JTextArea();
		typearea.setEditable(true);
		
		// we'll listen for when the user presses enter - when it's pressed, we'll
		// do the same thing as when the send button is pressed.
		typearea.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
				if (e.getKeyChar() == '\n')
					sendReceive();
				}
			public void keyPressed(KeyEvent e) {}
			public void keyReleased(KeyEvent e) {}
		});
		
		// put the text fields into scroll panes so that we can see everything that's 
		// been displayed throughout the program
		JScrollPane scroll = new JScrollPane (displayarea);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		JScrollPane scroll2 = new JScrollPane (typearea);
		scroll2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		// set a "preferred" size for our scroll panels - this will not always be used, but 
		// the window will try
		scroll.setPreferredSize(new Dimension(WIDTH,TEXTHEIGHT));
		scroll2.setPreferredSize(new Dimension(WIDTH,HEIGHT-TEXTHEIGHT));
		add(scroll);
		add(scroll2);
		
		// create and add a listener to the button
		JButton sendButton = new JButton("Send");
		sendButton.addActionListener(new ActionListener() {
			
			// what we want to happen when the user clicks this button
			public void actionPerformed(ActionEvent e) {
				sendReceive();
			}
		});
		
		// create an inner container for the button and add it to our main panel
		JPanel innerPanel = new JPanel();
		innerPanel.add(sendButton);
		add(innerPanel);
		
		// the main container, with the usual setup
		JFrame frame = new JFrame();
		frame.setSize(WIDTH, HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(this);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
		this.setFocusable(true);
		
		// beginning text display
		displayarea.setText("\n Welcome to AI ChatBot. Say anything to start the conversation.");
		
		run();
	}
	
	public void sendReceive() {
		
		// if the input field has legitimate text, add it to the display field
		if (!typearea.getText().trim().equals("")) {
			displayarea.setText(displayarea.getText()+"\n\n   User4931: "+typearea.getText().trim());
			entered = true;
		}
		
		// clear out the input field
		typearea.setText("");
		
	}
	
	// very simple run method
	public void run() {
		while (true) {
			
			// if there was user input, pause for a second then give a random answer
			if (entered) {
				try {Thread.sleep(500);}
				catch (InterruptedException e) {}
				int rand = (int)(Math.random()*answers.length);
				displayarea.setText(displayarea.getText()+"\n\n   ChatBot: "+answers[rand]);
				entered = false;
			}
			try {Thread.sleep(50);} 
			catch (InterruptedException e) {}
		}
	}
	
	// the usual basic main method to get our window running
	public static void main(String[] args) {
		new TextInputExample();}
}