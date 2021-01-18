import static org.junit.jupiter.api.Assertions.*;

import fh.campus.asd.battleship.backend.models.Field;
import fh.campus.asd.battleship.backend.models.Player;
import fh.campus.asd.battleship.backend.models.Ship;
import fh.campus.asd.battleship.backend.models.ShipPart;
import fh.campus.asd.battleship.backend.models.ImageShip;
import fh.campus.asd.battleship.forontend.enums.Direction;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.junit.jupiter.api.Test;

public class BackendTest {

    Player player1 = new Player();
    Image img;

    @Test
    void TestShipPart() {

        ShipPart shipPart = new ShipPart(4,6);

        assertEquals(4,shipPart.getX());
        assertEquals(6,shipPart.getY());

        assertFalse(shipPart.isDamaged());
        assertNotEquals(5, shipPart.getX());

        shipPart.destroy();
        assertNotEquals(false,shipPart.isDamaged());

    }


    @Test
    void TestPlayer() {


        player1.saveAttack(2,3);
        player1.saveAttack(3,5);
        player1.saveAttack(7,2);
        player1.saveAttack(7,2);

        assertFalse(player1.attackPossible(3,5));

        player1.saveAttack(1,2);
        player1.saveAttack(33,12);

        assertTrue(player1.attackPossible(4,5));

    }

    @Test
    void TestShip() {

        Direction direction1 = Direction.RIGHT;
        Ship ship1 = new Ship(3,4,10,direction1,4,6);

        assertEquals(Direction.RIGHT, ship1.getDirection() );

        assertNotEquals(4,ship1.getDivy());

        assertEquals(10, ship1.getLength());

        assertFalse(ship1.checkIfDestroyed());

        assertFalse(ship1.attack(5,6));

        assertFalse(ship1.checkIfDestroyed());

        assertTrue(ship1.attack(3,4));

        assertFalse(ship1.checkIfDestroyed());


    }

    @Test
    void TestField() {

        Field field1 = player1.getArea();

        assertFalse(field1.isFleetComplete());
        assertTrue(field1.gameOver());
    }

    @Test
    void TestImgShip() {
        ImageShip imgship = new ImageShip(1,2,3,img);
        imgship.setDiffvectorx(1);
        imgship.setDiffvectory(1);
        assertEquals(1,imgship.getDiffvectorx());
        assertEquals(1,imgship.getDiffvectory());
    }

}
