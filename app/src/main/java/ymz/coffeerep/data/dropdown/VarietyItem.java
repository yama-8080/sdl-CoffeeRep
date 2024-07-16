package ymz.coffeerep.data.dropdown;

import android.widget.ArrayAdapter;

public class VarietyItem {

    public enum Variety{
        DEFAULT(0, "-"),
        ROBUSTA(1, "ロブスタ種"),
        ARABICA(2, "アラビカ種"),
        LIBERICA(3, "リベリカ種"),
        OTHER(4, "その他"),
        ;

        private final int id;
        private final String text;

        private Variety(final int id, final String text) {
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
        public static Variety getType(final int id) {
            Variety[] items = Variety.values();
            for (Variety item : items) {
                if (item.getInt() == id) {
                    return item;
                }
            }
            return null;
        }

        //return the number of items
        public static int getNumber(){
            return Variety.values().length;
        }
    }

    //set items to dropdown list
    public static void setItemsToAdapter(ArrayAdapter<String> adapter){
        int loop = Variety.getNumber();
        for(int i=0; i<loop; i++){
            if(Variety.getType(i) != null)
                adapter.add(Variety.getType(i).getString());
        }
    }
}
