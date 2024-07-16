package ymz.coffeerep.data.dropdown;

import android.widget.ArrayAdapter;

public class RoastLevelItem {

    public enum RoastLevel{
        LIGHT(0, "ライトロースト"),
        CINNAMON(1, "シナモンロースト"),
        MEDIUM(2, "ミディアムロースト"),
        HIGH(3, "ハイロースト"),
        CITY(4, "シティロースト"),
        FULLCITY(5, "フルシティロースト"),
        FRENCH(6, "フレンチロースト"),
        ITALIAN(7, "イタリアンロースト"),
        OTHER(8, "その他"),
        ;

        private final int id;
        private final String text;

        private RoastLevel(final int id, final String text) {
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
        public static RoastLevelItem.RoastLevel getType(final int id) {
            RoastLevelItem.RoastLevel[] items = RoastLevelItem.RoastLevel.values();
            for (RoastLevelItem.RoastLevel item : items) {
                if (item.getInt() == id) {
                    return item;
                }
            }
            return null;
        }

        //return the number of items
        public static int getNumber(){
            return RoastLevelItem.RoastLevel.values().length;
        }
    }

    //set items to dropdown list
    public static void setItemsToAdapter(ArrayAdapter<String> adapter){
        int loop = RoastLevelItem.RoastLevel.getNumber();
        for(int i=0; i<loop; i++){
            if(RoastLevelItem.RoastLevel.getType(i) != null)
                adapter.add(RoastLevelItem.RoastLevel.getType(i).getString());
        }
    }
}
