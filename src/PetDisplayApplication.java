import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class PetDisplayApplication extends JFrame {
    private JRadioButton dogButton, catButton, birdButton, pigButton, rabbitButton;
    private ButtonGroup petGroup;
    private JLabel petImageLabel;

    public PetDisplayApplication() {
        setTitle("Pet Viewer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);

        // radio buttons
        dogButton = new JRadioButton("Dog");
        catButton = new JRadioButton("Cat");
        birdButton = new JRadioButton("Bird");
        pigButton = new JRadioButton("Pig");
        rabbitButton = new JRadioButton("Rabbit");

        //only one radio button can be selected at a time
        petGroup = new ButtonGroup();
        petGroup.add(dogButton);
        petGroup.add(catButton);
        petGroup.add(birdButton);
        petGroup.add(pigButton);
        petGroup.add(rabbitButton);

        // Create a label to display the pet image
        petImageLabel = new JLabel();
        petImageLabel.setHorizontalAlignment(SwingConstants.CENTER);

        dogButton.addActionListener(new PetSelectionListener("dog.jpg"));
        catButton.addActionListener(new PetSelectionListener("cat.jpg"));
        birdButton.addActionListener(new PetSelectionListener("bird.jpg"));
        pigButton.addActionListener(new PetSelectionListener("pig.jpg"));
        rabbitButton.addActionListener(new PetSelectionListener("rabbit.jpg"));

        //panel for holding the radio buttons
        JPanel radioButtonPanel = new JPanel();
        radioButtonPanel.setLayout(new GridLayout(5, 1));
        radioButtonPanel.add(dogButton);
        radioButtonPanel.add(catButton);
        radioButtonPanel.add(birdButton);
        radioButtonPanel.add(pigButton);
        radioButtonPanel.add(rabbitButton);

        //panel for holding the label for the pet image
        JPanel imagePanel = new JPanel();
        imagePanel.add(petImageLabel);

        //main panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(radioButtonPanel, BorderLayout.WEST);
        mainPanel.add(imagePanel, BorderLayout.CENTER);

        // Add the main panel to the frame
        add(mainPanel);

        // initial image
        loadAndDisplayImage("dog.jpg");

        setVisible(true);
    }

    private class PetSelectionListener implements ActionListener {
        private String petImageFileName;

        public PetSelectionListener(String petImageFileName) {
            this.petImageFileName = petImageFileName;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            loadAndDisplayImage(petImageFileName);
        }
    }

    private void loadAndDisplayImage(String imageFileName) {
        URL imageUrl = getClass().getResource(imageFileName);
        if (imageUrl != null) {
            ImageIcon imageIcon = new ImageIcon(imageUrl);
            petImageLabel.setIcon(imageIcon);
        } else {
            petImageLabel.setIcon(null); // Clear the image if not found
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PetDisplayApplication());
    }
}
