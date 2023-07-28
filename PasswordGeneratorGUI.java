//render the GUI components layout, this class inherits from the JFrame class.
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class PasswordGeneratorGUI extends JFrame{
      private PasswordGenerator passwordGenerator;

      public PasswordGeneratorGUI(){
            //rendering frame and add a title.
            super("Password Generator");

            //setting the size of the GUI.
            setSize(540, 570);

            //preventing the GUI from resizing.
            setResizable(false);

            //setting layout to null to have control over position and size of our components in the app.
            setLayout(null);

            //terminates the program when the GUI is closed(ends the process).
            setDefaultCloseOperation(EXIT_ON_CLOSE);

            //centering the GUI to the screen
            setLocationRelativeTo(null);

            //init password generator.
            passwordGenerator = new PasswordGenerator();

            //rendering GUI components
            addGuiComponents();
      }
      private void addGuiComponents(){
            //creating title text
            JLabel titleLabel = new JLabel("Password Generator");

            //increasing the font size and make it bold.
            titleLabel.setFont(new Font("Dialog", Font.BOLD, 32));

            //centering the text to the screen.
            titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

            //setting x,y coordinates and width/height values.
            titleLabel.setBounds(0,10,540,39);

            //adding to GUI
            add(titleLabel);

            //creating result textarea.
            JTextArea passwordOutput = new JTextArea();

            // preventing editing the text area.
            passwordOutput.setEditable(false);
            passwordOutput.setFont(new Font("Dialog", Font.BOLD, 32));

            //adding scrollability in case output becomes too big.
            JScrollPane passwordOutputPane = new JScrollPane(passwordOutput);
            passwordOutputPane.setBounds(25, 97, 479, 70);

            //creating a black border around the text area.
            passwordOutputPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            add(passwordOutputPane);

            //creating password length label.
            JLabel passwordLengthLabel = new JLabel("Password Length: ");
            passwordLengthLabel.setFont(new Font("Dialog", Font.PLAIN, 25));
            passwordLengthLabel.setBounds(25, 215, 272, 39);
            add(passwordLengthLabel);

            //creating password length input field
            JTextArea passwordLengthInputArea = new JTextArea();
            passwordLengthInputArea.setFont(new Font("Dialog", Font.PLAIN, 32));
            passwordLengthInputArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            passwordLengthInputArea.setBounds(250, 215, 192, 39);
            add(passwordLengthInputArea);

            //creating toggle buttons
            //uppercase letter toggle 
            JToggleButton uppercaseToggle = new JToggleButton("Uppercase");
            uppercaseToggle.setFont(new Font("Dialog", Font.PLAIN, 26));
            uppercaseToggle.setBounds(25, 300, 225, 56);
            add(uppercaseToggle);
            
            //lowercase letter toggle.
            JToggleButton lowercaseToggle = new JToggleButton("Lowercase");
            lowercaseToggle.setFont(new Font("Dialog", Font.PLAIN, 26));
            lowercaseToggle.setBounds(280, 300, 225, 56);
            add(lowercaseToggle);

            //numbers toggle
            JToggleButton numbersToggle = new JToggleButton("Numbers");
            numbersToggle.setFont(new Font("Dialog", Font.PLAIN, 26));
            numbersToggle.setBounds(25, 373, 225, 56);
            add(numbersToggle);

            //symbols toggle.
            JToggleButton symbolsToggle = new JToggleButton("Symbols");
            symbolsToggle.setFont(new Font("Dialog", Font.PLAIN, 26));
            symbolsToggle.setBounds(280, 373, 225, 56);
            add(symbolsToggle);

            //creating generate button.
            JButton generateButton = new JButton("Generate");
            generateButton.setFont(new Font("Dialog", Font.PLAIN, 32));
            generateButton.setBounds(155, 477, 222, 41);
            generateButton.addActionListener(new ActionListener() {
                  public void actionPerformed(ActionEvent e) {
                        //validation: generate a password only when length > 0 and one of the toggled buttons is pressed.
                        if(passwordLengthInputArea.getText().length() <= 0) {
                              return;
                        }
                        boolean anyToggleSelected = lowercaseToggle.isSelected() || uppercaseToggle.isSelected() || numbersToggle.isSelected() || symbolsToggle.isSelected();

                        //generate password.
                        //converts the text to an integer value.
                        int passwordLength = Integer.parseInt(passwordLengthInputArea.getText());

                        if(anyToggleSelected) {
                              String generatedPassword = passwordGenerator.generatePassword(passwordLength,
                              uppercaseToggle.isSelected(),
                              lowercaseToggle.isSelected(),
                              numbersToggle.isSelected(),
                              symbolsToggle.isSelected());

                              //display password back to the user.
                              passwordOutput.setText(generatedPassword);
                        }
                  }
            });
            add(generateButton);
      }
}