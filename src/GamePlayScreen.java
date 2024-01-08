import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class GamePlayScreen extends JFrame {
    private int chances = 7;
    private JLabel wordDisplay = new JLabel();
    private String word;
    private JFormattedTextField guess;
    private JLabel displayGuessesLeft = new JLabel();

    public GamePlayScreen(int gameChoice) {
        ;
        if (gameChoice != wordsAndCurrentList.currentCategory) {
            getListOfWords(gameChoice);
            wordsAndCurrentList.currentCategory = gameChoice;
            wordsAndCurrentList.newCategory = true;
        } else {
            wordsAndCurrentList.newCategory = false;
        }
        word = getWord();
//        System.out.println(word);
        JLabel guessLeftLabel = new JLabel("Guesses left:");
        setTitle("HangMan");
        setSize(600, 500);
        setLocationRelativeTo(null);


//      gets word turns it into an array then changes each part into - unless it is a space or a period
        String[] wordCharactors = word.split("");
        String[] displayedLetters = new String[wordCharactors.length];
        for (int i = 0; i < wordCharactors.length; i++) {
            if (wordCharactors[i].equals("-")) {
                displayedLetters[i] = "-";
            }
            if (wordCharactors[i].equals(" ")) {
                displayedLetters[i] = " ";
            }
            if (wordCharactors[i].equals(".")) {
                displayedLetters[i] = ".";

            }
            if (displayedLetters[i] == null) {
                displayedLetters[i] = "_";
            }
        }

// Set the text of wordDisplay to the string representation of displayedLetters array
        this.wordDisplay.setText(Arrays.toString(displayedLetters));

// Set the text of displayGuessesLeft to the string representation of chances
        this.displayGuessesLeft.setText(String.valueOf(chances));

// Create new JPanel objects
        JPanel panel = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();

// Create a new JButton with the label "Back"
        JButton backButton = new JButton("Back");

// Create a new JButton with no label
        JButton fullGuess = new JButton();

// Create a new JFormattedTextField with the initial text "Enter full guess"
        guess = new JFormattedTextField("Enter full guess");

// Add a KeyListener to the guess field
        guess.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                // If the key pressed is ENTER
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    // Call the handleGuess method
                    handleGuess();

                    // Reset the text of the guess field
                    guess.setText("Enter full guess");
                }
            }
        });
        // Set the preferred size of the guess field to 200x30 pixels
        guess.setPreferredSize(new Dimension(200, 30));

// Set the font of wordDisplay to its current font family and plain style with size 30
        wordDisplay.setFont(new Font(wordDisplay.getFont().getName(), Font.PLAIN, 30));

// Create a new JButton with the label "Enter"
        JButton EnterButton = new JButton("Enter");

// Set the default close operation for the JFrame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

// Add an ActionListener to the EnterButton
        EnterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the text entered in the guess field
                String enteredGuess = guess.getText();

                // If the entered guess is equal to the word (ignoring case)
                if (word.equalsIgnoreCase(enteredGuess)) {
                    // Create a new WinWindow
                    WinWindow win = new WinWindow();

                    // Dispose the current window
                    dispose();
                } else {
                    // Decrement the chances counter
                    chances--;

                    // Update the displayGuessesLeft text to the current number of chances
                    displayGuessesLeft.setText(String.valueOf(chances));

                    // If there are no more chances left
                    if (chances == 0) {
                        // Create a new LossWindow with the word
                        LossWindow lossWindow = new LossWindow(word);
                        // Dispose the current window
                        dispose();
                    }

                    // Reset the text of the guess field
                    guess.setText("Enter full guess");
                }
            }
        });

        // checks to see if their full guess is correct
        //if it is it opens win screen
        //if it isn't it takes away one guess attempt
// Add an ActionListener to the fullGuess button
        fullGuess.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Call the handleGuess method when the button is clicked
                handleGuess();
            }
        });

// Add a FocusListener to the guess field
        guess.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                // Clear the text of the guess field when it gains focus
                guess.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
                // Set the text of the guess field to "Enter full guess" when it loses focus
                guess.setText("Enter full guess");
            }
        });

