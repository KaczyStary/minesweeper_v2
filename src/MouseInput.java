import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener {
    @Override
    public void mouseClicked(MouseEvent e) {

    }
    @Override
    public void mousePressed(MouseEvent e) {

        int mx = e.getX();
        int my = e.getY();

        mx=mx/BoardPanel.tileSize;
        my=my/BoardPanel.tileSize;

        //LEFT BUTTON
        if (e.getButton()==MouseEvent.BUTTON1){
            BoardActions.toggleReveal(my-2,mx);

        }
        //RIGHT BUTTON
        if (e.getButton()==MouseEvent.BUTTON3){
            BoardActions.toggleFlag(my-2,mx);
        }



    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
