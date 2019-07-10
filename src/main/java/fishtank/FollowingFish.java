package fishtank;
import java.awt.*;

/**
 * A fish.
 */
public class FollowingFish extends FishTankEntity {

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

    /** The entity that our fish is following */
    final Fish de;


    /**
     * Constructs a new hungry fish.
     */
    public FollowingFish(Fish f) {
        colour = Color.cyan.darker().darker().darker();
        appearance = "><FOLLOW>";
        goingRight = true;
        de = f;
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
     * Build and initialize this fish's forward and backward
     * appearances.
     */
    private void reverseAppearance() {
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
    }


    /**
     * Turns this fish to fc
     */
    protected void turnToFace() {
        if(de.getX() < this.getX() && goingRight) {
            goingRight = false;
            reverseAppearance();
        } else if(de.getX() > this.getX() && !goingRight) {
            goingRight = true;
            reverseAppearance();
        }
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
        turnToFace();

        // Move one spot to the right or left.
        if (r != de.getX() && goingRight && FishTank.getEntity((r + 1), c) == null) {
            r = r + 1;
        } else if(r != de.getX() && !goingRight && FishTank.getEntity((r - 1), c) == null) {
            r = r - 1;
        }

        if(Math.abs(de.getY() - c) > 2)
        {

            if(de.getY() <= c && FishTank.getEntity(r, (c - 1)) == null)
            {
                c = c - 1;
            }// will never be at the left side and keep going left.
            else if(de.getY() >= c && FishTank.getEntity(r, (c + 1)) == null )
            {
                c = c + 1;
            }
            //if the fish can't go up or down or it will delete another entity. So it need to move left or right to
            //decrease the distance(because here distY is already big than 2).
            else if(r > de.getX() && FishTank.getEntity((r - 1), c) == null)
            {
                r = r - 1;
            }
            else if(r < de.getX() && FishTank.getEntity((r + 1), c) == null)
            {
                r = r + 1;
            }
        }
        else if(Math.abs(de.getY() - c) == 1)
        {
            if(de.getY() > c && c > 5 &&FishTank.getEntity(r, (c - 1)) == null)
            {
                c = c - 1;
            }// here, because we want to increase disY, and the following fish is above the target, so we need to check
            //whether it is in the top bound or not.
            else if(de.getY() < c && c < 42 &&FishTank.getEntity(r, (c + 1)) == null)
            {
                c = c + 1;
            }
            //if it can not go up or down...

            //many cases... maybe r - getX() >=2, then we need to decrease disX, if it is 1, do nothing, if it is 0, increase it.
            else if(r > de.getX() && (r - de.getX()) >= 2 && FishTank.getEntity((r - 1), c) == null)
            {
                r = r - 1;
            }
            else if(r < de.getX() && (de.getX() - r) >= 2 && FishTank.getEntity((r + 1), c) == null)
            {
                r = r + 1;
            }
            else if(r == de.getX())
            {
                if(FishTank.getEntity((r + 1), c) == null)
                {
                    r = r + 1;
                }
                else if(FishTank.getEntity((r - 1), c) == null)
                {
                    r = r - 1;
                }
            }
        }
        else if (de.getY() == c)
        {
            if(de.getY() >= c && c>=5 &&FishTank.getEntity(r, (c - 1)) == null)
            {
                c = c - 1;
            }// here, because we want to increase disY, and the following fish is above the target, so we need to check
            //whether it is in the top bound or not.
            else if(de.getY() <= c && c<=42 &&FishTank.getEntity(r, (c + 1)) == null)
            {
                c = c + 1;
            }
        }
        }
}
