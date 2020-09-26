import java.util.ArrayList;

public class ClickRecorder {
    ArrayList<GridSquare> clickedGridSquares;


    public ClickRecorder(){
        this.clickedGridSquares = new ArrayList<GridSquare>();
    }

    public void recordClickedGridSquare(GridSquare newClickedGridSquare){
        this.clickedGridSquares.add(newClickedGridSquare);
    }

    public GridSquare[] getArrayOfClickedSquares(){
        return convertSquareArrayListToSquareArray(this.clickedGridSquares);
    }

    /**
     * This method takes an arrayList with Square elements and returns as array of Square elements from the
     * ArrayList in the same order as in the ArrayList.
     *
     * @param
     * @return
     */

    private GridSquare[] convertSquareArrayListToSquareArray(ArrayList<GridSquare> squareArrayList) {
        GridSquare[] squareArray = new GridSquare[squareArrayList.size()];
        for (int i = 0; i < squareArrayList.size(); i++) {
            squareArray[i] = squareArrayList.get(i);
        }
        return squareArray;
    }
}
