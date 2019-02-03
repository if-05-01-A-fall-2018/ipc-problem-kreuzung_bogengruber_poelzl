package sample;

public class Kiwara{
    private Road[] roads;
    private int count = 0;


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
            int[] directionsCount = new int[3];
            directionsCount = countDirections(r, directionsCount);

            if(mostLeft(directionsCount)){
                caseLeftDrive(r, directionsCount);
                return true;
            }
            if(mostRight(directionsCount)){
                caseRightDrive(r, directionsCount);
                return true;
            }
            if(mostStraight(directionsCount)){
                caseStraightDrive(r, directionsCount);
                return true;
            }

            switch (specialCase(directionsCount)){
                case "SR":
                    return caseStraightRightDrive(r, directionsCount);
                case "LR":
                    return caseLeftRightDrive(r, directionsCount);
                case "LS":
                    return caseLeftStraightDrive(r, directionsCount);
            }

        }
        return false;

    }

    private Boolean caseLeftStraightDrive(Road r, int[] directionsCount) {
        Boolean check = false;
        for (int i = 0; i < roads.length; i++) {
            if(roads[i].direction.equals("S") && roads[roads[roads[i].right].right].direction.equals("S")){
                roads[i].drive();
                check = true;
            }
        }
        if(!check)r.drive();
        return true;
    }

    private Boolean caseLeftRightDrive(Road r, int[] directionsCount) {
        for (int i = 0; i < roads.length; i++) {
            if(roads[i].direction.equals("R")){
                roads[i].drive();
                roads[roads[i].right].drive();
            }
        }
        return true;
    }

    private Boolean caseStraightRightDrive(Road r, int[] directionsCount) {
        if(r.direction == roads[roads[r.right].right].direction){
            r.drive();
            roads[roads[r.right].right].drive();
            return true;
        }
        for (int i = 0; i < roads.length; i++) {
            if(roads[i].direction.equals("S") && roads[roads[i].right].direction.equals("S")){
                roads[i].drive();
                roads[roads[i].left].drive();
                roads[roads[roads[i].right].right].drive();
                return true;
            }
        }
        return false;
    }

    private String specialCase(int[] directionsCount) {
        if(directionsCount[0] == 0)return "SR";
        if(directionsCount[1] == 0)return "LR";
        return "LS";
    }


    private void caseStraightDrive(Road r, int[] directionsCount) {
        switch (directionsCount[1]){
            case 2:
                for (int i = 0; i < roads.length; i++) {
                    if(roads[i].direction.equals("S") && roads[roads[roads[i].right].right].direction.equals("S")){
                        roads[i].drive();
                    }
                    else if(roads[i].direction.equals("R")){
                        roads[i].drive();
                        roads[roads[roads[i].right].right].drive();
                    }
                }
                break;
            case 3:
                for (int i = 0; i < roads.length; i++) {
                    if(roads[i].direction.equals("S") && roads[roads[roads[i].right].right].direction.equals("S")){
                        roads[i].drive();
                    }
                }
                break;
            case 4:
                allRoadsStraigth(r);
                break;
        }
    }

    private boolean mostStraight(int[] directionCount) {
        return directionCount[1] > directionCount[0] && directionCount[1] > directionCount[2];
    }

    private boolean mostRight(int[] directionCount) {
        return directionCount[2] > directionCount[1] && directionCount[2] > directionCount[0];
    }

    private boolean mostLeft(int[] directionCount) {
        return directionCount[0] > directionCount[1] && directionCount[0] > directionCount[2];
    }

    private Boolean caseRightDrive(Road r, int[] directionCount) {
        switch (directionCount[2]){
            case 2:
                for (int i = 0; i < roads.length; i++) {
                    if (roads[i].direction.equals("R") && roads[roads[i].left].direction.equals("R")){
                        roads[i].drive();
                        roads[roads[i].left].drive();
                        if(roads[roads[i].right].direction.equals("S"))roads[roads[i].right].drive();
                        return true;
                    }
                    else if(roads[i].direction.equals("R") && roads[roads[roads[i].right].right].direction.equals("R")){
                        roads[i].drive();
                    }
                }
                break;
            case 3:
                for (int i = 0; i < roads.length; i++) {
                    if(roads[i].direction.equals("R"))roads[i].drive();
                }
                break;
            case 4:
                allRoadsRight(r);
                break;
        }
        return true;
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

    private Boolean allRoadsOccupied(Road r){
        return r.isOccupied && roads[r.right].isOccupied && roads[r.left].isOccupied && roads[roads[r.right].right].isOccupied;
    }

    private int[] countDirections(Road r, int[] directionCount){
        switch (r.direction){
            case "L":
                directionCount[0]++;
                count++;
                break;
            case "S":
                directionCount[1]++;
                count++;
                break;
            case "R":
                directionCount[2]++;
                count++;
                break;
        }
        if(count != 4) countDirections(roads[r.right], directionCount);
        return directionCount;
    }

    private Boolean caseLeftDrive(Road r, int[] directionsCount){
        if(directionsCount[2] == 0)r.drive();
        else {
            for (int i = 0; i < roads.length; i++) {
                if(roads[i].direction.equals("R")){
                    roads[i].drive();
                    roads[roads[i].right].drive();
                }
            }
        }
        return true;
    }

    public Road[] getRoads(){
        return roads;
    }

    public void setRoad(Road r){
        for (int i = 0; i < roads.length; i++) {
            if(roads[i].getId() == r.getId())roads[i] = r;
        }
    }

}
