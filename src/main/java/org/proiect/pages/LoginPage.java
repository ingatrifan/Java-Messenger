package org.proiect.pages;

import org.proiect.controllers.UserController;
import org.proiect.models.LoggedInUser;
import org.proiect.models.User;
import org.proiect.utils.Helper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class LoginPage  extends JPanel {
    private MainFrame mainFrame;
    private JLabel emailLabel;
    private JLabel passwordLabel;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JLabel registerInfo;
    private JButton registerBtn;
    private JLabel title;
    private JButton loginBtn;
    private JLabel errorMessage;
    public LoginPage(MainFrame mainFrame){
        this.mainFrame = mainFrame;
        init();
    }
    private void init(){
        title = new JLabel("Login");
        title.setFont(new Font("Serif", Font.PLAIN, 34));
        emailLabel = new JLabel("Email:");
        passwordLabel = new JLabel("Password:");
        emailField = new JTextField(null,20);
        passwordField = new JPasswordField(20);
        loginBtn = new JButton("Login");
        errorMessage = new JLabel("");
        errorMessage.setFont(new Font("Serif", Font.PLAIN, 10));
        errorMessage.setForeground(Color.RED);
        registerInfo = new JLabel("If you don't have an account,");
        registerBtn = new JButton("Register");
        loginBtn.addActionListener(this::login);
        registerBtn.addActionListener(this::register);
        addComponents();
    }


    private void addComponents(){
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(500,300));
        JPanel registrationPanel = new JPanel();
        registrationPanel.add(registerInfo);
        registrationPanel.add(registerBtn);
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
                                .addComponent(passwordLabel)
                                .addComponent(passwordField)
                                .addComponent(loginBtn)
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
                        .addComponent(passwordLabel)
                        .addComponent(passwordField)
                        .addComponent(loginBtn)
                        .addComponent(registrationPanel)
        );
        panel.setLayout(layout);
        add(panel);
    }
    private String getEmailText(){
        return emailField.getText();
    }
    private String getPasswordText(){
        return String.valueOf(passwordField.getPassword());
    }
    private void login(ActionEvent actionEvent){
        User user = UserController.findByEmail(getEmailText());
        if (user!=null) {
            try {
                System.out.println(getPasswordText());
                if (Helper.check(getPasswordText(),user.getPassword())) {
                    LoggedInUser.login(user);
                    MainPage mainPage = new MainPage(mainFrame);
                    mainFrame.loadPage(mainPage);
                } else {
                    this.errorMessage.setText("Password is incorrect");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            this.errorMessage.setText("An account with this email does not exist");
        }
    }
    private void register(ActionEvent actionEvent){
        RegisterPage registerPage = new RegisterPage(this.mainFrame);
        mainFrame.loadPage(registerPage);
    }
}
