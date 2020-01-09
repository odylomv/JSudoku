package gr.auth.csd.sudoku.gui;

import javax.swing.*;
import java.awt.*;

/**
 * This class represents a sudoku cell for the GUI. It contains a JLabel sum (for the Killer variant) and a JTextField inputText for the input
 */

public class SudCell extends JPanel {
    private JLabel sum;
    private JTextField inputText;
    private Color cellColor = Color.WHITE;
    private static final Color wrongColor = new Color(255, 28, 4);
    private static final Font font = new Font("Arial", Font.BOLD, 15);

    /**
     * In the constructor the layout and border of each SudCell are set as well as the properties(document,font etc.) of inputText which is then added to the cell
     */
    public SudCell() {
        setLayout(new BorderLayout());
        inputText = new JTextField();
        inputText.setFont(font.deriveFont(Font.BOLD, 18));
        inputText.setBorder(BorderFactory.createEmptyBorder());
        inputText.setDocument(new TextLimit(1));
        inputText.setForeground(Color.BLACK);
        inputText.setHorizontalAlignment(JTextField.CENTER);

        add(inputText,BorderLayout.CENTER);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    /**
     * This method adds the sum Label to the cell, after having set its properties(size,font etc.)
     * @param text
     */
    public void addLabel(String text) {
        sum = new JLabel(text);
        sum.setFont(font);
        sum.setForeground(Color.BLACK);
        sum.setMinimumSize(new Dimension(10,15));
        sum.setPreferredSize(new Dimension(10,15));
        sum.setMaximumSize(new Dimension(10,15));
        add(sum,BorderLayout.NORTH);

        sum.setVisible(true);
    }

    public JTextField getInputTextField(){
        return inputText;
    }

    public void setInputText(String s){
        inputText.setText(s);
    }

    /**
     * Sets the background of both the SudCell and its inputText to cellColor
     * @param cellColor
     */
    public void setColor(Color cellColor) {
        this.cellColor = cellColor;
        setBackground(cellColor);
        inputText.setBackground(cellColor);
    }

    /**
     * This method is used to set the color of the cell based on the value of flag
     * If true, both SudCell's and inputText's color is set to wrong color, else the SedCell's color is restored
     * @param flag
     */
    public void setWarning(boolean flag) {
        if (flag) {
        setBackground(wrongColor);
        inputText.setBackground(wrongColor);
        } else {
            setColor(cellColor);
        }
    }


    public Color getCellColor(){ return cellColor; }
}
