package ymz.coffeerep.data.dropdown;

import android.widget.ArrayAdapter;

public class GrindLevelItem {

    public enum GrindLevel{
        DEFAULT(0, "-"),
        FINE(1, "細挽き"),
        MED_FINE(2, "中細挽き"),
        MEDIUM(3, "中挽き"),
        MED_COARSE(4, "中粗挽き"),
        COARSE(5, "粗挽き"),
        ;

        private final int id;
        private final String text;

        private GrindLevel(final int id, final String text) {
            this.id = id;
            this.text = text;
        }

        public int getInt() {
            return this.id;
        }

        public String getString() {
            return this.text;
        }

        //search items by id
        public static GrindLevelItem.GrindLevel getType(final int id) {
            GrindLevelItem.GrindLevel[] items = GrindLevelItem.GrindLevel.values();
            for (GrindLevelItem.GrindLevel item : items) {
                if (item.getInt() == id) {
                    return item;
                }
            }
            return null;
        }

        //return the number of items
        public static int getNumber(){
            return GrindLevelItem.GrindLevel.values().length;
        }
    }

    //set items to dropdown list
    public static void setItemsToAdapter(ArrayAdapter<String> adapter){
        int loop = GrindLevelItem.GrindLevel.getNumber();
        for(int i=0; i<loop; i++){
            if(GrindLevelItem.GrindLevel.getType(i) != null)
                adapter.add(GrindLevelItem.GrindLevel.getType(i).getString());
        }
    }
}
