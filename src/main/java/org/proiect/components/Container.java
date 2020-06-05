package org.proiect.components;

import javax.swing.*;
import java.awt.*;

public class Container extends JPanel {
    public Container(){
        init();
    }
    private void init(){
        setPreferredSize(new Dimension(740,800));
        setBackground(Color.RED);
    }
    public void setComponent(Component component){
        removeAll();
        add(component);
        invalidate();
        validate();
    }
}
