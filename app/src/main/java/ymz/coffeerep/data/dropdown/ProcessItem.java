package ymz.coffeerep.data.dropdown;

import android.widget.ArrayAdapter;

public class ProcessItem {

    public enum Process{
        DEFAULT(0, "-"),
        NATURAL(1, "ナチュラル"),
        WASHED(2, "ウォッシュド"),
        SEMIWASHED(3, "セミウォッシュド（パルプドナチュラル）"),
        HONEY(4, "ハニープロセス"),
        SUMATRA(5, "スマトラ式"),
        ANAEROBIC(6, "嫌気性発酵（アナエロビック）"),
        OTHER(7, "その他"),
        ;

        private final int id;
        private final String text;

        private Process(final int id, final String text) {
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
        public static ProcessItem.Process getType(final int id) {
            ProcessItem.Process[] items = ProcessItem.Process.values();
            for (ProcessItem.Process item : items) {
                if (item.getInt() == id) {
                    return item;
                }
            }
            return null;
        }

        //return the number of items
        public static int getNumber(){
            return ProcessItem.Process.values().length;
        }
    }

    //set items to dropdown list
    public static void setItemsToAdapter(ArrayAdapter<String> adapter){
        int loop = ProcessItem.Process.getNumber();
        for(int i=0; i<loop; i++){
            if(ProcessItem.Process.getType(i) != null)
                adapter.add(ProcessItem.Process.getType(i).getString());
        }
    }
}