// Create an ActionListener for the number buttons
        ActionListener numberButtons = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the button that was clicked
                JButton clickedButton = (JButton) e.getSource();

                // Get the text of the clicked button
                String buttonText = clickedButton.getText();

                // Hide the clicked button
                clickedButton.setVisible(false);

                // Check if the word contains the guessed number
                if (word.toUpperCase().contains(buttonText)) {
                    ArrayList<Integer> tempList = new ArrayList<>();
                    for (int i = 0; i < wordCharactors.length; i++) {
                        if (wordCharactors[i].equalsIgnoreCase(buttonText)) {
                            tempList.add(i);
                            displayedLetters[i] = buttonText;
                            wordDisplay.setText(Arrays.toString(displayedLetters));
                        }
                    }

                    if (!tempList.isEmpty()) {
                        String guessedSofar = Arrays.toString(displayedLetters).replaceAll(",", "");
                        guessedSofar = guessedSofar.replaceAll("\\[", "");
                        guessedSofar = guessedSofar.replaceAll("]", "");
                        guessedSofar = guessedSofar.replaceAll("\\s", "");  // Remove spaces
                        wordDisplay.setText(Arrays.toString(displayedLetters));

                        if (guessedSofar.replaceAll("\\s", "").equals(word)) {
                            WinWindow win = new WinWindow();
                            setVisible(false);
                            dispose();
                        }
                    }

                } else {
                    chances--;
                    displayGuessesLeft.setText(String.valueOf(chances));
                    if (chances == 0) {
                        LossWindow lossWindow = new LossWindow(word);
                        setVisible(false);
                        dispose();
                    }
                }
            }
        };
// Create an ActionListener for the letter buttons
        ActionListener letterButtons = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the button that was clicked
                JButton clickedButton = (JButton) e.getSource();

                // Get the text of the clicked button
                String buttonText = clickedButton.getText();

                // Hide the clicked button
                clickedButton.setVisible(false);

                // Check if the word contains the guessed letter
                if (word.toUpperCase().contains(buttonText)) {
                    ArrayList<Integer> tempList = new ArrayList<>();
                    for (int i = 0; i < wordCharactors.length; i++) {
                        if (wordCharactors[i].equalsIgnoreCase(buttonText)) {
                            tempList.add(i);
                            displayedLetters[i] = buttonText.toLowerCase();
                            wordDisplay.setText(Arrays.toString(displayedLetters));
                        }
                    }

                    if (!tempList.isEmpty()) {
                        String guessedSofar = Arrays.toString(displayedLetters).replaceAll(",", "");
                        guessedSofar = guessedSofar.replaceAll("\\[", "");
                        guessedSofar = guessedSofar.replaceAll("]", "");
                        guessedSofar = guessedSofar.replaceAll("\\s", "");  // Remove spaces
                        wordDisplay.setText(Arrays.toString(displayedLetters));

                        if (guessedSofar.replaceAll("\\s", "").equals(word)) {
                            WinWindow win = new WinWindow();
                            setVisible(false);
                            dispose();
                        }
                    }

                } else {
                    chances--;
                    displayGuessesLeft.setText(String.valueOf(chances));
                    if (chances == 0) {
                        LossWindow lossWindow = new LossWindow(word);
                        setVisible(false);
                        dispose();
                    }
                }
            }
        };

// Add an ActionListener to the backButton
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create a new MenuScreen when the button is clicked
                MenuScreen menuScreen = new MenuScreen();
                // Dispose the current window
                dispose();
            }
        });



        //adds letter buttons
        for (char c = 'A'; c <= 'Z'; c++) {
            JButton button = new JButton(Character.toString(c));
            button.addActionListener(letterButtons);
            panel.add(button);
        }


        //adds number buttons
        for (char c = '0'; c <= '9'; c++) {
            JButton button = new JButton(Character.toString(c));
            button.addActionListener(letterButtons);
            panel.add(button);
        }

// Add backButton, guess, and EnterButton to the panel
        panel.add(backButton);
        panel.add(guess);
        panel.add(EnterButton);

// Set the layout of the current container to BorderLayout
        setLayout(new BorderLayout());

