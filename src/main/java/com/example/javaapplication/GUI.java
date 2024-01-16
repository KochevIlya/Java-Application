package com.example.javaapplication;

import lombok.SneakyThrows;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileReader;
import java.io.StringReader;
import java.util.Objects;

public class GUI implements ActionListener {

    JFrame frame;
    JDialog decryptDialog;
    JDialog encryptDialog;
    JComboBox<String> versionChooser;
    JComboBox<String> actionChooser1;
    JComboBox<String> calcChooser;
    JComboBox<String> calcChooser2;
    JComboBox<String> findChooser;
    JPanel panel1;
    JPanel panel2;
    JPanel panel3;
    JPanel panel4;
    JPanel panel5;
    JPanel textAreasPanel;
    JPanel decryptPanel;
    JPanel encryptPanel;
    JPanel yourKeyPanel;
    JPanel submitAndReloadPane;
    JPanel submitPanel;
    JPanel submitPane;
    JPanel buttonPanel;
    JScrollPane pane;
    JScrollPane pane1;
    JLabel encLabel;
    JLabel decrLabel;
    JLabel textLabel;
    JLabel submitLabel1;
    JLabel calcLabel;
    JLabel findLabel;
    JTextArea preTextArea;
    JTextArea postTextArea;
    JPasswordField decrTextField;
    JPasswordField encTextField;
    Border border;
    JButton chooseInputButton;
    JButton chooseOutputButton;
    JButton submitButton;
    JButton reloadButton;
    JButton calcButton;
    JButton decrButton;
    JButton encryptWithYourKey;
    JButton encryptWithGeneratedKey;
    JButton submitYourKey;
    Font myFont = new Font("Cascadia Code", Font.PLAIN, 16);
    Font myFontLittle = new Font("Cascadia Code", Font.PLAIN, 13);
    String encKey = "SunnyDayHome1234";
    String decrKey = "";
    String readerName;
    String writerName;
    Result result = new Result();
    Decider decider = new Decider();
    Finder finder = new Finder();
    Calculator calculator = new Calculator();
    Replacer replacer = new Replacer();
    Reader reader;
    Writer writer;
    File inputFile;
    File outputFile;
    String outFile;

