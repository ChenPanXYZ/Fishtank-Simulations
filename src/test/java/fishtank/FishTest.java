package fishtank;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FishTest {

    /* Note: FishTest is in the package FishTank, so it has access to package
       private attributes.

       Also note: It's *vital* that you name any other test classes
       (ClassName)Test in the same directory as this one is in.
       properly capitalized -- we will be auto grading your tests, so make sure
       to follow this naming convention!
     */
    private Fish fish;

    @Before
    public void setUp() {
        fish = new Fish();
        FishTank.clear();
    }

    @Test
    public void testFishAlwaysInBound()
    {
        boolean stayInBound = true;
        for(int i = 0; i< 500; i++)
        {
            fish.setLocation(3, 5);
            fish.update();
            if (fish.getX() < 3 || fish.getY() < 5)
            {
                stayInBound = false;
                break;
            }
        }
        assertTrue(stayInBound);
        for(int i = 0; i< 500; i++)
        {
            fish.setLocation(102, 5);
            fish.update();
            if (fish.getX() > 102 || fish.getY() < 5)
            {
                stayInBound = false;
                break;
            }
        }
        assertTrue(stayInBound);
        for(int i = 0; i< 500; i++)
        {
            fish.setLocation(102, 42);
            fish.update();
            if (fish.getX() > 102 || fish.getY() > 42)
            {
                stayInBound = false;
                break;
            }
        }
        assertTrue(stayInBound);
    }
    @Test
    public void testFishBubbles() {
        //Note: This test currently fails, but should pass once you've
      // refactored &
        //fixed the starter code.
        boolean bubbleMade = false;
        for (int i = 0; i < 200; i++) {
            fish.setLocation(5, 10);
            fish.goingRight =
                false; //notice: I can edit package private attributes!
            fish.update();
            //fish should move one tile left and eventually blow a bubble.
            FishTankEntity e = FishTank.getEntity(fish.getX(), fish.getY() - 1);
            if (e instanceof Bubble) {
                bubbleMade = true;
                break;
            }
        }
        //You could also write "assert bubbleMade", but using the JUnit version
        //makes the message much nicer if it fails.
        assertTrue(bubbleMade);
    }
    @Test
    public void testFishBubblesProp() {
        int bubble_num = 0;
        for (int i = 0; i < 1000; i++)
        {
            fish.setLocation(23, 20);
            fish.update();
            if (FishTank.getEntity(fish.getX(), fish.getY() - 1) instanceof Bubble)
            {
                bubble_num += 1;
            }
            FishTank.clear();
        }
        assertTrue(bubble_num >= 50 && bubble_num <= 150);
    }
    @Test
    public void testFishTurnAroundProp(){
        int turnAround_num = 0;
        for (int i = 0; i < 1000; i++)
        {
            fish.setLocation(23, 20);
            boolean original_direction =(fish.goingRight);
            fish.update();
            if (original_direction != fish.goingRight)
            {
                turnAround_num += 1;
            }
        }
        assertTrue(turnAround_num >= 50 && turnAround_num <= 150);
    }
    @Test
    public void testFishMoveUpDownProp(){
        int moveUp_num = 0;
        int moveDown_num = 0;
        for (int i = 0; i < 1000; i++)
        {
            fish.setLocation(23, 20);
            int original_y = fish.getY();
            fish.update();
            if ((original_y - fish.getY()) == 1)
            {
                moveUp_num += 1;
            }
            else if ((fish.getY() - original_y) == 1)
            {
                moveDown_num += 1;
            }
            FishTank.clear();

        }
        assertTrue(moveDown_num >= 50 && moveDown_num <= 150 && moveUp_num >= 50 && moveUp_num <= 150);
    }
    @Test
    public void testFishGoRight(){
        fish.setLocation(23, 20);
        fish.setGoingRight(true);
        int original_x = fish.getX();
        fish.update();
        assertEquals(1, (fish.getX() - original_x));
    }
    @Test
    public void testFishGoLeft(){
        fish.setLocation(23, 20);
        fish.setGoingRight(false);
        int original_x = fish.getX();
        fish.update();
        assertEquals(1, (original_x - fish.getX()));
    }
    @Test
    public void testCollisionWithFish()
    {
        fish.setLocation(23, 20);
        FishTank.addEntity(23, 20, fish);
        Fish leftFish = new Fish();
        leftFish.setLocation(22, 20);
        FishTank.addEntity(22, 20, leftFish);

        Fish rightFish = new Fish();
        rightFish.setLocation(24, 20);
        FishTank.addEntity(24, 20, rightFish);

        Fish upFish = new Fish();
        upFish.setLocation(23, 19);
        FishTank.addEntity(23, 19, upFish);

        Fish bottomFish = new Fish();
        bottomFish.setLocation(23, 21);
        FishTank.addEntity(23, 21, bottomFish);

        fish.update();
        assertTrue(fish.getX() == 23 && fish.getY() == 20);
    }
    @Test
    public void testCollisionWithHungryFish()
    {
        fish.setLocation(23, 20);
        FishTank.addEntity(23, 20, fish);

        HungryFish leftHungryFish = new HungryFish();
        leftHungryFish.setLocation(22, 20);
        FishTank.addEntity(22, 20, leftHungryFish);


        HungryFish rightHungryFish = new HungryFish();
        rightHungryFish.setLocation(24, 20);
        FishTank.addEntity(24, 20, rightHungryFish);

        HungryFish upHungryFish = new HungryFish();
        upHungryFish.setLocation(23, 19);
        FishTank.addEntity(23, 19, upHungryFish);

        HungryFish bottomHungryFish = new HungryFish();
        bottomHungryFish.setLocation(23, 21);
        FishTank.addEntity(23, 21, bottomHungryFish);

        fish.update();
        assertTrue(fish.getX() == 23 && fish.getY() == 20);
    }
    @Test
    public void testCollisionWithBubble()
    {
        fish.setLocation(23, 20);
        FishTank.addEntity(23, 20, fish);

        Bubble leftBubble = new Bubble();
        leftBubble.setLocation(22, 20);
        FishTank.addEntity(22, 20, leftBubble);

        Bubble rightBubble = new Bubble();
        rightBubble.setLocation(24, 20);
        FishTank.addEntity(24, 20, rightBubble);

        Bubble upBubble = new Bubble();
        upBubble.setLocation(23, 19);
        FishTank.addEntity(23, 19, upBubble);

        Bubble bottomBubble = new Bubble();
        bottomBubble.setLocation(23, 21);
        FishTank.addEntity(23, 21, bottomBubble);

        fish.update();
        assertTrue(fish.getX() == 23 && fish.getY() == 20);
    }
    @Test
    public void testCollisionWithSeaweed()
    {
        fish.setLocation(23, 20);
        FishTank.addEntity(23, 20, fish);

        Seaweed leftSeaweed = new Seaweed(4);
        leftSeaweed.setLocation(22, 20);
        FishTank.addEntity(22, 20, leftSeaweed);

        Seaweed rightSeaweed = new Seaweed(6);
        rightSeaweed.setLocation(24, 20);
        FishTank.addEntity(24, 20, rightSeaweed);

        Seaweed upSeaweed = new Seaweed(7);
        upSeaweed.setLocation(23, 19);
        FishTank.addEntity(23, 19, upSeaweed);

        Seaweed bottomSeaweed = new Seaweed(9);
        bottomSeaweed.setLocation(23, 21);
        FishTank.addEntity(23, 21, bottomSeaweed);

        fish.update();
        assertTrue(fish.getX() == 23 && fish.getY() == 20);
    }

    @Test
    public void testEatSeaweed()
    {
        int eat_correct_num = 0;
        for(int i =0; i < 1000; i++)
        {
            Seaweed seaweed = new Seaweed(9);
            seaweed.setLocation(22, 25);
            fish.setLocation(23, 20);
            fish.setGoingRight(false);
            FishTank.addEntity(23, 20, fish);
            FishTank.addEntity(22, 25, seaweed);
            fish.update();
            if (seaweed.getLength() == 5)
            {
                eat_correct_num += 1;
            }
            FishTank.clear();
        }
        assertEquals(1000, eat_correct_num);
    }
}
