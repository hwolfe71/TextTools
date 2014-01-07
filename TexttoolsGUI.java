/**
 * TexttoolsGUI.java
 * @author Herb Wolfe Jr, hwolfe71@gmail.com
 *
 * A graphical interface for the Texttools class
 *
 * TODO:
 *		Implement/fix Menubar
 *		Add commands to activate a particular panel in results
 *		Add logic to get selected JRadioButton and display results
 *		Separate GUI from actual tools
 *		Read text from a file
 *		Center panel when opening
 *		reset label - when invisible, still need to reserve it's space.
 *			change text instead of setvisible(false) ?
 */

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

class TexttoolsGUI {

	private JFrame frame;
	private JTextArea textArea;
	private JRadioButton palindromeBtn;
	private JRadioButton wordsBtn;
	private JRadioButton lettersBtn;
	private JRadioButton reversalBtn;
	private JButton reset;
	private JButton display;

	private JPanel results;
	private JPanel blank;
	private JPanel labels;
	private JPanel letters;
	private JLabel[] letterCnt; 
	private JLabel resultsLabel;

	private JLabel resetLabel;

	private static String BLANKPANEL = "BLANKPANEL";
	private static String LABELSPANEL = "LABELSPANEL";
	private static String LETTERSPANEL = "LETTERSPANEL";
	private static String RESETLABELTXT = "Press the reset button to continue"; 

	private static Texttools tools;