    GUI() {
        frame = new JFrame("Java Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(800, 400));
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);

        panel1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));

        panel2 = new JPanel();
        panel2.setLayout(new GridBagLayout());

        panel3 = new JPanel(new GridBagLayout());
        panel4 = new JPanel(new GridBagLayout());
        panel5 = new JPanel(new GridBagLayout());

        panel1.setPreferredSize(new Dimension(100, 80));
        panel2.setPreferredSize(new Dimension(200, 100));
        panel3.setPreferredSize(new Dimension(300, 100));
        panel4.setPreferredSize(new Dimension(100, 45));
        panel5.setPreferredSize(new Dimension(200, 100));

        frame.add(panel1, BorderLayout.NORTH);
        frame.add(panel2, BorderLayout.WEST);
        frame.add(panel3, BorderLayout.EAST);
        frame.add(panel4, BorderLayout.SOUTH);
        frame.add(panel5, BorderLayout.CENTER);

        String[] versions = {"--", "Version 1", "Version 2"};
        versionChooser = new JComboBox<>(versions);
        versionChooser.setPreferredSize(new Dimension(200, 40));
        versionChooser.setFont(myFont);
        versionChooser.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {

                    String selectedOption = (String) versionChooser.getSelectedItem();
                    if(selectedOption.equals("Version 2")){
                        calcChooser.setVisible(false);
                        submitPanel.add(calcChooser2);
                    }
                }
            }
        });

        String[] actions = {"Archive", "Encrypt", "Archive -> Encrypt", "Encrypt -> Archive", "Do nothing"};
        actionChooser1 = new JComboBox<>(actions);
        actionChooser1.setPreferredSize(new Dimension(100, 40));
        actionChooser1.setFont(myFont);
        actionChooser1.setBorder(BorderFactory.createLineBorder( Color.BLUE, 2));

        encryptDialog = new JDialog();
        encryptDialog.setLayout(new GridBagLayout());
        encryptDialog.setSize(new Dimension(450, 450));
        encryptDialog.setLocationRelativeTo(null);

        encryptPanel = new JPanel(new GridLayout(0, 1, 80, 60));
        encryptPanel.setPreferredSize(new Dimension(260, 200));

        encryptWithYourKey = new JButton("Encrypt with your key");
        encryptWithYourKey.addActionListener(this);
        encryptWithYourKey.setFont(myFont);
        encryptWithYourKey.setOpaque(true);
        encryptWithYourKey.setBackground(Color.white);
        encryptWithYourKey.setBorder(BorderFactory.createLineBorder( Color.BLUE, 2));

        submitYourKey = new JButton("Submit Key!");
        submitYourKey.addActionListener(this);
        submitYourKey.setFont(myFont);
        submitYourKey.setOpaque(true);
        submitYourKey.setBackground(Color.white);
        submitYourKey.setBorder(BorderFactory.createLineBorder( Color.BLUE, 2));

        encryptWithGeneratedKey = new JButton("Encrypt with generated key");
        encryptWithGeneratedKey.addActionListener(this);
        encryptWithGeneratedKey.setFont(myFont);
        encryptWithGeneratedKey.setBorder(BorderFactory.createLineBorder( Color.BLUE, 2));
        encryptWithGeneratedKey.setOpaque(true);
        encryptWithGeneratedKey.setBackground(Color.white);

        encTextField = new JPasswordField();
        encTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                encTextField.setText(null); // Empty the text field when it receives focus
                encTextField.setForeground(Color.black);
                textLabel.setVisible(true);
            }
            @Override
            public void focusLost(FocusEvent e) {}
        });
        encTextField.setForeground(Color.lightGray);
        encTextField.setFont(myFontLittle);
        encTextField.setSize(100, 100);

        textLabel = new JLabel("<html>/* After entering KEY click<br>'Submit Key!' */</html>");
        textLabel.setFont(myFontLittle);
        textLabel.setForeground(Color.gray);
        textLabel.setLabelFor(encTextField);

        yourKeyPanel = new JPanel(new GridLayout(0, 1, 40, 30));
        yourKeyPanel.setPreferredSize(new Dimension(260, 200));
        yourKeyPanel.add(encTextField);
        yourKeyPanel.add(submitYourKey);
        yourKeyPanel.add(textLabel);
        textLabel.setVisible(false);
        encryptDialog.add(yourKeyPanel);
        yourKeyPanel.setVisible(false);

        actionChooser1.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {

                    String selectedOption = (String) actionChooser1.getSelectedItem();
                    if(selectedOption.equals("Encrypt") || selectedOption.equals("Encrypt -> Archive") || selectedOption.equals("Archive -> Encrypt")){
                        encryptPanel.add(encryptWithGeneratedKey);
                        encryptPanel.add(encryptWithYourKey);
                        encryptPanel.setBackground(Color.pink);
                        encryptDialog.add(encryptPanel);
                        encryptDialog.setVisible(true);
                    }
                }
            }
        });

        decryptDialog = new JDialog();
        decryptDialog.setLayout(new GridBagLayout());
        decryptDialog.setSize(new Dimension(450, 450));
        decryptDialog.setLocationRelativeTo(null);

        decryptPanel = new JPanel(new FlowLayout());
        decryptPanel.setPreferredSize(new Dimension(320, 230));

        decrLabel = new JLabel("<html>Oops!<br><br>Chosen file is encrypted!<br>Enter decryption key:<br><br></html>");
        decrLabel.setFont(myFontLittle);
        decrLabel.setLabelFor(decryptPanel);

        decrTextField = new JPasswordField();
        decrTextField.setFont(myFontLittle);
        decrTextField.setPreferredSize(new Dimension(280, 40));

        border = null;

        decrButton = new JButton("Decrypt");
        decrButton.addActionListener(this);
        decrButton.setFont(myFont);
        decrButton.setOpaque(true);
        decrButton.setPreferredSize(new Dimension(150, 40));
        decrButton.setBorder(BorderFactory.createLineBorder( Color.BLUE, 2));
        decrButton.setBackground(Color.white);

        decryptPanel.add(decrLabel);
        decryptPanel.add(decrTextField);
        decryptPanel.add(decrButton);
        decryptPanel.setBorder(BorderFactory.createTitledBorder(border, "¯\\_(ツ)_/¯", 1, 2, myFont, Color.BLUE));

        decryptDialog.add(decryptPanel);
        decryptDialog.setVisible(false);

        submitPane = new JPanel(new GridLayout(0, 1, 0, 10));

        submitLabel1 = new JLabel("Choose what to do with file:");
        submitLabel1.setFont(myFont);

        submitPane.add(submitLabel1);
        submitPane.add(actionChooser1);
        submitPane.setBackground(Color.white);

        calcLabel = new JLabel("Choose way to calculate:");
        calcLabel.setFont(myFont);

        findLabel = new JLabel("  Choose way to find:");
        findLabel.setFont(myFont);

        String[] waysToCalculate = {"Using API Calculator", "Using Regex"};
        String[] wayToCalculate = {"Using API Calculator"};
        calcChooser = new JComboBox<>(waysToCalculate);
        calcChooser.setPreferredSize(new Dimension(100, 40));
        calcChooser.setFont(myFontLittle);
        calcChooser.setBorder(BorderFactory.createLineBorder( Color.BLUE, 2));

        calcChooser2 = new JComboBox<>(wayToCalculate);
        calcChooser2.setPreferredSize(new Dimension(100, 40));
        calcChooser2.setFont(myFontLittle);
        calcChooser2.setBorder(BorderFactory.createLineBorder( Color.BLUE, 2));

        String[] waysToFind = { "Using Regex"};
        findChooser = new JComboBox<>(waysToFind);
        findChooser.setPreferredSize(new Dimension(100, 40));
        findChooser.setFont(myFontLittle);
        findChooser.setBorder(BorderFactory.createLineBorder( Color.BLUE, 2));

        submitPanel = new JPanel(new GridLayout(0, 1, 0, 10));
        submitPanel.add(findLabel);
        submitPanel.add(findChooser);
        submitPanel.add(calcLabel);
        submitPanel.add(calcChooser);
        submitPanel.setBackground(Color.white);

        encLabel = new JLabel("Choose Version :");
        encLabel.setFont(myFont);

        submitAndReloadPane = new JPanel(new GridLayout(1, 0, 592, 10));
        submitAndReloadPane.setBackground(Color.white);

        submitButton = new JButton("Submit!");
        submitButton.addActionListener(this);
        submitButton.setPreferredSize(new Dimension(100, 30));
        submitButton.setFont(myFont);
        submitButton.setBorder(BorderFactory.createLineBorder( Color.BLUE, 2));

        reloadButton = new JButton("Reload!");
        reloadButton.addActionListener(this);
        reloadButton.setPreferredSize(new Dimension(100, 30));
        reloadButton.setFont(myFont);
        reloadButton.setOpaque(true);
        reloadButton.setBorder(BorderFactory.createLineBorder( Color.BLUE, 2));
        submitAndReloadPane.add(reloadButton);
        submitAndReloadPane.add(submitButton);
        panel4.add(submitAndReloadPane);

        border = null;

        panel1.setBorder(BorderFactory.createTitledBorder(border, "Step 0", 1, 2, myFont, Color.BLUE));

        panel1.add(encLabel);
        panel1.add(versionChooser);

        panel2.setBorder(BorderFactory.createTitledBorder(border, "Step 1", 1, 2, myFont, Color.BLUE));

        panel3.add(submitPane);
        panel3.setBorder(BorderFactory.createTitledBorder(border, "Step 3", 1, 2, myFont, Color.BLUE));


        panel5.add(submitPanel);
        panel5.setBorder(BorderFactory.createTitledBorder(border, "Step 2", 1, 2, myFont, Color.BLUE));

        buttonPanel = new JPanel(new GridLayout(0, 1, 0, 35));
        buttonPanel.setBackground(Color.white);

        chooseInputButton = new JButton("File to read");
        chooseInputButton.addActionListener(this);
        chooseInputButton.setFont(myFont);
        chooseInputButton.setBackground(Color.pink);
        chooseInputButton.setOpaque(true);
        chooseInputButton.setBorder(BorderFactory.createLineBorder( Color.BLUE, 2));
        chooseInputButton.setFocusable(true);
        chooseInputButton.setPreferredSize(new Dimension(150, 40));

        chooseOutputButton = new JButton("File to write");
        chooseOutputButton.addActionListener(this);
        chooseOutputButton.setFont(myFont);
        chooseOutputButton.setBackground(Color.pink);
        chooseOutputButton.setOpaque(true);
        chooseOutputButton.setBorder(BorderFactory.createLineBorder( Color.BLUE, 2));
        chooseOutputButton.setPreferredSize(new Dimension(150, 40));

        buttonPanel.add(chooseInputButton);
        buttonPanel.add(chooseOutputButton);

        panel2.add(buttonPanel);

        calcButton = new JButton("Calculate!");
        calcButton.addActionListener(this);
        calcButton.setFont(myFont);
        calcButton.setFocusable(true);
        calcButton.setBounds(275, 20, 145, 50);
        calcButton.setVisible(false);

        panel1.setBackground(Color.white);
        panel2.setBackground(Color.white);
        panel3.setBackground(Color.white);
        panel4.setBackground(Color.white);
        panel5.setBackground(Color.white);

        textAreasPanel = new JPanel(new GridLayout());

        preTextArea = new JTextArea(5,30);
        preTextArea.setFont(myFont);
        pane = new JScrollPane(preTextArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        postTextArea = new JTextArea(5,30);
        postTextArea.setFont(myFont);
        pane1 = new JScrollPane(postTextArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        textAreasPanel.add(pane);
        textAreasPanel.add(pane1);
        //frame.add(textAreasPanel);
        //textAreasPanel.setVisible(false);

        frame.setVisible(true);
    }
    @SneakyThrows
    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == encryptWithGeneratedKey) {
            JOptionPane.showMessageDialog(frame, "Your encrypted key is : SunnyDayHome1234");
            encryptDialog.dispose();
        }

        if(e.getSource() == submitYourKey) {

            if(String.valueOf(encTextField.getPassword()).length() == 16) {
                result.setEncryptedKey(String.valueOf(encTextField.getPassword()));
                JOptionPane.showMessageDialog(frame, "Success! You typed the right key.");
                encryptDialog.dispose();
            }
            else {
                JOptionPane.showMessageDialog(frame, "Invalid key. Try again.", "Error Message", JOptionPane.ERROR_MESSAGE);
                encTextField.setText("");
                encryptDialog.setVisible(true);
            }
        }

        if(e.getSource() == encryptWithYourKey) {
            encryptPanel.setVisible(false);
            yourKeyPanel.setVisible(true);
        }

        if(e.getSource() == decrButton) {
            if(String.valueOf(decrTextField.getPassword()).length() == 16) {
                result.setEncryptedKey(String.valueOf(decrTextField.getPassword()));
                JOptionPane.showMessageDialog(frame, "Success! Your data is decrypted.");
                decryptDialog.dispose();
            }
            else {
                JOptionPane.showMessageDialog(frame, "Invalid key. Try again.", "Error Message", JOptionPane.ERROR_MESSAGE);
                decrTextField.setText("");
                decryptDialog.setVisible(true);
            }

            // decryption here...

        }

        if(e.getSource() == chooseInputButton) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File("."));
            int response = fileChooser.showOpenDialog(null);   // select file to open
            if(response == JFileChooser.APPROVE_OPTION){
                inputFile = fileChooser.getSelectedFile();
                readerName = inputFile.getName();
                reader = new Reader(readerName);
                result.setCasesForReader(decider.makeDecisionReader(readerName));

                switch(result.getCasesForReader()) {

                    case(2) :
                        // разархиватор сигмы
                        break;
                    case(3):
                        decryptDialog.setVisible(true);
                        // расшифровка
                        break;
                    case(4):
                        // разархивация
                        decryptDialog.setVisible(true);
                        break;
                    case(5):
                        decryptDialog.setVisible(true);
                        // разархивация
                        break;

                }

                chooseInputButton.setText("File is read!");
            }
        }

        if(e.getSource() == chooseOutputButton) {
            JFileChooser fileChooser1 = new JFileChooser();
            fileChooser1.setCurrentDirectory(new File("."));
            int response1 = fileChooser1.showSaveDialog(null);   // select file to open
            if(response1 == JFileChooser.APPROVE_OPTION){
                outputFile = fileChooser1.getSelectedFile();
                writerName = outputFile.getName();
            }
        }

        if(e.getSource() == submitButton) {

            decider.makeDecision(readerName, writerName, result);

            reader = new Reader(readerName);
            if(Objects.equals(versionChooser.getSelectedItem(), "Version 1")) {

                reader.read(result);

                if(Objects.equals(findChooser.getSelectedItem().toString(), "Using API Finder")) {
                    // API Finder here...
                }
                if(Objects.equals(findChooser.getSelectedItem().toString(), "Using Regex")) {
                    finder.find(result);
                }
            }
            else if(Objects.equals(versionChooser.getSelectedItem(), "Version 2")) {

                reader.readV2(result);

                if(Objects.equals(findChooser.getSelectedItem().toString(), "Using API Finder")) {
                    // API Finder here...
                }
                if(Objects.equals(findChooser.getSelectedItem().toString(), "Using Regex")) {
                    finder.findV2(result, readerName);
                }
            }

            if(Objects.equals(calcChooser.getSelectedItem().toString(), "Using API Calculator")) {
                calculator.calculate(result);
            }
            else if(Objects.equals(calcChooser.getSelectedItem().toString(), "Using Regex")) {
                calculator = new Calculator(true);
                calculator.calculate(result);
            }

            if(Objects.equals(versionChooser.getSelectedItem(), "Version 1")) {
                replacer.replace(result);
                outFile = result.getReplacedText();
            }
            else if(Objects.equals(versionChooser.getSelectedItem(), "Version 2")) {
                replacer.replaceV2(result, readerName);
            }

            writer = new Writer(writerName);

            if(Objects.equals(actionChooser1.getSelectedItem().toString(), "Archive")) {
                result.setShouldArchive(true);
            }
            if(Objects.equals(actionChooser1.getSelectedItem().toString(), "Encrypt")) {
                result.setShouldEncrypt(true);
                result.setFirstEncrypt(true);
            }
            if(Objects.equals(actionChooser1.getSelectedItem().toString(), "Archive -> Encrypt")) {
                result.setShouldEncrypt(true);
                result.setShouldArchive(true);
            }
            if(Objects.equals(actionChooser1.getSelectedItem().toString(), "Encrypt -> Archive")) {
                result.setShouldEncrypt(true);
                result.setShouldArchive(true);
                result.setFirstEncrypt(true);
            }

            writer.write(result);

            if(Objects.equals(versionChooser.getSelectedItem(), "Version 2")) {
                writer.writeV2(result, readerName);
                outFile = result.getBuffer();
            }

            panel1.setVisible(false);
            panel2.setVisible(false);
            panel3.setVisible(false);
            panel4.setVisible(false);
            panel5.setVisible(false);

            FileReader fileReader = new FileReader(inputFile);
            preTextArea.read(fileReader, null);

            StringReader fileReader1 = new StringReader(outFile);
            postTextArea.read(fileReader1, null);

            frame.add(textAreasPanel);

        }
    }
}
