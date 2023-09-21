package codeSoftIntern;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;

public class WordCounterGUI extends JFrame {
    private JTextArea inputTextArea;
    private JTextArea outputTextArea;
    private JButton countButton;
    private JButton uniqueButton;
    private JButton ignoreButton;
    private JButton clearButton;

    public WordCounterGUI() {
        setTitle("Word Counter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 450);

        inputTextArea = new JTextArea(5, 30);
        outputTextArea = new JTextArea(10, 40);
        outputTextArea.setEditable(false);

        countButton = new JButton("Count Words");
        uniqueButton = new JButton("Unique Words");
        ignoreButton = new JButton("Ignore Stop Words");
        clearButton = new JButton("Clear");

        countButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputText = inputTextArea.getText();
                String split[] = inputText.split("\\s");
                // No_of_words
                int wordCount = WordCounter.noOfWords(split);
                outputTextArea.setText("Number of Words: " + wordCount + "\n");
            }
        });

        uniqueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputText = inputTextArea.getText();
                String split[] = inputText.split("\\s");
                // Unique words
                WordCounter.uniqueWords(split, outputTextArea);
            }
        });

        ignoreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputText = inputTextArea.getText();
                String split[] = inputText.split("\\s");
                // Ignore stop words
                WordCounter.stopWords(split, outputTextArea);
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inputTextArea.setText("");
                outputTextArea.setText("");
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(countButton);
        buttonPanel.add(uniqueButton);
        buttonPanel.add(ignoreButton);
        buttonPanel.add(clearButton);
        panel.add(buttonPanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(inputTextArea), BorderLayout.CENTER);
        panel.add(new JScrollPane(outputTextArea), BorderLayout.SOUTH);

        getContentPane().add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            WordCounterGUI app = new WordCounterGUI();
            app.setVisible(true);
        });
    }
}

class WordCounter {
    public static int noOfWords(String str[]) {
        int count = 0;
        for (int i = 0; i < str.length; i++) {
            count++;
        }
        return count;
    }

    public static void stopWords(String str[], JTextArea outputTextArea) {
        ArrayList<String> wordList = new ArrayList<>();
        HashSet<String> stopWord = new HashSet<>();

        stopWord.add("THIS");
        stopWord.add("BUT");
        stopWord.add("AND");
        stopWord.add("ITS");
        stopWord.add("THE");
        stopWord.add("CAN");
        stopWord.add("THERE'S");

        for (String s : str) {
            String wordCompare = s.toUpperCase();
            if (!stopWord.contains(wordCompare)) {
                wordList.add(s);
            }
        }

        Iterator<String> itr = wordList.iterator();
        outputTextArea.setText("\nIgnoring Stop Words:\n");
        while (itr.hasNext()) {
            outputTextArea.append(itr.next() + "\n");
        }
    }

    public static void uniqueWords(String split[], JTextArea outputTextArea) {
        LinkedHashSet<String> uniqueSet = new LinkedHashSet<>();

        for (String s : split) {
            uniqueSet.add(s);
        }

        Iterator<String> itr = uniqueSet.iterator();
        outputTextArea.setText("\nUnique Words:\n");
        while (itr.hasNext()) {
            outputTextArea.append(itr.next() + "\n");
        }
    }
}
