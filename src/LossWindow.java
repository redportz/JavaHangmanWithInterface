import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LossWindow extends JFrame{
    public LossWindow(String word){
        // Set the size of the window to 500x500 pixels
        setSize(500,500);

// Set the title of the window to "Loss Screen"
        setTitle("Loss Screen");

// Create a JLabel that displays the word
        JLabel displayWord= new JLabel("The word was: "+word);

        try {
            // Try to load an image from a file path
            ImageIcon imageIcon = new ImageIcon("sadness.jpg");
            if (imageIcon.getImageLoadStatus() == MediaTracker.COMPLETE) {
                Image image = imageIcon.getImage();
                Image newImg = image.getScaledInstance(400, 400, java.awt.Image.SCALE_SMOOTH);
                imageIcon = new ImageIcon(newImg);
                JLabel imageLabel = new JLabel(imageIcon);
                // Add the image to the center of the window
                getContentPane().add(imageLabel, BorderLayout.CENTER);
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            // If the image loading fails, try a different file path
            ImageIcon imageIcon = new ImageIcon("src/sadness.jpg");
            if (imageIcon.getImageLoadStatus() == MediaTracker.COMPLETE) {
                Image image = imageIcon.getImage();
                Image newImg = image.getScaledInstance(400, 400, java.awt.Image.SCALE_SMOOTH);
                imageIcon = new ImageIcon(newImg);
                JLabel imageLabel = new JLabel(imageIcon);
                // Add the image to the center of the window
                getContentPane().add(imageLabel, BorderLayout.CENTER);
            } else {
                // If the image still can't be found, print an error message
                System.out.println("Images not found");
            }
        }

// Center the window on the screen
        setLocationRelativeTo(null);

// Create a JPanel
        JPanel panel= new JPanel();

// Set the background color of the content pane to red
        getContentPane().setBackground(Color.red);

// Create a "Play Again?" button and a "Close" button
        JButton playAgain= new JButton("Play Again?");
        JButton closeButton = new JButton("Close");

// Add an action listener to the "Close" button that disposes the window when clicked
        closeButton.addActionListener(e-> dispose());

// Set the default close operation to EXIT_ON_CLOSE
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

// Add the JLabel and the "Close" button to the panel
        panel.add(displayWord);
        panel.add(closeButton);

// Create a GridBagConstraints object
        GridBagConstraints c = new GridBagConstraints();

// Set the gridx and gridy values to 0
        c.gridx = 0;
        c.gridy = 0;

// Set the insets to 10 pixels on all sides
        c.insets = new Insets(10, 10, 10, 10);

// Add the "Play Again?" button to the panel at the specified gridx and gridy values
        panel.add(playAgain, c);

// Set the gridy value to 1
        c.gridy = 1;

// Add the "Close" button to the panel at the specified gridx and gridy values
        panel.add(closeButton, c);

// Add the panel to the south area of the content pane
        getContentPane().add(panel, BorderLayout.SOUTH);

// Make the window visible
        setVisible(true);

// Add an action listener to the "Play Again?" button that creates a new MenuScreen and disposes the current window when clicked
        playAgain.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuScreen menuScreen = new MenuScreen();
                dispose();
            }
        });


    }
}
