public class OffByN implements CharacterComparator{
    private int N;

    @Override
    public boolean equalChars(char x, char y) {
        return Math.abs(x-y) == this.N;
    }

    public OffByN(int N){
        this.N = N;
    }
}
