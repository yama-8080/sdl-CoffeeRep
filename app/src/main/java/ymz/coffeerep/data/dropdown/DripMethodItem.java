package ymz.coffeerep.data.dropdown;

import android.widget.ArrayAdapter;

public class DripMethodItem {

    public enum DripMethod{
        DEFAULT(0, "-"),
        PAPER(1, "ペーパードリップ"),
        NEL(2, "ネルドリップ"),
        METAL(3, "メタルフィルター"),
        SIPHON(4, "サイフォン"),
        ESPRESSO(5, "エスプレッソ"),
        FRENCHPRESS(6, "フレンチプレス"),
        AEROPRESS(7, "エアロプレス"),
        COLDBREW(8, "水出し（コールドブリュー）"),
        PERCOLATOR(9, "パーコレーター"),
        OTHER(10, "その他"),
        ;

        private final int id;
        private final String text;

        private DripMethod(final int id, final String text) {
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
        public static DripMethodItem.DripMethod getType(final int id) {
            DripMethodItem.DripMethod[] items = DripMethodItem.DripMethod.values();
            for (DripMethodItem.DripMethod item : items) {
                if (item.getInt() == id) {
                    return item;
                }
            }
            return null;
        }

        //return the number of items
        public static int getNumber(){
            return DripMethodItem.DripMethod.values().length;
        }
    }

    //set items to dropdown list
    public static void setItemsToAdapter(ArrayAdapter<String> adapter){
        int loop = DripMethodItem.DripMethod.getNumber();
        for(int i=0; i<loop; i++){
            if(DripMethodItem.DripMethod.getType(i) != null)
                adapter.add(DripMethodItem.DripMethod.getType(i).getString());
        }
    }
}
