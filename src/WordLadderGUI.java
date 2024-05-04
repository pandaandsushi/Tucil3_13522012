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
        String start = startField.getText().trim();
        String end = endField.getText().trim();
        long startTime = System.nanoTime();
        List<String> ladder = Ucs.findUCS(start, end, wordDictionary);
        long endTime = System.nanoTime(); // Record end time
        double elapsedTimeInSeconds = (endTime - startTime) / 1e9; // Calculate elapsed time in seconds
        if (ladder != null) {
            listModel.clear();
            for (String word : ladder) {
                listModel.addElement(word);
            }
        } else {
            JOptionPane.showMessageDialog(this, "No word ladder found.", "Error", JOptionPane.ERROR_MESSAGE);
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