// Add the panel to the current container
        add(panel);

// Add wordDisplay to the east of panel2
        panel2.add(wordDisplay, BorderLayout.EAST);

// Add panel2 to the south of the current container
        add(panel2, BorderLayout.SOUTH);

// Add displayGuessesLeft to the south of panel3
        panel3.add(displayGuessesLeft, BorderLayout.SOUTH);

// Add guessLeftLabel to the north of panel3
        panel3.add(guessLeftLabel, BorderLayout.NORTH);

// Add panel3 to the north of the current container
        add(panel3, BorderLayout.NORTH);

// Make the current container visible
        setVisible(true);


    }

    private void getListOfWords(int gamePlayChoice) {
        String[] gameChoices = new String[4];
        wordsAndCurrentList.listOfWords.clear();
        gameChoices[0] = "src/DogBreeds.txt";
        gameChoices[1] = "src/GreekHeroes.txt";
        gameChoices[2] = "src/GreekGods.txt";
        gameChoices[3] = "src/USPresidents.txt";
        try {
            File file = new File(gameChoices[gamePlayChoice]);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                wordsAndCurrentList.listOfWords.add(line);
            }
            //if the file could not be run with src/ it removes src/ then tries the chosen file again.
        } catch (FileNotFoundException e) {
            gameChoices[0] = "DogBreeds.txt";
            gameChoices[1] = "GreekHeroes.txt";
            gameChoices[2] = "GreekGods.txt";
            gameChoices[3] = "USPresidents.txt";
            try {
                File file = new File(gameChoices[gamePlayChoice]);
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();

                    wordsAndCurrentList.listOfWords.add(line);
                }
            } catch (FileNotFoundException t) {
                System.out.println("An error occured.");
            }
        }
    }

    // Method to handle the user's guess
    private void handleGuess() {
        // Get the text entered by the user in the guess field
        String guessText = guess.getText();

        // If the user's guess is correct (ignoring case)
        if (guessText.equalsIgnoreCase(word)) {
            // Create a new WinWindow
            WinWindow win = new WinWindow();
            // Dispose the current window
            dispose();
        } else {
            // If the user's guess is incorrect

            // Decrement the chances counter
            chances--;

            // Update the displayGuessesLeft text to the current number of chances
            displayGuessesLeft.setText(String.valueOf(chances));

            // If there are no more chances left
            if (chances == 0) {
                // Create a new LossWindow with the word
                LossWindow lossWindow = new LossWindow(word);
                // Dispose the current window
                dispose();
            }
        }
    }

    // Method to get a random word from the list of words
    public String getWord() {
        // Create a new Random object
        Random random = new Random();

        // Keep trying until a suitable word is found
        while (true) {
            System.out.println(wordsAndCurrentList.listOfWords.size());
            // Get a random index within the size of the list of words
            int wordIndex = random.nextInt(wordsAndCurrentList.listOfWords.size());

            // If the category is not new
            if (!wordsAndCurrentList.newCategory) {
                // If the word at the random index is not the current, previous, or two words ago
                if (wordIndex != wordsAndCurrentList.currentWord && wordIndex != wordsAndCurrentList.previousWord && wordIndex != wordsAndCurrentList.twoWordsAgo) {
                    // Update the record of the last three words
                    wordsAndCurrentList.twoWordsAgo = wordsAndCurrentList.previousWord;
                    wordsAndCurrentList.previousWord = wordsAndCurrentList.currentWord;
                    wordsAndCurrentList.currentWord = wordIndex;

                    // Get the word at the random index
                    String word = wordsAndCurrentList.listOfWords.get(wordIndex);

                    // Return the word
                    return word;
                }
            } else {
                // If the category is new

                // Reset the record of the last three words
                wordsAndCurrentList.twoWordsAgo = -1;
                wordsAndCurrentList.previousWord = -1;
                wordsAndCurrentList.currentWord = wordIndex;

                // Get the word at the random index
                String word = wordsAndCurrentList.listOfWords.get(wordIndex);

                // Return the word
                return word;
            }
        }//ToDO; make it so when typing in the guess using letters if it has a space it dosnt move to win screen
    }
}
