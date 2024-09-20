/*
 * A GUI for a simple text editor: the text box can be edited
 * while the undo and redo buttons allow the user to undo and
 * redo recent edits.
 */

package textEditor;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentEvent.ElementChange;
import javax.swing.event.DocumentEvent.EventType;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Element;

public class TextEditor extends JFrame{

    Deque<String> past;
    Deque<String> future;

    public TextEditor(){
        super();
        this.past = new Deque<String>();
        this.future = new Deque<String>();
    }

    public void createAndShowGUI() {
        this.setSize(800, 500);
        this.setLayout(new FlowLayout());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        // add components and stuff
        JTextArea textArea = new JTextArea(20,60);
        textArea.setEditable(true);
        textArea.setLineWrap(true);
        JButton undo = new JButton("Undo");
        JButton redo = new JButton("Redo");
        
        
        undo.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                undo(textArea);
            }
        });
        redo.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                redo(textArea);
            }
        });
        Document d = textArea.getDocument();
        textArea.addKeyListener(new KeyListener(){

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                onEdit(d);
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
            
        });
        
        this.add(textArea);
        this.add(undo);
        this.add(redo);
        this.setVisible(true);
        undo.setVisible(true);
        redo.setVisible(true);
        textArea.setVisible(true);
    }

    public void insertUpdate(DocumentEvent ev){
        System.out.println(ev);
    }

    public void undo(JTextArea area){
        // TODO
    }

    public void redo(JTextArea area){
        // TODO
    }

    public void onEdit(Document d){
        // TODO
    }

    public static void main(String[] args) {
        TextEditor te = new TextEditor();

        te.createAndShowGUI();
    }
}
