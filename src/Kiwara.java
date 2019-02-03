import com.sun.org.apache.xpath.internal.operations.Bool;

public class Kiwara{
    private Road[] roads;
    private

    public Kiwara(Road[] roads){
        this.roads = roads;
    }


    public Boolean signal(Road r) {
        /*
        if (r.direction.equals("R")) return true;         //Rechts Abbiegen
        if (r.direction.equals("S") && !roads[r.right].isOccupied) return true;       //Gerade und Rechts frei
        if(!roads[r.right].isOccupied && !roads[r.left].isOccupied && !roads[roads[r.right].right].isOccupied)return true;      //Alle Straßen Frei
        */
        if(allRoadsOccupied(r)){
            if(r.direction.equals("S")
                    && roads[r.right].direction.equals("S")
                    && roads[r.left].direction.equals("S")
                    && roads[roads[r.right].right].direction.equals("S")) return allRoadsStraigth(r);       //All streets straight



            if(r.direction.equals("L")
                    && roads[r.right].direction.equals("L")
                    && roads[r.left].direction.equals("L")
                    && roads[roads[r.right].right].direction.equals("L")) return allRoadsLeft(r);       //All streets left



            if(r.direction.equals("R")
                    && roads[r.right].direction.equals("R")
                    && roads[r.left].direction.equals("R")
                    && roads[roads[r.right].right].direction.equals("R")) return allRoadsRight(r);      //all streets right

            if(countDirectionLeft(r) == 1){

            }



        }

    }

    private Boolean allRoadsRight(Road r) {
        r.drive();
        roads[r.right].drive();
        roads[r.left].drive();
        roads[roads[r.right].right].drive();
        return true;
    }

    private Boolean allRoadsStraigth(Road r){
        r.drive();
        roads[roads[r.right].right].drive();
        return true;
    }

    private Boolean allRoadsLeft(Road r){
        r.drive();
        return true;
    }

    private Boolean allRoadsOccupied(Road r){
        return roads[r.right].isOccupied && roads[r.left].isOccupied && roads[roads[r.right].right].isOccupied;
    }

    private int countDirectionLeft(Road r){
        int leftCount = 0;
        if(r.direction.equals("L"))leftCount++;
        if(roads[r.right].direction.equals("L"))leftCount++;
        if(roads[r.left].direction.equals("L"))leftCount++;
        if(roads[roads[r.right].right].direction.equals("L"))leftCount++;
        return leftCount;
    }
    private Boolean oneRoadLeft(Road r){

    }
}
