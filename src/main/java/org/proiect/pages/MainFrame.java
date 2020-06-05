package org.proiect.pages;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    public MainFrame() {
        super("Messenger");
        init();
    }
    private void init() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(800, 800));
        LoginPage loginPage = new LoginPage(this);
//        MainPage mainPage = new MainPage(this);
        Box box = new Box(BoxLayout.Y_AXIS);
        box.add(Box.createVerticalGlue());
        box.add(loginPage);
        box.add(Box.createVerticalGlue());
        add(box);
        pack();
        setVisible(true);
    }
    public void loadPage( JPanel panel){
        getContentPane().removeAll();
        Box box = new Box(BoxLayout.Y_AXIS);
        box.add(Box.createVerticalGlue());
        box.add(panel);
        box.add(Box.createVerticalGlue());
        add(box);
        invalidate();
        validate();
    }
}