public class Road {
    String direction;
    private int id;
    private int numberOfRoads;
    int left;
    int right;
    Boolean isOccupied;

    public Road(String direction, int id, int numberOfRoads, Boolean isOccupied)
    {
        this.direction = direction;
        this.numberOfRoads = numberOfRoads;
        this.id = id;
        left = (id + numberOfRoads - 1) % numberOfRoads;
        right = (id + numberOfRoads - 1) % numberOfRoads;
        this.isOccupied = isOccupied;
    }

    public int getNumberOfRoads(){return numberOfRoads;}

    public void drive(){
        isOccupied = false;
    }

}
