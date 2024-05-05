import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class WordLadderGUI extends JFrame {
    private JTextField startField, endField;
    private JButton findButton;
    private JList<String> wordList;
    private DefaultListModel<String> listModel;
    private Dict wordDictionary;

    public WordLadderGUI() {
        setTitle("Word Ladder Solver");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 2));

        JLabel startLabel = new JLabel("Start Word:");
        startField = new JTextField();
        JLabel endLabel = new JLabel("End Word:");
        endField = new JTextField();
        findButton = new JButton("Find");
        findButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                findWordLadder();
            }
        });

        inputPanel.add(startLabel);
        inputPanel.add(startField);
        inputPanel.add(endLabel);
        inputPanel.add(endField);
        inputPanel.add(findButton);

        listModel = new DefaultListModel<>();
        wordList = new JList<>(listModel);

        JScrollPane scrollPane = new JScrollPane(wordList);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(inputPanel, BorderLayout.NORTH);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        // Load dictionary
        wordDictionary = new Dict("Dictionary.txt");
    }

    private void findWordLadder() {
        String start = startField.getText().trim().toUpperCase();
        String end = endField.getText().trim().toUpperCase();
        Result res;
        long startTime = System.nanoTime();
        if (start.length()!=end.length()) {
            JOptionPane.showMessageDialog(this, "Both needs to be the same length.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (start.equals(end)) {
            JOptionPane.showMessageDialog(this, "Start and end words cannot be the same.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!wordDictionary.containsWord(start) && !wordDictionary.containsWord(end)) {
            JOptionPane.showMessageDialog(this, "Both word do not exist in the dictionary.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!wordDictionary.containsWord(start)) {
            JOptionPane.showMessageDialog(this, "Start word does not exist in the dictionary.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!wordDictionary.containsWord(end)) {
            JOptionPane.showMessageDialog(this, "End word does not exist in the dictionary.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String[] options = {"Uniform Cost Search", "Greedy Best First Search", "A*"};
        int selectedOption = JOptionPane.showOptionDialog(this, "Choose algorithm to solve:", "Algorithm Selection",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
        switch (selectedOption) {
            case 0:
                res = Algorithm.findPath(start, end, wordDictionary, "UCS");
                break;
            case 1:
                res = Algorithm.findPath(start, end, wordDictionary, "GBFS");
                break;
            case 2:
                res = Algorithm.findPath(start, end, wordDictionary, "Astar");
                break;
            default:
                return;
        }
        long endTime = System.nanoTime(); // Record end time
        double elapsedTimeInSeconds = (endTime - startTime) / 1e9; // Calculate elapsed time in seconds
        if (res.getResultlist() != null) {
            listModel.clear();
            for (String word : res.getResultlist()) {
                listModel.addElement(word);
            }
            JOptionPane.showMessageDialog(this, "Word ladder found. Length: " + res.getResultlist().size()
                    + "\nNodes checked: " + res.getnumofcheckednodes() + "\nExecution time: " + elapsedTimeInSeconds + " seconds",
                    "Found it :)", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Sadly, there's no solution for it." + "\nNodes checked: " + res.getnumofcheckednodes() + "\nExecution time: " + elapsedTimeInSeconds + " seconds", ":(", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                WordLadderGUI gui = new WordLadderGUI();
                gui.setVisible(true);
            }
        });
    }
}
