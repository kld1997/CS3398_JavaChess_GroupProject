import javax.swing.*;
import java.awt.*;
import java.util.Observer;

//Top Panel of ChessGui
//Has Labels for time and team.
public class InfoPanel extends GenericInfoPanel
{
    ChessGui thisGui;
    public InfoPanel(ChessGui g)
    {
        thisGui = g;
        setLayout(new BorderLayout());
        teamText = new JLabel();
        teamText.setFont(new Font("Serif", Font.BOLD, 20));
        teamText.setHorizontalAlignment(JLabel.CENTER);
        teamText.setText("White Turn");
        add(teamText, BorderLayout.CENTER);
    }

    @Override
    public int getType() {
        return 0;
    }
}
