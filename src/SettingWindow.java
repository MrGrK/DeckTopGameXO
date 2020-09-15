

import javax.swing.*;
import java.awt.*;

public class SettingWindow extends JFrame {
    private GameWindow gameWindow;

    static final int WINDOW_X = GameWindow.WINDOW_X + 50;
    static final int WINDOW_Y = GameWindow.WINDOW_Y + 50;
    static final int WINDOW_WIDTH = 405;
    static final int WINDOW_HEIGHT = 400;
    private static final int MIN_FIELD_SIZE = 3;
    private static final int MAX_FIELD_SIZE = 10;

    static final int MODE_H_VS_A = 0;
    static final int MODE_H_VS_H = 1;

    private JRadioButton _jrbHumVsAi;
    private JRadioButton _jrbHumVsHum;
    private ButtonGroup _gameModeGroup;

    private JSlider _slFieldSize;
    private JSlider _slWinningLength;


    public SettingWindow(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
        setBounds(WINDOW_X, WINDOW_Y, WINDOW_WIDTH, WINDOW_HEIGHT);
        setTitle("Setting");

        setLayout(new GridLayout(8, 1));
        add(new JLabel("Choose game mode:"));

        _jrbHumVsAi = new JRadioButton("HumVsAi", true);
        _jrbHumVsHum = new JRadioButton("HumVsHum");
        _jrbHumVsHum.setEnabled(false);
        _gameModeGroup = new ButtonGroup();
        _gameModeGroup.add(_jrbHumVsAi);
        _gameModeGroup.add(_jrbHumVsHum);
        add(_jrbHumVsAi);
        add(_jrbHumVsHum);

        add(new JLabel("Choose field size:"));
        _slFieldSize = new JSlider(MIN_FIELD_SIZE, MAX_FIELD_SIZE, MIN_FIELD_SIZE);
        _slFieldSize.setMajorTickSpacing(1);
        _slFieldSize.setPaintLabels(true);
        _slFieldSize.setPaintTicks(true);
        add(_slFieldSize);

        add(new JLabel("Choose winning length:"));
        _slWinningLength = new JSlider(MIN_FIELD_SIZE, MIN_FIELD_SIZE, MIN_FIELD_SIZE);
        _slWinningLength.setMajorTickSpacing(1);
        _slWinningLength.setPaintLabels(true);
        _slWinningLength.setPaintTicks(true);
        add(_slWinningLength);

        _slFieldSize.addChangeListener(e -> {
            _slWinningLength.setMaximum(_slFieldSize.getValue());
        });

        JButton buttonStartGame = new JButton("Start a game");
        add(buttonStartGame);
        buttonStartGame.addActionListener(e -> {
            int mode;
            if (_jrbHumVsAi.isSelected()) {
                mode = MODE_H_VS_A;
            } else {
                mode = MODE_H_VS_H;
            }

            int fieldSize = _slFieldSize.getValue();
            int winningLength = _slWinningLength.getValue();

            Logic.SIZE = fieldSize;
            Logic.DOTS_TO_WIN = winningLength;
            Logic.initMap();
            Logic.isFinishedGame = false;

            gameWindow.startNewGame(mode, fieldSize, winningLength);

            setVisible(false);
        });

        setVisible(false);
    }
}
