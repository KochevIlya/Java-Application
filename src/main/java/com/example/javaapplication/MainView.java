package com.example.javaapplication;

import com.sun.jna.platform.win32.Shell32;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.button.Button;

import java.awt.*;
import java.io.*;
import java.util.Map;
import java.util.Objects;

@Route("/hello")
public class MainView extends HorizontalLayout {
    private MemoryBuffer inputBuffer = new MemoryBuffer();
    private MemoryBuffer outputBuffer = new MemoryBuffer();
    private ComboBox<String> versionComboBox;
    private ComboBox<String> optionsComboBox;
    private Dialog encryptionDialog = new Dialog("Your Encryption Key");
    private Dialog decryptionDialog = new Dialog("Your Decryption Key");
    private Dialog endDialog = new Dialog("All done");
    private Decider decider = new Decider();
    private Reader reader;
    private Finder finder = new Finder();
    private Calculator calculator = new Calculator(true);
    private Replacer replacer = new Replacer();
    private Writer writer;
    private Result result = new Result();
    private boolean isWithName = false;
    public MainView()
    {
        createDialogs();



        versionComboBox = new ComboBox<>("Select Version");
        versionComboBox.setItems("1", "2");
        versionComboBox.setPlaceholder("Select Version");

        Upload inputUpload = new Upload(inputBuffer);
        inputUpload.setUploadButton(new Button("Upload Input File"));
        inputUpload.addSucceededListener(event -> {
            String fileName = event.getFileName();
            reader = new Reader(fileName);
            if(fileName.contains(".enc"))
                encryptionDialog.open();
            // Получение данных из входного файла в виде строк
            try (InputStream inputStream = inputBuffer.getInputStream();
                 BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

                StringBuilder content = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    // Обработка каждой строки из файла
                    content.append(line).append("\n");
                }
                try {
                    FileWriter fw = new FileWriter(fileName);
                    fw.write(content.toString());
                    fw.close();
                }
                catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } catch (IOException e) {
                e.printStackTrace();
                Notification.show("Error reading input file");
            }
        });

        Upload outputUpload = new Upload(outputBuffer);
        outputUpload.setUploadButton(new Button("Upload Output File"));
        outputUpload.addSucceededListener(event -> {
            String fileName = event.getFileName();
            writer = new Writer(fileName);
        });
        TextField outputName = new TextField("Or write the output file");
        VerticalLayout uploadVerticalLayout = new VerticalLayout();
        uploadVerticalLayout.add(inputUpload, outputUpload, outputName);

        optionsComboBox = new ComboBox<>("Select Option");
        optionsComboBox.setItems("Nothing", "Encrypt", "Archive", "Encrypt -> Archive", "Archive -> Encrypt");
        optionsComboBox.setPlaceholder("Select Option");

        VerticalLayout optionVerticalLayout = new VerticalLayout();
        Button submitButton = new Button("Submit");
        optionVerticalLayout.add(optionsComboBox, submitButton);
        submitButton.addClickListener(onSubmitButtonClicked(optionsComboBox, versionComboBox, outputName));
        add(versionComboBox, uploadVerticalLayout, optionVerticalLayout);
    }
    public ComponentEventListener<ClickEvent<Button>> onSubmitButtonClicked(ComboBox<String> optionsComboBox, ComboBox<String> versionComboBox, TextField outputName)
    {
        return event -> {
            String version = versionComboBox.getValue();
            String option = optionsComboBox.getValue();
            if(reader == null || (Objects.equals(outputName.getValue(), "") && writer == null)) {
                Notification.show(outputName.getValue());
                Notification.show("Please, enter Output File and Input File");
                return;
            }
            if(!(Objects.equals(outputName.getValue(), "") || writer == null) && !isWithName) {
                Notification.show("Please,select only one option for output File");
                return;
            }
            if(!Objects.equals(outputName.getValue(), "")) {
                writer = new Writer(outputName.getValue());
                isWithName = true;
            }
            if(Objects.equals(version, "1"))
            {
                decider.makeDecision(reader.getFileName(), writer.getFileName(), result);

                switch (option)
                {
                    case "Encrypt":
                        result.setShouldEncrypt(true);
                        break;
                    case "Archive":
                        result.setShouldArchive(true);
                        break;
                    case "Encrypt -> Archive":
                        result.setShouldEncrypt(true);
                        result.setShouldArchive(true);
                        result.setFirstEncrypt(true);
                        break;
                    case "Archive -> Encrypt":
                        result.setShouldArchive(true);
                        result.setShouldEncrypt(true);
                        result.setFirstEncrypt(false);
                        break;
                    case "Nothing":
                        break;
                    default: {
                        Notification.show("Please select an option");
                        return;
                    }
                }
                reader.read(result);
                finder.find(result);
                calculator.calculate(result);
                replacer.replace(result);
                writer.write(result);
                result = new Result();
                return;
            }
            else if(Objects.equals(version, "2"))
            {
                switch (option)
                {
                    case "Encrypt":
                    case "Archive":
                    case "Encrypt -> Archive":
                    case "Arcive -> Encrypt":
                    case "Nothing":
                    default:
                        Notification.show("Please select an option");
                }
            }
            else
            {
                Notification.show("Please select a version");
            }

        };
    }
    private void createDialogs()
    {
        PasswordField keyField = new PasswordField("Enter Encryption Key");
        Button confirmButton = new Button("Confirm");
        encryptionDialog.add(keyField, confirmButton);
        Text label = new Text("Enter teh 16 ch key, or it will be SunnyDayHome1234");
        confirmButton.addClickListener(event -> {
            if(keyField.getValue().length() != 16) {
                Notification.show("Your key is not relevant( Try again");
                return;
            }
            encryptionDialog.close();

        }
        );
        TextField textField = new TextField("Enter the Decryption key");
        Button confirmDecryption = new Button("Confirm");
        decryptionDialog.add(label, textField, confirmDecryption);
    }

}
