package ymz.coffeerep.data.dropdown;

import android.widget.ArrayAdapter;

public class BrendItem {
    public enum Brend{
        DEFAULT(0, "-"),
        SINGLE(1, "シングル"),
        BREND(2, "ブレンド"),
        OTHER(3, "その他"),
        ;

        private final int id;
        private final String text;

        private Brend(final int id, final String text) {
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
        public static BrendItem.Brend getType(final int id) {
            BrendItem.Brend[] items = BrendItem.Brend.values();
            for (BrendItem.Brend item : items) {
                if (item.getInt() == id) {
                    return item;
                }
            }
            return null;
        }

        //return the number of items
        public static int getNumber(){
            return BrendItem.Brend.values().length;
        }
    }

    //set items to dropdown list
    public static void setItemsToAdapter(ArrayAdapter<String> adapter){
        int loop = BrendItem.Brend.getNumber();
        for(int i=0; i<loop; i++){
            if(BrendItem.Brend.getType(i) != null)
                adapter.add(BrendItem.Brend.getType(i).getString());
        }
    }
}
