import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WinWindow extends JFrame {

    public WinWindow() {
        try {
            ImageIcon imageIcon = new ImageIcon("awesome.jpg");
            if (imageIcon.getImageLoadStatus() == MediaTracker.COMPLETE) {
                Image image = imageIcon.getImage();
                Image newImg = image.getScaledInstance(400, 400, java.awt.Image.SCALE_SMOOTH);
                imageIcon = new ImageIcon(newImg);
                JLabel imageLabel = new JLabel(imageIcon);
                getContentPane().add(imageLabel, BorderLayout.CENTER);
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
                ImageIcon imageIcon = new ImageIcon("src/awesome.jpg");
                if (imageIcon.getImageLoadStatus() == MediaTracker.COMPLETE) {
                    Image image = imageIcon.getImage();
                    Image newImg = image.getScaledInstance(400, 400, java.awt.Image.SCALE_SMOOTH);
                    imageIcon = new ImageIcon(newImg);
                    JLabel imageLabel = new JLabel(imageIcon);
                    getContentPane().add(imageLabel, BorderLayout.CENTER);
                } else {
                    System.out.println("Images not found");
                }
        }



    //
        setSize(500, 500);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.decode("#A020F0"));
        JLabel win= new JLabel("Winner!");
        JPanel panel= new JPanel(new GridBagLayout());
        JButton playAgain= new JButton("Play Again?");
        JButton closeButton = new JButton("Close");
        setTitle("Win Screen");
        closeButton.addActionListener(e-> dispose());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


//        panel.add(playAgain);
//        panel.add(closeButton);
//        getContentPane().add(panel);
//        getContentPane().add(panel, BorderLayout.EAST );
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(10, 10, 10, 10);
        panel.add(playAgain, c);
        c.gridx = 0;
        c.gridy = 1;
        panel.add(closeButton, c);
        getContentPane().add(panel, BorderLayout.SOUTH);
//        getContentPane().add(imageLabel, BorderLayout.CENTER);
        setVisible(true);
        playAgain.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuScreen menuScreen = new MenuScreen();
                setVisible(false);
                dispose();
            }
        });

    }
}
