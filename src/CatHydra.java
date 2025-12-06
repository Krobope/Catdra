import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class CatHydra {

    static int catNum = 0;
    static int delay = 0;
    static boolean auto;

    public static void main(String[] args) {

        startKeyListener();

        String[] options = {"classic", "auto"};
        var selection = JOptionPane.showOptionDialog(null,"Select your Mode", "Catdra", 0, 3, new ImageIcon("cat.png"), options, options[0]);

        auto = (selection == 1);

        if(auto) {

            options = new String[] {"Snail", "Fish", "Cat", "Cheetah", "Light"};
            selection = JOptionPane.showOptionDialog(null,"Select your Speed", "Catdra", 0, 3, new ImageIcon("cat.png"), options, options[0]);
            switch (selection) {
                case 0:
                    JOptionPane.showMessageDialog(null, "Snail Mode Selected", "Mode Selected", JOptionPane.INFORMATION_MESSAGE, null);
                    delay = 1000;
                    break;
                case 1:
                    JOptionPane.showMessageDialog(null, "Fish Mode Selected", "Mode Selected", JOptionPane.INFORMATION_MESSAGE, null);
                    delay = 500;
                    break;
                case 2:
                    JOptionPane.showMessageDialog(null, "Alright Mode Selected", "Mode Selected", JOptionPane.INFORMATION_MESSAGE, null);
                    delay = 300;
                    break;
                case 3:
                    JOptionPane.showMessageDialog(null, "Cheetah Mode Selected", "Mode Selected", JOptionPane.INFORMATION_MESSAGE, null);
                    delay = 100;
                    break;
                case 4:
                    JOptionPane.showMessageDialog(null, "Warning! This speed is limited by only your hardware so it is quite taxing, use it with caution. Press 'K' now to quit.", "WARNING", JOptionPane.WARNING_MESSAGE, null);
                    JOptionPane.showMessageDialog(null, "I know you think it can't be that bad just opening nothing-burger windows but it is really bad, please trust me.\nIt will probably require you to forcefully shut off your computer.\nThe exit key seemingly no longer works once it has begun.\nIt is effectively a virus at this point.\nIt's gotten out of control, I'm sorry, I can't stop them anymore. Meow...", "WARNING", JOptionPane.WARNING_MESSAGE, null);
                    JOptionPane.showMessageDialog(null, "Lightspeed Mode Selected", "Mode Selected", JOptionPane.INFORMATION_MESSAGE, null);
                    delay = 0;
                    break;
            }
        }

        final int finalDelay = delay;
        genBox();

    }

    public static void genBox(){

        catNum++;

        final int finalDelay = delay;

        ImageIcon cat = new ImageIcon("cat.png");
        Random rand = new Random();

        // Get screen size
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        // Calculate random x and y positions for the dialog
        int randomX = rand.nextInt(screenSize.width - 300);
        int randomY = rand.nextInt(screenSize.height - 300);

        Object[] options = {"hai x3"};

        JOptionPane optionPane = new JOptionPane(
                "haiiii!! :3",
                JOptionPane.INFORMATION_MESSAGE,
                JOptionPane.DEFAULT_OPTION,
                cat,
                options,
                options[0]
        );

        JDialog dialog = optionPane.createDialog("The Catdra " + catNum);

        dialog.setIconImage(cat.getImage());
        dialog.setAlwaysOnTop(true);
        dialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        dialog.setModal(!auto);
        dialog.setLocation(randomX, randomY);
        dialog.setVisible(true);
        dialog.getRootPane().requestFocusInWindow();

        Object choice = optionPane.getValue();

        if(finalDelay > 0) {
            try {
                Thread.sleep(finalDelay);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        Thread t1=new Thread() {
            public void run() {
                genBox();
            }
        };
        t1.start();
        Thread t2=new Thread() {
            public void run() {
                genBox();
            }
        };
        t2.start();

        if(auto) dialog.setVisible(false);

    }

    public static void startKeyListener() {
        KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventDispatcher(new KeyEventDispatcher() {
            @Override
            public boolean dispatchKeyEvent(KeyEvent e) {
                if (e.getID() == KeyEvent.KEY_PRESSED) {
                    if (e.getKeyCode() == KeyEvent.VK_K) {
                        System.exit(0);  // Exit the application when 'k' is pressed
                    }
                }
                return false;  // Allow other key events to be dispatched
            }
        });
    }
}
