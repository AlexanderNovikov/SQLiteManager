import javax.swing.*;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SampleTree extends JFrame {
    public static void main ( String[] args )
    {
        JFrame frame = new JFrame ();

        final JTree tree = new JTree ();
        tree.addMouseListener ( new MouseAdapter ()
        {
            public void mousePressed ( MouseEvent e )
            {
                if ( SwingUtilities.isRightMouseButton ( e ) )
                {
                    TreePath path = tree.getPathForLocation ( e.getX (), e.getY () );
                    Rectangle pathBounds = tree.getUI ().getPathBounds ( tree, path );
                    if ( pathBounds != null && pathBounds.contains ( e.getX (), e.getY () ) )
                    {
                        JPopupMenu menu = new JPopupMenu ();
                        menu.add ( new JMenuItem ( "Test" ) );
                        menu.show ( tree, pathBounds.x, pathBounds.y + pathBounds.height );
                    }
                }
            }
        } );
        frame.add ( tree );


        frame.pack ();
        frame.setLocationRelativeTo ( null );
        frame.setVisible ( true );
    }
}