package fh.campus.asd.battleship.backend.models;

import java.util.ArrayList;

//In der Klasse sind alle Eigenschaften, die ein Player hat
public class Player
{
    public Field getArea() {
        return area;
    }

    Field area = new Field();
    private ArrayList<AttackPositions> attackpositions = new ArrayList<>();

    /*SaveAttack speichert alle Attacken in die Arraylist*/
    public void saveAttack(int x, int y)
    {
        this.attackpositions.add(new AttackPositions(x, y));
    }

    /*Wir verhindern doppelten Angriff. Wir schauen, mit der foreach Schleife, ob die Übergebenen x,y von attackPossible
    schon in einer der gespeicherten Stellen in unserer ArrayList attackpositions enthalten ist.*/
    public boolean attackPossible(int x, int y)
    {
        for (AttackPositions a : this.attackpositions)
        {
            if ((a.getX() == x) && (a.getY() == y))
            {
                return false;
            }
        }
        return true;
    }

    /*Reset überschreibt unsere Klassenarraylist, die wir oben erstellt haben, mit einer Leeren Arraylist --> Resetet es*/
    public void reset()
    {
        this.attackpositions = new ArrayList<>();
    }


}
