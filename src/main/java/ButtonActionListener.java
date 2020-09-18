import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ButtonActionListener implements ActionListener {

    public Gui Gui;

    ButtonActionListener(Gui gui){
        this.Gui=gui;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch(((JButton) e.getSource()).getText()) {


            case "Confirm":
                Gui.dim = Integer.parseInt(Gui.dimField.getText().trim());
                if (Gui.dim > 20) {
                    JOptionPane.showMessageDialog(null, "Your matrix is too big.\nYou have to work on files.");
                } else {
                    Gui.dataArea.setVisible(true);
                    Gui.dataConfirm.setVisible(true);
                    Gui.dataLabel.setVisible(true);
                }
                break;


             case "DECOMPOSE!":
                Gui.data = Gui.dataArea.getText().trim();

                if(new ElementCounter(Gui.data).counter!=(Gui.dim*Gui.dim)){
                JOptionPane.showMessageDialog(null,"This matrix does not have given dimension.");
                 }

                else{
                Gui.info.setText("Decomposition: ");

                MatrixHandler result = new MatrixHandler(Gui.data,Gui.dim);

                Gui.outputAreaR.setText(result.getR());
                Gui.outputAreaQ.setText(result.getQ());

                Gui.outputPanel.setVisible(true);
                Gui.fileButton.setVisible(false);
                }
                break;


            case "Work on files":
                if(e.getSource() == Gui.fileButton){
                    int returnVal = Gui.fc.showOpenDialog(null);
                    if(returnVal == JFileChooser.APPROVE_OPTION){
                        Gui.path = Gui.fc.getSelectedFile().getAbsolutePath();
                    }
                }

                FileHandler fh = null;
                try {
                    fh = new FileHandler(Gui.path);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                fh.SaveDecomp();
                JOptionPane.showMessageDialog(null,"Decomposition has been saved.");

                break;


            case"Again?":
                Gui.dimField.setText("");
                Gui.dim=0;
                Gui.dataArea.setText("");
                Gui.data=null;
                Gui.outputPanel.setVisible(false);
                Gui.dataConfirm.setVisible(false);
                Gui.dataArea.setVisible(false);
                Gui.dataLabel.setVisible(false);
                Gui.fileButton.setVisible(true);
                Gui.info.setText("Enter a square matrix.");

                break;

        }
    }

}



