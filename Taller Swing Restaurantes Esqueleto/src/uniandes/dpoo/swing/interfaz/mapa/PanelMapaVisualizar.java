package uniandes.dpoo.swing.interfaz.mapa;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import uniandes.dpoo.swing.mundo.Restaurante;

@SuppressWarnings("serial")
public class PanelMapaVisualizar extends JPanel
{
    /**
     * La etiqueta donde se dibuja el mapa y se hacen las señales de los restaurantes
     */
    private JLabel labMapa;

    /**
     * La lista de restaurantes que se están dibujando en el mapa
     */
    private List<Restaurante> restaurantes;

    public PanelMapaVisualizar( )
    {
        this.labMapa = new JLabel( new ImageIcon( "./imagenes/mapa.png" ) );
        labMapa.setBorder( new LineBorder( Color.DARK_GRAY ) );
        add( labMapa, BorderLayout.CENTER );
    }

    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        if (restaurantes == null)
        {
            return;
        }
        
        Graphics2D graficos = (Graphics2D) g.create();
        for (Restaurante restaurante : restaurantes)
        {
            int x = labMapa.getX() + restaurante.getX();
            int y = labMapa.getY() + restaurante.getY();
            graficos.setColor(Color.RED);
            graficos.fillOval(x - 3, y - 3, 7, 7);
            graficos.setColor(Color.BLACK);
            String nombre = restaurante.getNombre();
            int anchoNombre = graficos.getFontMetrics().stringWidth(nombre);
            int bordeDerecho = labMapa.getX() + labMapa.getWidth();
            int xNombre = x + 8;
            if (xNombre + anchoNombre > bordeDerecho)
            {
                xNombre = x - anchoNombre - 8;
            }
            graficos.drawString(nombre, xNombre, y - 5);
        }

        graficos.dispose();
    }

    /**
     * Actualiza la lista de restaurantes y llama al método repaint() para que se actualice el mapa
     * @param nuevosRestaurantes
     */
    public void actualizarMapa( List<Restaurante> nuevosRestaurantes )
    {
        if( restaurantes != null )
        {
            this.restaurantes.clear( );
            this.restaurantes.addAll( nuevosRestaurantes );
        }
        else
        {
            this.restaurantes = nuevosRestaurantes;
        }
        repaint( );
    }
}
