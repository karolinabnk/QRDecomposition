public class ElementCounter {

    public int counter = 0;

    public ElementCounter(String string){
        this.counter = count(string);
    }

    static int count(String string) {
        int count=0;
        int i=0;
        while(i<string.length()-1) {
            if(i==0 || (i>0 && (Character.isWhitespace(string.charAt(i)) && !Character.isWhitespace(string.charAt(i+1)) )))
                count++;
            i++;
        }
        return count;
    }
}

