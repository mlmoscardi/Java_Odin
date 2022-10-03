package frame;

import copyDir.*;
import java.io.IOException;
import java.awt.BorderLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class OdinUI {
    String src = new String();
    String dst = new String();
    CopyDir copyDir = new CopyDir();

    String folderPlusProjName;
    boolean isDone = false;

    public void createOdinUI() throws IOException {
       JFrame frame = new JFrame();           
       frame.setTitle("Odin");
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
       LayoutManager layout = new BorderLayout();  
       frame.setLayout(layout);

       // Change App Icon
       ImageIcon icon = new ImageIcon("logo.png");
       frame.setIconImage(icon.getImage());
 
       frame.setSize(600, 600);
       frame.setResizable(false);   
       frame.setLocationRelativeTo(null);
 
       // Odin Image
       JLabel labelImg = new JLabel();
       ImageIcon odinImg = new ImageIcon("logo.png");
       labelImg.setIcon(odinImg);
       labelImg.setBounds(225, 10, 200, 200);
 
       // Project Name Label
       JLabel labelProjName = new JLabel();
       labelProjName.setText("Project Name:");
       labelProjName.setBounds(110, 200, 200, 30);
 
       // Project Name Input Box
       JTextField projName = new JTextField();
       projName.setHorizontalAlignment(JTextField.LEFT);
       projName.setBounds(200, 200, 200, 25);
 
       // Project Folder Button
       JButton dirButton = new JButton();
       dirButton.setText("Select Directory");
       dirButton.setBounds(230, 250, 140, 30);
 
       // Folder Path Label
       JLabel labelfolderPath = new JLabel();
       labelfolderPath.setText("Please select a folder Path");
       labelfolderPath.setHorizontalAlignment(JLabel.CENTER);
       labelfolderPath.setBounds(20, 280, 560, 30);
 
       // Project Template Label
       JLabel labelTemplateType = new JLabel();
       labelTemplateType.setText("Template Type:");
       labelTemplateType.setBounds(100, 330, 100, 30);
 
       // Dropdown Template Type
       String[] choices = { "Basic", "Advanced"};
       JComboBox<String> templateTypeSpinner = new JComboBox<String>(choices);
       templateTypeSpinner.setBounds(200, 330, 200, 30);
 
       // Template Label Description
       JLabel templateDesc = new JLabel();
       templateDesc.setText("Basic");
       templateDesc.setHorizontalAlignment(JLabel.CENTER);
       templateDesc.setBounds(200, 360, 200, 30);  
       
        // Create project Button
        JButton createButton = new JButton();
        createButton.setText("Create Project");
        createButton.setBounds(230, 400, 140, 30);

        // Label Results
        JLabel resultLabel = new JLabel();

        // Label Results Location Saved
        JLabel locationSaved = new JLabel();

       // Directory Button Folder ActionListener
        dirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {            
                LayoutManager layout = new BorderLayout();  
                frame.setLayout(layout);
    
                JFileChooser folderChooser = new JFileChooser();
                folderChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int option = folderChooser.showOpenDialog(frame);
                
                isDone = false;                
                while(!isDone) {
                    if(projName.getText().equals("")) {
                        labelfolderPath.setHorizontalAlignment(JLabel.CENTER);
                        labelfolderPath.setBounds(20, 280, 560, 30);
                        labelfolderPath.setText("Please insert a project name");
                        isDone = true;
                    }
                    else {
                        if(option == JFileChooser.APPROVE_OPTION) {
                            File file = folderChooser.getSelectedFile();                            
                            folderPlusProjName = file.getPath() + "\\" + projName.getText();
                            labelfolderPath.setText(folderPlusProjName);
                            labelfolderPath.setHorizontalAlignment(JLabel.CENTER);
                            labelfolderPath.setBounds(20, 280, 560, 30);
                            isDone = true;
                        }
                        else {
                            labelfolderPath.setText("Open command canceled");
                            labelfolderPath.setHorizontalAlignment(JLabel.CENTER);
                            labelfolderPath.setBounds(20, 280, 560, 30);
                            isDone = true;
                        } 
                    }
                } //while
            } // actionPerformed
        }); // addActionListener
 
       // Dropdown/Spinner Listener
        templateTypeSpinner.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LayoutManager layout = new BorderLayout();  
                frame.setLayout(layout);

                if(e.getSource() == templateTypeSpinner) {
                    String choice = (String)templateTypeSpinner.getSelectedItem();
                    switch(choice) {
                        case "Basic": {
                            templateDesc.setText(choice);
                            templateDesc.setHorizontalAlignment(JLabel.CENTER);
                            templateDesc.setBounds(200, 360, 200, 30); 
                            break;
                        }
                        case "Advanced": {
                            templateDesc.setText(choice);
                            templateDesc.setHorizontalAlignment(JLabel.CENTER);
                            templateDesc.setBounds(200, 360, 200, 30);    
                            break;
                        }
                        default: {
                            templateDesc.setText("Select one template");
                            templateDesc.setHorizontalAlignment(JLabel.CENTER);
                            templateDesc.setBounds(200, 360, 200, 30);  
                        }
                    } // switch
                } 
            } // actionPerformed
        }); // addActionListener

        // createButton Listener
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LayoutManager layout = new BorderLayout();  
                frame.setLayout(layout);
                
                isDone = false;
                while(!isDone) {
                    if(projName.getText().equals("") || 
                    labelfolderPath.getText().equals("Please select a folder Path") ||
                    labelfolderPath.getText().equals("Please insert a project name") ||
                    labelfolderPath.getText().equals("Open command canceled") ||
                    labelfolderPath.getText().equals("") ||
                    labelfolderPath.getText().equals("null")) {
                        resultLabel.setText("Please fill in all fields");
                        resultLabel.setHorizontalAlignment(JLabel.CENTER);
                        resultLabel.setBounds(175, 430, 250, 30);
                        isDone = true;
                    } // if
                    else {
                        resultLabel.setText((String)templateTypeSpinner.getSelectedItem() + 
                                                " Template created Sucessfully!!!");
                        resultLabel.setHorizontalAlignment(JLabel.CENTER);
                        resultLabel.setBounds(175, 430, 250, 30);
                        
                        locationSaved.setText("Template Saved on: " + folderPlusProjName);
                        locationSaved.setHorizontalAlignment(JLabel.CENTER);
                        locationSaved.setBounds(10, 460, 580, 30);   

                        src = (String)templateTypeSpinner.getSelectedItem();
                        if(src.equals("Basic")) {
                            src = "FileRes/basic";
                            dst = folderPlusProjName;  
                        }
                        else {
                            src = "FileRes/advanced";
                            dst = folderPlusProjName;
                        }                    
                        try {
                            copyDir.CopyDirectory(src, dst);
                        } catch (IOException e1) {
                            resultLabel.setText("Something went Wrong ");
                            e1.printStackTrace();
                        }
                        isDone = true;
                    } // outter else
                } // while

            } // actionPerformed
        }); // addActionListener 
       
       ///////////////////////////////////
       //// Add all frame components ////
       //////////////////////////////////
    
       // Proj Image
       frame.add(labelImg);
 
       // Proj Name
       frame.add(labelProjName);
       frame.add(projName); 
 
       // Proj Button and folder path
       frame.add(dirButton);
       frame.add(labelfolderPath);      
 
       // Template label, Dropdown and Create Project Button Result message
       frame.add(templateTypeSpinner);      
       frame.add(labelTemplateType);
       frame.add(createButton);     
       frame.add(templateDesc); 
       frame.add(resultLabel);
       frame.add(locationSaved);
        
       // Set whole frame to visible
       frame.setVisible(true);
    } // OdinUI

} // OdinUI class