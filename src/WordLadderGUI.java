import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class WordLadderGUI extends JFrame {
    private JTextField startField, endField;
    private JButton findButton;
    private JList<String> wordList;
    private DefaultListModel<String> listModel;
    private JLabel resultLabel;
    private Dict wordDictionary;

    public WordLadderGUI() {
        setTitle("Word Ladder Solver :3");
        setSize(400, 420);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);


        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 2));

        JLabel startLabel = new JLabel("Start Word:");
        startField = new JTextField();
        JLabel endLabel = new JLabel("End Word:");
        endField = new JTextField();
        findButton = new JButton("Let's Go!!");
        findButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                findWordLadder();
            }
        });

        inputPanel.add(startLabel);
        inputPanel.add(startField);
        inputPanel.add(endLabel);
        inputPanel.add(endField);

        listModel = new DefaultListModel<>();
        wordList = new JList<>(listModel);

        JScrollPane scrollPane = new JScrollPane(wordList);

        resultLabel = new JLabel();
        resultLabel.setHorizontalAlignment(SwingConstants.CENTER);

        getContentPane().setLayout(null); // Set null layout

        inputPanel.setBounds(10, 10, 350, 90);
        scrollPane.setBounds(10, 110, 350, 200);
        resultLabel.setBounds(10, 320, 350, 50);
        findButton.setBounds(250, 320, 110, 30);

        getContentPane().add(inputPanel);
        getContentPane().add(scrollPane);
        getContentPane().add(resultLabel);
        getContentPane().add(findButton);

        wordDictionary = new Dict("Dictionary.txt");
    }

    private void findWordLadder() {
        String start = startField.getText().trim().toUpperCase();
        String end = endField.getText().trim().toUpperCase();
        Result res;
        long startTime = System.nanoTime();
        // Exceptions
        if (start.length() != end.length()) {
            JOptionPane.showMessageDialog(this, "Both words need to be the same length.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (start.equals(end)) {
            JOptionPane.showMessageDialog(this, "Start and end words cannot be the same.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!wordDictionary.containsWord(start) && !wordDictionary.containsWord(end)) {
            JOptionPane.showMessageDialog(this, "Both words do not exist in the dictionary.", "Error", JOptionPane.ERROR_MESSAGE);
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
        String algorithmName;
        switch (selectedOption) {
            case 0:
                res = Algorithm.findPath(start, end, wordDictionary, "UCS");
                algorithmName = "Uniform Cost Search";
                break;
            case 1:
                res = Algorithm.findPath(start, end, wordDictionary, "GBFS");
                algorithmName = "Greedy Best First Search";
                break;
            case 2:
                res = Algorithm.findPath(start, end, wordDictionary, "Astar");
                algorithmName = "A*";
                break;
            default:
                return;
        }
        long endTime = System.nanoTime();
        double elapsedTimeInSeconds = (endTime - startTime) / 1e9;
        // Print res
        if (res.getResultlist() != null) {
            listModel.clear();
            for (String word : res.getResultlist()) {
                listModel.addElement(word);
            }
            // Set result label text
            resultLabel.setBounds(0, 320, 250, 50); 
            resultLabel.setText("<html><div style='text-align: left;'>" + "Length: " + res.getResultlist().size()
                    + "<br>Nodes checked: " + res.getnumofcheckednodes() + "<br>Execution time: " + elapsedTimeInSeconds + " seconds</div></html>");
        } else {
            JOptionPane.showMessageDialog(this, "No solution found for " + algorithmName + ". Nodes checked: " + res.getnumofcheckednodes() + ", Execution time: " + elapsedTimeInSeconds + " seconds", ":(", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception e) {
                    e.printStackTrace();
                }

                WordLadderGUI gui = new WordLadderGUI();
                gui.setVisible(true);
            }
        });
    }
}
