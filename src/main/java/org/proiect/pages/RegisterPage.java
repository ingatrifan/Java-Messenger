package org.proiect.pages;

import org.proiect.controllers.UserController;
import org.proiect.models.LoggedInUser;
import org.proiect.models.User;
import org.proiect.utils.Helper;
import org.proiect.utils.MailSender;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class RegisterPage extends JPanel {
    private MainFrame mainFrame;
    private JLabel emailLabel;
    private JLabel fullNameLabel;
    private JLabel passwordLabel;
    private JTextField emailField;
    private JTextField fullNameField;
    private JPasswordField passwordField;
    private JLabel loginInfo;
    private JButton loginBtn;
    private JLabel title;
    private JButton registerBtn;
    private JLabel errorMessage;
    RegisterPage(MainFrame mainFrame){
        this.mainFrame = mainFrame;
        init();
    }
    private void init(){
        title = new JLabel("Register");
        title.setFont(new Font("Serif", Font.PLAIN, 34));
        emailLabel = new JLabel("Email:");
        fullNameLabel = new JLabel("Full name:");
        passwordLabel = new JLabel("Password:");
        emailField = new JTextField(null,20);
        fullNameField = new JTextField(null,20);
        passwordField = new JPasswordField(20);
        registerBtn = new JButton("Register");
        errorMessage = new JLabel("");
        errorMessage.setFont(new Font("Serif", Font.PLAIN, 10));
        errorMessage.setForeground(Color.RED);
        loginInfo = new JLabel("If you already have an account,");
        loginBtn = new JButton("Login");
        loginBtn.addActionListener(this::login);
        registerBtn.addActionListener(this::register);
        addComponents();
    }


    private void addComponents(){
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(500,300));
        JPanel registrationPanel = new JPanel();
        registrationPanel.add(loginInfo);
        registrationPanel.add(loginBtn);
        GroupLayout layout = new GroupLayout(panel);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(
                                GroupLayout.Alignment.LEADING)
                                .addComponent(title)
                                .addComponent(errorMessage)
                                .addComponent(emailLabel)
                                .addComponent(emailField)
                                .addComponent(fullNameLabel)
                                .addComponent(fullNameField)
                                .addComponent(passwordLabel)
                                .addComponent(passwordField)
                                .addComponent(registerBtn)
                                .addComponent(registrationPanel)
                        )
                )
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addComponent(title)
                        .addComponent(errorMessage)
                        .addComponent(emailLabel)
                        .addComponent(emailField)
                        .addComponent(fullNameLabel)
                        .addComponent(fullNameField)
                        .addComponent(passwordLabel)
                        .addComponent(passwordField)
                        .addComponent(registerBtn)
                        .addComponent(registrationPanel)
        );
        panel.setLayout(layout);
        add(panel);
    }
    private String getEmailText(){
        return emailField.getText();
    }
    private String getFullNameText(){
        return fullNameField.getText();
    }
    private String getPasswordText(){
        return String.valueOf(passwordField.getPassword());
    }

    private void login(ActionEvent actionEvent){
        LoginPage loginPage = new LoginPage(this.mainFrame);
        mainFrame.loadPage(loginPage);
    }
    private void register(ActionEvent actionEvent){
        if (!Helper.validateEmail(getEmailText())){
            errorMessage.setText("Email is not valid");
            return;
        }
        if (getFullNameText().length() <3){
            errorMessage.setText("Full name is too short");
            return;
        }
        if (getPasswordText().length() <5){
            errorMessage.setText("Password is too short");
            return;
        }
        User user = new User(getEmailText(),getPasswordText(),getFullNameText());
        try {
            user.setPassword(Helper.getSaltedHash(getPasswordText()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        String rs = UserController.create(user);
        if (rs.contains("An account with this email already exists"))
            errorMessage.setText(rs);
        else {
            LoggedInUser.login(user);
            MailSender.sendMail();
            MainPage mainPage = new MainPage(mainFrame);
            mainFrame.loadPage(mainPage);
        }
    }
}
