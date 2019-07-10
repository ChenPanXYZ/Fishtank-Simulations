package fishtank;
import java.awt.*;

/**
 * A fish.
 */
public class HungryFish extends FishTankEntity {

    /** How this fish appears on the screen. */
    public String appearance;

    /** Indicates whether this fish is moving right. */
    boolean goingRight;

    /** This fish's first coordinate. */
    int r;
    /** This fish's second coordinate. */
    int c;
    /** The colour of this fish. */
    private final Color colour;


    /**
     * Constructs a new hungry fish.
     */
    public HungryFish() {
        colour = Color.cyan.darker().darker().darker();
        appearance = "><MEHUNGRY>";
        goingRight = true;
    }


    /**
     * Set this item's location.
     * @param a the first coordinate.
     * @param b  the second coordinate.
     */
    public void setLocation(int a, int b) {
      r = a;
      c = b;
    }

    int getX() {
        return r;
    }

    int getY() {
        return c;
    }


    /**
     * Causes this fish to blow a bubble.
     */
    protected void blowBubble() {
        if (FishTank.getEntity(r, c - 1) == null && c > 5 )
        {
            Bubble b = new Bubble();
            b.setLocation(r, (c - 1));
            System.out.println(r + " " + (c - 1));
            FishTank.addEntity(r, (c - 1), b);
        }
    }



    /**
     * Build and initialize this fish's forward and backward
     * appearances.
     */
    private String reverseAppearance() {
      System.out.println("Turning around" + this.appearance);
        StringBuilder reverse = new StringBuilder();
        for (int i=appearance.length()-1; i>=0; i--) {
            switch (appearance.charAt(i)) {
            case ')': reverse.append('('); break;
            case '(': reverse.append(')'); break;
            case '>': reverse.append('<'); break;
            case '<': reverse.append('>'); break;
            case '}': reverse.append('{'); break;
            case '{': reverse.append('}'); break;
            case '[': reverse.append(']'); break;
            case ']': reverse.append('['); break;
            default: reverse.append(appearance.charAt(i)); break;
            }
        }
        System.out.println("Turned around" + this.appearance);
        appearance = reverse.toString();
        return reverse.toString();
    }


    /**
     * Turns this fish around, causing it to reverse direction.
     */
    protected void turnAround() {
        goingRight = !goingRight;
        if (goingRight) {
            appearance = reverseAppearance();
        } else {
            appearance = reverseAppearance();
        }
    }

    public void setGoingRight(boolean goingRight) {
        this.goingRight = goingRight;
    }

    /** The font used to draw instances of this class. */
    static final Font FONT = new Font("Monospaced", Font.PLAIN, 10);


    /**
     * Draws the given string in the given graphics context at
     * at the given cursor location.
     *
     * @param  g  the graphics context in which to draw the string.
     * @param  s  the string to draw.
     * @param  x  the x-coordinate of the string's cursor location.
     * @param  y  the y-coordinate of the string's cursor location.
     */
    void drawString(Graphics g, String s, int x, int y) {
        g.setColor(colour);
        g.setFont(FONT);
        FontMetrics fm = g.getFontMetrics(FONT);
        g.drawString(s, x*fm.charWidth('W'), y*fm.getAscent());
    }



    /**
     * Draws this fish tank item.
     *
     * @param  g  the graphics context in which to draw this item.
     */
    void draw(Graphics g) {
        drawString(g, appearance, r, c);
    }



    /**
     * Causes this item to take its turn in the fish-tank simulation.
     */
    public void update() {
        // Move one spot to the right or left.
        if (goingRight && r<102 && FishTank.getEntity((r + 1), c) == null)
        {
            r = r + 1;
        }
        else if (!goingRight && r > 3 && FishTank.getEntity((r - 1), c) == null)
        {
            r = r - 1;
        }

        hitSeaweed();
        double d = Math.random();
        if (d < 0.1) { turnAround(); }  //there must be a bug occur when turnAround called.
        // Figure out whether I blow a bubble.

        // Figure out whether to move up or down, or neither.
        d = Math.random();
        if (c > 5 && d < 0.1 && FishTank.getEntity(r, (c - 1)) == null) {
            c = c - 1;
        }
        else if (c < 42 && d < 0.2 && FishTank.getEntity(r, (c + 1)) == null) {
            c = c + 1;
        }
        d = Math.random();
        if (d < 0.1)
        {
            blowBubble();
        }
    }


    public void hitSeaweed()
    {
        for(int i = c;i <= 43;i++)
        {
            if(FishTank.getEntity(r, i) instanceof Seaweed)
            {
                if (i - ((Seaweed) FishTank.getEntity(r, i)).getLength() <= c)
                {
                    ((Seaweed) FishTank.getEntity(r, i)).beingEaten(i - c);
                }
            }
        }
    }
}
