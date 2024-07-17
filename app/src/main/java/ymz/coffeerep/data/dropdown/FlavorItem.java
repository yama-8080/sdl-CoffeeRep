package ymz.coffeerep.data.dropdown;

import android.widget.ArrayAdapter;

public class FlavorItem {

    public enum Flavor{
        DEFAULT(0, "-"),
        FL_ROSE(1, "フローラル - バラ"),
        FL_JASMINE(2, "フローラル - ジャスミン"),
        FR_BERRY(3, "フルーティ - ベリー"),
        FR_PRUNE(4, "フルーティ - プルーン"),
        FR_CHERRY(5, "フルーティ - チェリー"),
        FR_CITRUS(6, "フルーティ - シトラス"),
        SO_VINEGAR(7, "サワー - 酢"),
        SO_WINE(8, "サワー - ワイン"),
        GR_OLIVE(9, "グリーン - オリーブ"),
        GR_PEAS(10, "グリーン - エンドウ豆"),
        GR_HERB(11, "グリーン - ハーブ"),
        SW_HONEY(12, "スイート - はちみつ"),
        SW_VANILLA(13, "スイート - バニラ"),
        NU_ARMOND(14, "ナッツ・ココア - アーモンド"),
        NU_CHOCO(15, "ナッツ・ココア - チョコ"),
        SP_PEPPER(16, "スパイス - コショウ"),
        SP_CINNAMON(17, "スパイス - シナモン"),
        RO_CIGAR(18, "ロースト - タバコ"),
        RO_BURNT(19, "ロースト - 焦げ"),
        OT_WOOD(20, "その他 - 木材"),
        OT_PAINT(21, "その他 - 絵具"),
        OTHER(22, "その他"),
        ;

        private final int id;
        private final String text;

        private Flavor(final int id, final String text) {
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
        public static FlavorItem.Flavor getType(final int id) {
            FlavorItem.Flavor[] items = FlavorItem.Flavor.values();
            for (FlavorItem.Flavor item : items) {
                if (item.getInt() == id) {
                    return item;
                }
            }
            return null;
        }

        //return the number of items
        public static int getNumber(){
            return FlavorItem.Flavor.values().length;
        }
    }

    //set items to dropdown list
    public static void setItemsToAdapter(ArrayAdapter<String> adapter){
        int loop = FlavorItem.Flavor.getNumber();
        for(int i=0; i<loop; i++){
            if(FlavorItem.Flavor.getType(i) != null)
                adapter.add(FlavorItem.Flavor.getType(i).getString());
        }
    }
}
