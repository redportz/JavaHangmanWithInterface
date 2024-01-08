import javax.print.attribute.standard.JobMessageFromOperator;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuScreen extends JFrame {
public MenuScreen() {
    JLabel intro = new JLabel("What Category would you like to play?");
    JPanel panel = new JPanel(new GridBagLayout());
    JButton closeButton = new JButton("Close");
    setTitle("HangMan");
    closeButton.addActionListener(e -> dispose());
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JButton DogBreads = new JButton("Dog Breeds");
    JButton GreekGods = new JButton("Greek Gods");
    JButton GreatHeroes = new JButton("Greek Heroes");
    JButton USPresidents = new JButton("U.S Presidents");

    setSize(500, 500);
    setLocationRelativeTo(null);

    // Add the intro component to the panel
    panel.add(intro);

// Create a new GridBagConstraints object
    GridBagConstraints c = new GridBagConstraints();

// Set the gridx, gridy, and insets for the constraints
    c.gridx = 0;
    c.gridy = 1;
    c.insets = new Insets(10, 10, 10, 10);

// Add the DogBreads component to the panel with the specified constraints
    panel.add(DogBreads, c);

// Update the gridy for the constraints and add the GreatHeroes component to the panel
    c.gridy = 2;
    panel.add(GreatHeroes, c);

// Update the gridy for the constraints and add the GreekGods component to the panel
    c.gridy = 3;
    panel.add(GreekGods, c);

// Update the gridy for the constraints and add the USPresidents component to the panel
    c.gridy = 4;
    panel.add(USPresidents, c);

// Update the gridy for the constraints and add the closeButton component to the panel
    c.gridy = 5;
    panel.add(closeButton, c);

// Add the panel to the center of the content pane
    getContentPane().add(panel, BorderLayout.CENTER);

// Make the current container visible
    setVisible(true);

// Add an ActionListener to the DogBreads button
    DogBreads.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Create a new GamePlayScreen for DogBreads and make it visible
            GamePlayScreen DogBreadGame = new GamePlayScreen(0);
            DogBreadGame.setVisible(true);

            // Dispose the current window
            dispose();
        }
    });

// Add an ActionListener to the GreatHeroes button
    GreatHeroes.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Create a new GamePlayScreen for GreatHeroes and make it visible
            GamePlayScreen GreatHeroesGame = new GamePlayScreen(1);
            GreatHeroesGame.setVisible(true);

            // Dispose the current window
            dispose();
        }
    });

// Add an ActionListener to the GreekGods button
    GreekGods.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Create a new GamePlayScreen for GreekGods and make it visible
            GamePlayScreen GreekGodsGame = new GamePlayScreen(2);
            GreekGodsGame.setVisible(true);

            // Dispose the current window
            dispose();
        }
    });

// Add an ActionListener to the USPresidents button
    USPresidents.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Create a new GamePlayScreen for USPresidents and make it visible
            GamePlayScreen USPresidentsGame = new GamePlayScreen(3);
            USPresidentsGame.setVisible(true);

            // Dispose the current window
            dispose();
        }
    });
    }
}