	private ActionListener resetAction = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			// TODO: set the blank panel to be the active panel
			resetTextArea();
		}
	} ;

	private ActionListener displayAction = new ActionListener() {
		public void actionPerformed(ActionEvent e) {

			// TODO: display results based on selected JRadioButton
			/*
			   call displayPalindrome();	// completed
			   or displayReversedString();	// completed
			   or displayWordCount();		// in progress
			   or displayLetterCount();		// in progress
			 */

			if (palindromeBtn.isSelected()) {
				displayPalindrome();
			} else if (wordsBtn.isSelected()) {
				displayWordCount();
			} else if (lettersBtn.isSelected()) {
				displayLetterCount();
			} else if (reversalBtn.isSelected()) {
				displayReversedString();
			}
		}

	} ;

	public TexttoolsGUI (JFrame f) {
		this.frame = f;

		Container contents = this.frame.getContentPane();
		contents.setLayout(new BorderLayout());

		Component resetPane = this.createResetPane();
		contents.add(resetPane, BorderLayout.NORTH);

		Component text = this.createTextPane();
		contents.add(text, BorderLayout.CENTER);

		Component buttons = this.createButtons();
		contents.add(buttons, BorderLayout.EAST);

		Component results = this.createResultsPane();
		contents.add(results, BorderLayout.SOUTH);

		// create file menu with open and exit options
		JMenuBar menu = this.createMenuBar();
		//frame.addMenuBar(menu);

		tools = new Texttools();

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	} // Constructor

	/** 
	 * Create choice buttons to check if palindrome, reverse the string, count
	 * the words, count the letters 
	 * @return - the JPanel containing the buttongroup
	 */

	private Component createButtons() {

		JPanel choices = new JPanel();
		ButtonGroup options = new ButtonGroup();
		choices.setLayout(new BoxLayout(choices, BoxLayout.Y_AXIS));

		this.palindromeBtn = new JRadioButton("Test if a palindrome");
		this.reversalBtn = new JRadioButton("Reverse String");
		this.wordsBtn = new JRadioButton("Count words");
		this.lettersBtn = new JRadioButton("Count letters");
		
		options.add(palindromeBtn);
		options.add(reversalBtn);
		options.add(wordsBtn);
		options.add(lettersBtn);

		// TODO: create button group, add buttons to group, add group to panel
		choices.add(palindromeBtn);
		choices.add(reversalBtn);
		choices.add(wordsBtn);
		choices.add(lettersBtn);

		// TODO: Add padding to buttons, to center them in layout? 
		reset = new JButton("Reset");
		reset.addActionListener(resetAction);

		display = new JButton("Display Results");
		display.addActionListener(displayAction);

		choices.add(reset);
		choices.add(display);

		return choices;
	}

	/**
	 * Create Menubar
	 * @return - the JMenubar
	 */

	private JMenuBar createMenuBar() {
		JMenuBar fileMenuBar = new JMenuBar();

		// TODO: Add file menu with open and exit opens

		return fileMenuBar;
	}

	/**
	 * Create panel to display reset instructions
	 * @return - the JPanel containing the instructions
	 */

	private Component createResetPane() {
		JPanel resetPanel = new JPanel();
		resetLabel = new JLabel(" ");
		resetPanel.add(resetLabel);
		return resetPanel;
	}

	/**
	 * Create panel to display results
	 * @return - the JPanel containing the results
	 */

	private Component createResultsPane() {
		int i = 0;

		letterCnt = new JLabel[26];
		results = new JPanel(new CardLayout());

		// Panel to display either word count or is/is not a palindrome 
		labels = new JPanel();
		resultsLabel = new JLabel();
		labels.add(resultsLabel);

		letters = new JPanel();
		letters.setLayout(new GridLayout(2,13));
		for (char ch = 'A'; ch <= 'Z'; ch++) {
			String str = ch + ": ";
			letterCnt[i] = new JLabel(str);
			letters.add(letterCnt[i]);
			i++;
		}

		blank = new JPanel();

		results.add(blank, BLANKPANEL);
		results.add(letters, LETTERSPANEL);
		results.add(labels, LABELSPANEL);

		return results;
	}

	/**
	 * Create panel for text entry
	 * @return - the JPanel containing the text area
	 */

	private Component createTextPane() {
		// rows, cols?
		textArea = new JTextArea(20,50);
		return textArea;
	}

	/**
	 * Display the number of letters in the string
	 */

	private void displayLetterCount() {
		String str = textArea.getText();
		String resultStr = "", tmp;
		int [] ltrCount = tools.getLetterCount(str);
		int i = 0, total = 0;

		for (char ch = 'A'; ch <= 'Z'; ch++) {
			tmp = ch + ": " + ltrCount[i];
			letterCnt[i].setText(tmp);
			total += ltrCount[i];
			i++;
			resultStr += (tmp + "\t");
			if (i % 5 == 0) {
				resultStr += "\n";
			}
		}
		// TODO: set the active result panel in results to letters
		setEditable(false); 

		// test to determine if function works
		this.output(resultStr);
	}

	/**
	 * Adds the text to the textarea - for testing purposes
	 * @param String - the text to add.
	 */

	private void output(String resultStr) {
		textArea.append("\n\n--\n\n");
		textArea.append(resultStr);
	}

	/**
	 * Display in the result pane, whether or not the text is a palindrome
	 */

	private void displayPalindrome() {
		String str = textArea.getText();
		String resultStr;
		if (tools.isPalindrome(str)) {
			resultStr = "The text is a palindrome!\n";
		} else {
			resultStr = "The text is not a palindrome!\n";
		}

		// test to determine if function works
		this.output(resultStr);

		resultsLabel.setText(resultStr);
		// activate the resultsLabel's panel
		// results.setActive(results);
		setEditable(false);
	}

	/**
	 * Reverses the contents of the text area and display it below the text
	 */

	private void displayReversedString() {
		String str = textArea.getText();
		String results = tools.reverseString(str);

		// test to determine if function works
		this.output("The reversed string is:\n" + results);

		setEditable(false);
		// results.setActive(results);
	}

	/**
	 * Display in the result pane, the number of words in the text area
	 */

	private void displayWordCount() {
		int count = tools.getWordCount(textArea.getText());
		String resultStr;
		switch (count) {
			case 0 :
				resultStr = "There are no words in the text area!";
				break;
			case 1:
				resultStr = "There is one word in the text area!";
				break;
			default :
				resultStr = "There are " + count + " words in the text area!";
				break;
		}

		// for testing purposes
		this.output(resultStr);

		resultsLabel.setText(resultStr);
		setEditable(false);
	}

	/**
	 * Clears the text area and sets it editable
	 */

	private void resetTextArea() {
		this.textArea.setText("");
		this.setEditable(true);
	}

	/**
	 * Sets whether or not the user can type in the text area and the status
	 * of the display button and the reset label
	 * @param boolean - if the user can type in the text area.
	 */

	private void setEditable(boolean b) {
		textArea.setEditable(b);
		display.setEnabled(b);
		if (b) {
			resetLabel.setText(" ");
		} else {
			resetLabel.setText(RESETLABELTXT);
		}

	}

	public static void main (String args[]) {
		JFrame frame = new JFrame("Text utils");
		TexttoolsGUI app = new TexttoolsGUI(frame);
	}

}	// end class TexttoolsGUI
