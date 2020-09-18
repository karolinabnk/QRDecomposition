import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.util.Enumeration;

public class Gui extends JFrame {

    public static int dim;
    public String data;
    public String path;

    public JPanel formPanel;
    public static JTextField dimField;
    public static JTextArea dataArea;
    public JFileChooser fc;

    public JButton dimConfirm;
    public static JButton dataConfirm;
    public JButton fileButton;
    public JButton againButton;

    public JPanel outputPanel;
    public JTextArea outputAreaQ, outputAreaR;

    public JLabel info;
    public JLabel dataLabel;



    public Gui() {

        createView();
        setTitle("QR Decomposition");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setSize(1200,1000);
        setLocationRelativeTo(null);
    }


    private void createView() {

        setFont(new FontUIResource("Arial", Font.BOLD, 16));

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.WHITE );
        getContentPane().add(mainPanel);

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5);

        formPanel = new JPanel(new GridBagLayout());
        mainPanel.add(formPanel,c);
        formPanelFill();

        c.gridx = 1; c.gridy = 3;

        outputPanel = new JPanel(new GridBagLayout());
        outputPanelFill();
        formPanel.add(outputPanel, c);
        outputPanel.setVisible(false);

        fc = new JFileChooser();
        fc.setDialogTitle("Choose a text file");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
        fc.setFileFilter(filter);


    }

    private void formPanelFill() {

        formPanel.setBackground(Color.WHITE);

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5);


        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.LINE_END;

        JLabel dimLabel = new JLabel("Enter the dimension of the matrix: ");
        dimLabel.setForeground(Color.BLACK);
        formPanel.add(dimLabel, c);

        dataLabel = new JLabel("Enter the elements of the matrix: ");
        dataLabel.setForeground(Color.BLACK);
        c.gridy=1;
        formPanel.add(dataLabel, c);
        dataLabel.setVisible(false);


        c.gridx = 1;
        c.gridy = 0;
        c.anchor = GridBagConstraints.LINE_START;

        dimField = new JTextField();
        dimField.setPreferredSize(new Dimension(90, 30));
        formPanel.add(dimField, c);

        c.gridy = 1;

        dataArea = new JTextArea();
        dataArea.setPreferredSize(new Dimension(300, 300));
        dataArea.setLineWrap(true);
        dataArea.setWrapStyleWord(true);
        dataArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        formPanel.add(dataArea, c);
        dataArea.setVisible(false);

        c.gridy = 2;

        info = new JLabel("Enter a square matrix.");
        formPanel.add(info, c);


        c.gridx = 2;
        c.gridy = 0;

        dimConfirm = new JButton("Confirm");
        dimConfirm.addActionListener(new ButtonActionListener(this));
        formPanel.add(dimConfirm, c);
        dimField.getRootPane().setDefaultButton(dimConfirm);

        c.gridy = 1;

        dataConfirm = new JButton("DECOMPOSE!");
        dataConfirm.addActionListener(new ButtonActionListener(this));
        formPanel.add(dataConfirm, c);
        dataConfirm.setVisible(false);

        c.gridx = 1;
        c.gridy = 10;
        c.anchor = c.CENTER;
        c.insets = new Insets(30, 5, 5, 5);

        fileButton = new JButton("Work on files");
        fileButton.addActionListener(new ButtonActionListener(this));
        formPanel.add(fileButton, c);

    }

    private void outputPanelFill(){

        outputPanel.setBackground(Color.WHITE);

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 1, 5, 1);

        c.fill = c.BOTH;
        c.gridx=0;
        c.gridy=0;

        JLabel QLabel = new JLabel("Q = ");
        outputPanel.add(QLabel,c);

        c.gridy=1;

        JLabel RLabel = new JLabel("R = ");
        outputPanel.add(RLabel,c);

        c.gridx=1;
        c.gridy=0;

        outputAreaQ = new JTextArea();
        outputPanel.add(outputAreaQ,c);

        c.gridy=1;

        outputAreaR = new JTextArea();
        outputPanel.add(outputAreaR,c);

        Border border = BorderFactory.createLineBorder(Color.gray);
        outputAreaQ.setBorder(BorderFactory.createCompoundBorder(border,
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        outputAreaR.setBorder(BorderFactory.createCompoundBorder(border,
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));


        c.gridx=1;
        c.gridy=2;
        c.fill = c.NONE;

        againButton = new JButton("Again?");
        againButton.addActionListener(new ButtonActionListener(this));
        outputPanel.add(againButton,c);


    }

    private void setFont (FontUIResource f){
        Enumeration keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get (key);
            if (value instanceof FontUIResource)
                UIManager.put (key, f);
        }
    }

}
